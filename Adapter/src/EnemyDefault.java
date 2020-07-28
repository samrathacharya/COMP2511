
public class EnemyDefault implements EnemyAttacker{
	//Default class that has the same methods as the interface
	
	@Override
	public void fireWeapon() {
		System.out.println("Fired weapons");
		
	}

	@Override
	public void drive() {
		System.out.println("Drove");
		
	}

	@Override
	public void speak() {
		System.out.println("Spoke");
		
	}

}
