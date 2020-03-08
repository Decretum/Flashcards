import javax.swing.*;
import java.awt.*;

public class Flashcards {

	JFrame frame;

	public Flashcards() {
		frame = new JFrame("Flashcards");
		frame.setLayout(new GridLayout(3, 5, 1, 1));
		frame.setSize(700, 250);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		for (int x = 0; x < 6; x++) {
			frame.add(new JLabel());
		}
		JButton create = new JButton("Create Card Set");
		JButton load = new JButton("Load Card Set");
		create.addActionListener(ae -> CreateCards.main(null));
		load.addActionListener(ae -> {
			frame.setVisible(false);
			ViewCards.main(null);
		});
		frame.add(create);
		frame.add(new JLabel());
		frame.add(load);
		for (int x = 0; x < 6; x++) {
			frame.add(new JLabel());
		}
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(Flashcards::new);
	}
}
