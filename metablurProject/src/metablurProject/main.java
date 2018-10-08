package metablurProject;
import java.util.Scanner;
import java.sql.*;

//import metablurProject.sql.*;

public class main {
//test

	
	public static void main(String[] args) {

		String fname = "", lname = "", address = "", NIN = "", IBAN = "";
		float startingSalary = 0;
		Scanner scanner = new Scanner(System.in);
	
		
		
		while (fname.length() > 30 || fname.isEmpty() == true)
		{
			System.out.println("What is the Employee's first name? (LESS THAN 30 CHARS)");
			fname = scanner.nextLine();
		}
		while (lname.length() > 30 || lname.isEmpty() == true)
		{
		System.out.println("What is the Employee's last name? (LESS THAN 30 CHARS)");
		lname = scanner.nextLine();
		}
		while (address.length() > 40 || address.isEmpty() == true)
		{
		System.out.println("What is the Employee's address? (LESS THAN 40 CHARS)");
		address = scanner.nextLine();
		}
		while (NIN.length() != 9 || NIN.isEmpty() == true)
		{
		System.out.println("What is the Employee's National Insurance Number? (9 CHARS ONLY)");
		NIN = scanner.nextLine();
		}
		while (IBAN.length() > 34 || IBAN.length() < 8 || lname.isEmpty() == true)
		{
		System.out.println("What is the Employee's IBAN? (AT LEAST 8 CHARS AND LESS THAN 34 CHARS)");
		IBAN = scanner.nextLine();
		}
		while (startingSalary >= 9999.99 || startingSalary == 0)
		{
		System.out.println("What is the Employee's Salary?");
		try {
		startingSalary = Float.parseFloat(scanner.nextLine());
		}
		catch (Exception e){
			System.out.println("Please insert numbers ONLY!");
		}
		}
	
		
		try {
			Class driver = Class.forName("com.mysql.jdbc.Driver");

			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/metablurProject?useSSL=false", "shane", "password");
			Statement st = c.createStatement();
            String SQL = "INSERT INTO Employee(fname, lname, Address, NIN, IBAN, StartingSalary) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = c.prepareStatement(SQL);
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, address);
            pstmt.setString(4, NIN);
            pstmt.setString(5, IBAN);
            pstmt.setFloat(6, startingSalary);
            pstmt.executeUpdate();
            pstmt.close();
			
			ResultSet rs = st.executeQuery("SELECT * FROM Employee");
			while (rs.next()) {
				String out = String.format("Employee Number %s: %s %s.", rs.getString("EmployeeID"), rs.getString("fname"), rs.getString("lname"));
				System.out.println(out);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
	}
}
