
public class Test {
	
//	Responsible for generating the necessary components
	public static void generateButtonCheckBox(GUIFactoryInterface guiFactory) {
		ButtonInterface b1 = guiFactory.getButton();
		b1.setLabel("Hello!");
		b1.click();
		
		CheckboxInterface ch1 = guiFactory.getCheckBox();
		ch1.setText("Select this if you prefer XYZ ");
		ch1.setStatus(true);
	
		System.out.println(ch1.getStatus());				
	}

	public static void main(String[] args) {
		
//		Create the factory object here and ask to generate components required
		GUIFactoryInterface factory = new GUIFactoryWindows();
		generateButtonCheckBox( factory );
	
		System.out.println(" -------------- ------------ ----------  ");
		
		factory = new GUIFactoryLinux();
		generateButtonCheckBox( factory );
	
		return;
	}

}
