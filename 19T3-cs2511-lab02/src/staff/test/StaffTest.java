package staff.test;
import staff.StaffMember;
import staff.Lecturer;
import java.util.Date;

public class StaffTest {

	public static void printStaffDetails(StaffMember newStaff) {
		System.out.println(newStaff.toString());
	}
	
	//Main
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Staff Member
		Date hireRobDate = new Date(10000);
		Date fireRobDate = new Date();
		StaffMember rob = new StaffMember("Rob", 10000, hireRobDate, fireRobDate);
		//toString
		printStaffDetails(rob);
		//Equals
		StaffMember same = new StaffMember("Rob", 10000, hireRobDate, fireRobDate);
		StaffMember notSame = new StaffMember("John", 10000, hireRobDate, fireRobDate);
		System.out.println("Same: "+rob.equals(same));
		System.out.println("Not same: "+rob.equals(notSame));
		
		//Lecturer
		Date hireDate = new Date(10000);
		Date fireDate = new Date();
		Lecturer samLecturer = new Lecturer("Sam", 20000, hireDate, fireDate, "UNSW", 'B');
		//toString
		printStaffDetails(samLecturer);
		//Equals
		Lecturer sameLecturer = new Lecturer("Sam", 20000, hireDate, fireDate, "UNSW", 'B');
		Lecturer notSameLecturer = new Lecturer("Sam", 20000, hireDate, fireDate, "UNSW", 'C');
		System.out.println("Same: "+samLecturer.equals(sameLecturer));
		System.out.println("Not same: "+samLecturer.equals(notSameLecturer));
		
	}
}
