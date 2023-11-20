package com.jspiders.jdbc.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBC {
 
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	private String query;
	
	   
	public void addStudent(Student student) {
		 
		try {
			connection=openConnection();
			query="insert into students values(?,?,?,?,?)";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3,student.getEmail());
			preparedStatement.setInt(4,student.getAge());
			preparedStatement.setDouble(5, student.getFees());
			int row= preparedStatement.executeUpdate();
			System.out.println(row +"row(s) affected");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public List<Student> getAllStudent() {
		ArrayList<Student> list=new ArrayList<Student>();
				
		try {
			connection=openConnection();
			query="select * from students";
			preparedStatement=connection.prepareStatement(query);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				Student student=new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setEmail(resultSet.getString(3));
				student.setAge(resultSet.getInt(4));
				student.setFees(resultSet.getDouble(5));
				list.add(student);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	public Student getStudentById(int id) {
		Student student=new Student();
		try {
			connection=openConnection();
			query="select * from students where id=?";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			if (resultSet.next()) {
				 student.setId(resultSet.getInt(1));
				 student.setName(resultSet.getString(2));
				 student.setEmail(resultSet.getString(3));
				 student.setAge(resultSet.getInt(4));
				 student.setFees(resultSet.getDouble(5));
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			 try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return student;
	}
	
	public void deleteStudent(int id) {
		try {
			connection=openConnection();
			query="delete from students where id =?";
			preparedStatement=connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			int row=preparedStatement.executeUpdate();
			System.out.println( row +"row(s) affected");
					
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
		  try {
			closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	public void updateStudent(int id, Scanner scanner) {
		try {
			connection=openConnection();
			query="update students set name=?, email=?, age=?, fees=? where id=?";
			preparedStatement=connection.prepareStatement(query);
			scanner.nextLine();
			System.out.println("Enter student Name ");
			preparedStatement.setString(1, scanner.nextLine());
			System.out.println("Enter student Email");
			preparedStatement.setString(2, scanner.nextLine());
			System.out.println("Enter student Age ");
			preparedStatement.setInt(3,scanner.nextInt());
			System.out.println("Enter student Fees ");
			preparedStatement.setDouble(4,scanner.nextDouble());
			preparedStatement.setInt(5,id);
			int row =preparedStatement.executeUpdate();
			System.out.println(row+"row(s) affected");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	 private Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		 return DriverManager.getConnection("jdbc:mysql://localhost:3306/weja2","root", "root");
				 
	 }
	 private void closeConnection() throws SQLException {
		 if (preparedStatement !=null) {
			preparedStatement.close();
		}
		 if (resultSet !=null) {
			resultSet.close();
		}
		 if (connection !=null) {
			connection.close();
		}
	 }
}
