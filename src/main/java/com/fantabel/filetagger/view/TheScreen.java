package com.fantabel.filetagger.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;

public class TheScreen extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private FileTree fileTree;
	private JButton btnGo;
	
	public TheScreen() {
		super("FileTagger");
		init();
	}
	
	private void init() {
		this.getContentPane().setLayout(new BorderLayout());
		
		btnGo = new JButton("Go!");
		
		fileTree = new FileTree(new File("./The.X-Files"));
		
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static TheScreen createAndShowGUI() {
		TheScreen frame = new TheScreen();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addComponentsToPane();
		
		// Display the window.
		frame.pack();
		// frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		return frame;
		
	}
	
	private void addComponentsToPane() {
		this.getContentPane().add(btnGo, BorderLayout.PAGE_END);
		this.getContentPane().add(fileTree, BorderLayout.LINE_START);
		
	}
	
	public void addBoutonGoActionListener(ActionListener actionListener) {
		btnGo.addActionListener(actionListener);
		
	}
}
