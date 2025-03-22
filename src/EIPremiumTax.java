//
// Assignment 2
// Written by: Ziad Agha - 40312869 / Abderrahmane Bensassi-Nour - 40317017
//
public class EIPremiumTax extends Deductions{

	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		double[] deductions = new double[employeeList.length];
		double ei;
		int paliers = 0;
		for(int i = 0; i < employeeList.length; i++) {
			if(employeeList[i]!=null) {
			paliers = (int)employeeList[i].getAnnGrsSalary()/100;
			ei = paliers*1.64;
			if(ei>1077.48) {
				deductions[i] = 1077.48;
			}
			else {
				deductions[i] = ei;
			}
			employeeList[i].setDeductions(employeeList[i].getDeductions() + deductions[i]);
			employeeList[i].setAnnNetSalary(employeeList[i].getAnnGrsSalary() - employeeList[i].getDeductions());
			
		}
		}
	}
	
}
