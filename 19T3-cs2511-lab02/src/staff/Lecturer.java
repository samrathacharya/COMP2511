package staff;

import java.util.Date;

import com.sun.xml.internal.bind.v2.runtime.Name;

/**
 * A lecturer
 * @author z5161678
 *
 */
public class Lecturer extends StaffMember{
	String school;
	char academicStatus;	//Change to enum
	
	
	//Constructor
	/**
	 * Creates a new lecturer with their name, salaray, hiring date,
	 * end of contract date, school and academic status
	 * @param name	The name of the lecturer
	 * @param salary The salary of the lecturer
	 * @param hireDate The hiring date of the lecturer
	 * @param endDate The date that the contract for the lecturer ends
	 * @param school The school the lecturer works at
	 * @param academicStatus The academic status for the lecturer A for an Associate Lecturer, B  for a Lecturer, and C for a Senior Lecturer)
	 */
	public Lecturer(String name, float salary, Date hireDate, Date endDate, 
			String school, char academicStatus) {
		super(name, salary, hireDate, endDate);
		this.school = school;
		this.academicStatus = academicStatus;
	}
	
	//toString
	@Override
	public String toString() {
		String toRetString =  "*Lecturer | School: "+school+" | Academic Status: "+academicStatus;
		return toRetString;
	}
		
	//equals
	@Override
	public boolean equals(Object obj) {
		// Check to see if it passes the super equals
		if (super.equals(obj) == false) {
			return false;
		}
		
		//Cast down and check for the new variables
		Lecturer lecturer = (Lecturer) obj;
		if (this.academicStatus == lecturer.academicStatus && this.school == lecturer.school) {
			return true;
		}
		
		return false;
	}
	
	//Getters
	/**
	 * Gets the school for the lecturer
	 * @return The school that the lecturer belongs to
	 */
	public String getSchool() {
		return school;
	}
	
	/**
	 * Gets the academic status of the lecturer
	 * @return Returns the academic status of the lecturer
	 */
	public float getAcademicStatus() {
		return academicStatus;
	}
	
	//Setters
	/**
	 * Sets the school for this lecturer
	 * @param school The school that the lecturer belongs to
	 */
	public void setSchool (String school) {
		this.school = school;
	}
	
	/**
	 * Sets the academicstatus for the lecturer
	 * @param academicStatus The academic status of the lecturer
	 */
	public void setAcademicStatus (char academicStatus) {
		this.academicStatus = academicStatus;
	}

}
