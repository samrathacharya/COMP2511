
public class GUIFactoryLinux implements GUIFactoryInterface {

	@Override
	public ButtonInterface getButton() {
		return new ButtonLinux();
	}

	@Override
	public CheckboxInterface getCheckBox() {
		// TODO Auto-generated method stub
		return new CheckBoxLinux();
	}


}
