//
// Assignment 2
// Written by: Ziad Agha - 40312869 / Abderrahmane Bensassi-Nour - 40317017
//
public class FederalIncomeTax extends Deductions{

	@Override
	public void calculateTax(FileManager FM) {
		Employee[] employeeList = FM.getEmployees();
		
		
		for (Employee emp : employeeList) {
            if (emp != null) {
                double grossSalary = emp.getAnnGrsSalary();
                double fedTax = 0;

                if (grossSalary > 253414) {
                    fedTax += (grossSalary - 253414) * 0.33;
                    grossSalary = 253414;
                }
                if (grossSalary > 177882) {
                    fedTax += (grossSalary - 177882) * 0.29;
                    grossSalary = 177882;
                }
                if (grossSalary > 114750) {
                    fedTax += (grossSalary - 114750) * 0.26;
                    grossSalary = 114750;
                }
                if (grossSalary > 57375) {
                    fedTax += (grossSalary - 57375) * 0.205;
                    grossSalary = 57375;
                }
                if (grossSalary > 16129) {
                    fedTax += (grossSalary - 16129) * 0.15;
                }

                // Accumulate Federal Tax deduction
                emp.setDeductions(emp.getDeductions() + fedTax);
                emp.setAnnNetSalary(emp.getAnnGrsSalary() - emp.getDeductions());
		}
	}
	
	}
}
