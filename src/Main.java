import javax.swing.JFrame;

public class Main {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Shooter");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1024, 730);
		frame.add(new Scene(1024, 730));
		frame.setVisible(true);
	}

}
