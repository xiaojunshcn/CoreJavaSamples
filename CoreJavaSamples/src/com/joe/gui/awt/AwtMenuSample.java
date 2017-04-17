package com.joe.gui.awt;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class AwtMenuSample {
	private Frame f;
	private MenuBar menuBar;
	private TextArea ta;
	private Menu fileMenu;
	private MenuItem openItem, saveItem, closeItem;
	
	private FileDialog openDialog, saveDialog;
	
	private File file;
	AwtMenuSample() {
		init();
	}
	
	public void init() {
		f =new Frame("my window");
		f.setBounds(300,100,650,600);
		
		menuBar = new MenuBar();
		ta = new TextArea();
		
		fileMenu = new Menu("File");
		openItem = new MenuItem("Open");
		saveItem = new MenuItem("Save");
		closeItem = new MenuItem("Close");
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(closeItem);
		menuBar.add(fileMenu);
		
		f.setMenuBar(menuBar);
		
		openDialog = new FileDialog(f,"Open...", FileDialog.LOAD);
		saveDialog = new FileDialog(f,"Save...", FileDialog.SAVE);
		
		f.add(ta);
		
		myEvent();
		
		f.setVisible(true);
	}
	
	
	public void myEvent() {

		saveItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (file == null) {
					saveDialog.setVisible(true);
					
					String dirPath = openDialog.getDirectory();
					String fileName = openDialog.getFile();
					
					if ((dirPath == null) || (fileName == null))
						return;
					
					file = new File(dirPath, fileName);
				}
				
				try{
					BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
					
					String text = ta.getText();
					
					bufw.write(text);
					bufw.flush();
					
					bufw.close();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				}
			}
			
		});
		
		openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				openDialog.setVisible(true);
				
				String dirPath = openDialog.getDirectory();
				String fileName = openDialog.getFile();
				//System.out.println(dirPath + "," + fileName);
				
				if ((dirPath == null) || (fileName == null))
					return;
				
				ta.setText("");
				file = new File(dirPath, fileName);
				
				try{
					BufferedReader bufr = new BufferedReader(new FileReader(file));
					
					String line = null;
					
					while((line = bufr.readLine()) != null) {
						ta.append(line + "\r\n");
					}
					bufr.close();
				} catch(IOException ioe) {
					ioe.printStackTrace();
				} 
			}
			
		});
		
		closeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public static void main(String[] args) {
		AwtMenuSample menu = new AwtMenuSample();
	}
}