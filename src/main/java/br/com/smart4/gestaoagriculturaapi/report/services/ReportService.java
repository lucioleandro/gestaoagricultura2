package br.com.smart4.gestaoagriculturaapi.report.services;

import br.com.smart4.gestaoagriculturaapi.report.daos.ReportDAO;
import br.com.smart4.gestaoagriculturaapi.report.daos.ReportDAOWithJDBC;
import org.springframework.stereotype.Service;

import java.sql.Connection;


@Service
public class ReportService {

	private ReportDAO reportDAO;
	
	public ReportService() {
		this.reportDAO = new ReportDAOWithJDBC();
	}
	
	public Connection getConnection() {
		return reportDAO.getConnection();
	}

	public void close(Connection conn) {
		reportDAO.close(conn);
	}
}
