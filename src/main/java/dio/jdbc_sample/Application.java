package dio.jdbc_sample;

import dio.jdbc_sample.persistence.ContactDAO;
import dio.jdbc_sample.persistence.EmployeeAuditDAO;
import dio.jdbc_sample.persistence.EmployeeParamDAO;
import dio.jdbc_sample.persistence.ModuleDAO;
import dio.jdbc_sample.persistence.entity.ContactEntity;
import dio.jdbc_sample.persistence.entity.EmployeeEntity;
import dio.jdbc_sample.persistence.entity.ModuleEntity;
import net.datafaker.Faker;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Stream;

@SpringBootApplication
public class Application {

	private final static EmployeeParamDAO employeeDAO = new EmployeeParamDAO();
	private final static EmployeeEntity employeeEntity = new EmployeeEntity();
	private final static EmployeeAuditDAO employeeAuditDAO = new EmployeeAuditDAO();
	private final static Faker faker = new Faker(Locale.of("pt", "BR"));
	private final static ContactDAO contactDAO = new ContactDAO();
	private final static ModuleDAO moduleDAO = new ModuleDAO();

	public static void main(String[] args) {
		new SpringApplicationBuilder(Application.class)
				.run(args);



    	//employeeEntity.insertTable(employeeDAO, "Yoshi", 5000.0, birthday -> birthday.minusYears(10).plusMonths(5));

//		employeeDAO.findAll().forEach(System.out::println);

//		System.out.println(employeeDAO.findById(1));

		//employeeDAO.delete(16);

		//employeeAuditDAO.findAll().forEach(System.out::println);

/*		var entities = Stream.generate(() -> {
			var employee = new EmployeeEntity();
			employee.setName(faker.name().fullName());
			employee.setSalary(new BigDecimal(faker.number().digits(4)));
			employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, ZoneOffset.UTC));
			return employee;
		}).limit(10).toList();

		employeeDAO.insertBatch(entities);*/

/*		x2
		var employee = new EmployeeEntity();
		var contact = new ContactEntity();
		contact.setDescription("miguel@miguel.com");
		contact.setType("e-mail");
		contact.setEmployee(employee);
		contactDAO.insert(contact);*/

		//System.out.println(employeeDAO.findById(1));
		//employeeDAO.findAll().forEach(System.out::println);

		 /*var entities = Stream.generate(() -> {
            var employee = new EmployeeEntity();
            employee.setName(faker.name().fullName());
            employee.setSalary(new BigDecimal(faker.number().digits(4)));
            employee.setBirthday(OffsetDateTime.of(LocalDate.now().minusYears(faker.number().numberBetween(40, 20)), LocalTime.MIN, ZoneOffset.UTC));
            employee.setModules(new ArrayList<>());
            var moduleAmount = faker.number().numberBetween(1, 4);
            for (int i = 0; i < moduleAmount; i++) {
                var module = new ModuleEntity();
                module.setId(i +1);
                employee.getModules().add(module);
            }
            return employee;
        }).limit(3).toList();
        entities.forEach(employeeDAO::insert);*/

		//moduleDAO.findAll().forEach(System.out::println);
	}
}
