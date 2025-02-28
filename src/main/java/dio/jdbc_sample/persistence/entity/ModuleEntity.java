package dio.jdbc_sample.persistence.entity;

import lombok.Data;

import java.util.List;

@Data
public class ModuleEntity {

    private long id;

    private String name;

    private List<EmployeeEntity> employees;

}
