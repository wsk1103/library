package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class tray {
	JFrame frame=Student.frame;
	Font font2=new Font("����",Font.BOLD,20);
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	
	public void miniTray() { // ������С��������������

		ImageIcon trayImg = new ImageIcon("image/biaoti.gif");// ����ͼ��

		PopupMenu pop = new PopupMenu(); // ���������һ��˵�
		MenuItem show = new MenuItem("��ԭ");
		show.setFont(font2);
		MenuItem exit = new MenuItem("�˳�");
		exit.setFont(font2);

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // ���»�ԭ��

				tray.remove(trayIcon);
				frame.setVisible(true);
				frame.setExtendedState(JFrame.NORMAL);
				frame.toFront();
			}

		});

		exit.addActionListener(new ActionListener() { // �����˳���

			public void actionPerformed(ActionEvent e) {

				tray.remove(trayIcon);
				System.exit(0);

			}

		});

		pop.add(show);
		pop.add(exit);

		trayIcon = new TrayIcon(trayImg.getImage(), "����ͼ��軹����ϵͳ", pop);
		trayIcon.setImageAutoSize(true);

		trayIcon.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) { // �����˫���¼�

				if (e.getClickCount() == 2) {

					tray.remove(trayIcon); // ��ȥ����ͼ��
					frame.setVisible(true);
					frame.setExtendedState(JFrame.NORMAL); // ��ԭ����
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
