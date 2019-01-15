package GUI;

import java.awt.*;

import javax.swing.*;

import java.awt.Toolkit;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import bean.StudentAcount;

public class Student {

	static JFrame frame;
	static int information=0;
	static int studentChangPassword=0;
	static int studentChangQuestion=0;
	public String num = Login.JTnum.getText();// ѧ�ŵ�ֵ
	String user;// �û�����ֵ
	String sex;// �Ա�
	static int count;// ʣ����Խ�Ĵ���
	Font font = new Font("����", Font.BOLD, 16);
	Font font2=new Font("����",Font.BOLD,20);
	static JLabel JTcount;
	JTabbedPane jtb;
	
	public Student() {
		initialize();// ��ͼ
	}

	private void initialize() {
		frame = new JFrame();
		ContentServer();// �������ݿ�
		frame.getContentPane().setLayout(null);

		// �˵���
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// �û���ť
		JMenu mmUser = new JMenu("�û�U");
		mmUser.setMnemonic('u');
		mmUser.setFont(font);
		menuBar.add(mmUser);
		// �޸�����
		JMenuItem mntmChangpassword = new JMenuItem("�޸�����");
		mntmChangpassword.setMnemonic('C');
		mntmChangpassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mntmChangpassword.setFont(font);
		mntmChangpassword.setIcon(new ImageIcon("menu/changpassword.gif"));
		mmUser.add(mntmChangpassword);
		mntmChangpassword.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Point p = frame.getLocation();
				int x = (int) p.getX();
				int y = (int) p.getY();
				new ChangPassword().show(x + 200, y + 170);

			}
		});
		// �޸��ܱ�����
		JMenuItem mntmChangQustion = new JMenuItem("�޸��ܱ�");
		mntmChangQustion.setMnemonic('d');
		mntmChangQustion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		mntmChangQustion.setFont(font);
		mntmChangQustion.setIcon(new ImageIcon("menu/changquestion.gif"));
		mmUser.add(mntmChangQustion);
		mntmChangQustion.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Point p = frame.getLocation();
				int x = (int) p.getX();
				int y = (int) p.getY();
				new ChangQuestion().show(x + 200, y + 160);
			}
		});
		// �л��û�
		JMenuItem mntmSwithuser = new JMenuItem("�л��û�");
		mntmSwithuser.setMnemonic('s');
		mntmSwithuser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mntmSwithuser.setFont(font);
		mntmSwithuser.setIcon(new ImageIcon("menu/swithuser.gif"));
		mmUser.add(mntmSwithuser);
		mntmSwithuser.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���л���","ȷ���л���",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				Login l = new Login();
				int x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
				int y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
				l.show(x, y);
				frame.dispose();
				if (information==1)
					BookInformation.frame.dispose();
				if (studentChangPassword==1)
					ChangPassword.frame.dispose();
				if (studentChangQuestion==1)
					ChangQuestion.frame.dispose();
				}
			}
		});
		// �˳�
		JMenuItem mntmExit = new JMenuItem("�˳�ϵͳ");
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mntmExit.setFont(font);
		mntmExit.setIcon(new ImageIcon("menu/exit.gif"));
		mmUser.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���˳���","ȷ���˳���",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		// ����
		JMenu mmHelp = new JMenu("����H");
		mmHelp.setMnemonic('h');
		mmHelp.setFont(font);
		menuBar.add(mmHelp);
		// ��ȡ����
		JMenuItem mntmGethelp = new JMenuItem("��ȡ����");
		mntmGethelp.setMnemonic('h');
		mntmGethelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGethelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mntmGethelp.setFont(font);
		mmHelp.add(mntmGethelp);
		mntmGethelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="���ʹ�ù��̳��������⣬��ӭ��ʱ������\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("����",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"����һ��˧�����ʾ",JOptionPane.PLAIN_MESSAGE);
			}
		});

		// ���ñ���
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		panel.setBounds(0, 0, 1000, 650);
		panel.setOpaque(true);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		// ���ֱ�ǩ�������
		// ����
		JLabel JLuser = new JLabel("����:");
		JLuser.setHorizontalAlignment(SwingConstants.RIGHT);
		JLuser.setBounds(49, 21, 54, 16);
		panel.add(JLuser);
		JLuser.setFont(font2);

		JLabel JTuser = new JLabel(user);
		JTuser.setBounds(113, 22, 98, 16);
		panel.add(JTuser);
		JTuser.setFont(font2);
		// ���
		JLabel JLid = new JLabel("���:");
		JLid.setHorizontalAlignment(SwingConstants.RIGHT);
		JLid.setBounds(49, 58, 54, 16);
		panel.add(JLid);
		JLid.setFont(font2);

		JLabel JTid = new JLabel("ѧ��");
		JTid.setBounds(113, 53, 114, 29);
		panel.add(JTid);
		JTid.setFont(font2);
		// ʣ��ɽ����
		JLabel JLcount = new JLabel("ʣ��ɽ����:");
		JLcount.setHorizontalAlignment(SwingConstants.RIGHT);
		JLcount.setBounds(237, 59, 201, 16);
		panel.add(JLcount);
		JLcount.setFont(font2);

		JTcount = new JLabel("" + count);
		JTcount.setBounds(446, 58, 107, 16);
		panel.add(JTcount);
		JTcount.setFont(font2);
		// ѧ��
		JLabel JLnum = new JLabel("ѧ��:");
		JLnum.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnum.setBounds(297, 21, 56, 16);
		panel.add(JLnum);
		JLnum.setFont(font2);

		JLabel JTnum = new JLabel(num);
		JTnum.setBounds(363, 22, 170, 16);
		panel.add(JTnum);
		JTnum.setFont(font2);

		// �Ա�
		JLabel JLsex = new JLabel("�Ա�:");
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setBounds(540, 22, 63, 16);
		panel.add(JLsex);
		JLsex.setFont(font2);

		JLabel JTsex = new JLabel(sex);
		JTsex.setBounds(610, 22, 86, 16);
		panel.add(JTsex);
		JTsex.setFont(font2);
		// JtabbedPane����
		UIManager.put("TabbedPane.contentOpaque", false);
		jtb = new JTabbedPane();
		jtb.setFont(new Font("����", Font.BOLD, 16));
		StudentOrder o = new StudentOrder();// ��ѯ����
		StudentBorrow b = new StudentBorrow();// ���黭��
		StudentReturn r = new StudentReturn();// ���黭��
		StudentAppointment app=new StudentAppointment();//ԤԼģ��
		jtb.setOpaque(false);// ����͸��
		jtb.add(o, "��ѯԤԼ");
		jtb.add(app,"ԤԼ���");
		jtb.add(b, "�������");
		jtb.add(r, "�������");
			
		jtb.setBounds(0, 84, 1000, 600);
		panel.add(jtb, BorderLayout.CENTER);

	}

	// ��ʾ��������ͼ������
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("ѧ����");
		frame.setBounds(x, y, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() { // ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // ������С���¼�

				frame.setVisible(false);
				tray t=new tray();
				t.miniTray();

			}

		});
		//initialize();
	}
	// �������ݿ⣬����ʼ��������ǩ��Ӧ��ֵ
	private void ContentServer() {
		try {
			Socket socket = new Socket(Login.ip, 8181);// ����˿�Ϊ8182
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());// ���ݵ�����˵���������ΪString
			//System.out.println(num);
			toServer.writeInt(2);
			toServer.writeUTF(num);// ��������
			toServer.writeInt(1);
			ObjectInputStream fromServer = new ObjectInputStream(
					socket.getInputStream());// �ӷ���˻�ȡ����
			ArrayList<StudentAcount> list = (ArrayList<StudentAcount>) fromServer
					.readObject();// ����ȡ�Ķ���ת��ΪArraylist
			StudentAcount s = new StudentAcount();// Javabean
			// ��ʼ����Ӧ��ֵ
			s = list.get(0);
			user = s.getNum();
			sex = s.getQuestion();
			count = s.getStats();

			socket.close();// �رյ�ǰ�˿�����
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
