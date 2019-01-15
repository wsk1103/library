package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

public class ChangPassword {

	private JPanel contentPane;
	private JPasswordField JTnewpassword;
	private JPasswordField JTpassword;
	static JFrame frame;
	private JTextField JTanswer;
	String Sid=Login.JTid.getSelectedItem().toString();
	String question;
	String answer;
	Font font=new Font("Serif", Font.BOLD, 22) ;
	Font font2=new Font("楷体", Font.BOLD, 16) ;
	Font font3=new Font("楷体", Font.BOLD, 20) ;
	
	//获取密保问题
	public void setQuestion() {
		try {
			Socket socket = new Socket(Login.ip, 8181);
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());
			String num = Login.JTnum.getText();
			System.out.println(Sid);
			toServer.writeInt(3);//写出3表示调用对应的方法
			toServer.writeUTF(Sid);
			toServer.writeInt(1);//写出1调用获取密保问题
			toServer.writeUTF(num);
			DataInputStream fromServer = new DataInputStream(
					socket.getInputStream());
			question = fromServer.readUTF();
			answer = fromServer.readUTF();
			//关闭
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	public ChangPassword() {
		Student.studentChangPassword=1;
		Admit.studentChangPassword=1;
		setQuestion();
		// JFrame
		frame = new JFrame();
		frame.setResizable(false);//设置无法最大化
		// 设置JFrame的布局格式
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		// 画板
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		panel.setBounds(0, 20, 552, 322);
		contentPane.add(panel);
		panel.setLayout(null);

		// 菜单栏
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 552, 21);
		contentPane.add(menuBar);
		// 用户
		JMenu mmUser = new JMenu("用户");
		mmUser.setMnemonic('u');
		mmUser.setFont(font2);
		menuBar.add(mmUser);
		// 退出
		JMenuItem mntmExit = new JMenuItem("退出系统");
		mntmExit.setFont(font2);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
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
		JMenu mmHelp = new JMenu("帮助");
		mmHelp.setFont(font2);
		menuBar.add(mmHelp);
		mmHelp.setMnemonic('h');
		// 获取帮助
		JMenuItem mntmGetHelp = new JMenuItem("获取帮助");
		mntmGetHelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGetHelp.setFont(font2);
		mntmGetHelp.setMnemonic('h');
		mntmGetHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mmHelp.add(mntmGetHelp);
		
		// 密保问题
		JLabel JLquestion = new JLabel("密保问题：");
		JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
		JLquestion.setBounds(42, 34, 166, 34);
		JLquestion.setFont(font3);
		panel.add(JLquestion);

		JLabel JTquestion = new JLabel(question);
		JTquestion.setBounds(234, 34, 308, 34);
		JTquestion.setFont(font3);
		panel.add(JTquestion);
		// 密保答案
		JLabel JLanswer = new JLabel("密保答案：");
		JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
		JLanswer.setBounds(42, 88, 166, 27);
		JLanswer.setFont(font3);
		panel.add(JLanswer);

		JTanswer = new JTextField();
		JTanswer.setHorizontalAlignment(SwingConstants.LEFT);
		JTanswer.setFont(font3);
		JTanswer.setBounds(234, 84, 215, 27);
		panel.add(JTanswer);
		JTanswer.setColumns(10);
		// 新密码
		JLabel JLpassword = new JLabel("新密码：");
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setBounds(42, 143, 166, 27);
		JLpassword.setFont(font3);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setBounds(234, 143, 215, 26);
		JTpassword.setFont(font);
		panel.add(JTpassword);
		// 确认新密码
		JLabel JLnewpassword = new JLabel("确认密码：");
		JLnewpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnewpassword.setBounds(42, 196, 166, 25);
		JLnewpassword.setFont(font3);
		panel.add(JLnewpassword);

		JTnewpassword = new JPasswordField();
		JTnewpassword.setBounds(234, 197, 215, 27);
		JTnewpassword.setFont(font);
		panel.add(JTnewpassword);
		// 重置按钮和监听
		JButton rem = new JButton("重置");
		rem.setBounds(154, 248, 107, 34);
		rem.setContentAreaFilled(false);
		rem.setFont(font);
		panel.add(rem);
		rem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JTpassword.setText("");
				JTnewpassword.setText(null);
				JTanswer.setText(null);
			}
		});
		// 确定按钮以及监听
		JButton sure = new JButton(" \u786E\u5B9A ");
		sure.setBounds(280, 248, 114, 34);
		sure.setContentAreaFilled(false);
		sure.setFont(font);
		panel.add(sure);
		frame.getRootPane().setDefaultButton(sure);
		sure.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "确定修改？","确定修改？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				String password = JTpassword.getText();
				String newpassword = JTnewpassword.getText();
				// TODO Auto-generated method stub
				if (answer == null || answer.isEmpty()) {
					if (password.equals(null) || newpassword.equals(null))
						JOptionPane.showMessageDialog(null, "填写新密码");
					if (!password.equals(newpassword)) {
						JOptionPane.showMessageDialog(null, "密码不一致");
					}
					else {
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							String num = Login.JTnum.getText();
							toServer.writeInt(3);//写出3 调用处理密保的方法
							toServer.writeUTF(Sid);
							toServer.writeInt(2);//写出2 调用修改密保的方法
							toServer.writeUTF(num);
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							//如果返回值为0则表示修改成功
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "修改成功");
								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "修改失败,长度不能超过20个字符");
							}
							//关闭
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						}
					}
				} 
				//用户已经设置了密保
				else {
					if (JTanswer.getText() == null
							|| JTanswer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "请填写答案");
						
					}  else if (password.isEmpty() || password == null
							|| newpassword.isEmpty() || newpassword == null)
						JOptionPane.showMessageDialog(null, "填写新密码");
					else if (!JTanswer.getText().equals(answer)) {
						JOptionPane.showMessageDialog(null, "答案错误");
					}
					else if (!password.equals(newpassword)) {
						JOptionPane.showMessageDialog(null, "密码不一致");
					} 
					else {
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							String num = Login.JTnum.getText();
							toServer.writeInt(3);//写出3 调用处理密保的方法
							toServer.writeUTF(Sid);
							toServer.writeInt(2);//写出2 调用修改
							toServer.writeUTF(num);
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "修改成功");
								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "修改失败,长度不能超过20个字符");
							}
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						}
					}
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
		frame.setTitle("修改密码");
		frame.setBounds(x, y, 552, 370);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
