package telran.company.net;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;
import telran.company.net.dto.DepartmentUpdateData;
import telran.company.net.dto.SalaryUpdateData;
import telran.company.service.CompanyService;
import telran.net.dto.Request;
import telran.net.dto.Response;
import telran.net.service.ApplProtocol;
@SuppressWarnings("unused")
public class CompanyProtocol implements ApplProtocol {
	CompanyService companyService;
	public CompanyProtocol(CompanyService companyService) {
		this.companyService = companyService;
	}
	@Override
	public Response getResponse(Request request) {
		//Assumed that requestType contains a method name, for example
		//requestType "hireEmployee" implies the call of the method hireEmployee
		//using class reflection
		Serializable responseData = null;
		Response response = null;
		String requestType = request.requestType();
		try {
			Method method = getClass().getDeclaredMethod(requestType,
					Serializable.class);
			responseData = (Serializable) method.invoke(this, request.requestData());
			response = new Response(Response.OK, responseData);
		} catch (NoSuchMethodException e) {
			response = new Response(Response.WRONG_TYPE, requestType + " Usupprted request");
		} catch (Exception e) {
			response = new Response(Response.WRONG_DATA, e.getMessage());
		}
		
		return response;
	}
	
	private Serializable hireEmployee(Serializable requestData) {
		Employee empl = (Employee) requestData;
		return companyService.hireEmployee(empl);
	}
	private Serializable fireEmployee(Serializable requestData) {
		long id = (long) requestData;
		return companyService.fireEmployee(id);
	}
	private Serializable getEmployeesByDepartment(Serializable requestData) {
		String department = (String) requestData;
		return new ArrayList<>(companyService.getEmployeesByDepartment(department));
	}
	private Serializable getAllEmployees(Serializable requestData) {
		return new ArrayList<>(companyService.getAllEmployees());
	}
	private Serializable getEmployee(Serializable requestData) {
		long id = (long) requestData;
		return companyService.getEmployee(id);
	}
	private Serializable getEmployeesBySalary(Serializable requestData) {
		int[] fromTo = (int[]) requestData;
		return new ArrayList<>(companyService.getEmployeesBySalary(fromTo[0], fromTo[1]));
	}
	private Serializable getEmployeeByAge(Serializable requestData) {
		int[] fromTo = (int[]) requestData;
		return new ArrayList<>(companyService.getEmployeeByAge(fromTo[0], fromTo[1]));
	}
	private Serializable salaryDistributionByDepartments(Serializable requestData) {
		
		return new ArrayList<>(companyService.salaryDistributionByDepartments());
	}
	private Serializable getSalaryDistribution(Serializable requestData) {
		int interval = (int) requestData;
		return new ArrayList<>(companyService.getSalaryDistribution(interval));
	}
	private Serializable updateDepartment(Serializable requestData) {
		DepartmentUpdateData updateData = (DepartmentUpdateData) requestData;
		return companyService.updateDepartment(updateData.id(), updateData.department());
	}
	private Serializable updateSalary(Serializable requestData) {
		SalaryUpdateData updateData = (SalaryUpdateData) requestData;
		return companyService.updateSalary(updateData.id(), updateData.salary());
	}
	private Serializable save(Serializable requestData) {
		String fileName = (String) requestData;
		companyService.save(fileName);
		return "File saved";
	}

}
