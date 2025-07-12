package br.com.smart4.gestaoagriculturaapi.report.dao;

import java.sql.Connection;

public interface ReportDAO {
	
	Connection getConnection();	
	
	void close(Connection conn);
	
}
