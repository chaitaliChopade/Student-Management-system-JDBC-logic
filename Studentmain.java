package com.jspiders.jdbc.insert;

import java.util.List;
import java.util.Scanner;

public class Studentmain {

	public static void main(String[] args) {
		
		
		JDBC jdbc =new JDBC();
		Scanner scanner=new Scanner(System.in);
		boolean flag =true;
		while (flag) {
			System.out.println("Enter 1 to add Student. \nEnter 2 to get all student. \nEnter 3 to get student by id."
					+ "\nEnter 4 to delete Student. \nEnter 5 to update Student. \nEnter 6 to Exist.");
			
			int choice=scanner.nextInt();
			switch (choice) {
			case 1:
				Student student=new Student();
				System.out.println("Enter Student id");
				student.setId(scanner.nextInt());
				scanner.nextLine();
				System.out.println("Enter Student name");
				student.setName(scanner.nextLine());
				System.out.println("Enter Student email");
				student.setEmail(scanner.nextLine());
				System.out.println("Enter Student age");
				student.setAge(scanner.nextInt());
				System.out.println("Enter Student fees");
				student.setFees(scanner.nextDouble());
				  jdbc.addStudent(student);
				break;
			case 2: 
				 List<Student> students=jdbc.getAllStudent();
				 for (Student s:students) {
					 System.out.println(s);
				 }
				 break;
			case 3:
				System.out.println("Enter student id");
				 Student s =jdbc.getStudentById(scanner.nextInt());
				 System.out.println(s);
				 break;
			case 4:
				System.out.println("Enter student id");
				jdbc.deleteStudent(scanner.nextInt());
				break;
			case 5:
				System.out.println("Enter student id");
				jdbc.updateStudent(scanner.nextInt(), scanner);
				break;
			case 6:
				System.out.println("Thank you!");
				flag=false;
				break;
			default:
				System.out.println("Invalid choice");
			}
			
		}
		scanner.close();
		}
	
}
