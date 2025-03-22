//
// Assignment 2
// Written by: Ziad Agha - 40312869 / Abderrahmane Bensassi-Nour - 40317017
//
public class QPPPremium extends Deductions{

	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		double[] deductions = new double[employeeList.length];
		double qpp;
		
		for(int i = 0; i < employeeList.length; i++) {
			if(employeeList[i]!=null) {
			qpp = employeeList[i].getAnnGrsSalary()*0.108;
			if(qpp>7700.40) {
				deductions[i] = 7700.40;
			}
			else {
				deductions[i] = qpp;
			}
			employeeList[i].setDeductions(employeeList[i].getDeductions() + deductions[i]);
			employeeList[i].setAnnNetSalary(employeeList[i].getAnnGrsSalary() - employeeList[i].getDeductions());
		}
		}
	}
	
}
