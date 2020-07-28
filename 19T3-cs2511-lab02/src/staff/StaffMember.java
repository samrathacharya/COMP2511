package staff;
import java.util.Date;

/**
 * A staff member
 * @author Robert Clifton-Everest
 *
 */

//TODO: Javadocs for all non-overriding methods and constructors
public class StaffMember {
	private String name;
	private float salary;
	private Date hireDate;
	private Date endDate;
	
//	Constructor
	/**
	 * Creates a new staff member with their name, salaray, hire date and the end date of
	 * their contract
	 * @param name This staff's name
	 * @param salary Staff's salary
	 * @param hireDate Starting date of the contract for the staff member
	 * @param endDate Ending date of the contract for the staff member
	 */
	public StaffMember(String name, float salary, Date hireDate, Date endDate) {
		super();
		this.name = name;
		this.salary = salary;
		this.hireDate = hireDate;
		this.endDate = endDate;
	}
	
	//toString
	@Override
	public String toString() {
		//Use the getClass() so you know which instance is being used.
		String toRetString = "*Staff | Name: "+name+" | "+" Salary: "+salary+" | Hired on: "
				+hireDate+" | Fired on: "+endDate;
		return toRetString;
	}
	
	//equals
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj == null) {return false; }
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		
		//Cast down to relevant object
		StaffMember other = (StaffMember) obj;
		//Check for values in StaffMember class
		
		//For class suck as String and Date, use .equals
		if (this.name == other.name && this.salary == other.salary 
				&& this.hireDate == other.hireDate && this.endDate == other.endDate) {
			return true;
		}
		
		return false;
		
	}
	
//	Getters
	/**
	 * Gets the name of the staff member
	 * @return this staff member's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the salary for the staff member
	 * @return This staff member's salary
	 */
	public float getSalary() {
		return salary;
	}
	
	/**
	 * Gets the end date of the contract for the staff member
	 * @return This staff member's contract end date
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * Gets the hire date of the contract for the staff member
	 * @return This staff member's contract hire date
	 */
	public Date getHireDate() {
		return hireDate;
	}
	
	
//  Setters
	/**
	 * Changes the name of the staff member
	 * @param name The staff member's name
	 */
	public void setName (String name) {
		this.name = name;
	}
	
	/**
	 * Changes the salary of the staff member
	 * @param salary The staff member's salary
	 */
	public void setSalary (float salary) {
		this.salary = salary;
	}
	
	/**
	 * Changes the hire date for the staff member
	 * @param newDate The staff member's hire date
	 */
	public void setHireDate (Date newDate) {
		this.hireDate = newDate;
	}
	
	/**
	 * Changes the end date for the staff member
	 * @param newDate The staff member's contract end date
	 */
	public void setEndDate (Date newDate) {
		this.endDate = endDate;
	}
	
}
