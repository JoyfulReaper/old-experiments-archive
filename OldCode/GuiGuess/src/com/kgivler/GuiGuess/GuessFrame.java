package com.kgivler.GuiGuess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuessFrame extends JFrame {
	private JLabel guessLabel;
	private JLabel resultLabel;
	private JTextField guessField;
	private JButton button;
	private JPanel panel;
	private Guess guess;
	private ActionListener listener;

	public GuessFrame()
	{
		listener = new MakeGuessListener();
		guess = new Guess();
		guess.init();

		resultLabel = new JLabel("result:         ");

		createTextField();
		createButton();
		createPanel();
	}

	private void createTextField()
	{
		guessLabel = new JLabel("Guess: ");
		final int FIELD_WIDTH = 5;
		guessField = new JTextField(FIELD_WIDTH);
		guessField.setText("");
		guessField.addActionListener(listener);
	}

	private void createButton()
	{
		button = new JButton("Make Guess");
		button.addActionListener(listener);
	}

	private void createPanel()
	{
		panel = new JPanel();
		panel.add(guessLabel);
		panel.add(guessField);
		panel.add(button);
		panel.add(resultLabel);
		add(panel);
		pack();
	}

	class MakeGuessListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			int myGuess = Integer.parseInt(guessField.getText());
			if (myGuess > guess.getTargetNumber())
				resultLabel.setText("Too High!");
			if (myGuess < guess.getTargetNumber())
				resultLabel.setText("Too Low!");
			if(myGuess == guess.getTargetNumber())
			{
				resultLabel.setText("You Win!");
				guess.init();
			}

			guessField.setText("");
		}
	}
}
