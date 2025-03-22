//
// Assignment 2
// Written by: Ziad Agha - 40312869 / Abderrahmane Bensassi-Nour - 40317017
//
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileManager {

    private Employee[] employees; // Array to store employee data
    private String payFilePath; // Path to the payroll file
    private String errorFilePath; // Path to the error file
    private ClassLoader classLoader = getClass().getClassLoader(); // ClassLoader to access resource files
    private Deductions[] dedTaxes = {new EIPremiumTax(), new FederalIncomeTax(), new ProvincialIncomeTax(), new QPIPPremium(), new QPPPremium()};
    private int errors = 0; // Counter for errors encountered

    // Constructor that initializes file paths and processes the payroll file
    public FileManager(String filePath, String errorFilePath) throws FileNotFoundException, IOException {
        this.payFilePath = filePath;
        this.errorFilePath = errorFilePath;
        this.employees = new Employee[findLines()]; // Determine the number of lines to initialize the employee array
        this.parseFile(); // Parse the file and populate employee data
        

        
    }

    // Method to count the number of lines in the payroll file
    public int findLines() throws FileNotFoundException, IOException {
        int lineCount = 0;
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(classLoader.getResourceAsStream(this.payFilePath)))) {
            while (reader.readLine() != null) {
                lineCount++;
            }
        }
        return lineCount;
    }

    // Method to parse the payroll file and extract employee details
    private void parseFile() throws IOException {
        try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(this.payFilePath))) {
            System.out.println(">Opening file payroll...");
            String line = "";
            System.out.println(">Reading file payroll...");
            for (int i = 0; i < employees.length; i++) {
                try {
                    if (scanner.hasNextLine()) {
                        line = scanner.nextLine();
                        Scanner scanner2 = new Scanner(line);

                        // Extract employee details from the line
                        long empNum = scanner2.nextLong();
                        String firstName = scanner2.next();
                        String lastName = scanner2.next();
                        double hrWorked = scanner2.nextDouble();
                        double hrlyWage = scanner2.nextDouble();
                        double annGrsSalary = hrlyWage * hrWorked * 52; // Calculate annual salary
                        scanner2.close();

                        employees[i] = new Employee(empNum, firstName, lastName, hrWorked, hrlyWage, annGrsSalary);

                        // Check if hourly wage is below the minimum threshold
                        if (hrlyWage < 15.75) {
                            throw new MinimumWageException("Error: Employee " + firstName + " " + lastName
                                    + " has an hourly wage below the minimum wage ($15.75)");
                        }
                    }
                } catch (MinimumWageException e) {
                    // Log employees with wage below the minimum threshold to an error file
                    try (BufferedWriter bW = new BufferedWriter(new FileWriter(errorFilePath, true))) {
                        bW.write(line);
                        bW.newLine();
                        errors++;
                    }
                    employees[i] = null; // Mark the employee entry as null
                } catch (InputMismatchException e) {
                    // Log incorrect data format errors to the error file
                    try (BufferedWriter bW = new BufferedWriter(new FileWriter(errorFilePath, true))) {
                        bW.write(line);
                        bW.newLine();
                        errors++;
                    }
                    employees[i] = null; // Mark the employee entry as null
                }
                if(errors==1)
                System.out.println(">Error lines found in payroll");
            }
            System.out.println(">"+this.findLines() + " lines read from file payroll");
            System.out.println(">"+this.errors + " lines were written to error file");

        }
    }

    // Method to generate a payroll report file
    public void createReport(String reportPath) throws IOException {
        System.out.println(">Calculating deductions");

        for (int i = 0; i < dedTaxes.length; i++) {
            dedTaxes[i].calculateTax(this); // Apply all tax deductions
        }
        System.out.println(">Writing report file");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(reportPath, true))) {
            // Writing table header
            writer.write(String.format("%-12s %-10s %-10s %12s %12s %12s",
                    "Emp No", "First Name", "Last Name", "Gross Salary", "Deductions", "Net Salary"));
            writer.newLine();
            writer.write("----------------------------------------------------------------------------------");
            writer.newLine();
            
            // Writing employee details
            for (Employee emp : this.employees) {
                if (emp != null) {
                    writer.write(String.format("%-12d %-10s %-10s %12.2f %12.2f %12.2f",
                            emp.getEmpNum(), emp.getFirstName(), emp.getLastName(),
                            emp.getAnnGrsSalary(), emp.getDeductions(), emp.getAnnNetSalary()));
                    writer.newLine();
                }
            }
            System.out.println(">Verify data in the files attached to this program");
        }
    }

    // Getter method to return employee array
    public Employee[] getEmployees() {
        return this.employees;
    }

    // Getter method to return the number of errors encountered
    public int getErrorNum() {
        return errors;
    }
}
