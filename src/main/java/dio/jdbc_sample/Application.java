package dio.jdbc_sample;

import dio.jdbc_sample.persistence.EmployeeDAO;
import dio.jdbc_sample.persistence.entity.EmployeeEntity;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Date;

@SpringBootApplication
public class Application {

	private final static EmployeeDAO employeeDAO = new EmployeeDAO();
	private final static EmployeeEntity employeeEntity = new EmployeeEntity();

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.run(args);

//		employeeEntity.insertTable(employeeDAO, "Ana", 1500.0, birthday -> birthday.minusYears(8).minusMonths(1));

//		employeeDAO.findAll().forEach(System.out::println);

//		System.out.println(employeeDAO.findById(1));

/*		var update = new EmployeeEntity();
		update.setId(6L);
		update.setName("Kyo");
		update.setSalary(new BigDecimal("1000"));
		update.setBirthday(OffsetDateTime.now().minusYears(1).plusMonths(4));
		employeeDAO.update(update);*/

		//employeeDAO.delete(4);
	}
}
