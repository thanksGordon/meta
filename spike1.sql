CREATE DATABASE metablurProject;

CREATE TABLE Employee
(
	EmployeeID mediumint PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR (30),
    lname VARCHAR(30),
    Address VARCHAR(40),
    NIN VARCHAR(9),
    IBAN VARCHAR(34),
    StartingSalary float
);

insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary) values ('Gordon', 'Kelly', 'Isnt Here', 'PG231569D', 'GB12 ABCD 123456 12345678', 2000.00);

