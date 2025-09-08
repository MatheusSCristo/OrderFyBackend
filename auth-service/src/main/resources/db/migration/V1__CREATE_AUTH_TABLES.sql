CREATE TABLE restaurants (
    id CHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    cnpj CHAR(14)
);

CREATE TABLE employees (
    id INT NOT NULL PRIMARY KEY,
    cpf CHAR(11) NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(50),
    last_name VARCHAR(100),
    role VARCHAR(50) NOT NULL,
    restaurant_id CHAR(36),
    CONSTRAINT uk_cpf_per_restaurant UNIQUE (cpf, restaurant_id),
    CONSTRAINT fk_employee_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);
