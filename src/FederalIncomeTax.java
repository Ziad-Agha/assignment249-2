
public class FederalIncomeTax extends Deductions{

	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		
		double[] deductions = new double[employeeList.length];
		
		for(int i = 0; i < employeeList.length; i++) {
			if(employeeList[i]!=null) {
			if(employeeList[i].getAnnGrsSalary() > 253414) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.33;
			}
			else if(employeeList[i].getAnnGrsSalary() > 177883) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.29;
			}
			else if(employeeList[i].getAnnGrsSalary() > 114751) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.26;
			}
			else if(employeeList[i].getAnnGrsSalary() > 57376) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.205;
			}
			else if(employeeList[i].getAnnGrsSalary() >= 16129) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.15;
			}
			else {
				deductions[i] = 0;
			}
			employeeList[i].setDeductions(employeeList[i].getDeductions() + deductions[i]);
			employeeList[i].setAnnNetSalary(employeeList[i].getAnnGrsSalary() - employeeList[i].getDeductions());
		}
		}
	}
	
}
