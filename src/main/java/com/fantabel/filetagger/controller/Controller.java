package com.fantabel.filetagger.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import com.fantabel.filetagger.view.TheScreen;

/**
 * Hello world!
 *
 */
public class Controller {
	
	private TheScreen mainFrame;
	
	private void createAndShowGui() {
		mainFrame = TheScreen.createAndShowGUI();
		
		mainFrame.addBoutonGoActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				File f = new File("xfiles.txt");
				try {
					List<String> list = Files.readAllLines(f.toPath(),
		                    Charset.defaultCharset());
					for (String s : list) {
						File tempFile = new File(s.replace("./", "./test/"));
						File parentFolder = tempFile.getParentFile();
						if (!parentFolder.exists()) {
							parentFolder.mkdirs();
						}
						System.out.println(tempFile.getParentFile() + " "
		                        + tempFile.toPath().toAbsolutePath() + " "
		                        + tempFile.exists());
						tempFile.createNewFile();
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		mainFrame.setVisible(true);
		
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
			public void run() {
				Controller c = new Controller();
				c.createAndShowGui();
			}
		});
	}
}
