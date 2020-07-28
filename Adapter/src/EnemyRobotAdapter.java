
public class EnemyRobotAdapter implements EnemyAttacker{
	
	//Takes private reference to the robot and initialises it through a constructor
	//All the methods from the interface are implemented using the methods in the classs
	
	EnemyRobot theRobot;
	public EnemyRobotAdapter(EnemyRobot robot) {
		this.theRobot = robot;
	}

	public void fireWeapon() {
		theRobot.smash();
	}

	@Override
	public void drive() {
		// TODO Auto-generated method stub
		theRobot.walk();
	}

	@Override
	public void speak() {
		// TODO Auto-generated method stub
		theRobot.laugh();
	}
	

}
