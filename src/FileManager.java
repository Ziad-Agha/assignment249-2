import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FileManager {

	private Employee[] employees;
	private String payFilePath;
	private String errorFilePath;
	private ClassLoader classLoader = getClass().getClassLoader();

	public FileManager(String filePath, String errorFilePath) throws FileNotFoundException, IOException {

		this.payFilePath = filePath;
		this.errorFilePath = errorFilePath;

		this.employees = new Employee[findLines()];
		this.parseFile();

	}

	public int findLines() throws FileNotFoundException, IOException {
		int lineCount = 0;
		try (BufferedReader reader = new BufferedReader(
				(new InputStreamReader(classLoader.getResourceAsStream(this.payFilePath))))) {
			while (reader.readLine() != null) {
				lineCount++;
			}
		}
		return lineCount;

	}

	private void parseFile() throws IOException {

		try (Scanner scanner = new Scanner(classLoader.getResourceAsStream(this.payFilePath))) {
			String line = "";
			for (int i = 0; i < employees.length; i++) {
				try {

					if (scanner.hasNextLine()) {
						line = scanner.nextLine();
						Scanner scanner2 = new Scanner(line);

						long empNum = scanner2.nextLong();
						String firstName = scanner2.next();
						String lastName = scanner2.next();
						double hrWorked = scanner2.nextDouble();
						double hrlyWage = scanner2.nextDouble();
						double annGrsSalary = hrlyWage * hrWorked;
						scanner2.close();
						employees[i] = new Employee(empNum, firstName, lastName, hrWorked, hrlyWage, annGrsSalary);
						if (hrlyWage < 15.75) {
							throw new MinimumWageException("Error: Employee " + firstName + " " + lastName
									+ " has an hourly wage below the minimum wage ($15.75)");

						}
					}

				} catch (MinimumWageException e) {
					try (BufferedWriter bW = new BufferedWriter((new FileWriter(errorFilePath, true)))) {

						bW.write(line);
						bW.newLine();

					}

					employees[i] = null;
				} catch (InputMismatchException e) {
					try (BufferedWriter bW = new BufferedWriter((new FileWriter(errorFilePath, true)))) {
						bW.write(line);
						bW.newLine();

					}
				}
				employees[i] = null;
			}

		}
	}

	public Employee[] getEmployees() {
		return this.employees;
	}
}
