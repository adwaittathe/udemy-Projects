import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.Reader;
import java.sql.*;
import java.util.Properties;
public class jdbc {

	public static void main(String args[]) throws SQLException {
		Connection conn=null;
		Statement statement=null;
		ResultSet resultSet=null;
		try {
			
			Properties prop = new Properties();
			prop.load(new FileInputStream("dbproperties"));
			String user= prop.getProperty("user");
			String password= prop.getProperty("password");
			String connection= prop.getProperty("connection"); 
			conn= DriverManager.getConnection(connection,user,password);
			statement = conn.createStatement();
					
			//Select
			resultSet = statement.executeQuery("select * from employees");
			// I D U can be performed normal query
			/*
			int row= statement.executeUpdate("insert into employees (last_name, first_name, email, department, salary) values "
					+ "('Adwait','Tathe','adwait.tathe@gmail.com','Computer Science','23400')");
			System.out.println(row); 
		    */	
					
			//To use statements multiple times		
			PreparedStatement prep= conn.prepareStatement("Select * from employees where salary>=? and department=?");
			prep.setDouble(1, 50000);
			prep.setString(2, "HR");
			resultSet= prep.executeQuery();
			prep.setDouble(1, 50000);
			prep.setString(2, "Legal");
			resultSet= prep.executeQuery();	
			
			
			//In Parameter
			/*
			CREATE DEFINER=`student`@`localhost` PROCEDURE `increase_salaries_for_department`(IN the_department VARCHAR(64), IN increase_amount DECIMAL(10,2))
					BEGIN
						UPDATE employees SET salary= salary + increase_amount where department=the_department;
					END
			*/ 
			CallableStatement call= conn.prepareCall("{call increase_salaries_for_department(?,?)}");
			call.setString(1, "HR");
			call.setDouble(2, 10000);
			call.execute();
			
			
			
			//INOUT Parameter
			/*
			CREATE DEFINER=`student`@`localhost` PROCEDURE `greet_the_department`(INOUT department VARCHAR(64))
					BEGIN
						SET department = concat('Hello to the awesome ', department, ' team!');
					END
			*/			
			CallableStatement call1= conn.prepareCall("{call greet_the_department(?)}");
			call1.registerOutParameter(1, Types.VARCHAR);
			call1.setString(1, "HR");
			call1.execute();
			String dept= call1.getString(1);
			System.out.println("Result - " + dept);
			
			
			
			//OUT
		    /*
			CREATE DEFINER=`student`@`localhost` PROCEDURE `get_count_for_department`(IN the_department VARCHAR(64), OUT the_count INT)
					BEGIN
						SELECT COUNT(*) INTO the_count FROM employees where department=the_department;
					END
			*/				
			CallableStatement call2= conn.prepareCall("{call get_count_for_department(?,?)}");
			call2.registerOutParameter(2, Types.INTEGER);
			call2.setString(1, "HR");
			call2.execute();
			int count= call2.getInt(2);
			System.out.println("Count - " + count);
			
			
						
			// SP that returns ResultSet
			/*
			CREATE DEFINER=`student`@`localhost` PROCEDURE `get_employees_for_department`(IN the_department VARCHAR(64))
					BEGIN
						SELECT * from employees where department=the_department;
					END
	        */
			CallableStatement call3= conn.prepareCall("{call get_employees_for_department(?)}");
			call3.setString(1, "HR");
			call3.execute();
			resultSet=call3.getResultSet();
						
			//Database Metadata
			DatabaseMetaData metaData = conn.getMetaData();
			resultSet = metaData.getColumns(null, null, "employees", null);
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("COLUMN_NAME"));
			}
						
			//ResultSet Metadata
			resultSet= statement.executeQuery("select * from employees");
			ResultSetMetaData resultMeta = resultSet.getMetaData();
			int col = resultMeta.getColumnCount();
			System.out.println("Col count - " + col);
			for(int i=1; i<=col; i++)
			{
				System.out.println("Name : " + resultMeta.getColumnName(i));
				System.out.println("Type : " + resultMeta.getColumnTypeName(i));
				System.out.println("IsAutoInc: " + resultMeta.isAutoIncrement(i));
			}
			
			//BLOB
			File file = new File("Resume.docx");
			FileInputStream input=new FileInputStream(file);
			PreparedStatement prep1= conn.prepareStatement("update employees set resume = ? where email='john.doe@foo.com'");
			prep1.setBinaryStream(1, input);
			prep1.executeUpdate();	
			Statement stm = conn.createStatement();
			resultSet= stm.executeQuery("select resume from employees where email='john.doe@foo.com' ");
			File outFile = new File ("Resume DB.docx");
			FileOutputStream out = new FileOutputStream(outFile);
			if(resultSet.next())
			{
				InputStream inp = resultSet.getBinaryStream("resume");
				byte[] buff = new byte[1024];
				while (inp.read(buff)>0)
				{
					out.write(buff);
				}
			}
			
			
			//CLOB
			File file1 = new File("Resume.docx");
			FileReader reader=new FileReader(file1);
			PreparedStatement prep2= conn.prepareStatement("update employees set resume = ? where email='john.doe@foo.com'");
			prep2.setCharacterStream(1, reader);
			prep2.executeUpdate();
			Statement stm1 = conn.createStatement();
			resultSet= stm1.executeQuery("select resume from employees where email='john.doe@foo.com'");
			File outFile1 = new File("DB.docx");
			FileWriter writer= new FileWriter(outFile1);
			if(resultSet.next())
			{
				Reader read = resultSet.getCharacterStream("resume");
				int i;
				while( (i=read.read()) >0 )
				{
					writer.write(i);
				}
			}
				
			while(resultSet.next())
			{
				System.out.println(resultSet.getString("last_name") + "    " +resultSet.getString("first_name") + "    " + resultSet.getString("salary"));
			}				

		} catch (Exception exp) {
			exp.printStackTrace();
		}
		finally {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}		
			if (conn != null) {
				conn.close();
			}
		}
	}
}
//Reference luv2code.com