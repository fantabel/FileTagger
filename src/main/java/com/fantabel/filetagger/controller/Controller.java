package com.fantabel.filetagger.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.fantabel.filetagger.view.TheScreen;

/**
 * Hello world!
 *
 */
public class Controller {
	
	private TheScreen mainFrame;
	
	private void mainScreenTurnOn() {
		mainFrame = TheScreen.createAndShowGUI();
		
		mainFrame.addBoutonGoActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		
	}
	
	private void listFiles(File parent) {
		File[] fileList = parent.listFiles();
		for (File f : fileList) {
			System.out.println(f.toPath().getFileName());
			if (f.isDirectory()) {
				listFiles(f);
			}
		}
	}
	
	public static void main(String[] args) {
		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
		
		}
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Controller c = new Controller();
				c.mainScreenTurnOn();
			}
		});
	}
}
