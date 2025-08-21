DROP DATABASE IF EXISTS orderfy;
CREATE DATABASE orderfy CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE orderfy;

CREATE TABLE restaurants (
                             id BIGINT NOT NULL AUTO_INCREMENT,
                             name VARCHAR(255) NOT NULL,
                             cnpj VARCHAR(14) NOT NULL UNIQUE,
                             created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                             updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                             PRIMARY KEY (id)
);

CREATE TABLE employees (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           restaurant_id BIGINT NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           cpf VARCHAR(11) NOT NULL UNIQUE,
                           password VARCHAR(255) NOT NULL,
                           role VARCHAR(50) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (id),
                           FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE customers (
                           id BIGINT NOT NULL AUTO_INCREMENT,
                           name VARCHAR(255) NULL,
                           cpf VARCHAR(11) NOT NULL UNIQUE,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           PRIMARY KEY (id)
);

CREATE TABLE tables (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        restaurant_id BIGINT NOT NULL,
                        uuid VARCHAR(36) NOT NULL UNIQUE,
                        number INT NOT NULL,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        PRIMARY KEY (id),
                        UNIQUE (restaurant_id, number),
                        FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE categories (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            restaurant_id BIGINT NOT NULL,
                            name VARCHAR(100) NOT NULL,
                            description VARCHAR(255) NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (id),
                            FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)
);

CREATE TABLE products (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          name VARCHAR(255) NOT NULL,
                          description TEXT NULL,
                          restaurant_id BIGINT NOT NULL,
                          price DECIMAL(10, 2) NOT NULL,
                          image_url VARCHAR(2048) NULL,
                          available BOOLEAN NOT NULL DEFAULT TRUE,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          PRIMARY KEY (id),
                          FOREIGN KEY (restaurant_id) REFERENCES restaurants(id)

);


CREATE TABLE products_categories (
                                     product_id BIGINT NOT NULL,
                                     category_id BIGINT NOT NULL,
                                     PRIMARY KEY (product_id, category_id),
                                     FOREIGN KEY (product_id) REFERENCES products(id),
                                     FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE tabs (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      restaurant_id BIGINT NOT NULL,
                      table_id BIGINT NOT NULL,
                      customer_id BIGINT NOT NULL,
                      waiter_id BIGINT NULL,
                      status VARCHAR(50) NOT NULL DEFAULT 'OPEN',
                      total_amount DECIMAL(10, 2) NOT NULL DEFAULT 0.00,
                      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                      closed_at TIMESTAMP NULL,
                      PRIMARY KEY (id),
                      FOREIGN KEY (restaurant_id) REFERENCES restaurants(id),
                      FOREIGN KEY (table_id) REFERENCES tables(id),
                      FOREIGN KEY (customer_id) REFERENCES customers(id),
                      FOREIGN KEY (waiter_id) REFERENCES employees(id)
);

CREATE TABLE tabs_items (
                            id BIGINT NOT NULL AUTO_INCREMENT,
                            tab_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity INT NOT NULL,
                            unit_price DECIMAL(10, 2) NOT NULL,
                            notes TEXT NULL,
                            created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                            PRIMARY KEY (id),
                            FOREIGN KEY (tab_id) REFERENCES tabs(id),
                            FOREIGN KEY (product_id) REFERENCES products(id)
);