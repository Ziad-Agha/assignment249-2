//
// Assignment 2
// Written by: Ziad Agha - 40312869 / Abderrahmane Bensassi-Nour - 40317017
//
public class ProvincialIncomeTax extends Deductions{
	
	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		
		for (Employee emp : employeeList) {
            if (emp != null) {
                double grossSalary = emp.getAnnGrsSalary();
                double provTax = 0;

                if (grossSalary > 129590) {
                    provTax += (grossSalary - 129590) * 0.2575;
                    grossSalary = 129590;
                }
                if (grossSalary > 106495) {
                    provTax += (grossSalary - 106495) * 0.24;
                    grossSalary = 106495;
                }
                if (grossSalary > 53255) {
                    provTax += (grossSalary - 53255) * 0.19;
                    grossSalary = 53255;
                }
                if (grossSalary > 18571) {
                    provTax += (grossSalary - 18571) * 0.14;
                }

                // Accumulate Provincial Tax deduction
                emp.setDeductions(emp.getDeductions() + provTax);
                emp.setAnnNetSalary(emp.getAnnGrsSalary() - emp.getDeductions());
            }
        }
		
	}

	
}
