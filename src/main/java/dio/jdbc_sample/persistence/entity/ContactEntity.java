package dio.jdbc_sample.persistence.entity;

import lombok.Data;

@Data
public class ContactEntity {

    private long id;

    private String description;

    private String type;

    private EmployeeEntity employee;

}
