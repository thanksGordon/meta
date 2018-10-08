DROP DATABASE metablurProject;
CREATE DATABASE metablurProject;
USE metablurProject;
CREATE TABLE Employee(
	EmployeeID mediumint PRIMARY KEY AUTO_INCREMENT,
    fname VARCHAR (30),
    lname VARCHAR(30),
    Address VARCHAR(40),
    NIN VARCHAR(9),
    IBAN VARCHAR(34),
    StartingSalary float
  /*  DepartmentID int references Department(DptID) */
);

DROP TABLE Department;
CREATE Table Department (
DptID int not null primary key AUTO_INCREMENT,
Dname varchar(45)
/*headID mediumint references Employee(EmployeeID)*/
);
ALTER TABLE Employee ADD DepartmentID int references Department(DptID);
alter table EmployeeSales(
add column DepartmentID int references Department(DptID)); 
DROP TABLE EmpSales;
Create table EmpSales (
Month datetime not null default NOW() ,
EmpID mediumint not null references Employee(EmployeeID),
TotalSales float DEFAULT 0,
primary key (Month, EmpID)
);
DROP TABLE Project;
CREATE TABLE Project(
ProjID Smallint primary key not null AUTO_INCREMENT,
ProjectName varchar(45) not null,
LeaderID mediumint references Employee(EmployeeID),
Budget float ,
Deadline datetime default Now()
);
DROP TABLE Teams;
CREATE TABLE Teams(
ProjID int not null references Project(ProjID)
EmpID mediumint not null references Employee(EmployeeID)
primary key(ProjID, EmpID)
);
/* inserting first user */
insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary,DepartmentID) values ('Gordon', 'Kelly', 'Isnt Here', 'PG231569D', 'GB12 ABCD 123456 12345678', 2000.00,1);
insert into Department(DptID, Dname) values (1,'Java division',1);
Insert into Project(ProjID,ProjectName,LeaderID,Budget) values (1,'Java based database', 1,900000.50);
insert into Teams(EmpID, ProjID) values (1,1);
/* Second user */
insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary,DepartmentID) values ('Joesph', 'Blogs', 'Isnt Here', 'PG241579D', 'GB32 AGFD 123456 21345687', 12800.01,1);
insert into Teams(EmpID, ProjID) values (2,1);
/* 3rd user */
insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary,DepartmentID) values ('John', 'Doe', 'Isnt Here', 'PG231569D', 'BT12 QWER 123456 13426578', 22000.40,2);
insert into Department(DptID, Dname) values (2,'Marketing');
Insert into Project(ProjID,ProjectName,LeaderID,Budget) values (2,'Selling database', 3,90000.50);
insert into Teams(EmpID, ProjID) values (3,2);
/* 4th user */
insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary,DepartmentID) values ('Connor', 'Fungus', 'Isnt Here', 'PE886669D', 'NZ12 QWER 123456 66426578', 12666.40,3);

insert into EmpSales (EmpID) values (4);
/* 5th user*/
insert into Employee(fname, lname, Address, NIN, IBAN, StartingSalary,DepartmentID) values ('Mike', 'Shoemaker', 'Isnt Here', 'PE819999D', 'NP12 DEAD 321654 66426578', 12999.40,3);
insert into Department(DptID, Dname) values (3,'Sales');
insert into EmpSales (EmpID,TotalSales) values (5,800);