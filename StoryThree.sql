
Delimiter // 
/* this procedure assumes the reocrd is being created an update procedure is seperate*/
CREATE procedure NewSalesEmp(IN Fnm Varchar(30),Lnm Varchar(30),NatIN Char(9) ,IB varchar(34) ,Sales float,Commission float)
begin
/* inserting intial values */
Insert into Employee(fname,lname,NIN,IBAN,DepartmentID) values (Fnm,Lnm,NatIN,IB,3); /* Error with unkown cause */
/* trying to find the new created record and obtain its ID */
@Ident := (select EmployeeID
from Employee
where NIN = NatIN and IBAN = IB);
/* using the newly created variable to mimic the record and insert the correct values */
insert into EmpSales(EmpID,Month,TotalSales,ComRate) values (Ident,Now(),Sales,Commission);
 end;
 
Delimiter //
