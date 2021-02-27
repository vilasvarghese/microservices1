package first;

import org.springframework.stereotype.Component;

@Component
public class Employee {
	private int empId = 1;
	private String name = "Vilas";
	private int salary = 1000;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	public void print () {
		System.out.println("EmpId "+empId +", empName "+name+", sal "+ salary);
	}

}
