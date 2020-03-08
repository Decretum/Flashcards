import javax.swing.*;

import java.awt.*;
import java.io.*;

public class CreateCards {

	static PrintWriter writer;
	JTextField[] textFields;
	JTextField deckNameField;
	int numCards;

	public CreateCards() {
		JFrame frame = new JFrame("Flash Card Setup");
		frame.setSize(300, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		numCards = Integer.parseInt(JOptionPane.showInputDialog(frame, "Number of cards:", "0"));

		JPanel panel = new JPanel(new GridLayout(numCards + 3, 2, 1, 1));
		JScrollPane scrollPane = new JScrollPane(panel);
		panel.add(new JLabel("Name of Deck:"));
		deckNameField = new JTextField(10);
		panel.add(deckNameField);
		panel.add(new JLabel("Front:", SwingConstants.CENTER));
		panel.add(new JLabel("Back:", SwingConstants.CENTER));
		textFields = new JTextField[2 * numCards];
		for (int x = 0; x < textFields.length; x++) {
			textFields[x] = new JTextField(10);
			panel.add(textFields[x]);
		}
		JButton create = new JButton("Create!");
		create.addActionListener(ae -> {
			try {
				writer = new PrintWriter(deckNameField.getText() + ".txt");
			} catch (IOException e) {
				e.printStackTrace();
			}
			writer.println(numCards);
			for (JTextField jTextField : textFields) {
				writer.println(jTextField.getText());
			}
			writer.flush();
			JOptionPane.showMessageDialog(scrollPane, "Successfully created " + deckNameField.getText() + ".txt");
		});
		panel.add(create);
		JButton close = new JButton("Close");
		close.addActionListener(ae -> System.exit(0));
		panel.add(close);
		frame.add(scrollPane);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(CreateCards::new);
	}
}
