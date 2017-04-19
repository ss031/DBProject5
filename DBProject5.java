import java.sql.*;

/*
jdbc_insert_restaurant.java    // java program that is called by php that just does the insert; calls jdbc_db.java to connect and do the actual insert
jdbc_db.java // class (no main program) that has useful methods
*/

public class DBProject5
{
   // The main program that inserts a restaurant
   public static void main(String[] args) throws SQLException 
   {
		String Username = "arutiaga";              // Change to your own username
		String mysqlPassword = "LaLaLand";    // Change to your own mysql Password
		int choice = Integer.parseInt(args[0]); // Menu Item	
		// Connect to the database
		jdbc_db myDB = new jdbc_db();	
		myDB.connect(Username, mysqlPassword);			    
		// For debugging purposes:  Show the database before the insert
		StringBuilder builder = new StringBuilder();
		
		
		if(choice == 1)
		{	
			String query = "SELECT * FROM Student";
			builder.append("<br> Table Student before:" + myDB.query(query) + "<br>"); 
			
			String studentName = args[1];
			String studentID = args[2];
			String Major = args[3];

			// Insert the new restaurant
			String input = "" + studentID + ", '" + studentName + "', '" + Major + "'";
			System.out.println(input);              
			myDB.insert("Student", input);    // insert new student
			
					// For debugging purposes:  Show the database after the insert
			builder.append("<br><br><br> Table Student after:" + myDB.query(query));
			System.out.println(builder.toString()); 
			
		}
		
		else if(choice == 2)
		{
			String query = "SELECT * from Course";
			builder.append("<br> Table Course before:" + myDB.query(query) + "<br>"); 
			
			String departmentCode = args[1];
			String courseNumber = args[2];
			String courseTitle = args[3];
			String creditHours = args[4];
			
			String input = "'" + departmentCode + "'," + courseNumber + ",'" + courseTitle
							+ "'," + creditHours;
							
			myDB.insert("Course", input);
			builder.append("<br><br><br> Table Student after:" + myDB.query(query));
			System.out.println(builder.toString()); 
		}
		
		else if(choice == 3)
		{
			String query = "SELECT * from Enrollment";
			builder.append("<br> Table Enrollment before:" + myDB.query(query) + "<br>"); 
			
			String studentID = args[1];
			String departmentCode = args[2];
			String courseNumber = args[3];
			
			String input = "" + studentID + ",'" + departmentCode + "'," + courseNumber + "";
			myDB.insert("Enrollment", input);
			
			builder.append("<br><br><br> Enrollment Table after:" + myDB.query(query));
			System.out.println(builder.toString()); 
		}
		
		else if(choice == 4)
		{
			String query = "SELECT * FROM Student";
			builder.append("<br> All students:" + myDB.query(query) + "<br>");
			System.out.println(builder.toString()); 
		}
		
		else if(choice == 5)
		{
			String department = args[1];
			String query = "SELECT * " +
				    	   "FROM Course " +
				    	   "WHERE deptCode='" + department + "'";
			builder.append("<br> All courses from the " + department + " department: " + myDB.query(query) + "<br>");
			System.out.println(builder.toString()); 
		}
		
		else if(choice == 6)
		{
			Integer studentID = Integer.parseInt(args[1]);
			String query = "SELECT c.deptCode, c.courseNum, c.title, c.creditHours " +
						   "FROM Enrollment e, Course c " +
						   "WHERE e.deptCode=c.deptCode " +
						   "AND e.courseNum=c.courseNum " +
						   "AND e.studentID=" + studentID.toString();
			builder.append("<br> All courses that student " + studentID + " is taking: " + myDB.query(query) + "<br>");
			System.out.println(builder.toString()); 
		}		    

		myDB.disConnect();
	}
}
