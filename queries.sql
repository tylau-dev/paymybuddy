CREATE DATABASE paymybuddy;

USE paymybuddy;


CREATE TABLE IF NOT EXISTS role (
	role_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	name varchar(25) NOT NULL UNIQUE
);

INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('CREATOR');
INSERT INTO role (name) VALUES ('EDITOR');
INSERT INTO role (name) VALUES ('ADMIN');

CREATE TABLE IF NOT EXISTS user (
	user_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	email VARCHAR(255) NOT NULL UNIQUE,
	password VARCHAR(255) NOT NULL,
	firstname VARCHAR(255),
	lastname VARCHAR(255)
);

INSERT INTO user (email, password, firstname, lastname) VALUES 
('john@gmail.com','$2a$12$U29ONFtPmYKC7kgwDBataOCGKSSxkUw9nRxz0ADRMX2ikyN7NjU7K', 'John', 'Doe'),
('pierre@gmail.com', '$2a$12$U29ONFtPmYKC7kgwDBataOCGKSSxkUw9nRxz0ADRMX2ikyN7NjU7K', 'Pierre', 'Dupont');


CREATE TABLE IF NOT EXISTS user_role (
	user_id INTEGER NOT NULL,
	role_id INTEGER NOT NULL,
	FOREIGN KEY (role_id) REFERENCES role (role_id),
	FOREIGN KEY (user_id) REFERENCES user (user_id)
);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `user_role` (`user_id`, `role_id`) VALUES (2, 1);

CREATE TABLE IF NOT EXISTS account (
	account_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	user_id INTEGER NOT NULL,
	account_name VARCHAR(255) NOT NULL,
	balance float,
	FOREIGN KEY (user_id) REFERENCES user(user_id)
);

INSERT INTO account (user_id, account_name,balance) VALUES 
(1, 'Doe John', 0),
(2, 'Dupont Pierre', 0);


CREATE TABLE IF NOT EXISTS contact (
	contact_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	sender_account_id int NOT NULL,
    receiver_account_id int NOT NULL,
	FOREIGN KEY (sender_account_id) REFERENCES account (account_id),
	FOREIGN KEY (receiver_account_id) REFERENCES account (account_id)	
);

INSERT INTO contact (sender_account_id, receiver_account_id) VALUES 
(1, 2),
(2, 1);

CREATE TABLE IF NOT EXISTS transaction (
	transaction_id INTEGER NOT NULL AUTO_INCREMENT PRIMARY KEY,
	contact_id int NOT NULL,
    description VARCHAR(255),
    transferred_amount float,
    paid_amount float,
    transaction_date DATE, 
	FOREIGN KEY (contact_id) REFERENCES contact (contact_id)	
);

INSERT INTO transaction (contact_id, description, transferred_amount, paid_amount, transaction_date) VALUES
(1, 'Test transfer', 10, 10.5, '2022-08-26');
