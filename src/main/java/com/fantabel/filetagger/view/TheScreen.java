package com.fantabel.filetagger.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TheScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private JButton btnGo;
	
	public TheScreen() {
		super("FileTagger");
		init();
	}
	
	private void init() {
		this.getContentPane().setLayout(new BorderLayout());
		
		btnGo = new JButton("Go!");
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static TheScreen createAndShowGUI() {
		TheScreen frame = new TheScreen();
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addComponentsToPane();
		
		// Display the window.
		frame.pack();
		return frame;
		
	}
	
	private void addComponentsToPane() {
		this.getContentPane().add(btnGo);
		
	}
	
	public void addBoutonGoActionListener(ActionListener actionListener) {
		btnGo.addActionListener(actionListener);
		
	}
}
