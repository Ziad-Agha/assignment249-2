
public class QPIPPremium extends Deductions {

	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		double[] deductions = new double[employeeList.length];
		double qpip;

		for(int i = 0; i < employeeList.length; i++) {
			qpip = 0.00494*employeeList[i].getAnnGrsSalary();
			if(qpip>484.12) {
				deductions[i] = 484.12;
			}
			else {
				deductions[i] = qpip;
			}
			employeeList[i].setDeductions(deductions[i]);
		}
		
	}
	
}
