package br.com.smart4.gestaoagriculturaapi.report.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ReportDAOWithJDBC implements ReportDAO {

	private Properties props; 

	public Connection getConnection(){
		props  = new Properties();
		InputStream in = this.getClass().getResourceAsStream("/application.yml");

		try{  
			props.load(in);  
			in.close();  
		}  
		catch(IOException e){
			e.printStackTrace();
		}     

		try {
			
			Class.forName((String)props.getProperty("spring.datasource.driver-class-name"));
			
			return DriverManager.getConnection((String)props.getProperty("spring.datasource.url"), 
					(String)props.getProperty("spring.datasource.username"), (String)props.getProperty("spring.datasource.password"));
			
		} catch (Exception e) {
			
			System.out.println("erro na conexão do relatório"+e.getMessage( ));
		}
		return null;
	}

	public void close(Connection conn) {
		try {
			System.out.println(conn);
			System.out.println(conn.isClosed());
			if (conn != null)conn.close( );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		/*
		 try {
	            if (conn != null)conn.close( );
	        } catch (Exception e) {
	            System.out.println(e.getMessage( ));
	        }
		 */
	}

}
