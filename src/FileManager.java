import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class FileManager {
	
	private Employee[] employees;
	private String filePath;
	
	public int findLines() throws FileNotFoundException, IOException {
	        int lineCount = 0;

	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            while (reader.readLine() != null) {
	                lineCount++;
	            }
	        } 
	        return lineCount;
	        
	    }

	public FileManager(String filePath) throws FileNotFoundException, IOException {
		
		this.employees = new Employee[findLines()];
		this.filePath = filePath;
		this.parseFile();
	}
	
	private void parseFile() throws FileNotFoundException {
		 try (Scanner scanner = new Scanner(new File(filePath))) {
	            for (int i = 0; i < employees.length; i++) {
	            	try {
	            		
	            	 long empNum = scanner.nextLong();
	            	 String firstName = scanner.next();
	            	 String lastName = scanner.next();
	            	 double hrWorked = scanner.nextDouble();
	            	 double hrlyWage = scanner.nextDouble();
	            	 double annGrsSalary = hrlyWage*hrWorked;
	            	 employees[i] = new Employee(empNum,firstName, lastName, hrWorked, hrlyWage,annGrsSalary);
	            	 scanner.nextLine();
	            	 if (hrlyWage < 15.75) {
	                        throw new MinimumWageException("Error: Employee " + firstName + " " + lastName +
	                                " has an hourly wage below the minimum wage ($15.75)");
	            		
	            	}
				}catch(MinimumWageException e){
					employees[i]=null;
					continue;
				}
	            catch(InputMismatchException e) {
	            		employees[i]=null;
	            		continue;
	            	}
	            }      
	            
	         
		
		
	}
	
	
	
	}
}
	
	


