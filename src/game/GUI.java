package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	public GUI() {
		buildWindow();
	}
	
	private JTextArea mainDisplay; // Game's output will go here
	private JTextField input; // Input box for player's commands
	
	public void print(Object obj) {
		mainDisplay.append(obj.toString()+"\n");
	}
	
	public void actionPerformed(ActionEvent e) {
		Game.processCommand(input.getText());
		input.setText("");
	}

	private void buildWindow() {
	    setTitle("Fairy Queens Dilemma");
	    setLayout(new BorderLayout());

	    // Correctly initialize instance variables
	    mainDisplay = new JTextArea();
	    JScrollPane scroll = new JScrollPane(mainDisplay); 
	    input = new JTextField();

	    JPanel spanel = new JPanel(new GridLayout(3, 1));
	    JLabel label = new JLabel("What would you like to do?");
	    JButton button = new JButton("Execute");
	    button.addActionListener(this);  // Attach listener correctly

	    // Add components to frame
	    add(scroll, BorderLayout.CENTER);
	    add(spanel, BorderLayout.SOUTH);

	    spanel.add(label);
	    spanel.add(input);
	    spanel.add(button);

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setSize(500, 500);
	    setLocationRelativeTo(null);  // Center the window
	    setVisible(true);  // Show the window
	}
}