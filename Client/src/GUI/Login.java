package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.swing.*;

public class Login extends JFrame {
	
	private static final long serialVersionUID = 1L;
	static JTextField JTnum;// 学号的值
	private JPasswordField JTpassword;// 密码
	private JTextField JTyan;// 验证码
	private boolean ok = false;
	public static JComboBox JTid;
//	public static String ip="10.94.71.25";//锐捷
	public static String ip="localhost";//本地
	private JLabel Yan;
	ImageIcon i;
	Font font;
	Font font2;
	String Syan;
	JPanel panel;
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;

	public static void main(String[] args) {
		new Login().show(400, 150);
	}

	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);

		font = new Font("楷体", Font.BOLD, 20);
		font2 = new Font("楷体", Font.BOLD, 15);

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			// 画图，背景图片，鼠标图案，标题图案
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
				setIconImage(biaoti);
				Image mouse = new ImageIcon("image/28.gif").getImage();
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,new Point(), null));
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};

		// 制作菜单栏
		JMenuBar JMBuser;
		JMBuser = new JMenuBar();
		setJMenuBar(JMBuser);
		// 用户
		JMenu JMuser = new JMenu("用户U");
		JMuser.setFont(font2);
		JMBuser.add(JMuser);
		JMuser.setMnemonic('U');
		// 帮助
		JMenu JMhelp = new JMenu("帮助H");
		JMhelp.setFont(font2);
		JMBuser.add(JMhelp);
		JMhelp.setMnemonic('H');

		// 忘记密码
		JMenuItem JMIForgetPassword = new JMenuItem("忘记密码");
		JMIForgetPassword.setFont(font2);
		JMuser.add(JMIForgetPassword);
		JMIForgetPassword.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				Point p = getLocation();
//				int x = (int) p.getX();
//				int y = (int) p.getY();
//				ForgetLoading f = new ForgetLoading();
//				f.show(x + 20, y + 20);
				try {
					//跳转到网页，可以用这个来实现网页忘记密码
					URI url = new URI("http://localhost:8080/ClientWeb/forgetPwd_1.jsp");
					Desktop des=Desktop.getDesktop();
					des.browse(url);
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		JMIForgetPassword.setIcon(new ImageIcon("menu/forget.gif"));
		JMIForgetPassword.setMnemonic('F');
		JMIForgetPassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.CTRL_MASK));
		// 退出
		JMenuItem JMIexit = new JMenuItem("退出系统");
		JMIexit.setFont(font2);
		JMuser.add(JMIexit);
		JMIexit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "确定退出？","确定退出？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		JMIexit.setIcon(new ImageIcon("menu/exit.gif"));
		JMIexit.setMnemonic('e');
		JMIexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		// 获取帮助
		JMenuItem JMIhelp = new JMenuItem("获取帮助");
		JMIhelp.setFont(font2);
		JMhelp.add(JMIhelp);
		JMIhelp.setIcon(new ImageIcon("menu/help.gif"));
		JMIhelp.setMnemonic('G');
		JMIhelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.CTRL_MASK));
		JMIhelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="如果使用过程出现了问题，欢迎随时反馈。\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("楷体",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"来自一个帅哥的提示",JOptionPane.PLAIN_MESSAGE);
			}
		});

		panel.setLayout(null);
		// 学号
		JLabel JLnum = new JLabel("\u5B66\u53F7/\u5DE5\u53F7:");
		JLnum.setFont(font);
		JLnum.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnum.setBounds(80, 57, 120, 34);
		panel.add(JLnum);

		JTnum = new JTextField();
		JTnum.addKeyListener(null);
		JTnum.setBounds(210, 60, 145, 28);
		JTnum.setFont(font);
		panel.add(JTnum);
		JTnum.setColumns(10);
		// 身份选择
		String[] s = { "学生", "管理员","超级管理员" };
		JTid = new JComboBox(s);
		JTid.setBackground(Color.white);
		JTid.setFont(new Font("楷体", Font.BOLD, 15));
		JTid.setBounds(360, 60, 109, 28);
		panel.add(JTid);
		// 密码
		JLabel JLpassword = new JLabel("密    码:");
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setFont(font);
		JLpassword.setBounds(80, 124, 120, 34);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setBounds(210, 126, 259, 28);

		JTpassword.setFont(new Font("Serif", Font.BOLD, 22));
		panel.add(JTpassword);
		// 验证码
		JLabel JLyan = new JLabel("验 证 码:");
		JLyan.setFont(font);
		JLyan.setHorizontalAlignment(SwingConstants.RIGHT);
		JLyan.setBounds(80, 185, 117, 34);
		panel.add(JLyan);

		JTyan = new JTextField();
		JTyan.setFont(new Font("楷体", Font.BOLD, 24));
		JTyan.setBounds(210, 186, 136, 28);
		panel.add(JTyan);
		JTyan.setColumns(10);
		// 更换验证码

		try {
			yan y = new yan();// 调用函数并且生成验证码
			Syan = y.s.toLowerCase();
			// cc=y.cc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i = new ImageIcon("Random/" + Syan + ".jpg");// 获取验证码图片

		Yan = new JLabel(i);
		Yan.setBounds(360, 181, 97, 34);
		panel.add(Yan);

		Yan.addMouseListener(new MouseListener() {

			
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
			
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Syan = getYan();
			}
		});

		// 重置
		JButton rem = new JButton("\u91CD\u7F6E");
		rem.setContentAreaFilled(false);
		rem.setFont(font);
		rem.setBounds(160, 250, 93, 28);
		panel.add(rem);
		rem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JTnum.setText(null);
				JTyan.setText(null);
				JTpassword.setText(null);
			}
		});
		// 确定
		JButton sure = new JButton("\u786E\u5B9A");
		sure.setContentAreaFilled(false);
		sure.setFont(font);
		sure.setBounds(300, 250, 93, 28);
		panel.add(sure);
		//sure.requestFocus();
		getRootPane().setDefaultButton(sure);
		sure.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				sureLoading();
			}
		});

		getContentPane().add(panel);
		setResizable(false);
	}

	public void deleteFile(File file) {
		// TODO Auto-generated method stub
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 删除;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}

	// 更换验证码图片并且返回验证码的值
	public String getYan() {
		String syan = null;
		try {
			File f = new File("Random");
			deleteFile(f);
			f.mkdir();
			yan y = new yan();
			syan = y.s.toLowerCase();
			i = new ImageIcon("Random/" + syan + ".jpg");
			Yan.setIcon(i);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return syan;
	}

	// 确定登录功能
	public void sureLoading() {
		String s = JTyan.getText().toLowerCase();// 获取验证码
		if (JTnum.getText().isEmpty())// num为空
			JOptionPane.showMessageDialog(null, "学号/工号不能为空");
//		else if (JTnum.getText().length()>20)
//			JOptionPane.showMessageDialog(null, "学号/工号不能为空");
		else if (JTpassword.getText().isEmpty())// 密码为空
			JOptionPane.showMessageDialog(null, "请填写密码");
		else if (s.isEmpty()) {// 验证码为空
			JOptionPane.showMessageDialog(null, "验证码不能为空");

		} else if (s.compareTo(Syan) != 0) {// 验证码错误
			JOptionPane.showMessageDialog(null, "验证码错误");
			Syan = getYan();
		} else {
			// 登录
			
			String num = JTnum.getText();// 获取当前的学号，用于传递到服务端
			String password = JTpassword.getText();// 获取当前密码，用于验证
			String Admit = (String) JTid.getSelectedItem();// 获取当前组合框的值，
			try {
				Long.parseLong(num);
				Socket socket = new Socket(ip, 8181);// 服务端端口为8181
				DataOutputStream toServer = new DataOutputStream(
						socket.getOutputStream());
				toServer.writeInt(1);
				toServer.writeUTF(Admit);// 传递组合框的值-身份
				toServer.writeUTF(num);// 传递学号
				DataInputStream fromServer = new DataInputStream(
						socket.getInputStream());
				String fromPassword = fromServer.readUTF();// 从服务端获取当前学号对应的密码
				if (fromPassword.equals("")) {// 用户不存在，换验证码图片
					JOptionPane.showMessageDialog(null, "该用户不存在", "来自一个帅哥的提醒",
							JOptionPane.YES_NO_CANCEL_OPTION);
					Syan = getYan();
				} else if (password.equals(fromPassword))
					ok = true;
				else {// 密码错误，并且换验证码图片
					JOptionPane.showMessageDialog(null, "密码错误", "来自一个帅哥的提醒",
							JOptionPane.YES_NO_CANCEL_OPTION);
					Syan = getYan();
				}
				socket.close();// 关闭当前连接

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "无法连接网络", "连接错误",
						JOptionPane.YES_NO_OPTION);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}catch(NumberFormatException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "学号/工号只能使用数字");
			}
			// 验证码正确后登录
			if (Admit.equals("学生") && ok) {
				Student student = new Student();
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				//int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				student.show(x/5, 100);
				ok = false;
				dispose();
			} else if (Admit.equals("管理员") && ok) {
				Admit admit = new Admit(1);
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				//int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				admit.show(x/5, 100);
				ok = false;
				dispose();
			}else if (Admit.equals("超级管理员")&&ok){
				Admit admit= new Admit(2);
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				//int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				admit.show(x/5, 100);
				ok=false;
				dispose();
			}
		}
	}

	public void show(int x, int y) {
		setTitle("图书借还管理系统登录界面");
		x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
		y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
		setSize(600, 400);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() { // 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // 窗口最小化事件

				setVisible(false);
				miniTray();

			}

		});
	}

	private void miniTray() { // 窗口最小化到任务栏托盘

		ImageIcon trayImg = new ImageIcon("image/biaoti.gif");// 托盘图标

		PopupMenu pop = new PopupMenu(); // 增加托盘右击菜单
		MenuItem show = new MenuItem("还原");
		show.setFont(font2);
		MenuItem exit = new MenuItem("退出");
		exit.setFont(font2);

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // 按下还原键

				tray.remove(trayIcon);
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
				toFront();
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
					setVisible(true);
					setExtendedState(JFrame.NORMAL); // 还原窗口
					toFront();
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
