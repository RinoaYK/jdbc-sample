package dio.jdbc_sample;

import dio.jdbc_sample.persistence.ConnectionUtil;
import dio.jdbc_sample.persistence.EmployeeDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.sql.SQLException;

@SpringBootApplication
public class Main {

	private final static EmployeeDAO employeeDAO = new EmployeeDAO();

	public static void main(String[] args) {

/*
		try(var connection = ConnectionUtil.getConnection()){
			System.out.println("Conectou!");
		}catch (SQLException ex){
			ex.printStackTrace();
		}
 */
	}

}
