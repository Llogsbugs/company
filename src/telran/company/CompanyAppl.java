package telran.company;

import telran.company.controller.CompanyItems;
import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;
import telran.view.*;
public class CompanyAppl {

	private static final String FILE_NAME = "employees.data";

	public static void main(String[] args) {
		CompanyService company = new CompanyServiceImpl();
		company.restore(FILE_NAME);
		Item[] items = CompanyItems.getItems(company, FILE_NAME);
		InputOutput io = new StandardInputOutput();
		Menu menu = new Menu(items, "Company Application");
		menu.perform(io);

	}

}
