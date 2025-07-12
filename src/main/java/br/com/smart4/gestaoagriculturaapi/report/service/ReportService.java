package br.com.smart4.gestaoagriculturaapi.report.service;

import br.com.smart4.gestaoagriculturaapi.report.dao.ReportDAO;
import br.com.smart4.gestaoagriculturaapi.report.dao.ReportDAOWithJDBC;
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
