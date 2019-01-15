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
	static JTextField JTnum;// ѧ�ŵ�ֵ
	private JPasswordField JTpassword;// ����
	private JTextField JTyan;// ��֤��
	private boolean ok = false;
	public static JComboBox JTid;
//	public static String ip="10.94.71.25";//���
	public static String ip="localhost";//����
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

		font = new Font("����", Font.BOLD, 20);
		font2 = new Font("����", Font.BOLD, 15);

		panel = new JPanel() {
			private static final long serialVersionUID = 1L;
			// ��ͼ������ͼƬ�����ͼ��������ͼ��
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

		// �����˵���
		JMenuBar JMBuser;
		JMBuser = new JMenuBar();
		setJMenuBar(JMBuser);
		// �û�
		JMenu JMuser = new JMenu("�û�U");
		JMuser.setFont(font2);
		JMBuser.add(JMuser);
		JMuser.setMnemonic('U');
		// ����
		JMenu JMhelp = new JMenu("����H");
		JMhelp.setFont(font2);
		JMBuser.add(JMhelp);
		JMhelp.setMnemonic('H');

		// ��������
		JMenuItem JMIForgetPassword = new JMenuItem("��������");
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
					//��ת����ҳ�������������ʵ����ҳ��������
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
		// �˳�
		JMenuItem JMIexit = new JMenuItem("�˳�ϵͳ");
		JMIexit.setFont(font2);
		JMuser.add(JMIexit);
		JMIexit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���˳���","ȷ���˳���",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		JMIexit.setIcon(new ImageIcon("menu/exit.gif"));
		JMIexit.setMnemonic('e');
		JMIexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				ActionEvent.CTRL_MASK));
		// ��ȡ����
		JMenuItem JMIhelp = new JMenuItem("��ȡ����");
		JMIhelp.setFont(font2);
		JMhelp.add(JMIhelp);
		JMIhelp.setIcon(new ImageIcon("menu/help.gif"));
		JMIhelp.setMnemonic('G');
		JMIhelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				ActionEvent.CTRL_MASK));
		JMIhelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="���ʹ�ù��̳��������⣬��ӭ��ʱ������\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("����",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"����һ��˧�����ʾ",JOptionPane.PLAIN_MESSAGE);
			}
		});

		panel.setLayout(null);
		// ѧ��
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
		// ���ѡ��
		String[] s = { "ѧ��", "����Ա","��������Ա" };
		JTid = new JComboBox(s);
		JTid.setBackground(Color.white);
		JTid.setFont(new Font("����", Font.BOLD, 15));
		JTid.setBounds(360, 60, 109, 28);
		panel.add(JTid);
		// ����
		JLabel JLpassword = new JLabel("��    ��:");
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setFont(font);
		JLpassword.setBounds(80, 124, 120, 34);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setBounds(210, 126, 259, 28);

		JTpassword.setFont(new Font("Serif", Font.BOLD, 22));
		panel.add(JTpassword);
		// ��֤��
		JLabel JLyan = new JLabel("�� ֤ ��:");
		JLyan.setFont(font);
		JLyan.setHorizontalAlignment(SwingConstants.RIGHT);
		JLyan.setBounds(80, 185, 117, 34);
		panel.add(JLyan);

		JTyan = new JTextField();
		JTyan.setFont(new Font("����", Font.BOLD, 24));
		JTyan.setBounds(210, 186, 136, 28);
		panel.add(JTyan);
		JTyan.setColumns(10);
		// ������֤��

		try {
			yan y = new yan();// ���ú�������������֤��
			Syan = y.s.toLowerCase();
			// cc=y.cc;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		i = new ImageIcon("Random/" + Syan + ".jpg");// ��ȡ��֤��ͼƬ

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

		// ����
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
		// ȷ��
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
		if (file.exists()) { // �ж��ļ��Ƿ����
			if (file.isFile()) { // �ж��Ƿ����ļ�
				file.delete(); // delete()���� ɾ��;
			} else if (file.isDirectory()) { // �����������һ��Ŀ¼
				File files[] = file.listFiles(); // ����Ŀ¼�����е��ļ� files[];
				for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�
					this.deleteFile(files[i]); // ��ÿ���ļ� ������������е���
				}
			}
			file.delete();
		} else {
			System.out.println("��ɾ�����ļ������ڣ�" + '\n');
		}
	}

	// ������֤��ͼƬ���ҷ�����֤���ֵ
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

	// ȷ����¼����
	public void sureLoading() {
		String s = JTyan.getText().toLowerCase();// ��ȡ��֤��
		if (JTnum.getText().isEmpty())// numΪ��
			JOptionPane.showMessageDialog(null, "ѧ��/���Ų���Ϊ��");
//		else if (JTnum.getText().length()>20)
//			JOptionPane.showMessageDialog(null, "ѧ��/���Ų���Ϊ��");
		else if (JTpassword.getText().isEmpty())// ����Ϊ��
			JOptionPane.showMessageDialog(null, "����д����");
		else if (s.isEmpty()) {// ��֤��Ϊ��
			JOptionPane.showMessageDialog(null, "��֤�벻��Ϊ��");

		} else if (s.compareTo(Syan) != 0) {// ��֤�����
			JOptionPane.showMessageDialog(null, "��֤�����");
			Syan = getYan();
		} else {
			// ��¼
			
			String num = JTnum.getText();// ��ȡ��ǰ��ѧ�ţ����ڴ��ݵ������
			String password = JTpassword.getText();// ��ȡ��ǰ���룬������֤
			String Admit = (String) JTid.getSelectedItem();// ��ȡ��ǰ��Ͽ��ֵ��
			try {
				Long.parseLong(num);
				Socket socket = new Socket(ip, 8181);// ����˶˿�Ϊ8181
				DataOutputStream toServer = new DataOutputStream(
						socket.getOutputStream());
				toServer.writeInt(1);
				toServer.writeUTF(Admit);// ������Ͽ��ֵ-���
				toServer.writeUTF(num);// ����ѧ��
				DataInputStream fromServer = new DataInputStream(
						socket.getInputStream());
				String fromPassword = fromServer.readUTF();// �ӷ���˻�ȡ��ǰѧ�Ŷ�Ӧ������
				if (fromPassword.equals("")) {// �û������ڣ�����֤��ͼƬ
					JOptionPane.showMessageDialog(null, "���û�������", "����һ��˧�������",
							JOptionPane.YES_NO_CANCEL_OPTION);
					Syan = getYan();
				} else if (password.equals(fromPassword))
					ok = true;
				else {// ������󣬲��һ���֤��ͼƬ
					JOptionPane.showMessageDialog(null, "�������", "����һ��˧�������",
							JOptionPane.YES_NO_CANCEL_OPTION);
					Syan = getYan();
				}
				socket.close();// �رյ�ǰ����

			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(this, "�޷���������", "���Ӵ���",
						JOptionPane.YES_NO_OPTION);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			}catch(NumberFormatException e){
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "ѧ��/����ֻ��ʹ������");
			}
			// ��֤����ȷ���¼
			if (Admit.equals("ѧ��") && ok) {
				Student student = new Student();
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				//int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				student.show(x/5, 100);
				ok = false;
				dispose();
			} else if (Admit.equals("����Ա") && ok) {
				Admit admit = new Admit(1);
				int x = Toolkit.getDefaultToolkit().getScreenSize().width;
				//int y = Toolkit.getDefaultToolkit().getScreenSize().height;
				admit.show(x/5, 100);
				ok = false;
				dispose();
			}else if (Admit.equals("��������Ա")&&ok){
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
		setTitle("ͼ��軹����ϵͳ��¼����");
		x = Toolkit.getDefaultToolkit().getScreenSize().width/3;
		y = Toolkit.getDefaultToolkit().getScreenSize().height/4;
		setSize(600, 400);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addWindowListener(new WindowAdapter() { // ���ڹر��¼�
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			};

			public void windowIconified(WindowEvent e) { // ������С���¼�

				setVisible(false);
				miniTray();

			}

		});
	}

	private void miniTray() { // ������С��������������

		ImageIcon trayImg = new ImageIcon("image/biaoti.gif");// ����ͼ��

		PopupMenu pop = new PopupMenu(); // ���������һ��˵�
		MenuItem show = new MenuItem("��ԭ");
		show.setFont(font2);
		MenuItem exit = new MenuItem("�˳�");
		exit.setFont(font2);

		show.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) { // ���»�ԭ��

				tray.remove(trayIcon);
				setVisible(true);
				setExtendedState(JFrame.NORMAL);
				toFront();
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
					setVisible(true);
					setExtendedState(JFrame.NORMAL); // ��ԭ����
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
