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
	public String num = Login.JTnum.getText();// 学号的值
	String user;// 用户名的值
	String sex;// 性别
	static int count;// 剩余可以借的次数
	Font font = new Font("楷体", Font.BOLD, 16);
	Font font2=new Font("楷体",Font.BOLD,20);
	static JLabel JTcount;
	JTabbedPane jtb;
	
	public Student() {
		initialize();// 画图
	}

	private void initialize() {
		frame = new JFrame();
		ContentServer();// 连接数据库
		frame.getContentPane().setLayout(null);

		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		// 用户按钮
		JMenu mmUser = new JMenu("用户U");
		mmUser.setMnemonic('u');
		mmUser.setFont(font);
		menuBar.add(mmUser);
		// 修改密码
		JMenuItem mntmChangpassword = new JMenuItem("修改密码");
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
		// 修改密保问题
		JMenuItem mntmChangQustion = new JMenuItem("修改密保");
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
		// 切换用户
		JMenuItem mntmSwithuser = new JMenuItem("切换用户");
		mntmSwithuser.setMnemonic('s');
		mntmSwithuser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		mntmSwithuser.setFont(font);
		mntmSwithuser.setIcon(new ImageIcon("menu/swithuser.gif"));
		mmUser.add(mntmSwithuser);
		mntmSwithuser.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "确定切换？","确定切换？",JOptionPane.YES_NO_OPTION);
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
		// 退出
		JMenuItem mntmExit = new JMenuItem("退出系统");
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mntmExit.setFont(font);
		mntmExit.setIcon(new ImageIcon("menu/exit.gif"));
		mmUser.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "确定退出？","确定退出？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		// 帮助
		JMenu mmHelp = new JMenu("帮助H");
		mmHelp.setMnemonic('h');
		mmHelp.setFont(font);
		menuBar.add(mmHelp);
		// 获取帮助
		JMenuItem mntmGethelp = new JMenuItem("获取帮助");
		mntmGethelp.setMnemonic('h');
		mntmGethelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGethelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mntmGethelp.setFont(font);
		mmHelp.add(mntmGethelp);
		mntmGethelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="如果使用过程出现了问题，欢迎随时反馈。\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("楷体",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"来自一个帅哥的提示",JOptionPane.PLAIN_MESSAGE);
			}
		});

		// 设置背景
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

		// 各种标签和其监听
		// 姓名
		JLabel JLuser = new JLabel("姓名:");
		JLuser.setHorizontalAlignment(SwingConstants.RIGHT);
		JLuser.setBounds(49, 21, 54, 16);
		panel.add(JLuser);
		JLuser.setFont(font2);

		JLabel JTuser = new JLabel(user);
		JTuser.setBounds(113, 22, 98, 16);
		panel.add(JTuser);
		JTuser.setFont(font2);
		// 身份
		JLabel JLid = new JLabel("身份:");
		JLid.setHorizontalAlignment(SwingConstants.RIGHT);
		JLid.setBounds(49, 58, 54, 16);
		panel.add(JLid);
		JLid.setFont(font2);

		JLabel JTid = new JLabel("学生");
		JTid.setBounds(113, 53, 114, 29);
		panel.add(JTid);
		JTid.setFont(font2);
		// 剩余可借次数
		JLabel JLcount = new JLabel("剩余可借次数:");
		JLcount.setHorizontalAlignment(SwingConstants.RIGHT);
		JLcount.setBounds(237, 59, 201, 16);
		panel.add(JLcount);
		JLcount.setFont(font2);

		JTcount = new JLabel("" + count);
		JTcount.setBounds(446, 58, 107, 16);
		panel.add(JTcount);
		JTcount.setFont(font2);
		// 学号
		JLabel JLnum = new JLabel("学号:");
		JLnum.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnum.setBounds(297, 21, 56, 16);
		panel.add(JLnum);
		JLnum.setFont(font2);

		JLabel JTnum = new JLabel(num);
		JTnum.setBounds(363, 22, 170, 16);
		panel.add(JTnum);
		JTnum.setFont(font2);

		// 性别
		JLabel JLsex = new JLabel("性别:");
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setBounds(540, 22, 63, 16);
		panel.add(JLsex);
		JLsex.setFont(font2);

		JLabel JTsex = new JLabel(sex);
		JTsex.setBounds(610, 22, 86, 16);
		panel.add(JTsex);
		JTsex.setFont(font2);
		// JtabbedPane布局
		UIManager.put("TabbedPane.contentOpaque", false);
		jtb = new JTabbedPane();
		jtb.setFont(new Font("楷体", Font.BOLD, 16));
		StudentOrder o = new StudentOrder();// 查询画板
		StudentBorrow b = new StudentBorrow();// 还书画板
		StudentReturn r = new StudentReturn();// 借书画板
		StudentAppointment app=new StudentAppointment();//预约模块
		jtb.setOpaque(false);// 设置透明
		jtb.add(o, "查询预约");
		jtb.add(app,"预约情况");
		jtb.add(b, "借书情况");
		jtb.add(r, "还书情况");
			
		jtb.setBounds(0, 84, 1000, 600);
		panel.add(jtb, BorderLayout.CENTER);

	}

	// 显示并且设置图标和鼠标
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("学生端");
		frame.setBounds(x, y, 1000, 650);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() { // 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // 窗口最小化事件

				frame.setVisible(false);
				tray t=new tray();
				t.miniTray();

			}

		});
		//initialize();
	}
	// 连接数据库，并初始化各个标签对应的值
	private void ContentServer() {
		try {
			Socket socket = new Socket(Login.ip, 8181);// 网络端口为8182
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());// 传递到服务端的数据类型为String
			//System.out.println(num);
			toServer.writeInt(2);
			toServer.writeUTF(num);// 传递数据
			toServer.writeInt(1);
			ObjectInputStream fromServer = new ObjectInputStream(
					socket.getInputStream());// 从服务端获取对象
			ArrayList<StudentAcount> list = (ArrayList<StudentAcount>) fromServer
					.readObject();// 将获取的对象转型为Arraylist
			StudentAcount s = new StudentAcount();// Javabean
			// 初始化对应的值
			s = list.get(0);
			user = s.getNum();
			sex = s.getQuestion();
			count = s.getStats();

			socket.close();// 关闭当前端口连接
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
