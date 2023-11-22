package telran.company.controller;

import java.time.LocalDate;
import java.util.*;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.service.CompanyService;
import telran.view.InputOutput;
import telran.view.Item;
import static telran.view.Item.*;

public class CompanyItems {
    private static final int MIN_SALARY = 5500;
	private static final int MAX_SALARY = 50000;
	private static final int MIN_AGE = 20;
	private static final int MAX_AGE = 70;
	static CompanyService company;
    static String fileName;
    static Set<String> departments = new HashSet<>(Arrays.asList("Development", "QA",
    		"Audit", "Accounting", "Management", "Sales"));
	public static Item[] getItems(CompanyService companyService, String file) {
		company = companyService;
		fileName = file;
		Item[] items = {
				of("Hiring Employee", io -> hireEmployee(io)),
				of("Firing Employee", io -> fireEmployee(io)),
				of("Display data about Employee", io -> getEmployee(io)),
				of("Display data about Employees in Department",
						io -> getEmployeesByDepartment(io)),
				of("Display data about all Employees", io -> getAllEmployees(io)),
				of("Display data about Employees with salary values",
						io -> getEmployeesBySalary(io)),
				of("Display data about Employees having given age values",
						io -> getEmployeesByAge(io)),
				of("Display salary distribution by departments",
						io -> salaryDistributionByDepartments(io)),
				of("Display salary distibution by given interval",
						io -> salaryDistributionByInterval(io)),
				of("Updating employee's department", io -> updateDepartment(io)),
				of("Updating employee's salary", io -> updateSalary(io)),
				of("Exit and Save", io -> saveEmployees(io), true)
				
				};
		
		return null;
	}
	private static void saveEmployees(InputOutput io) {
		
		company.save(fileName);
	}
	private static void updateSalary(InputOutput io) {
		long id = io.readLong("Enter id", "Wrong id");
		int salary = io.readInt("Enter salary value", "Wrong salary", MIN_SALARY, MAX_SALARY);
		company.updateSalary(id, salary);
		io.writeLine("Salary updated");
	}
	private static void updateDepartment(InputOutput io) {
		long id = io.readLong("Enter id", "Wrong id");
		String department = io.readOptions("Enter Department " + departments,
				"Wrong Department", departments);
		company.updateDepartment(id, department);
		io.writeLine("Department iupdated");
	}
	private static void salaryDistributionByInterval(InputOutput io) {
		int interval = io.readInt("Enter interval value", "Wrong interval", 500, 5000);
		
		displayList(io, company.getSalaryDistribution(interval));
	}
	private static void salaryDistributionByDepartments(InputOutput io) {
		
		displayList(io, company.salaryDistributionByDepartments());
	}
	private static void getEmployeesByAge(InputOutput io) {
		int fromAge = io.readInt("Enter age from", "Wrong age value", MIN_AGE, MAX_AGE - 1);
		int toAge = io.readInt("Enter age to", "Wrong age value", fromAge + 1, MAX_AGE);
		displayList(io, company.getEmployeeByAge(fromAge, toAge));
		
	}
	private static void getEmployeesBySalary(InputOutput io) {
		int salaryFrom = io.readInt("Enter salary from", "Wrong salary value", MIN_SALARY,
				MAX_SALARY - 1);
		int salaryTo = io.readInt("Enter salary to", "Wrong salary value",
				salaryFrom + 1, MAX_SALARY);
		displayList(io, company.getEmployeesBySalary(salaryFrom, salaryTo));
	}
	private static void getAllEmployees(InputOutput io) {
		displayList(io, company.getAllEmployees());
	}
	private static void getEmployeesByDepartment(InputOutput io) {
		String department = io.readOptions("Enter department", "Wrong department", departments);
		displayList(io, company.getEmployeesByDepartment(department));
	}
	private static void getEmployee(InputOutput io) {
		long id = io.readLong("Enter id", "Wrong id");
		Employee empl = company.getEmployee(id);
		String res = empl == null ? "Employee not found" : empl.toString();
		io.writeLine(res);
	}
	private static void fireEmployee(InputOutput io) {
		long id = io.readLong("Enter id", "Wrong id");
		company.fireEmployee(id);
		io.writeLine("Employee has been fired succesfully");
	}
	private static void hireEmployee(InputOutput io) {
		long id = io.readLong("Enter id", "Wrong id");
		if(company.getEmployee(id) != null) {
			throw new RuntimeException("Employee with entered id already exists");
		}
		String name = io.readPredicate("Enter name", "Wrong name", str -> checkName(str));
		
		int salary = io.readInt("Enter salary value", "Wrong salary", MIN_SALARY, MAX_SALARY);
		String department= io.readOptions("Enter department", "Wrong department", departments);
		LocalDate birthDate = io.readDate("Enter birthdate", "Wrong birthdate value",
				getDate(MAX_AGE), getDate(MIN_AGE));
		Employee empl = new Employee(id, name, salary, department, birthDate);
		company.hireEmployee(empl );
	}
	private static LocalDate getDate(int age) {
		
		return LocalDate.now().minusYears(age);
	}
	static private <T> void displayList(InputOutput io, List<T> list) {
		list.forEach(io::writeLine);
	}

}
