package com.examples.empapp.service;

import com.examples.empapp.dao.EmployeeDao;
import com.examples.empapp.exception.ApplicationException;
import com.examples.empapp.model.Employee;
import com.examples.empapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements  EmployeeService {

//	@Autowired
//	EmployeeDao employeeDao;

	@Autowired
	EmployeeRepository empRepo;

	Comparator<Employee> EMPLOYEE_NAME_ASC_SORT = new Comparator<Employee>() {
		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.getName().compareTo(o2.getName());
		}
	};

	public EmployeeServiceImpl() {
//		employeeDao = new EmployeeDaoJdbcImpl();
	}

	public EmployeeServiceImpl(EmployeeDao empDao) {
//		this.employeeDao = empDao;
	}

	public boolean create(Employee employee) throws ApplicationException {
		//validate employee details
//		return employeeDao.create(employee);
		return empRepo.save(employee) != null ? true : false;
	}

	public Employee get(int id) {
//		return employeeDao.get(id);
		return empRepo.findById(id).get();
	}

	public List<Employee> getAll() {
//		return employeeDao.getAll();
		return empRepo.findAll();
	}

	public boolean update(Employee employee) {
	//	return employeeDao.update(employee);
		return empRepo.save(employee) != null ? true : false;
	}

	public boolean delete(int id) {
//		return employeeDao.delete(id);
		empRepo.deleteById(id);
		return true;
	}

	public boolean validate(Employee emp, String msg, Predicate<Employee> condition,
			Function<String, Boolean> operation) {
		if (!condition.test(emp)) {
			return operation.apply(msg);
		}
		return true;
	}

	// Get Employee count greater than given age
	public long getEmployeeCountAgeGreaterThan(Predicate<Employee> condition) {
		long count = this.getAll().stream().filter(condition).count();

		return count;
	}

	// Get list of Employee IDs whose age is greater than given age
	public List<Integer> getEmployeeIdsAgeGreaterThan(int age) {
		List<Integer> empIds = this.getAll()
				.stream()
				.filter(emp -> emp.getAge() > age)
				.map(emp -> emp.getEmpId())
				.collect(Collectors.toList());
		return empIds;
	}

	// Get Department wise Employee count
	public Map<String, Long> getEmployeeCountByDepartment() {

		return this.getAll()
				.stream()
				.map(Employee::getDepartment) // output -> Department name
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
				// Key - Department name
				// Value - Count
	}

	// Get Department wise Employee count ordered by Department name
	public Map<String, Long> getEmployeeCountByDepartmentOdered() {
		return this.getAll()
				.stream()
				.sorted(Comparator.comparing(Employee::getDepartment))
				.collect(Collectors.groupingBy(Employee::getDepartment, LinkedHashMap::new, Collectors.counting()));
	}

	// Get Department wise average Employee age ordered by Department name
	public Map<String, Double> getAvgEmployeeAgeByDept() {
		return this.getAll()
				.stream()
				.sorted(Comparator.comparing(Employee::getDepartment)).collect(Collectors
				.groupingBy(Employee::getDepartment, LinkedHashMap::new, Collectors.averagingInt(Employee::getAge)));
	}

	// Get Departments have more than given Employee count
	public List<String> getDepartmentsHaveEmployeesMoreThan(int criteria) {
		// List<String> deptList = new ArrayList<>();

		return this.getAll()
				.stream()
				.sorted(Comparator.comparing(Employee::getDepartment))
				.collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()))
				// .forEach((k,v) -> {if(v > criteria) {deptList.add(k);}});
				// return deptList;

				.entrySet().stream() // Creating one more stream to filter the output
				.filter(entry -> entry.getValue() > criteria).map(Map.Entry::getKey).collect(Collectors.toList());
	}

	// Get Employee names starting with given string
	public List<String> getEmployeeNamesStartsWith(String prefix) {
		return this.getAll()
				.stream().filter(emp -> emp.getName().startsWith(prefix)).map(emp -> emp.getName())
				.collect(Collectors.toList());
	}

	public void bulkImport() throws ApplicationException {
		int counter = 0;
		try (Scanner in = new Scanner(new FileReader(".\\input\\employee-input.txt"))) {
			while (in.hasNextLine()) {
				String emp = in.nextLine();
				Employee employee = new Employee();
				StringTokenizer tokenizer = new StringTokenizer(emp, ",");

				// Emp ID - Auto Generated
//				employee.setEmpId(Integer.parseInt(tokenizer.nextToken()));
				// Name
				employee.setName(tokenizer.nextToken());
				// Age
				employee.setAge(Integer.parseInt(tokenizer.nextToken()));
				// Designation
				employee.setDesignation(tokenizer.nextToken());
				// Department
				employee.setDepartment(tokenizer.nextToken());
				// Country
				employee.setCountry(tokenizer.nextToken());

				this.create(employee);
				counter++;
			}
			System.out.format("%d Employees are imported successfully.", counter);
		} catch (IOException e) {
			System.out.println("Error occured while importing employee data. " + e.getMessage());
		}
	}

	public void bulkExport() {
		try (FileWriter out = new FileWriter(".\\output\\employee-output.txt")) {
			this
					.getAll()
					.stream()
					.map(emp -> emp.getEmpId() + "," + emp.getName() + "," + emp.getAge() + ","
							+ emp.getDesignation() + "," + emp.getDepartment() + "," + emp.getCountry() + "\n")
					.forEach(rec -> {
						try {
							out.write(rec);
						} catch (IOException e) {
							System.out
									.println("Error occured while writing employee data into file. " + e.getMessage());
							e.printStackTrace();
						}
					});
			System.out.format("%d Employees are exported successfully.", this.getAll().size());
		} catch (IOException e) {
			System.out.println("Error occured while exporting employee data. " + e.getMessage());
		}		
	}
}
