DELIMITER $$
CREATE PROCEDURE prc_insert_employee(
        OUT p_id BIGINT,
        IN p_name VARCHAR(150),
        IN p_salary DECIMAL(10,2),
        IN p_birthday TIMESTAMP,
        IN p_created_at TIMESTAMP
)
BEGIN
    INSERT INTO employees (name, salary, birthday, created_at) VALUES (p_name , p_salary , p_birthday, p_created_at);
    SET p_id = LAST_INSERT_ID();
END $$