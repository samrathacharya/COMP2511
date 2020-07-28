
public class ButtonWindows implements ButtonInterface {

	private String labelText = "";
	@Override
	public void setLabel(String labelText) {
		this.labelText = labelText;
		System.out.println("ButtonWin: Setting label " + labelText);
		
	}

	@Override
	public void click() {
		
		System.out.println("ButtonWin: clicking button with label " + this.labelText);
		
	}

	
	
}