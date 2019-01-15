package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class tray {
	JFrame frame=Student.frame;
	Font font2=new Font("楷体",Font.BOLD,20);
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	
	public void miniTray() { // 窗口最小化到任务栏托盘

		ImageIcon trayImg = new ImageIcon("image/biaoti.gif");// 托盘图标

		PopupMenu pop = new PopupMenu(); // 增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		show.setFont(font2);
		MenuItem exit = new MenuItem("退出");
		exit.setFont(font2);

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // 按下还原键

				tray.remove(trayIcon);
				frame.setVisible(true);
				frame.setExtendedState(JFrame.NORMAL);
				frame.toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // 按下退出键

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "网上图书借还管理系统", pop);
		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // 鼠标器双击事件

				if (e.getClickCount() == 2) {

					tray.remove(trayIcon); // 移去托盘图标
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL); // 还原窗口
					frame.toFront();
				}

			}

		});

		try {

			tray.add(trayIcon);

		} catch (AWTException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
