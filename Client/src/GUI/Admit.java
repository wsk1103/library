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
	String[] s = { "���", "����", "����", "����", "������", "����", "ʱ��" };
	String[] ss = { "���", "����", "����", "����", "������", "����", "���", "����ʱ��", "����" };
	private Font font = new Font("����", Font.BOLD, 20);
	private Font font2 = new Font("����", Font.BOLD, 17);
	private JScrollPane scrollPane;
	private TableAction tableaction = new TableAction();
	public String no = Login.JTnum.getText();// ѧ�ŵ�ֵ
	String name = null;// �û�����ֵ
	String sex = null;// �Ա�
	static SystemTray tray = SystemTray.getSystemTray();
	private static TrayIcon trayIcon = null;
	private String Sid = Login.JTid.getSelectedItem().toString();//���

	public Admit(int i) {
		ContentServer(i);// ��ʼ����ֵ
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
		// �˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 984, 21);
		contentPane.add(menuBar);
		// �û�
		JMenu user = new JMenu("�û�U");
		user.setMnemonic('u');
		user.setFont(font2);
		menuBar.add(user);
		// �޸�����
		JMenuItem changpassword = new JMenuItem("�޸�����");
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
		// �޸��ܱ�
		JMenuItem changquestion = new JMenuItem("�޸��ܱ�");
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
		// �л��û�
		JMenuItem swithuser = new JMenuItem("�л��û�");
		swithuser.setMnemonic('s');
		swithuser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				ActionEvent.CTRL_MASK));
		swithuser.setIcon(new ImageIcon("menu/swithuser.gif"));
		swithuser.setFont(font2);
		swithuser.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���л���","ȷ���л���",JOptionPane.YES_NO_OPTION);
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
		// �˳�
		JMenuItem exit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		exit.setMnemonic('e');
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		exit.setIcon(new ImageIcon("menu/exit.gif"));
		exit.setFont(font2);
		exit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���˳���","ȷ���˳���",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		user.add(exit);
		if (i == 1) {
			// ����ѧ����ť
			JMenu Mstudent = new JMenu("����ѧ��S");
			Mstudent.setFont(font2);
			Mstudent.setMnemonic('s');
			menuBar.add(Mstudent);
			JMenuItem student = new JMenuItem("ѧ���軹�鼮���");
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
		// ����
		JMenu help = new JMenu("\u5E2E\u52A9H");
		help.setFont(font2);
		help.setMnemonic('H');
		menuBar.add(help);
		// ��ȡ����
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
				String string="���ʹ�ù��̳��������⣬��ӭ��ʱ������\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("����",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"����һ��˧�����ʾ",JOptionPane.PLAIN_MESSAGE);
			}
		});
		// �򿪼�����
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
		// �򿪼��±�
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
		// �򿪻�ͼ��
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
		// ��������
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
			JMenu Mstudent = new JMenu("Ȩ���ƽ�S");
			Mstudent.setFont(font2);
			Mstudent.setMnemonic('s');
			menuBar.add(Mstudent);
			JMenuItem student = new JMenuItem("�ƽ���������Ա���");
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

		// ����
		JLabel JLname = new JLabel("\u59D3\u540D\uFF1A");
		JLname.setBounds(47, 28, 104, 35);
		JLname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLname.setFont(font);
		contentPane.add(JLname);

		JLabel JTname = new JLabel(name);
		JTname.setBounds(161, 28, 173, 35);
		JTname.setFont(font);
		contentPane.add(JTname);
		// ����
		JLabel JLno = new JLabel("\u5DE5\u53F7\uFF1A");
		JLno.setBounds(331, 28, 104, 35);
		JLno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLno.setFont(font);
		contentPane.add(JLno);

		JLabel JTno = new JLabel(no);
		JTno.setBounds(445, 28, 173, 35);
		JTno.setFont(font);
		contentPane.add(JTno);
		// �Ա�
		JLabel JLsex = new JLabel("\u6027\u522B\uFF1A");
		JLsex.setBounds(628, 28, 104, 35);
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setFont(font);
		contentPane.add(JLsex);

		JLabel JTsex = new JLabel(sex);
		JTsex.setBounds(742, 28, 173, 35);
		JTsex.setFont(font);
		contentPane.add(JTsex);
		// ���
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
			// ��ѯ��ʽ
			JLabel label = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
			label.setBounds(10, 118, 115, 35);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			label.setFont(font);
			contentPane.add(label);
			// ��Ͽ�
			comboBox = new JComboBox(s);
			comboBox.setBounds(135, 118, 93, 35);
			comboBox.setBackground(Color.white);
			comboBox.setFont(font);
			contentPane.add(comboBox);
			// �����ı���
			JTfind = new JTextField();
			JTfind.setBounds(238, 118, 193, 35);
			JTfind.setFont(font);
			contentPane.add(JTfind);
			JTfind.setColumns(10);
			// ����Ų���
			JButton JBfindNo = new JButton("����������ѯ");
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
						toServer.writeInt(5);// ��ʽ5
						toServer.writeInt(2);// �ӷ�ʽ2
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
								ob[i][j++] = "˫���޸�";
								i++;
							}

							table = new JTable(new MyTable(ob, ss));
							table.setFont(font2);
							table.addMouseListener(tableaction);
							table.setRowHeight(28);
						} else {
							JOptionPane.showMessageDialog(null, "δ��ѯ�����");
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
			// ��ʱ�����
			JButton JBfindTime = new JButton("��ʱ�������ѯ");
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
								ob[i][j++] = "˫���޸�";
								i++;
							}

							table = new JTable(new MyTable(ob, ss));
							table.setFont(font2);
							table.addMouseListener(tableaction);
							table.setRowHeight(28);
						} else {
							JOptionPane.showMessageDialog(null, "δ��ѯ�����");
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
			// ����鼮
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

			// ����
			scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 163, 984, 448);
			scrollPane.setOpaque(false);
			scrollPane.getViewport().setOpaque(false);
			contentPane.add(scrollPane);
			// ���
			table = new JTable(new MyTable(ob, ss));
			table.setRowHeight(24);
			table.setFont(font2);
			table.setEnabled(false);
			scrollPane.setViewportView(table);
		}
	}

	// ��ʾ��������ͼ������
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("����Ա��");
		frame.setBounds(x, y, 988, 640);
//		frame.setBounds(x, y, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() { // ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // ������С���¼�

				frame.setVisible(false);
				miniTray();

			}

		});
	}

	// �������ݿ⣬����ʼ��������ǩ��Ӧ��ֵ
	private void ContentServer(int i) {
		try {
			Socket socket = new Socket(Login.ip, 8181);// ����˿�Ϊ8182
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());// ���ݵ�����˵���������ΪString
			// System.out.println(num);
			toServer.writeInt(2);
			toServer.writeUTF(no);// ��������
			if (i == 1)
				toServer.writeInt(2);
			else if (i == 2)
				toServer.writeInt(3);
			ObjectInputStream fromServer = new ObjectInputStream(
					socket.getInputStream());// �ӷ���˻�ȡ����
			ArrayList<StudentAcount> list = (ArrayList<StudentAcount>) fromServer
					.readObject();// ����ȡ�Ķ���ת��ΪArraylist
			StudentAcount s = new StudentAcount();// Javabean
			// ��ʼ����Ӧ��ֵ
			s = list.get(0);
			name = s.getNum();
			sex = s.getQuestion();

			socket.close();// �رյ�ǰ�˿�����
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// �������
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

	// ������С��������������
	public void miniTray() {
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
