package telran.company;

import telran.company.controller.CompanyItems;
import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;
import telran.net.client.TcpClient;
import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class CompanyClientApplication {

	private static final String FILE_NAME = "employees.data";
	private static final String HOST = "10.73.140.251";
	private static final int PORT = 5000;
	
	public static void main(String[] args) throws Exception {
		TcpClient tcpClient = new TcpClient(HOST, PORT);
		CompanyService company = new CompanyServiceNetworkProxy(tcpClient);
		
		Item[] items = CompanyItems.getItems(company, FILE_NAME);
		InputOutput io = new StandardInputOutput();
		Menu menu = new Menu(items, "Company Application");
		menu.perform(io);
		tcpClient.close();
	}

}

