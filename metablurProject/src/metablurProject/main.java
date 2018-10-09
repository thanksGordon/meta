package metablurProject;

import java.util.Scanner;
import java.sql.*;

//import metablurProject.sql.*;

public class main {

	public static void ErrorMessage() {
		System.out.println("Invalid input, try again!");
	}

	public static void UserStory1(String sQLUsername, String sQLPassword) {
		String fname = "", lname = "", address = "", NIN = "", IBAN = "";
		float startingSalary = 0;
		int DepartmentID = 0;
		Scanner scanner = new Scanner(System.in);

		while (fname.length() > 30 || fname.isEmpty() == true) {
			if (fname.length() > 30) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's first name? (LESS THAN 30 CHARS)");
			fname = scanner.nextLine();
		}
		while (lname.length() > 30 || lname.isEmpty() == true) {
			if (lname.length() > 30) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's last name? (LESS THAN 30 CHARS)");
			lname = scanner.nextLine();
		}
		while (address.length() > 40 || address.isEmpty() == true) {
			if (address.length() > 40) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's address? (LESS THAN 40 CHARS)");
			address = scanner.nextLine();
		}
		while (!NIN.matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$")
				|| NIN.isEmpty() == true) {
			System.out.println("What is the Employee's National Insurance Number? (9 CHARS ONLY)");
			NIN = scanner.nextLine();
			if (!NIN.matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$")) {
				ErrorMessage();
			}
		}
		while (IBAN.length() > 34 || IBAN.length() < 8 || lname.isEmpty() == true) {
			System.out.println("What is the Employee's IBAN? (AT LEAST 8 CHARS AND LESS THAN 34 CHARS)");
			IBAN = scanner.nextLine();
			if (IBAN.length() > 34 || IBAN.length() > 0 && IBAN.length() < 8) {
				ErrorMessage();
			}
		}
		while (startingSalary >= 99999.99 || startingSalary == 0) {
			System.out.println("What is the Employee's Salary?");
			try {
				startingSalary = Float.parseFloat(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Please insert numbers ONLY!");
			}
		}
		while (DepartmentID <= 0 || DepartmentID > 3) {
			System.out.println("What is the Employee's Department ID? (1, 2 OR 3)");
			try { DepartmentID = Integer.parseInt(scanner.nextLine()); }
			catch (Exception e) { System.out.println("Please insert a valid number!");
			}
		}

		try {
			Class driver = Class.forName("com.mysql.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/metablurProject?useSSL=false",
					sQLUsername, sQLPassword);
			Statement st = c.createStatement();
			String SQL = "INSERT INTO Employee(fname, lname, Address, NIN, IBAN, StartingSalary, DepartmentID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = c.prepareStatement(SQL);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, address);
			pstmt.setString(4, NIN);
			pstmt.setString(5, IBAN);
			pstmt.setFloat(6, startingSalary);
			pstmt.setInt(7,  DepartmentID);
			pstmt.executeUpdate();
			pstmt.close();

			ResultSet rs = st.executeQuery("SELECT * FROM Employee");
			while (rs.next()) {
				String out = String.format("Employee Number %s: %s %s.\n", rs.getString("EmployeeID"),
						rs.getString("fname"), rs.getString("lname"));
				System.out.println(out);
			}
			
			main(null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to the database, please contact System Administrator!");
		}
	}

	public static void UserStory2(String sQLUsername, String sQLPassword) {
		try {
			Class driver = Class.forName("com.mysql.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/metablurProject?useSSL=false",
					sQLUsername, sQLPassword);
			Statement st = c.createStatement();
			ResultSet rs = st.executeQuery(
					"SELECT * FROM Employee JOIN Department ON Employee.DepartmentID = Department.DptID WHERE Employee.DepartmentID ORDER BY Employee.DepartmentID;");
			while (rs.next()) {
				String out = String.format(
						"Employee Number %s: %s %s. \nAddress: %s \nPostcode: %s \nIBAN: %s \nStarting Salary: %f \nDepartment ID: %s \nDepartment Name: %s\n",
						rs.getString("EmployeeID"), rs.getString("fname"), rs.getString("lname"),
						rs.getString("Address"), rs.getString("NIN"), rs.getString("IBAN"),
						rs.getFloat("StartingSalary"), rs.getString("DepartmentID"), rs.getString("DName"));
				System.out.println(out);
			}
			main(null);
		}

		catch (Exception e) {
			System.out.println("Unable to connect to the database, please contact System Administrator!\n" + e);
		}

	}

	public static void UserStory3(String sQLUsername, String sQLPassword) {
		String fname = "", lname = "", address = "", NIN = "", IBAN = "";
		float startingSalary = 0;
		int DepartmentID = 0;
		Scanner scanner = new Scanner(System.in);

		while (fname.length() > 30 || fname.isEmpty() == true) {
			if (fname.length() > 30) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's first name? (LESS THAN 30 CHARS)");
			fname = scanner.nextLine();
		}
		while (lname.length() > 30 || lname.isEmpty() == true) {
			if (lname.length() > 30) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's last name? (LESS THAN 30 CHARS)");
			lname = scanner.nextLine();
		}
		while (address.length() > 40 || address.isEmpty() == true) {
			if (address.length() > 40) {
				ErrorMessage();
			}
			System.out.println("What is the Employee's address? (LESS THAN 40 CHARS)");
			address = scanner.nextLine();
		}
		while (!NIN.matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$")
				|| NIN.isEmpty() == true) {
			System.out.println("What is the Employee's National Insurance Number? (9 CHARS ONLY)");
			NIN = scanner.nextLine();
			if (!NIN.matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$")) {
				ErrorMessage();
			}
		}
		while (IBAN.length() > 34 || IBAN.length() < 8 || lname.isEmpty() == true) {
			System.out.println("What is the Employee's IBAN? (AT LEAST 8 CHARS AND LESS THAN 34 CHARS)");
			IBAN = scanner.nextLine();
			if (IBAN.length() > 34 || IBAN.length() > 0 && IBAN.length() < 8) {
				ErrorMessage();
			}
		}
		while (startingSalary >= 99999.99 || startingSalary == 0) {
			System.out.println("What is the Employee's Salary?");
			try {
				startingSalary = Float.parseFloat(scanner.nextLine());
			} catch (Exception e) {
				System.out.println("Please insert numbers ONLY!");
			}
		}
		while (DepartmentID <= 0 || DepartmentID > 3) {
			System.out.println("What is the Employee's Department ID? (1, 2 OR 3)");
			try { DepartmentID = Integer.parseInt(scanner.nextLine()); }
			catch (Exception e) { System.out.println("Please insert a valid number!");
			}
		}

		try {
			Class driver = Class.forName("com.mysql.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/metablurProject?useSSL=false",
					sQLUsername, sQLPassword);
			Statement st = c.createStatement();
			String SQL = "INSERT INTO Employee(fname, lname, Address, NIN, IBAN, StartingSalary, DepartmentID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = c.prepareStatement(SQL);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, address);
			pstmt.setString(4, NIN);
			pstmt.setString(5, IBAN);
			pstmt.setFloat(6, startingSalary);
			pstmt.setInt(7,  DepartmentID);
			pstmt.executeUpdate();
			pstmt.close();

			
			float TotalSales = 0;
			ResultSet rs = st.executeQuery("SELECT EmployeeID FROM Employee ORDER BY EmployeeID DESC Limit 1");
			
			int EmpID = Integer.parseInt(rs.getString(1));
			
			while (TotalSales <= 0) {
				System.out.println("How many sales has this Employee done?");
				try { TotalSales = Integer.parseInt(scanner.nextLine()); }
				catch (Exception e) { System.out.println("Please insert a valid number!");
				}
			}
			
			String newSQL = "INSERT INTO EmpSales(EmpID, TotalSales) VALUES (?, ?)";
			PreparedStatement stmt = c.prepareStatement(newSQL);
			stmt.setInt(1, EmpID);
			stmt.setFloat(2, TotalSales);
			
			main(null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Unable to connect to the database, please contact System Administrator!\n" + e);
		}
	}

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter your username for SQL workbench");
		String SQLUsername = scanner.nextLine();
		System.out.println("Please enter your password for SQL workbench");
		String SQLPassword = scanner.nextLine();

		int choice = 0;
		while (choice < 1 || choice > 8) {
			System.out.println(
					"Press 1 to insert an employee, 2 to generate a report of employees by department and 3 to insert a sales employee");
			try {
				choice = Integer.parseInt(scanner.nextLine());
			} catch (Exception e) {
			}
			switch (choice) {
			case 1: {
				UserStory1(SQLUsername, SQLPassword);
				break;
			}
			case 2: {
				UserStory2(SQLUsername, SQLPassword);
				break;
			}
			case 3: {
				UserStory3(SQLUsername, SQLPassword);
				break;
			}
			default: {
				System.out.println("Please insert a valid number.");
			}

			/*
			 * while (fname.length() > 30 || fname.isEmpty() == true) { if (fname.length() >
			 * 30) { ErrorMessage(); }
			 * System.out.println("What is the Employee's first name? (LESS THAN 30 CHARS)"
			 * ); fname = scanner.nextLine(); } while (lname.length() > 30 ||
			 * lname.isEmpty() == true) { if (lname.length() > 30) { ErrorMessage(); }
			 * System.out.println("What is the Employee's last name? (LESS THAN 30 CHARS)");
			 * lname = scanner.nextLine(); } while (address.length() > 40 ||
			 * address.isEmpty() == true) { if (address.length() > 40) { ErrorMessage(); }
			 * System.out.println("What is the Employee's address? (LESS THAN 40 CHARS)");
			 * address = scanner.nextLine(); } while (!NIN.
			 * matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$"
			 * ) || NIN.isEmpty() == true) { System.out.
			 * println("What is the Employee's National Insurance Number? (9 CHARS ONLY)");
			 * NIN = scanner.nextLine(); if(!NIN.
			 * matches("^([a-zA-Z]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([0-9]){2}( )?([a-zA-Z]){1}?$"
			 * )) { ErrorMessage(); } }
			 * 
			 * while (IBAN.length() > 34 || IBAN.length() < 8 || lname.isEmpty() == true) {
			 * System.out.
			 * println("What is the Employee's IBAN? (AT LEAST 8 CHARS AND LESS THAN 34 CHARS)"
			 * ); IBAN = scanner.nextLine(); if (IBAN.length() > 34 || IBAN.length() > 0 &&
			 * IBAN.length() < 8) { ErrorMessage(); } } while (startingSalary >= 99999.99 ||
			 * startingSalary == 0) { System.out.println("What is the Employee's Salary?");
			 * try { startingSalary = Float.parseFloat(scanner.nextLine()); } catch
			 * (Exception e) { System.out.println("Please insert numbers ONLY!"); } }
			 * 
			 * try { Class driver = Class.forName("com.mysql.jdbc.Driver");
			 * 
			 * Connection c = DriverManager.getConnection(
			 * "jdbc:mysql://localhost/metablurProject?useSSL=false", SQLUsername,
			 * SQLPassword); Statement st = c.createStatement(); String SQL =
			 * "INSERT INTO Employee(fname, lname, Address, NIN, IBAN, StartingSalary) VALUES (?, ?, ?, ?, ?, ?)"
			 * ; PreparedStatement pstmt = c.prepareStatement(SQL); pstmt.setString(1,
			 * fname); pstmt.setString(2, lname); pstmt.setString(3, address);
			 * pstmt.setString(4, NIN); pstmt.setString(5, IBAN); pstmt.setFloat(6,
			 * startingSalary); pstmt.executeUpdate(); pstmt.close();
			 * 
			 * UserStory2(SQLUsername, SQLPassword);
			 * 
			 * ResultSet rs = st.executeQuery("SELECT * FROM Employee"); while (rs.next()) {
			 * String out = String.format("Employee Number %s: %s %s.",
			 * rs.getString("EmployeeID"), rs.getString("fname"), rs.getString("lname"));
			 * System.out.println(out); }
			 * 
			 * } catch (Exception e) { // TODO Auto-generated catch block System.out.
			 * println("Unable to connect to the database, please contact System Administrator!"
			 * ); }
			 */
			}
		}
	}
}
