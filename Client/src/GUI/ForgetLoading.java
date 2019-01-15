package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ForgetLoading extends JFrame {

	private JPanel contentPane;
	static JTextField JTnum;
	static JComboBox CBid;
	Font font=new Font("楷体", Font.BOLD,20);
	Font font2=new Font("楷体", Font.BOLD,16);
	

	public ForgetLoading() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 573, 395);
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/06.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 571, 21);
		contentPane.add(menuBar);
		//用户
		JMenu mnUser = new JMenu("\u7528\u6237U");
		mnUser.setFont(font2);
		mnUser.setMnemonic('u');
		menuBar.add(mnUser);
		//退出
		JMenuItem JMIexit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		JMIexit.setFont(font2);
		JMIexit.setIcon(new ImageIcon("menu/exit.gif"));
		JMIexit.setMnemonic('e');
		JMIexit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mnUser.add(JMIexit);	
		JMIexit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		//关于
		JMenuItem JMIabout = new JMenuItem("\u5173\u4E8E");
		JMIabout.setFont(font2);
		JMIabout.setIcon(new ImageIcon("menu/about.gif"));
		JMIabout.setMnemonic('a');
		JMIabout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mnUser.add(JMIabout);
		//帮助
		JMenu JMhelp = new JMenu("\u5E2E\u52A9H");
		JMhelp.setFont(font2);
		JMhelp.setMnemonic('H');
		menuBar.add(JMhelp);
		//获取帮助
		JMenuItem JMIgethelp = new JMenuItem("\u83B7\u53D6\u5E2E\u52A9H");
		JMIgethelp.setFont(font2);
		JMIgethelp.setMnemonic('h');
		JMIgethelp.setIcon(new ImageIcon("menu/help.gif"));
		JMIgethelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		JMhelp.add(JMIgethelp);
		JMIgethelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="如果使用过程出现了问题，欢迎随时反馈。\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("楷体",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"来自一个帅哥的提示",JOptionPane.PLAIN_MESSAGE);
			}
		});
		//标签
		JLabel JLnum = new JLabel("\u5B66\u53F7/\u5DE5\u53F7\uFF1A");
		JLnum.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnum.setFont(font2);
		JLnum.setBounds(10, 134, 143, 33);
		contentPane.add(JLnum);
		

		JTnum = new JTextField();
		//JTnum.setHorizontalAlignment(SwingConstants.LEFT);
		//JTnum.setToolTipText("");
		JTnum.setFont(font);
		JTnum.setBounds(163, 131, 206, 36);
		contentPane.add(JTnum);
		JTnum.setColumns(10);
		//身份组合框
		String[] Sid = { "学生", "管理员" ,"超级管理员"};
		CBid = new JComboBox(Sid);
		CBid.setFont(new Font("楷体", Font.BOLD,16));
		CBid.setBounds(379, 134, 115, 33);
		contentPane.add(CBid);
//下一步按钮
		JButton JBnext = new JButton("\u4E0B\u4E00\u6B65");
		JBnext.setContentAreaFilled(false);
		JBnext.setFont(font);
		JBnext.setBounds(219, 177, 108, 33);
		contentPane.add(JBnext);
		getRootPane().setDefaultButton(JBnext);
		JBnext.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				if (JTnum.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "学号/工号不能为空");
				}else if(JTnum.getText().length()>25){
					JOptionPane.showMessageDialog(null, "学号/工号过长，最多25个字符");
				}
				else
				try {
					Socket socket = new Socket(Login.ip, 8181);

					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					DataInputStream fromServer = new DataInputStream(socket
							.getInputStream());
					toServer.writeInt(1);//登录
					String num = JTnum.getText();
					String Sid = CBid.getSelectedItem().toString();
					toServer.writeUTF(Sid);
					toServer.writeUTF(num);
					String isExit = fromServer.readUTF();
					if (!isExit.equals("")) {
						ForgetPassword f = new ForgetPassword();
						Point p = getLocation();
						int x = (int) p.getX();
						int y = (int) p.getY();
						f.show(x, y);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "该用户不存在",
								"来自一个帅哥的提醒", JOptionPane.YES_NO_CANCEL_OPTION);
					}

					toServer.flush();
					toServer.close();
					fromServer.close();
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

	}

	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		this.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		this.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		this.setTitle("忘记密码");
		this.setBounds(x, y, 573, 395);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

}
