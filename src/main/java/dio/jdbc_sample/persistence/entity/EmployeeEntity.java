package dio.jdbc_sample.persistence.entity;

import dio.jdbc_sample.persistence.EmployeeDAO;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.function.UnaryOperator;

@Data
public class EmployeeEntity {

    private Long id;

    private String name;

    private BigDecimal salary;

    private OffsetDateTime birthday;

    private Date createdAt;

    private List<ContactEntity> contacts;

    private List<ModuleEntity> modules;

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + formatBirthday() +
                ", createdAt=" + (createdAt != null ? dateFormat.format(createdAt) : "N/A") +
                '}';
    }

    private String formatBirthday() {
        return birthday.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public void insertTable(EmployeeDAO employeeDAO, String name, double salary, UnaryOperator<OffsetDateTime> birthdayModifier) {
        var insert = new EmployeeEntity();
        insert.setName(name);
        insert.setSalary(BigDecimal.valueOf(salary));
        insert.setBirthday(birthdayModifier.apply(OffsetDateTime.now()));

        employeeDAO.insert(insert);

        EmployeeEntity savedEmployee = employeeDAO.findById(insert.getId());
        System.out.println(savedEmployee);
    }
}
