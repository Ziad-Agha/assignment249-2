
public class Employee {
	private long empNum;
	private String firstName;
	private String lastName;
	private double hrWorked;
	private double hrlyWage;
	private double annGrsSalary;
	private double deductions = 0;
	private double annNetSalary;
	public Employee(long empNum, String firstName, String lastName, double hrWorked, double hrlyWage,
			double annGrsSalary) {
		this.empNum = empNum;
		this.firstName = firstName;
		this.lastName = lastName;
		this.hrWorked = hrWorked;
		this.hrlyWage = hrlyWage;
		this.annGrsSalary = annGrsSalary;
	}
	public long getEmpNum() {
		return empNum;
	}
	public void setEmpNum(long empNum) {
		this.empNum = empNum;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getHrWorked() {
		return hrWorked;
	}
	public void setHrWorked(double hrWorked) {
		this.hrWorked = hrWorked;
	}
	public double getHrlyWage() {
		return hrlyWage;
	}
	public void setHrlyWage(double hrlyWage) {
		this.hrlyWage = hrlyWage;
	}
	public double getAnnGrsSalary() {
		return annGrsSalary;
	}
	public void setAnnGrsSalary(double annGrsSalary) {
		this.annGrsSalary = annGrsSalary;
	}
	public void setAnnNetSalary(double annNetSalary) {
		this.annNetSalary = annNetSalary;
	}
	public void setDeductions(double deductions) {
		this.deductions += deductions;
	}
	public double getDeductions() {
		return deductions;
	}
	public double getAnnNetSalary() {
		return annNetSalary;
	}
	
	
	
	
	

}
