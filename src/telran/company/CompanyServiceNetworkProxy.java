package telran.company;

import java.util.List;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;
import telran.company.net.dto.DepartmentUpdateData;
import telran.company.net.dto.SalaryUpdateData;
import telran.company.service.CompanyService;
import telran.net.client.TcpClient;

public class CompanyServiceNetworkProxy implements CompanyService {
   TcpClient tcpClient;
   
	public CompanyServiceNetworkProxy(TcpClient tcpClient) {
	this.tcpClient = tcpClient;
}

	@Override
	public Employee hireEmployee(Employee empl) {
		
		return tcpClient.send("hireEmployee", empl);
	}

	@Override
	public Employee fireEmployee(long id) {
		
		return tcpClient.send("fireEmployee", id);
	}

	@Override
	public Employee getEmployee(long id) {
		
		return tcpClient.send("getEmployee", id);
	}

	@Override
	public List<Employee> getEmployeesByDepartment(String department) {
		
		return tcpClient.send("getEmployeesByDepartment", department);
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		return tcpClient.send("getAllEmployees", null);
	}

	@Override
	public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
		
		return tcpClient.send("getEmployeesBySalary",
				new int[] {salaryFrom, salaryTo});
	}

	@Override
	public List<Employee> getEmployeeByAge(int ageFrom, int ageTo) {
		
		return tcpClient.send("getEmployeeByAge",
				new int[] {ageFrom, ageTo});
	}

	@Override
	public List<DepartmentAvgSalary> salaryDistributionByDepartments() {
		
		return tcpClient.send("salaryDistributionByDepartments", null);
	}

	@Override
	public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) {
		
		return tcpClient.send("getSalaryDistribution", interval);
	}

	@Override
	public Employee updateDepartment(long id, String newDepartment) {
		DepartmentUpdateData updateData = new DepartmentUpdateData(id, newDepartment);
		return tcpClient.send("updateDepartment", updateData);
	}

	@Override
	public Employee updateSalary(long id, int newSalary) {
		SalaryUpdateData updateData = new SalaryUpdateData(id, newSalary);
		return tcpClient.send("updateSalary", updateData);
	}

	@Override
	public void save(String filePath) {
		tcpClient.send("save", filePath);

	}

	@Override
	public void restore(String filePath) {
		// TODO Auto-generated method stub

	}

}
