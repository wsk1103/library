package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.*;

import bean.MstudentBean;

public class Information {
	static JFrame frame;
	private JPanel contentPane;
	private JTextField JTno;
	private JTextField JTSno;
	JRadioButton RBmale;
	JRadioButton RBfemale;
	ButtonGroup bg;
	String Sno;
	JTextField JTname, JTphone, JTemail, JTid;
	JButton JBsure;

	public Information(String no, String name, String sex, String phone,
			String id, String email, String xi, int i) {
		this.Sno = no;
		frame=new JFrame();
		Admit.information=3;
		contentPane = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/backgroud.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		// 添加管理员
		if (i == 2) {
			//学号
			JLabel JLno = new JLabel("\u5DE5\u53F7:");
			JLno.setHorizontalAlignment(SwingConstants.RIGHT);
			JLno.setFont(new Font("楷体", Font.BOLD, 20));
			JLno.setBounds(59, 47, 75, 35);
			contentPane.add(JLno);
//姓名标签
			JLabel JLname = new JLabel("\u540D\u79F0:");
			JLname.setHorizontalAlignment(SwingConstants.RIGHT);
			JLname.setFont(new Font("楷体", Font.BOLD, 20));
			JLname.setBounds(59, 92, 75, 37);
			contentPane.add(JLname);
//性别标签
			JLabel JLsex = new JLabel("\u6027\u522B:");
			JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
			JLsex.setFont(new Font("楷体", Font.BOLD, 20));
			JLsex.setBounds(59, 139, 75, 31);
			contentPane.add(JLsex);
//手机号码标签
			JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
			JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
			JLphone.setFont(new Font("楷体", Font.BOLD, 20));
			JLphone.setBounds(30, 180, 104, 35);
			contentPane.add(JLphone);
//email标签
			JLabel JLemail = new JLabel("\u90AE\u7BB1:");
			JLemail.setHorizontalAlignment(SwingConstants.RIGHT);
			JLemail.setFont(new Font("楷体", Font.BOLD, 20));
			JLemail.setBounds(59, 270, 75, 37);
			contentPane.add(JLemail);
//学号 -JT
			JTno = new JTextField();
			JTno.setFont(new Font("楷体", Font.BOLD, 20));
			JTno.setBounds(144, 47, 244, 34);
			contentPane.add(JTno);
			JTno.setColumns(10);
//姓名-JT
			JTname = new JTextField();
			JTname.setFont(new Font("楷体", Font.BOLD, 20));
			JTname.setColumns(10);
			JTname.setBounds(144, 93, 244, 34);
			contentPane.add(JTname);
//单选组合框
			bg = new ButtonGroup(); // 定义组
			//男
			RBmale = new JRadioButton("\u7537");
			RBmale.setSelected(true);
			RBmale.setOpaque(false);
			RBmale.setFont(new Font("楷体", Font.BOLD, 20));
			RBmale.setBounds(148, 143, 60, 23);
			contentPane.add(RBmale);
//女
			RBfemale = new JRadioButton("\u5973");
			RBfemale.setFont(new Font("楷体", Font.BOLD, 20));
			RBfemale.setBounds(211, 143, 60, 23);
			contentPane.add(RBfemale);
			RBfemale.setOpaque(false);
			bg.add(RBfemale);
			bg.add(RBmale);
//手机 -JT
			JTphone = new JTextField();
			JTphone.setFont(new Font("楷体", Font.BOLD, 20));
			JTphone.setColumns(10);
			JTphone.setBounds(144, 180, 244, 34);
			contentPane.add(JTphone);
//email-JT
			JTemail = new JTextField();
			JTemail.setFont(new Font("楷体", Font.BOLD, 20));
			JTemail.setColumns(10);
			JTemail.setBounds(144, 271, 244, 34);
			contentPane.add(JTemail);
//身份证号码 标签
			JLabel JLid = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801:");
			JLid.setHorizontalAlignment(SwingConstants.RIGHT);
			JLid.setFont(new Font("楷体", Font.BOLD, 20));
			JLid.setBounds(10, 225, 124, 35);
			contentPane.add(JLid);
//身份证 -JT
			JTid = new JTextField();
			JTid.setFont(new Font("楷体", Font.BOLD, 20));
			JTid.setColumns(10);
			JTid.setBounds(144, 225, 244, 34);
			contentPane.add(JTid);
//确定按钮
			JBsure = new JButton("\u786E\u5B9A");
			JBsure.setFont(new Font("楷体", Font.BOLD, 20));
			JBsure.setBounds(208, 335, 104, 35);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.setContentAreaFilled(false);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int isYES=JOptionPane.showConfirmDialog(null, "确定添加？","确定添加？",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					if (JTno.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "工号不能为空");
					} else if (JTname.getText().isEmpty()
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
							}
							else if (!JTemail.getText().isEmpty()&&!isEmail(JTemail.getText().trim()))
								JOptionPane.showMessageDialog(null, "邮箱格式不正确");
							else
								try {
									Integer.parseInt(JTno.getText().trim());
									// Long.parseLong(JTid.getText());
									Socket socket = new Socket(Login.ip, 8181);
									DataOutputStream toServer = new DataOutputStream(
											socket.getOutputStream());
									DataInputStream fromServer = new DataInputStream(
											socket.getInputStream());
									toServer.writeInt(13);
									toServer.writeInt(3);
									toServer.writeInt(2);
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
												"添加成功");
										frame.dispose();
									} else if (ok == 0) {
										JOptionPane.showMessageDialog(null,
												"添加失败，已经存在该工号");
									}
									socket.close();
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
						}
				}}
			});
//退出按钮
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
		// 添加学生用户
	 else
		if (i == 1) {
			//setSize(450, 300);
			//学号
			JLabel JLSno = new JLabel("学号:");
			JLSno.setHorizontalAlignment(SwingConstants.RIGHT);
			JLSno.setFont(new Font("楷体", Font.BOLD, 20));
			JLSno.setBounds(38, 140, 91, 35);
			contentPane.add(JLSno);
			JTSno = new JTextField();
			JTSno.setFont(new Font("楷体", Font.BOLD, 20));
			JTSno.setBounds(140, 140, 234, 30);
			contentPane.add(JTSno);
			JTSno.setColumns(10);
//确定按钮
			JBsure = new JButton("\u786E\u5B9A");
			JBsure.setFont(new Font("楷体", Font.BOLD, 20));
			JBsure.setBounds(163, 176, 115, 30);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.setContentAreaFilled(false);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					int isYES=JOptionPane.showConfirmDialog(null, "确定添加？","确定添加？",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					if (JTSno.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "学号不能为空");
					} else
						try {
							Integer.parseInt(JTSno.getText());
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							toServer.writeInt(13);
							toServer.writeInt(3);
							toServer.writeInt(1);

							toServer.writeUTF(JTSno.getText().trim());
							int ok = fromServer.readInt();
							if (ok == 1) {
								JOptionPane.showMessageDialog(null, "添加成功");
								frame.dispose();
							} else if (ok == 0) {
								JOptionPane.showMessageDialog(null,
										"添加失败,学校没有该学号");
							} else if (ok == 2) {
								JOptionPane.showMessageDialog(null, "该学生已经存在");
							}
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						} catch (NumberFormatException e) {
							JOptionPane.showMessageDialog(null,
									"学号输入格式错误，只能输入数字");
						}
				}}
			});
		}
		// 查看学生账号信息
		else if (i == 3) {
			frame.setBounds(350, 120, 450, 600);
			//学号
			JLabel JLno = new JLabel("\u5DE5\u53F7:");
			JLno.setHorizontalAlignment(SwingConstants.RIGHT);
			JLno.setFont(new Font("楷体", Font.BOLD, 20));
			JLno.setBounds(59, 23, 75, 35);
			contentPane.add(JLno);
			JLabel JTJLname = new JLabel(no);
			// JTno = new JTextField(no);
			JTJLname.setFont(new Font("楷体", Font.BOLD, 20));
			JTJLname.setBounds(144, 23, 244, 34);
			contentPane.add(JTJLname);
//姓名
			JLabel JLname = new JLabel("\u540D\u79F0:");
			JLname.setHorizontalAlignment(SwingConstants.RIGHT);
			JLname.setFont(new Font("楷体", Font.BOLD, 20));
			JLname.setBounds(59, 68, 75, 37);
			contentPane.add(JLname);
			JLabel JTname = new JLabel(name);
			JTname.setFont(new Font("楷体", Font.BOLD, 20));
			JTname.setBounds(144, 71, 244, 34);
			contentPane.add(JTname);
//性别
			JLabel JLsex = new JLabel("\u6027\u522B:");
			JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
			JLsex.setFont(new Font("楷体", Font.BOLD, 20));
			JLsex.setBounds(59, 115, 75, 31);
			contentPane.add(JLsex);
//			bg = new ButtonGroup(); // 定义组
//			RBmale = new JRadioButton("\u7537");
//			RBmale.setOpaque(false);
//			RBmale.setFont(new Font("楷体", Font.BOLD, 20));
//			RBmale.setBounds(140, 121, 60, 23);
//			contentPane.add(RBmale);
//性别单选框
//			RBfemale = new JRadioButton("\u5973");
//			RBfemale.setFont(new Font("楷体", Font.BOLD, 20));
//			RBfemale.setBounds(210, 121, 60, 23);
//			contentPane.add(RBfemale);
//			RBfemale.setOpaque(false);
//			bg.add(RBfemale);
//			bg.add(RBmale);
			JLabel JTsex=new JLabel();
			JTsex.setText(sex);
			JTsex.setFont(new Font("楷体", Font.BOLD, 20));
			JTsex.setBounds(140, 121, 60, 23);
			contentPane.add(JTsex);
//			if (sex.equals("男"))
//				RBmale.setSelected(true);
//			else
//				RBfemale.setSelected(true);
//班级
			JLabel JLclass = new JLabel("\u6240\u5728\u73ED\u7EA7:");
			JLclass.setHorizontalAlignment(SwingConstants.RIGHT);
			JLclass.setFont(new Font("楷体", Font.BOLD, 20));
			JLclass.setBounds(30, 156, 104, 35);
			contentPane.add(JLclass);
			JLabel JTclass = new JLabel(phone);
			JTclass.setFont(new Font("楷体", Font.BOLD, 20));
			JTclass.setBounds(144, 156, 244, 34);
			contentPane.add(JTclass);
//院
			JLabel JLyuan = new JLabel("\u6240\u5728\u9662:");
			JLyuan.setHorizontalAlignment(SwingConstants.RIGHT);
			JLyuan.setFont(new Font("楷体", Font.BOLD, 20));
			JLyuan.setBounds(59, 249, 75, 37);
			contentPane.add(JLyuan);
			JLabel JTyuan = new JLabel(email);
			JTyuan.setFont(new Font("楷体", Font.BOLD, 20));
			JTyuan.setBounds(144, 250, 244, 34);
			contentPane.add(JTyuan);
//系
			JLabel JLxi = new JLabel("\u6240\u5728\u7CFB:");
			JLxi.setHorizontalAlignment(SwingConstants.RIGHT);
			JLxi.setFont(new Font("楷体", Font.BOLD, 20));
			JLxi.setBounds(10, 204, 124, 35);
			contentPane.add(JLxi);
			JLabel JTxi = new JLabel(id);
			JTxi.setFont(new Font("楷体", Font.BOLD, 20));
			JTxi.setBounds(144, 204, 244, 34);
			contentPane.add(JTxi);
//手机号码
			JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
			JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
			JLphone.setFont(new Font("楷体", Font.BOLD, 20));
			JLphone.setBounds(30, 290, 104, 37);
			contentPane.add(JLphone);
			JLabel JTphone = new JLabel(xi);
			JTphone.setFont(new Font("楷体", Font.BOLD, 20));
			JTphone.setBounds(144, 290, 244, 34);
			contentPane.add(JTphone);
//密码，密保问题，密保答案
			String password = null, question = null, answer = null;
			try {
				Socket socket = new Socket(Login.ip, 8181);
				DataOutputStream toServer = new DataOutputStream(
						socket.getOutputStream());
				DataInputStream fromServer = new DataInputStream(
						socket.getInputStream());
				toServer.writeInt(13);
				toServer.writeInt(4);
				toServer.writeInt(1);
				toServer.writeUTF(no);
				password = fromServer.readUTF();
				question = fromServer.readUTF();
				answer = fromServer.readUTF();
				socket.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
//账号密码
			JLabel JLpassword = new JLabel("\u8D26\u53F7\u5BC6\u7801:");
			JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
			JLpassword.setFont(new Font("楷体", Font.BOLD, 20));
			JLpassword.setBounds(10, 330, 124, 37);
			contentPane.add(JLpassword);
			JLabel JTpassword = new JLabel(password);
			JTpassword.setFont(new Font("楷体", Font.BOLD, 20));
			JTpassword.setBounds(144, 330, 244, 34);
			contentPane.add(JTpassword);
//密保问题
			JLabel JLquestion = new JLabel("\u5BC6\u4FDD\u95EE\u9898:");
			JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
			JLquestion.setFont(new Font("楷体", Font.BOLD, 20));
			JLquestion.setBounds(10, 360, 124, 37);
			contentPane.add(JLquestion);
			JLabel JTquestion = new JLabel(question);
			JTquestion.setFont(new Font("楷体", Font.BOLD, 20));
			JTquestion.setBounds(144, 360, 244, 34);
			contentPane.add(JTquestion);
//密保答案
			JLabel JLanswer = new JLabel("密保答案:");
			JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
			JLanswer.setFont(new Font("楷体", Font.BOLD, 20));
			JLanswer.setBounds(10, 390, 124, 37);
			contentPane.add(JLanswer);
			JLabel JTanswer = new JLabel(answer);
			JTanswer.setFont(new Font("楷体", Font.BOLD, 20));
			JTanswer.setBounds(144, 390, 244, 34);
			contentPane.add(JTanswer);
//确定按钮
			JBsure = new JButton("\u786E\u5B9A");
			JBsure.setFont(new Font("楷体", Font.BOLD, 19));
			JBsure.setBounds(158, 420, 93, 35);
			contentPane.add(JBsure);
			JBsure.setContentAreaFilled(false);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});

		}
		// 查看，修改管理员信息
		else if (i == 4) {
			frame.setBounds(350, 120, 450, 600);
			//学号
			JLabel JLno = new JLabel("\u5DE5\u53F7:");
			JLno.setHorizontalAlignment(SwingConstants.RIGHT);
			JLno.setFont(new Font("楷体", Font.BOLD, 20));
			JLno.setBounds(59, 23, 75, 35);
			contentPane.add(JLno);
//姓名 标签
			JLabel JLname = new JLabel("\u540D\u79F0:");
			JLname.setHorizontalAlignment(SwingConstants.RIGHT);
			JLname.setFont(new Font("楷体", Font.BOLD, 20));
			JLname.setBounds(59, 68, 75, 37);
			contentPane.add(JLname);
//性别标签
			JLabel JLsex = new JLabel("\u6027\u522B:");
			JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
			JLsex.setFont(new Font("楷体", Font.BOLD, 20));
			JLsex.setBounds(59, 115, 75, 31);
			contentPane.add(JLsex);
//手机号码
			JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
			JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
			JLphone.setFont(new Font("楷体", Font.BOLD, 20));
			JLphone.setBounds(30, 156, 104, 35);
			contentPane.add(JLphone);
//邮箱 标签
			JLabel JLemail = new JLabel("\u90AE\u7BB1:");
			JLemail.setHorizontalAlignment(SwingConstants.RIGHT);
			JLemail.setFont(new Font("楷体", Font.BOLD, 20));
			JLemail.setBounds(59, 249, 75, 37);
			contentPane.add(JLemail);
//工号
			JLabel JTJLname = new JLabel(no);
			// JTno = new JTextField(no);
			JTJLname.setFont(new Font("楷体", Font.BOLD, 20));
			JTJLname.setBounds(144, 23, 244, 34);
			contentPane.add(JTJLname);
			// JTno.setColumns(10);
//姓名
			JTname = new JTextField(name);
			JTname.setFont(new Font("楷体", Font.BOLD, 20));
			JTname.setColumns(10);
			JTname.setBounds(144, 68, 244, 34);
			contentPane.add(JTname);
//男，单选框
			bg = new ButtonGroup(); // 定义组
			RBmale = new JRadioButton("\u7537");
			RBmale.setOpaque(false);
			RBmale.setFont(new Font("楷体", Font.BOLD, 20));
			RBmale.setBounds(140, 121, 60, 23);
			contentPane.add(RBmale);
//女，单选框
			RBfemale = new JRadioButton("\u5973");
			RBfemale.setFont(new Font("楷体", Font.BOLD, 20));
			RBfemale.setBounds(210, 121, 60, 23);
			contentPane.add(RBfemale);
			RBfemale.setOpaque(false);
			bg.add(RBfemale);
			bg.add(RBmale);
			if (sex.equals("男"))
				RBmale.setSelected(true);
			else
				RBfemale.setSelected(true);
//手机号码-JT
			JTphone = new JTextField(phone);
			JTphone.setFont(new Font("楷体", Font.BOLD, 20));
			JTphone.setColumns(10);
			JTphone.setBounds(144, 156, 244, 34);
			contentPane.add(JTphone);
//email =JT
			JTemail = new JTextField(email);
			JTemail.setFont(new Font("楷体", Font.BOLD, 20));
			JTemail.setColumns(10);
			JTemail.setBounds(144, 250, 244, 34);
			contentPane.add(JTemail);
//身份证号码标签
			JLabel JLid = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801:");
			JLid.setHorizontalAlignment(SwingConstants.RIGHT);
			JLid.setFont(new Font("楷体", Font.BOLD, 20));
			JLid.setBounds(10, 204, 124, 35);
			contentPane.add(JLid);
			JTid = new JTextField(id);
			JTid.setFont(new Font("楷体", Font.BOLD, 20));
			JTid.setColumns(10);
			JTid.setBounds(144, 200, 244, 34);
			contentPane.add(JTid);
			//连接服务器，获取密码，密保问题，密保答案
			String password = null, question = null, answer = null;
			try {
				Socket socket = new Socket(Login.ip, 8181);
				DataOutputStream toServer = new DataOutputStream(
						socket.getOutputStream());
				DataInputStream fromServer = new DataInputStream(
						socket.getInputStream());
				toServer.writeInt(13);
				toServer.writeInt(4);
				toServer.writeInt(2);
				toServer.writeUTF(no);
				password = fromServer.readUTF();
				question = fromServer.readUTF();
				answer = fromServer.readUTF();
				socket.close();
			} catch (IOException e) {
				// TODO: handle exception
			}
			//账号密码
			JLabel JLpassword = new JLabel("账号密码:");
			JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
			JLpassword.setFont(new Font("楷体", Font.BOLD, 20));
			JLpassword.setBounds(10, 350, 124, 35);
			contentPane.add(JLpassword);
			JLabel JTpassword = new JLabel(password);
			JTpassword.setFont(new Font("楷体", Font.BOLD, 20));
			JTpassword.setBounds(144, 350, 244, 35);
			contentPane.add(JTpassword);
			//密保问题
			JLabel JLquestion = new JLabel("密保问题:");
			JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
			JLquestion.setFont(new Font("楷体", Font.BOLD, 20));
			JLquestion.setBounds(10, 380, 124, 35);
			contentPane.add(JLquestion);
			JLabel JTquestion = new JLabel(question);
			JTquestion.setFont(new Font("楷体", Font.BOLD, 20));
			JTquestion.setBounds(144, 380, 244, 35);
			contentPane.add(JTquestion);
			//密保答案
			JLabel JLanswer = new JLabel("密保答案:");
			JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
			JLanswer.setFont(new Font("楷体", Font.BOLD, 20));
			JLanswer.setBounds(10, 410, 124, 35);
			contentPane.add(JLanswer);
			JLabel JTanswer = new JLabel(answer);
			JTanswer.setFont(new Font("楷体", Font.BOLD, 20));
			JTanswer.setBounds(144, 410, 244, 35);
			contentPane.add(JTanswer);
//确定按钮
			JBsure = new JButton("\u786E\u5B9A");
			JBsure.setFont(new Font("楷体", Font.BOLD, 20));
			JBsure.setBounds(210, 307, 104, 35);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);//设置默认按钮，=回车
			JBsure.setContentAreaFilled(false);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "确定修改？","确定修改？",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					// TODO Auto-generated method stub
					if (JTname.getText().length() > 25
							|| JTname.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "姓名不能为空,不能超过25个字符");
					else if (JTphone.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "手机号码不能为空");
					else if (!isPhone(JTphone.getText()))
						JOptionPane.showMessageDialog(null, "手机号码格式不正确");
					else
						try {
							if (JTid.getText().isEmpty())
								JOptionPane
										.showMessageDialog(null, "身份证号码不能为空");
							else if (!isID.IDCardValidate(JTid.getText()))
								JOptionPane.showMessageDialog(null, "身份证号码不正确");
							else if (!JTemail.getText().isEmpty()
									&& !isEmail(JTemail.getText().trim()))
								JOptionPane.showMessageDialog(null, "邮箱格式不正确");
							else
								try {
									Socket socket = new Socket(Login.ip, 8181);
									DataOutputStream toServer = new DataOutputStream(
											socket.getOutputStream());
									DataInputStream fromServer = new DataInputStream(
											socket.getInputStream());
									toServer.writeInt(13);
									toServer.writeInt(2);
									ArrayList<MstudentBean> list = new ArrayList<MstudentBean>();
									MstudentBean ms = new MstudentBean();
									ms.setSNo(Sno);
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
												"修改成功");
										frame.dispose();
									}
									socket.close();
								} catch (IOException e) {
									// TODO: handle exception
								} 
						} catch (HeadlessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}}
			});
//退出按钮
			JButton JBexit = new JButton("\u53D6\u6D88");
			JBexit.setFont(new Font("楷体", Font.BOLD, 20));
			JBexit.setBounds(96, 307, 104, 35);
			contentPane.add(JBexit);
			JBexit.setContentAreaFilled(false);

			JBexit.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		}

	}
//显示
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("学生/管理员信息");
		frame.setBounds(x, y, 450, 500);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

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
}
