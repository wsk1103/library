package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;

import bean.MstudentBean;

public class yijiaoquanxian {
	static JFrame frame;
	private JTextField JTno;
	JRadioButton RBmale;
	JRadioButton RBfemale;
	ButtonGroup bg;
	String Sno;
	JTextField JTname, JTphone, JTemail, JTid;
	JButton JBsure;

	/**
	 * Launch the application.
	 */
	// public static void main(String[] args) {
	// EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// try {
	// yijiaoquanxian frame = new yijiaoquanxian();
	// frame.setVisible(true);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// });
	// }
	// public static void main(String[] args) {
	// yijiaoquanxian yi=new yijiaoquanxian();
	// yi.show(400, 100);
	// }

	/**
	 * Create the frame.
	 */
	public yijiaoquanxian() {
		frame = new JFrame();
		Admit.yijiao = 1;
		JPanel contentPane = new JPanel() {
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
		// 学号
		JLabel JLno = new JLabel("\u5DE5\u53F7:");
		JLno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLno.setFont(new Font("楷体", Font.BOLD, 20));
		JLno.setBounds(59, 47, 75, 35);
		contentPane.add(JLno);
		// 姓名标签
		JLabel JLname = new JLabel("\u540D\u79F0:");
		JLname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLname.setFont(new Font("楷体", Font.BOLD, 20));
		JLname.setBounds(59, 92, 75, 37);
		contentPane.add(JLname);
		// 性别标签
		JLabel JLsex = new JLabel("\u6027\u522B:");
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setFont(new Font("楷体", Font.BOLD, 20));
		JLsex.setBounds(59, 139, 75, 31);
		contentPane.add(JLsex);
		// 手机号码标签
		JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
		JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
		JLphone.setFont(new Font("楷体", Font.BOLD, 20));
		JLphone.setBounds(30, 180, 104, 35);
		contentPane.add(JLphone);
		// email标签
		JLabel JLemail = new JLabel("\u90AE\u7BB1:");
		JLemail.setHorizontalAlignment(SwingConstants.RIGHT);
		JLemail.setFont(new Font("楷体", Font.BOLD, 20));
		JLemail.setBounds(59, 270, 75, 37);
		contentPane.add(JLemail);
		// 学号 -JT
		JTno = new JTextField();
		JTno.setFont(new Font("楷体", Font.BOLD, 20));
		JTno.setBounds(144, 47, 244, 34);
		contentPane.add(JTno);
		JTno.setColumns(10);
		// 姓名-JT
		JTname = new JTextField();
		JTname.setFont(new Font("楷体", Font.BOLD, 20));
		JTname.setColumns(10);
		JTname.setBounds(144, 93, 244, 34);
		contentPane.add(JTname);
		// 单选组合框
		bg = new ButtonGroup(); // 定义组
		// 男
		RBmale = new JRadioButton("\u7537");
		RBmale.setSelected(true);
		RBmale.setOpaque(false);
		RBmale.setFont(new Font("楷体", Font.BOLD, 20));
		RBmale.setBounds(148, 143, 60, 23);
		contentPane.add(RBmale);
		// 女
		RBfemale = new JRadioButton("\u5973");
		RBfemale.setFont(new Font("楷体", Font.BOLD, 20));
		RBfemale.setBounds(211, 143, 60, 23);
		contentPane.add(RBfemale);
		RBfemale.setOpaque(false);
		bg.add(RBfemale);
		bg.add(RBmale);
		// 手机 -JT
		JTphone = new JTextField();
		JTphone.setFont(new Font("楷体", Font.BOLD, 20));
		JTphone.setColumns(10);
		JTphone.setBounds(144, 180, 244, 34);
		contentPane.add(JTphone);
		// email-JT
		JTemail = new JTextField();
		JTemail.setFont(new Font("楷体", Font.BOLD, 20));
		JTemail.setColumns(10);
		JTemail.setBounds(144, 271, 244, 34);
		contentPane.add(JTemail);
		// 身份证号码 标签
		JLabel JLid = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801:");
		JLid.setHorizontalAlignment(SwingConstants.RIGHT);
		JLid.setFont(new Font("楷体", Font.BOLD, 20));
		JLid.setBounds(10, 225, 124, 35);
		contentPane.add(JLid);
		// 身份证 -JT
		JTid = new JTextField();
		JTid.setFont(new Font("楷体", Font.BOLD, 20));
		JTid.setColumns(10);
		JTid.setBounds(144, 225, 244, 34);
		contentPane.add(JTid);
		// 确定按钮
		JBsure = new JButton("\u786E\u5B9A");
		JBsure.setFont(new Font("楷体", Font.BOLD, 20));
		JBsure.setBounds(208, 335, 104, 35);
		contentPane.add(JBsure);
		frame.getRootPane().setDefaultButton(JBsure);
		JBsure.setContentAreaFilled(false);
		JBsure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "确定退出？","确定退出？",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){

				// TODO Auto-generated method stub
				if (JTno.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "工号不能为空");
				} else if (JTno.getText().length() >= 20)
					JOptionPane.showMessageDialog(null, "工号过长，不能超过20个字符");
				else if (JTname.getText().isEmpty()
						|| JTname.getText().length() > 25)
					JOptionPane.showMessageDialog(null, "姓名不能为空,不能超过25个字符");
				else if (JTphone.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "手机号码不能为空");
				else if (!isPhone(JTphone.getText()))
					JOptionPane.showMessageDialog(null, "手机号码格式不正确");
				else
					try {
						if (!isID.IDCardValidate(JTid.getText())) {
							JOptionPane.showMessageDialog(null, "身份证号码不正确");
						} else if (!isEmail(JTemail.getText().trim()))
							JOptionPane.showMessageDialog(null, "邮箱格式不正确");
						else
							try {
								Integer.parseInt(JTno.getText().trim());
								// Long.parseLong(JTid.getText());
								int isYes = JOptionPane.showConfirmDialog(null,
										"确定移交！？", "移交权限",
										JOptionPane.YES_NO_OPTION);
								if (isYes == JOptionPane.YES_OPTION) {
									Socket socket = new Socket(Login.ip, 8181);
									DataOutputStream toServer = new DataOutputStream(
											socket.getOutputStream());
									DataInputStream fromServer = new DataInputStream(
											socket.getInputStream());
									toServer.writeInt(13);
									toServer.writeInt(5);
									// toServer.writeInt(2);
									String sid = Login.JTnum.getText();
									toServer.writeUTF(sid);
									ArrayList<MstudentBean> list = new ArrayList<MstudentBean>();
									MstudentBean ms = new MstudentBean();
									ms.setSNo(JTno.getText());
									ms.setSName(JTname.getText());
									if (RBmale.isSelected())
										ms.setBno("男");
									else if (RBfemale.isSelected())
										ms.setBno("女");
									ms.setTime(JTphone.getText());
									ms.setOrderTime(JTemail.getText());
									ms.setAuthor(JTid.getText());
									list.add(ms);
									ObjectOutputStream toServerOb = new ObjectOutputStream(
											socket.getOutputStream());
									toServerOb.writeObject(list);
									int ok = fromServer.readInt();
									if (ok == 1) {
										JOptionPane.showMessageDialog(null,
												"移交成功");
										frame.dispose();
										Admit.frame.dispose();
										Login l = new Login();
										int x = Toolkit.getDefaultToolkit()
												.getScreenSize().width;
										int y = Toolkit.getDefaultToolkit()
												.getScreenSize().height;
										l.show(x / 3, y / 4);
									} else if (ok == 0) {
										JOptionPane.showMessageDialog(null,
												"移交失败，已经存在该工号");
									}
									socket.close();
								}
							} catch (IOException e) {
								// TODO: handle exception
							} catch (NumberFormatException e) {
								e.printStackTrace();
								JOptionPane.showMessageDialog(null,
										"工号输入格式错误，只能输入数字");
							}

					} catch (HeadlessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (java.text.ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		// 退出按钮
		JButton JBexit = new JButton("\u53D6\u6D88");
		JBexit.setFont(new Font("楷体", Font.BOLD, 20));
		JBexit.setBounds(96, 335, 104, 35);
		contentPane.add(JBexit);
		JBexit.setContentAreaFilled(false);

		JBexit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				frame.dispose();
			}
		});
	}

	// 判断邮箱格式
	public boolean isEmail(String email) {
		Pattern p = Pattern
				.compile("^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\\.([a-zA-Z0-9_-])+)+$");
		Matcher m = p.matcher(email);
		// Mather m = p.matcher("wangxu198709@gmail.com.cn");这种也是可以的！
		boolean b = m.matches();
		return b;
	}

	// 判断手机号码是否正确
	public boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("^(13|15|18)\\d{9}$");
		Matcher matcher = pattern.matcher(phone);

		if (matcher.matches()) {
			return true;
		}
		return false;
	}

	// 显示
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("超级管理员权限移交");
		frame.setBounds(x, y, 450, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
