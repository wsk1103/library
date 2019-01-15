package GUI;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ChangQuestion {

	private JPasswordField JTpassword;
	private JTextField JTanswer;
	static JFrame frame;
	private JComboBox JTquestion;
	Font font=new Font("楷体",Font.BOLD,16);
	Font font2=new Font("楷体",Font.BOLD,20);
	String Sid=Login.JTid.getSelectedItem().toString();
	public ChangQuestion() {
		Student.studentChangQuestion=1;
		Admit.studentChangQuestion=1;
		frame = new JFrame();
		frame.setResizable(false);
		// 设置画板格式
		frame.getContentPane().setLayout(null);
		// 画板背景
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		// panel格式
		panel.setBounds(0, 0, 557, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 557, 21);
		panel.add(menuBar);
		// 用户
		JMenu mmUser = new JMenu("用户");
		mmUser.setMnemonic('u');
		menuBar.add(mmUser);
		mmUser.setFont(font);
		// 退出
		JMenuItem mntmExit = new JMenuItem("退出系统");
		mntmExit.setIcon(new ImageIcon("menu/exit.gif"));
		mntmExit.setFont(font);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
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
		JMenu mmHelp = new JMenu("帮助");
		mmHelp.setMnemonic('h');
		mmHelp.setFont(font);
		menuBar.add(mmHelp);
		// 获取帮助
		JMenuItem mntmGetHelp = new JMenuItem("获取帮助");
		mntmGetHelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGetHelp.setFont(font);
		mntmGetHelp.setMnemonic('h');
		mntmGetHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mmHelp.add(mntmGetHelp);
		// 密码
		JLabel JLpassword = new JLabel("登录\u5BC6\u7801\uFF1A");
		JLpassword.setFont(font2);
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setBounds(26, 81, 177, 34);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setFont(new Font("Serif", Font.BOLD, 22));
		JTpassword.setBounds(213, 83, 233, 31);
		panel.add(JTpassword);
		// 新密保问题
		JLabel JLquestion = new JLabel("\u65B0\u5BC6\u4FDD\u95EE\u9898\uFF1A");
		JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
		JLquestion.setFont(font2);
		JLquestion.setBounds(26, 146, 177, 34);
		panel.add(JLquestion);
		
		String[] s={
				"\u6211\u7236\u4EB2\u7684\u540D\u5B57",
				"\u6211\u7236\u4EB2\u7684\u751F\u65E5",
				"\u6211\u5C0F\u5B66\u7684\u540C\u684C",
				"\u6211\u521D\u4E2D\u7684\u597D\u53CB",
				"\u6211\u9AD8\u4E2D\u7684\u5BA4\u53CB",
				"\u6211\u7684\u5BB6\u5EAD\u5730\u5740",
				"\u6211\u7684\u751F\u65E5", "\u6211\u7684\u7231\u4EBA",
				"\u6211\u6BCD\u4EB2\u7684\u751F\u65E5",
		"\u6211\u4EEC\u7684\u56DE\u5FC6","我的手机号码" };
		JTquestion = new JComboBox(s);
		JTquestion.setFont(font2);
		JTquestion.setBackground(Color.white);
		JTquestion.setBounds(215, 148, 233, 31);
		panel.add(JTquestion);
		// 新密保问题答案
		JLabel JLanswer = new JLabel("\u65B0\u5BC6\u4FDD\u7B54\u6848\uFF1A");
		JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
		JLanswer.setFont(font2);
		JLanswer.setBounds(26, 203, 177, 34);
		panel.add(JLanswer);

		JTanswer = new JTextField();
		JTanswer.setFont(new Font("楷体", Font.BOLD, 18));
		JTanswer.setBounds(215, 205, 233, 31);
		panel.add(JTanswer);
		JTanswer.setColumns(10);
		// 重置按钮和监听
		JButton rem = new JButton("\u91CD\u7F6E");
		rem.setContentAreaFilled(false);
		rem.setFont(new Font("楷体", Font.BOLD, 20));
		rem.setBounds(153, 266, 110, 34);
		panel.add(rem);
		rem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				JTanswer.setText(null);
				JTpassword.setText(null);
			}
		});
		// 退出按钮和监听
		JButton sure = new JButton("\u786E\u5B9A");
		sure.setContentAreaFilled(false);
		sure.setFont(new Font("楷体", Font.BOLD, 20));
		sure.setBounds(273, 266, 115, 34);
		panel.add(sure);
		frame.getRootPane().setDefaultButton(sure);
		//确定按钮监听
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "确定修改？","确定修改？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				String password=JTpassword.getText();//获取登录密码
				String question=JTquestion.getSelectedItem().toString();//获取问题
				String answer=JTanswer.getText();//获取答案
				String num=Login.JTnum.getText();//获取用户名
				if (password==null||password.isEmpty()){
					JOptionPane.showMessageDialog(null, "登录密码不能为空");
				}
				else if (answer==null||answer.isEmpty()){
					JOptionPane.showMessageDialog(null, "答案不能为空");
				}
				else
					try {
					Socket socket=new Socket(Login.ip, 8181);//连接到端口8181
					DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());//输出数据
					DataInputStream fromServer=new DataInputStream(socket.getInputStream());//接受数据
					toServer.writeInt(4);//输出一个4，表示调用哪种处理方式
					toServer.writeUTF(Sid);
					toServer.writeUTF(num);//输出用户名
					toServer.writeInt(2);//输出2，用于获取正确密码
					String fromServerPassword=fromServer.readUTF();//接收密码
					 if (!password.equals(fromServerPassword)){
						JOptionPane.showMessageDialog(null, "登录密码错误");
					}
					else {
						toServer.writeInt(1);//输出1表示修改密保问题和答案
						toServer.writeUTF(question);//输出
						toServer.writeUTF(answer);
						int i=fromServer.readInt();//接收数字，如果为1则修改成功
						if (i==1){
							JOptionPane.showMessageDialog(null, "修改成功");
							frame.dispose();
						}
						else 
							JOptionPane.showMessageDialog(null, "修改失败,不能超过20个字符");
					}
					 //关闭
					toServer.flush();
					toServer.close();
					fromServer.close();
					socket.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}}
		});
	}

	// 显示
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("修改密保");
		frame.setBounds(x, y, 540, 380);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
