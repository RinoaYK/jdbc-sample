package dio.jdbc_sample.persistence.entity;

import dio.jdbc_sample.persistence.EmployeeDAO;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.TimeZone;
import java.util.function.UnaryOperator;

@Entity
@Data
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    @Column(name = "birthday")
    private OffsetDateTime birthday;

    @Column(name = "created_at")
    private Date createdAt;

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
