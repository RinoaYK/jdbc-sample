package dio.jdbc_sample;

import dio.jdbc_sample.persistence.EmployeeDAO;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Application {

	private final static EmployeeDAO employeeDAO = new EmployeeDAO();

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.run(args);
	}
}
