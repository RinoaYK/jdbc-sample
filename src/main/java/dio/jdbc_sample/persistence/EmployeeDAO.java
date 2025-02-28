package dio.jdbc_sample.persistence;

import dio.jdbc_sample.persistence.entity.EmployeeEntity;
import com.mysql.cj.jdbc.StatementImpl;

import java.sql.SQLException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.ZoneOffset.UTC;

public class EmployeeDAO {

    public void insert(final EmployeeEntity entity){
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            var sql = "INSERT INTO employees (name, salary, birthday) values ('" +
                    entity.getName() + "', " +
                    entity.getSalary().toString() + ", " +
                    "'" + formatOffsetDateTime(entity.getBirthday()) + "' )";
            statement.executeUpdate(sql);
            if (statement instanceof StatementImpl impl)
                entity.setId(impl.getLastInsertID());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public List<EmployeeEntity> findAll() {
        List<EmployeeEntity> entities = new ArrayList<>();
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            var resultSet = statement.executeQuery("SELECT * FROM employees ORDER BY id");
            while (resultSet.next()) {
                var entity = new EmployeeEntity();
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));

                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                entity.setCreatedAt(resultSet.getTimestamp("created_at"));

                entities.add(entity);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return entities;
    }

    public EmployeeEntity findById(final long id){
        var entity = new EmployeeEntity();
        try(
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ){
            statement.executeQuery("SELECT * FROM employees WHERE id = " + id);
            var resultSet = statement.getResultSet();
            if (resultSet.next()){
                entity.setId(resultSet.getLong("id"));
                entity.setName(resultSet.getString("name"));
                entity.setSalary(resultSet.getBigDecimal("salary"));
                var birthdayInstant = resultSet.getTimestamp("birthday").toInstant();
                entity.setBirthday(OffsetDateTime.ofInstant(birthdayInstant, UTC));
                entity.setCreatedAt(resultSet.getTimestamp("created_at"));
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return entity;
    }

    public void update(final EmployeeEntity entity) {
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            StringBuilder sql = new StringBuilder("UPDATE employees SET ");

            if (entity.getName() != null) {
                sql.append("name = '").append(entity.getName()).append("', ");
            }
            if (entity.getSalary() != null) {
                sql.append("salary = ").append(entity.getSalary().toString()).append(", ");
            }
            if (entity.getBirthday() != null) {
                sql.append("birthday = '").append(formatOffsetDateTime(entity.getBirthday())).append("', ");
            }

            if (sql.toString().endsWith(", ")) {
                sql.setLength(sql.length() - 2);
            }

            sql.append(" WHERE id = ").append(entity.getId());

            int rowsAffected = statement.executeUpdate(sql.toString());
            System.out.printf("Foram afetados %d registros na base de dados.%n", rowsAffected);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public void delete(final long id) {
        try (
                var connection = ConnectionUtil.getConnection();
                var statement = connection.createStatement()
        ) {
            var checkSql = "SELECT COUNT(*) FROM employees WHERE id = " + id;
            var resultSet = statement.executeQuery(checkSql);
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count == 0) {
                throw new SQLException("O ID " + id + " não foi encontrado na base de dados.");
            }

           var sql = "DELETE FROM employees WHERE id = " + id;
            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Registro com ID " + id + " removido com sucesso!");
            }
        } catch (SQLException ex) {
            System.err.println("Erro ao excluir o registro: " + ex.getMessage());
        }
    }


    private String formatOffsetDateTime(final OffsetDateTime dateTime){
        var utcDatetime = dateTime.withOffsetSameInstant(UTC);
        return utcDatetime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
