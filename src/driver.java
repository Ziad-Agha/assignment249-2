import java.io.FileNotFoundException;
import java.io.IOException;

public class driver {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try {
			FileManager FM = new FileManager("files/payroll.txt", "src/files/ErrorFile.txt");
			
			FM.createReport("src/files/ReportFile.txt");
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}

	}

}
