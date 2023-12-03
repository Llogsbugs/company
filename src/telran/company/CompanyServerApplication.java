package telran.company;

import telran.company.net.CompanyProtocol;
import telran.company.service.*;
import telran.net.service.TcpServer;

public class CompanyServerApplication {
public static void main(String[] args) throws Exception {
	CompanyService company = new CompanyServiceImpl();
	company.restore("employees.data");
	TcpServer server = new TcpServer(5000, new CompanyProtocol(company));
	server.run();
}
}
