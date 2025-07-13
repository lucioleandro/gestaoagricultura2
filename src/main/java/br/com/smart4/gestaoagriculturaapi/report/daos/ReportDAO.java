package br.com.smart4.gestaoagriculturaapi.report.daos;

import java.sql.Connection;

public interface ReportDAO {
	
	Connection getConnection();	
	
	void close(Connection conn);
	
}
