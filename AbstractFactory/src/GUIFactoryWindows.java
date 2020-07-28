
public class GUIFactoryWindows  implements GUIFactoryInterface{

	@Override
	public ButtonInterface getButton() {
		return new ButtonWindows();
	}

	@Override
	public CheckboxInterface getCheckBox() {
		// TODO Auto-generated method stub
		return new CheckBoxWindows();
	}
}
