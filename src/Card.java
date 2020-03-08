
public class Card {

	public static final String PREFIX = "<html>";
	String front;
	String back;
	boolean isFront;

	public Card() {
		front = PREFIX;
		back = PREFIX;
		isFront = true;
	}

	public Card(String a, String b) {
		front = PREFIX + a;
		back = PREFIX + b;
		isFront = true;
	}

	public void setFront(String a) {
		front = PREFIX + a;
	}

	public void setBack(String b) {
		back = PREFIX + b;
	}

	private String getFront() {
		return front;
	}

	private String getBack() {
		return back;
	}

	public String getText() {
		if (isFront) {
			return getFront();
		} else {
			return getBack();
		}
	}

	public String flipAndGetText() {
		isFront = !isFront;
		return getText();
	}

	public void display() {
		// For testing the code, mostly
		System.out.println(front);
		System.out.println(back);
	}

	public static void main(String[] args) {
		Card george = new Card("George Zhang", "The guy who made this program.");
		george.display();
	}
}
