package com.joe.gui.awt;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * 列出指定文件夹下的所有文件名
 * 
 */
public class AwtMyWindowDemo {
	private Frame f;
	private TextField tf;
	private Button but;
	private TextArea ta;
	
	//for dialog
	private Dialog d;
	private Label lab;
	private Button okBut;
	
	AwtMyWindowDemo(){
		init();
	}
	
	public void init() {
		f = new Frame("my window");
		f.setBounds(300,100,600,500);
		f.setLayout(new FlowLayout());
		
		tf = new TextField(60);
		
		but = new Button("转到");
		ta = new TextArea(25,70);
		
		//for dialog
		d = new Dialog(f,"提示信息-self", true);
		d.setBounds(400,200,240, 150);
		d.setLayout(new FlowLayout());
		lab = new Label();
		okBut = new Button("OK");
		d.add(lab);
		d.add(okBut);
		
		f.add(tf);
		f.add(but);
		f.add(ta);
		
		myEvent();
		f.setVisible(true);
		
	}
	
	
	private void myEvent() {
		//dialog event
		d.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				d.setVisible(false);
			}
		});
		
		okBut.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				d.setVisible(false);
			}
		});
		
		tf.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					showDir();
				}
			}
		});
		
		but.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				showDir();
			}
			
		});
		
		f.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	private void showDir() {
		String dirPath = tf.getText();
		
		File dir = new File(dirPath);
		
		if (dir.exists() && dir.isDirectory()) {
			ta.setText("");
			String[] names = dir.list();
			
			for (String name: names) {
				ta.append( name + "\r\n");
			}
		} else {
			lab.setText("unavailable path");
			//pop up a dialog
			d.setVisible(true);
		}
		//System.out.println(text);
		
		tf.setText("");
	}
	
	public static void main(String[] args) {
		AwtMyWindowDemo mwd = new AwtMyWindowDemo();
	}
}