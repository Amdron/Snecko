package everything;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Window window = Window.getWindow();
		Thread t1 = new Thread(window);
		t1.start();
	}

}
