package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bean.StudentAcount;
import bean.book;

public class Admit  {
	static JFrame frame;
	static int ok=0;
	static int information=0;
	static int studentinformation=0;
	static int studentChangPassword=0;
	static int studentChangQuestion=0;
	static int yijiao=0;
	private JPanel contentPane;
	private JTextField JTfind;
	private JTable table;
	JComboBox comboBox;
	private Object[][] ob = new Object[0][9];
	String[] s = { "编号", "书名", "作者", "译者", "出版社", "类型", "时间" };
	String[] ss = { "编号", "书名", "作者", "译者", "出版社", "类型", "库存", "进货时间", "操作" };
	private Font font = new Font("楷体", Font.BOLD, 20);
	private Font font2 = new Font("楷体", Font.BOLD, 17);
	private JScrollPane scrollPane;
	private TableAction tableaction = new TableAction();
	public String no = Login.JTnum.getText();// 学号的值
	String name = null;// 用户名的值
	String sex = null;// 性别
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	private String Sid = Login.JTid.getSelectedItem().toString();//身份

	public Admit(int i) {
		ContentServer(i);// 初始化各值
		frame=new JFrame();

		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 984, 21);
		contentPane.add(menuBar);
		// 用户
		JMenu user = new JMenu("用户U");
		user.setMnemonic('u');
		user.setFont(font2);
		menuBar.add(user);
		// 修改密码
		JMenuItem changpassword = new JMenuItem("修改密码");
		changpassword.setFont(font2);
		changpassword.setMnemonic('c');
		changpassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				ActionEvent.CTRL_MASK));
		changpassword.setIcon(new ImageIcon("menu/changpassword.gif"));
		changpassword.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Point p = frame.getLocation();
				int x = (int) p.getX();
				int y = (int) p.getY();
				new ChangPassword().show(x + 200, y + 160);
			}
		});
		user.add(changpassword);
		// 修改密保
		JMenuItem changquestion = new JMenuItem("修改密保");
		changquestion.setFont(font2);
		changquestion.setMnemonic('d');
		changquestion.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				ActionEvent.CTRL_MASK));
		changquestion.setIcon(new ImageIcon("menu/changquestion.gif"));
		changquestion.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Point p = frame.getLocation();
				int x = (int) p.getX();
				int y = (int) p.getY();
				new ChangQuestion().show(x + 200, y + 160);
			}
		});
		user.add(changquestion);
		// 切换用户
		JMenuItem swithuser = new JMenuItem("切换用户");
		swithuser.setMnemonic('s');
		swithuser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		swithuser.setIcon(new ImageIcon("menu/swithuser.gif"));
		swithuser.setFont(font2);
		swithuser.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "确定切换？","确定切换？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				Login l = new Login();
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				l.show(x/3, y/4);
				if (ok==1)
					Mstudent.frame.dispose();
				if (information==2)
					ChangBookInformation.frame.dispose();
				if (information==3)
					Information.frame.dispose();
				if (studentinformation==1)
					StudentInformation.frame.dispose();
				if (yijiao==1)
					yijiaoquanxian.frame.dispose();
				if (studentChangPassword==1)
					ChangPassword.frame.dispose();
				if (studentChangQuestion==1)
					ChangQuestion.frame.dispose();
				frame.dispose();}
			}
		});
		user.add(swithuser);
		// 退出
		JMenuItem exit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		exit.setMnemonic('e');
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		exit.setIcon(new ImageIcon("menu/exit.gif"));
		exit.setFont(font2);
		exit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "确定退出？","确定退出？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		user.add(exit);
		if (i == 1) {
			// 管理学生按钮
			JMenu Mstudent = new JMenu("管理学生S");
			Mstudent.setFont(font2);
			Mstudent.setMnemonic('s');
			menuBar.add(Mstudent);
			JMenuItem student = new JMenuItem("学生借还书籍情况");
			student.setMnemonic('g');
			student.setIcon(new ImageIcon("menu/3.gif"));
			student.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
					ActionEvent.CTRL_MASK));
			student.setFont(font2);
			Mstudent.add(student);
			student.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					Mstudent ms = new Mstudent();
					Point p =frame. getLocation();
					int x = (int) p.getX() + 50;
					int y = (int) p.getY() + 50;
					ms.show(x, y);
				}
			});
		}
		// 帮助
		JMenu help = new JMenu("\u5E2E\u52A9H");
		help.setFont(font2);
		help.setMnemonic('H');
		menuBar.add(help);
		// 获取帮助
		JMenuItem gethelp = new JMenuItem("\u83B7\u53D6\u5E2E\u52A9");
		gethelp.setFont(font2);
		gethelp.setMnemonic('h');
		gethelp.setIcon(new ImageIcon("menu/help.gif"));
		gethelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				ActionEvent.CTRL_MASK));
		help.add(gethelp);
		gethelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="如果使用过程出现了问题，欢迎随时反馈。\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("楷体",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"来自一个帅哥的提示",JOptionPane.PLAIN_MESSAGE);
			}
		});
		// 打开计算器
		JMenuItem calc = new JMenuItem("\u6253\u5F00\u8BA1\u7B97\u5668");
		calc.setMnemonic('v');
		calc.setIcon(new ImageIcon("menu/funtion.gif"));
		calc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				ActionEvent.CTRL_MASK));
		calc.setFont(font2);
		calc.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("cmd /c start calc");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		help.add(calc);
		// 打开记事本
		JMenuItem notepad = new JMenuItem("\u6253\u5F00\u8BB0\u4E8B\u672C");
		notepad.setFont(font2);
		notepad.setMnemonic('n');
		notepad.setIcon(new ImageIcon("menu/funtion.gif"));
		notepad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				ActionEvent.CTRL_MASK));
		notepad.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("cmd /c start notepad");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		help.add(notepad);
		// 打开画图板
		JMenuItem mspaint = new JMenuItem("\u6253\u5F00\u753B\u56FE\u677F");
		mspaint.setFont(font2);
		mspaint.setMnemonic('m');
		mspaint.setIcon(new ImageIcon("menu/funtion.gif"));
		mspaint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M,
				ActionEvent.CTRL_MASK));
		mspaint.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("cmd /c start mspaint");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		help.add(mspaint);
		// 打开造字器
		JMenuItem eudcedit = new JMenuItem("\u6253\u5F00\u9020\u5B57\u5668");
		eudcedit.setFont(font2);
		eudcedit.setMnemonic('l');
		eudcedit.setIcon(new ImageIcon("menu/funtion.gif"));
		eudcedit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				ActionEvent.CTRL_MASK));
		eudcedit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Runtime.getRuntime().exec("cmd /c start eudcedit");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		help.add(eudcedit);
		if (i==2){
			JMenu Mstudent = new JMenu("权限移交S");
			Mstudent.setFont(font2);
			Mstudent.setMnemonic('s');
			menuBar.add(Mstudent);
			JMenuItem student = new JMenuItem("移交超级管理员身份");
			student.setMnemonic('g');
			student.setIcon(new ImageIcon("menu/3.gif"));
			student.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,
					ActionEvent.CTRL_MASK));
			student.setFont(font2);
			Mstudent.add(student);
			student.addActionListener(new ActionListener() {	
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					yijiaoquanxian ms = new yijiaoquanxian();
					Point p =frame. getLocation();
					int x = (int) p.getX() + 220;
					int y = (int) p.getY() + 150;
					ms.show(x, y);
				}
			});
		}

		// 姓名
		JLabel JLname = new JLabel("\u59D3\u540D\uFF1A");
		JLname.setBounds(47, 28, 104, 35);
		JLname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLname.setFont(font);
		contentPane.add(JLname);

		JLabel JTname = new JLabel(name);
		JTname.setBounds(161, 28, 173, 35);
		JTname.setFont(font);
		contentPane.add(JTname);
		// 工号
		JLabel JLno = new JLabel("\u5DE5\u53F7\uFF1A");
		JLno.setBounds(331, 28, 104, 35);
		JLno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLno.setFont(font);
		contentPane.add(JLno);

		JLabel JTno = new JLabel(no);
		JTno.setBounds(445, 28, 173, 35);
		JTno.setFont(font);
		contentPane.add(JTno);
		// 性别
		JLabel JLsex = new JLabel("\u6027\u522B\uFF1A");
		JLsex.setBounds(628, 28, 104, 35);
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setFont(font);
		contentPane.add(JLsex);

		JLabel JTsex = new JLabel(sex);
		JTsex.setBounds(742, 28, 173, 35);
		JTsex.setFont(font);
		contentPane.add(JTsex);
		// 身份
		JLabel JLid = new JLabel("\u8EAB\u4EFD\uFF1A");
		JLid.setBounds(47, 73, 104, 35);
		JLid.setHorizontalAlignment(SwingConstants.RIGHT);
		JLid.setFont(font);
		contentPane.add(JLid);
		JLabel JTid = new JLabel(Sid);
		JTid.setBounds(161, 73, 173, 35);
		JTid.setFont(font);
		contentPane.add(JTid);
		if (i==2){
		superAdmit sa=new superAdmit();
		sa.setBounds(0, 112, 972, 490);
		contentPane.add(sa);
		}

		if (i == 1) {
			// 查询方式
			JLabel label = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
			label.setBounds(10, 118, 115, 35);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(font);
			contentPane.add(label);
			// 组合框
			comboBox = new JComboBox(s);
			comboBox.setBounds(135, 118, 93, 35);
			comboBox.setBackground(Color.white);
			comboBox.setFont(font);
			contentPane.add(comboBox);
			// 搜索文本框
			JTfind = new JTextField();
			JTfind.setBounds(238, 118, 193, 35);
			JTfind.setFont(font);
			contentPane.add(JTfind);
			JTfind.setColumns(10);
			// 按编号查找
			JButton JBfindNo = new JButton("按编号排序查询");
			JBfindNo.setBounds(445, 116, 185, 38);
			JBfindNo.setContentAreaFilled(false);
			JBfindNo.setFont(font);
			contentPane.add(JBfindNo);
			frame.getRootPane().setDefaultButton(JBfindNo);
			JBfindNo.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						Socket socket = new Socket(Login.ip, 8181);
						DataOutputStream toServer = new DataOutputStream(socket
								.getOutputStream());
						toServer.writeInt(5);// 方式5
						toServer.writeInt(2);// 子方式2
						toServer.writeUTF(comboBox.getSelectedItem().toString());
						toServer.writeUTF(JTfind.getText());
						ObjectInputStream fromServer = new ObjectInputStream(
								socket.getInputStream());
						ArrayList<book> list = (ArrayList<book>) fromServer
								.readObject();
						// System.out.println(list.size());
						int i = 0, j = 0;
						ob = new Object[list.size()][9];
						if (list.size() != 0) {
							for (book book : list) {
								if (j > 8) {
									j = 0;
								}
								ob[i][j++] = book.getNo();
								ob[i][j++] = book.getName();
								ob[i][j++] = book.getAuthor();
								ob[i][j++] = book.getTranslator();
								ob[i][j++] = book.getPublsh();
								ob[i][j++] = book.getSort();
								ob[i][j++] = book.getStock();
								ob[i][j++] = book.getTime();
								ob[i][j++] = "双击修改";
								i++;
							}

							table = new JTable(new MyTable(ob, ss));
							table.setFont(font2);
							table.addMouseListener(tableaction);
							table.setRowHeight(28);
						} else {
							JOptionPane.showMessageDialog(null, "未查询到结果");
							ob = new Object[0][0];
							table = new JTable(new MyTable(ob, ss));
							table.setFont(font);
						}
						// table.setEnabled(false);
						scrollPane.setViewportView(table);
						toServer.flush();
						toServer.close();
						fromServer.close();
						socket.close();
					} catch (IOException e) {
						// TODO: handle exception
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			// 按时间查找
			JButton JBfindTime = new JButton("按时间排序查询");
			JBfindTime.setBounds(638, 116, 191, 38);
			JBfindTime.setContentAreaFilled(false);
			JBfindTime.setFont(font);
			contentPane.add(JBfindTime);
			JBfindTime.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						Socket socket = new Socket(Login.ip, 8181);
						DataOutputStream toServer = new DataOutputStream(socket
								.getOutputStream());
						toServer.writeInt(5);
						toServer.writeInt(3);
						toServer.writeUTF(comboBox.getSelectedItem().toString());
						toServer.writeUTF(JTfind.getText());
						ObjectInputStream fromServer = new ObjectInputStream(
								socket.getInputStream());
						ArrayList<book> list = (ArrayList<book>) fromServer
								.readObject();
						// System.out.println(list.size());
						int i = 0, j = 0;
						ob = new Object[list.size()][9];
						if (list.size() != 0) {
							for (book book : list) {
								if (j > 8) {
									j = 0;
								}
								ob[i][j++] = book.getNo();
								ob[i][j++] = book.getName();
								ob[i][j++] = book.getAuthor();
								ob[i][j++] = book.getTranslator();
								ob[i][j++] = book.getPublsh();
								ob[i][j++] = book.getSort();
								ob[i][j++] = book.getStock();
								ob[i][j++] = book.getTime();
								ob[i][j++] = "双击修改";
								i++;
							}

							table = new JTable(new MyTable(ob, ss));
							table.setFont(font2);
							table.addMouseListener(tableaction);
							table.setRowHeight(28);
						} else {
							JOptionPane.showMessageDialog(null, "未查询到结果");
							ob = new Object[0][0];
							table = new JTable(new MyTable(ob, ss));
							table.setFont(font);
						}
						scrollPane.setViewportView(table);
						toServer.flush();
						toServer.close();
						fromServer.close();
						socket.close();
					} catch (IOException e) {
						// TODO: handle exception
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			// 添加书籍
			JButton JBaddBook = new JButton("\u6DFB\u52A0\u4E66\u7C4D");
			JBaddBook.setBounds(843, 116, 127, 38);
			JBaddBook.setContentAreaFilled(false);
			JBaddBook.setFont(font);
			contentPane.add(JBaddBook);
			JBaddBook.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					ChangBookInformation bookinformation = new ChangBookInformation(
							null, null, null, null, null, null, null, null, 2);
					Point p = frame.getLocation();
					int x = (int) p.getX();
					int y = (int) p.getY();
					bookinformation.show(x + 120, y + 50);
				}
			});

			// 滑板
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 163, 984, 448);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			contentPane.add(scrollPane);
			// 表格
			table = new JTable(new MyTable(ob, ss));
			table.setRowHeight(24);
			table.setFont(font2);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
		}
	}

	// 显示并且设置图标和鼠标
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("管理员端");
		frame.setBounds(x, y, 988, 640);
//		frame.setBounds(x, y, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() { // 窗口关闭事件
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // 窗口最小化事件

				frame.setVisible(false);
				miniTray();

			}

		});
	}

	// 连接数据库，并初始化各个标签对应的值
	private void ContentServer(int i) {
		try {
			Socket socket = new Socket(Login.ip, 8181);// 网络端口为8182
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());// 传递到服务端的数据类型为String
			// System.out.println(num);
			toServer.writeInt(2);
			toServer.writeUTF(no);// 传递数据
			if (i == 1)
				toServer.writeInt(2);
			else if (i == 2)
				toServer.writeInt(3);
			ObjectInputStream fromServer = new ObjectInputStream(
					socket.getInputStream());// 从服务端获取对象
			ArrayList<StudentAcount> list = (ArrayList<StudentAcount>) fromServer
					.readObject();// 将获取的对象转型为Arraylist
			StudentAcount s = new StudentAcount();// Javabean
			// 初始化对应的值
			s = list.get(0);
			name = s.getNum();
			sex = s.getQuestion();

			socket.close();// 关闭当前端口连接
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 监听表格
	class TableAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int Mousercount = e.getClickCount();
			if (Mousercount == 2) {
				int r = table.getSelectedRow();
				String BookNum = table.getValueAt(r, 0).toString();
				String BookName = table.getValueAt(r, 1).toString();
				String BookAuthor = table.getValueAt(r, 2).toString();
				String BookTranslator;
				if (null == ob[r][3]) {
					BookTranslator = "";
				} else
					BookTranslator = table.getValueAt(r, 3).toString();
				String BookSort = table.getValueAt(r, 5).toString();
				String BookPublish = table.getValueAt(r, 4).toString();
				String Time = table.getValueAt(r, 7).toString();

				Point p = frame.getLocation();
				String Sstock = table.getValueAt(r, 6).toString();
				int x = (int) p.getX();
				int y = (int) p.getY();
				ChangBookInformation bookinformation = new ChangBookInformation(
						BookNum, BookName, BookAuthor, BookTranslator,
						BookSort, BookPublish, Sstock, Time, 1);
				bookinformation.show(x + 120, y + 50);

			}
		}

		
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	// 窗口最小化到任务栏托盘
	public void miniTray() {
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
