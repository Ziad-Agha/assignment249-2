public class ProvincialIncomeTax extends Deductions{
	
	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		
		double[] deductions = new double[employeeList.length];
		
		for(int i = 0; i < employeeList.length; i++) {
			if(employeeList[i]!=null) {
			if(employeeList[i].getAnnGrsSalary() > 129590) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.2575;
			}
			else if(employeeList[i].getAnnGrsSalary() > 106495) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.24;
			}
			else if(employeeList[i].getAnnGrsSalary() > 53255) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.19;
			}
			else if(employeeList[i].getAnnGrsSalary() > 18571) {
				deductions[i] = employeeList[i].getAnnGrsSalary()*0.14;
			}
			else {
				deductions[i] = 0;
			}
			employeeList[i].setDeductions(employeeList[i].getDeductions() + deductions[i]);
			employeeList[i].setAnnNetSalary(employeeList[i].getAnnGrsSalary() - employeeList[i].getDeductions());		}
		}
		
	}

	
}
