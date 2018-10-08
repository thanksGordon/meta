SELECT 
    *
FROM
    Employee
        JOIN
    Department ON Employee.DepartmentID = Department.DptID
WHERE
    Employee.DepartmentID
ORDER BY Employee.DepartmentID;