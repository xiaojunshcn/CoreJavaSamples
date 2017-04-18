package com.joe.gui.awt;

import java.awt.Color;
import java.awt.Event;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.io.DataInputStream;
import java.net.URL;

/**
 * 加载图片或文本内容到Frame中完成显示
 * 
 * AwtLoadFileContentToFrame类继承Frame类，生成一个带标题的窗口  
 * 
 * @author JoeXIAO
 *
 */
public class AwtLoadFileContentToFrame extends Frame {

	private static final long serialVersionUID = 1L;
	private MenuBar menuBar;
	private boolean drawImage = false;
	private DataInputStream dataInputStream;
	private int i = 0;
	private String line_str;
	private boolean first = true;
	private Font font;

	public AwtLoadFileContentToFrame() {
		// 生成一个菜单条
		menuBar = new MenuBar();
		setMenuBar(menuBar);
		// 为菜单条第一个菜单取名“display”
		Menu display = new Menu("display");
		menuBar.add(display);
		// 生成display菜单下的两个菜单项
		MenuItem beanty_display = new MenuItem("display beauty");
		MenuItem text_display = new MenuItem("display text");
		display.add(beanty_display);
		display.add(text_display);
		// 设置背景颜色和文本的字体
		setBackground(Color.white);
		font = new Font("System", Font.BOLD, 20);
		// 设置带有菜单的窗口的标题
		setTitle("sample:use URL get data");
		setSize(400, 300);
		// 显示窗口
		show();
	}

	// 处理窗口中的菜单事件
	@Override
	public boolean action(Event evt, Object what) {
		if (evt.target instanceof MenuItem) {
			String message = (String) what;
			if (message == "display beauty") {
				drawImage = true;
				doDrawImage();
			} else {
				drawImage = false;
				first = true;
				if (message == "display text") {
					doWrite("c:/tt.txt");
				}
			}
		}
		return true;
	}

	// 处理窗事件
	@Override
	public boolean handleEvent(Event evt) {
		switch (evt.id) {
		case Event.WINDOW_DESTROY: {
			dispose();
			System.exit(0);
		}
		default: {
			return super.handleEvent(evt);
		}

		}
	}

	public static void main(String[] args) {
		new AwtLoadFileContentToFrame();
	}

	public void paint(Graphics g) {
		if (drawImage) {
			try {
				//when in an intranet, this 2 lines are required
				System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
				System.setProperty("http.proxyPort", "8080");
				URL image_url = new URL(
						"https://www.ibm.com/developerworks/cn/web/wa-lo-json/fig001.jpg");
				Toolkit object_Toolkit = Toolkit.getDefaultToolkit();
				Image object_Image = object_Toolkit.getImage(image_url);
				g.setColor(Color.white);
				g.fillRect(0, 0, 300, 400);
				g.drawImage(object_Image, 40, 80, 160, 200, this);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (first) {
				first = false;
				g.setColor(Color.white);
				g.fillRect(0, 0, 400, 300);
				g.setFont(font);
			}
			if (line_str != null) {
				g.drawString(line_str, 10, i * 20);
				i++;
			}
		}
	}

	private void doDrawImage() {
		drawImage = true;
		repaint();
	}

	private void doWrite(String url_str) {
		try {
			//when in an intranet, this 2 lines are required
			System.setProperty("http.proxyHost", "proxy.jpn.hp.com");
			System.setProperty("http.proxyPort", "8080");
			// 用参数url_str生成一个绝对的URL，它指向本机上的一个文本文件
			URL url = new URL(url_str);
			dataInputStream = new DataInputStream(url.openStream());
			try {
				i = 1;
				line_str = dataInputStream.readLine();
				while (line_str != null) {
					paint(getGraphics());
					line_str = dataInputStream.readLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
