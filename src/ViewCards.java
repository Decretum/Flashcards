import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ViewCards {

	public static final String NEXT_PNG = "next.png";
	public static final String PREVIOUS_PNG = "previous.png";
	public static final String PROJECT_DIRECTORY = "user.dir";
	JFileChooser fileChooser;
	JButton middleButton;
	JButton cardButton;
	List<Card> deck;
	FileReader fileReader;
	Scanner scanner;
	int index = -1;

	public ViewCards() {
		fileChooser = new JFileChooser();
		File workingDirectory = new File(System.getProperty(PROJECT_DIRECTORY));
		fileChooser.setCurrentDirectory(workingDirectory);
		fileChooser.setFileFilter(new JavaFileFilter());
		JFrame frame = new JFrame("Flash Cards");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		middleButton = new JButton("Please select a deck");
		middleButton.addActionListener(ae -> {
			if (deck == null) {
				chooseCardSet();
			} else {
				flip();
			}
		});
		JButton next = new JButton(new ImageIcon(NEXT_PNG));
		next.addActionListener(ae -> nextCard());
		JButton previous = new JButton(new ImageIcon(PREVIOUS_PNG));
		previous.addActionListener(ae -> previousCard());
		cardButton = new JButton("O++O");
		JButton choose = new JButton("Deck Selection");
		choose.addActionListener(ae -> chooseCardSet());
		frame.add(middleButton);
		frame.add(next, BorderLayout.EAST);
		frame.add(previous, BorderLayout.WEST);
		frame.add(choose, BorderLayout.NORTH);
		frame.add(cardButton, BorderLayout.SOUTH);

		KeyListener hotkeyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// nope
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// nope
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == 32) {
					// space
					if (e.getSource() != middleButton) {
						// Apparently pressing space on the middle button also triggers the "you clicked on the middle button" event
						// So we don't want to flip twice in this case, as that does nothing.
						flip();
					}
				} else if (e.getKeyCode() == 37) {
					// left
					previousCard();
				} else if (e.getKeyCode() == 39) {
					// right
					nextCard();
				}
			}
		};

		middleButton.addKeyListener(hotkeyListener);
		cardButton.addKeyListener(hotkeyListener);

		frame.setVisible(true);
	}

	private void chooseCardSet() {
		int result = fileChooser.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				fileReader = new FileReader(fileChooser.getSelectedFile().getName());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			scanner = new Scanner(fileReader);
			int numCards = scanner.nextInt();
			scanner.nextLine();
			deck = new ArrayList<>(numCards);
			for (int x = 0; x < numCards; x++) {
				Card card = new Card();
				card.setFront(scanner.nextLine());
				card.setBack(scanner.nextLine());
				deck.add(card);
			}
			Collections.shuffle(deck);
		}
		index = 0;
		middleButton.setText(deck.get(index).getText());
		cardButton.setText((index + 1) + "/" + deck.size());
	}

	private void previousCard() {
		if (index != -1) {
			if (index == 0) {
				index = deck.size() - 1;
			} else {
				index--;
			}
			middleButton.setText(deck.get(index).getText());
			cardButton.setText((index + 1) + "/" + deck.size());
		}
	}

	private void nextCard() {
		if (index != -1) {
			if (index == deck.size() - 1) {
				index = 0;
			} else {
				index++;
			}
			middleButton.setText(deck.get(index).getText());
			cardButton.setText((index + 1) + "/" + deck.size());
		}
	}

	private void flip() {
		if (index != -1) {
			middleButton.setText(deck.get(index).flipAndGetText());
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(ViewCards::new);
	}
}
