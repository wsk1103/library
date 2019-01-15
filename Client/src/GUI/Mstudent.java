package GUI;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.*;

import bean.MstudentBean;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Mstudent {
	static JFrame frame;
	private JPanel contentPane;
	private JTextField JTfind;
	private JTable table;
	JScrollPane scrollPane;
	JComboBox comboBox;
	private TableAction tableaction = new TableAction();
	String[] Sfind = { "学生学号", "学生姓名", "书籍编号", "书籍名称", "书籍作者", "书籍译者", "书籍出版社",
			"书籍类型", "进货时间" };
	Object[][] ob = new Object[30][8];
	Font font = new Font("楷体", Font.BOLD, 20);
	Font font2 = new Font("楷体", Font.BOLD, 16);

//	public static void main(String[] args) {
//		Mstudent ms = new Mstudent();
//		ms.show(300, 100);
//	}
	public Mstudent() {
		frame=new JFrame();
		Admit.ok=1;
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
//查找方式标签
		JLabel JLfind = new JLabel("\u67E5\u8BE2\u65B9\u5F0F:");
		JLfind.setHorizontalAlignment(SwingConstants.RIGHT);
		JLfind.setFont(new Font("宋体", Font.BOLD, 20));
		JLfind.setBounds(10, 10, 113, 24);
		contentPane.add(JLfind);
//查找方式
		comboBox = new JComboBox(Sfind);
		comboBox.setBackground(Color.white);
		comboBox.setFont(font);
		comboBox.setBounds(133, 10, 120, 30);
		contentPane.add(comboBox);
//查找--key
		JTfind = new JTextField();
		JTfind.setFont(font);
		JTfind.setBounds(260, 10, 270, 30);
		contentPane.add(JTfind);
		JTfind.setColumns(10);
//处理预约过期
		JButton JBapp = new JButton("\u5904\u7406\u8FC7\u671F\u9884\u7EA6");
		JBapp.setContentAreaFilled(false);
		JBapp.setFont(font);
		JBapp.setBounds(20, 44, 170, 33);
		contentPane.add(JBapp);
		JBapp.setToolTipText("默认按逾期时间排序");
		JBapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);//方式11
					toServer.writeInt(1);//子方式1
					toServer.writeUTF(comboBox.getSelectedItem().toString());//传递查找的方式
					toServer.writeUTF(JTfind.getText());//传递key
					ObjectInputStream fromServer = new ObjectInputStream(socket
							.getInputStream());
					ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromServer
							.readObject();
					ob = new Object[list.size()][8];
					if (list.size() != 0) {
						int i = 0, j = 0;
						for (MstudentBean book : list) {
							if (j > 6)
								j = 0;
							ob[i][j++] = book.getSNo();
							ob[i][j++] = book.getSName();
							ob[i][j++] = book.getBno();
							ob[i][j++] = book.getBname();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getOrderTime();

							ob[i][j++] = "双击删除预约";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "查无此结果");
					}
					toServer.flush();
					toServer.close();
					fromServer.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "学号", "姓名", "编号", "书名", "作者", "预约到期时间", "操作" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);//点击监听
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);//设置显示
			}
		});
//借书过期处理
		JButton JBorerdue = new JButton("\u5904\u7406\u903E\u671F\u672A\u8FD8");
		JBorerdue.setContentAreaFilled(false);
		JBorerdue.setFont(font);
		JBorerdue.setBounds(216, 45, 170, 33);
		contentPane.add(JBorerdue);
		JBorerdue.setToolTipText("默认按逾期时间排序");
		JBorerdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);//方11
					toServer.writeInt(4);//子方式4
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					ObjectInputStream fromServerOb = new ObjectInputStream(
							socket.getInputStream());
					// toServer.writeUTF(arg0);
					ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromServerOb
							.readObject();
					ob = new Object[list.size()][8];
					int i = 0, j = 0;
					if (list.size() != 0) {
						for (MstudentBean book : list) {
							if (j > 7)
								j = 0;
							ob[i][j++] = book.getSNo();
							ob[i][j++] = book.getSName();
							ob[i][j++] = book.getBno();
							ob[i][j++] = book.getBname();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getOrderTime();
							//获取逾期了几天
							String overdue = book.getOrderTime();
							Date Soverdue;
							Date now = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat(
									"yy-MM-dd");
							String Snow = sdf.format(now);
							now = sdf.parse(Snow);
							Soverdue = sdf.parse(overdue);
							long l = now.getTime() - Soverdue.getTime();
							long day = l / (24 * 60 * 60 * 1000);
							float money=(float) (day * 0.1*100)/100;
							if (money >= 0)
								ob[i][j++] =money;//每天0.1元
							//System.out.println(ob[i][j]);
							else
								ob[i][j++] = 0;
							ob[i][j++] = "双击查看详情";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "查无此结果");
					}
					toServer.flush();
					toServer.close();
					// fromServer.close();
					fromServerOb.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				// TODO Auto-generated method stub
				String[] ss = { "学号", "姓名", "编号", "书名", "作者", "借书到期时间",
						"欠费/(元)", "操作" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//查看借书情况
		JButton JBborrow = new JButton("\u67E5\u770B\u501F\u4E66\u60C5\u51B5");
		JBborrow.setContentAreaFilled(false);
		JBborrow.setFont(font);
		JBborrow.setBounds(411, 44, 170, 33);
		contentPane.add(JBborrow);
		JBborrow.setToolTipText("默认按借书时间排序");
		JBborrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);
					toServer.writeInt(2);//子方式2
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					ObjectInputStream fromServerOb = new ObjectInputStream(
							socket.getInputStream());
					ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromServerOb
							.readObject();
					ob = new Object[list.size()][8];
					int i = 0, j = 0;
					if (list.size() != 0) {
						for (MstudentBean book : list) {
							if (j > 7)
								j = 0;
							ob[i][j++] = book.getSNo();
							ob[i][j++] = book.getSName();
							ob[i][j++] = book.getBno();
							ob[i][j++] = book.getBname();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getTime();
							ob[i][j++] = book.getOrderTime();
							ob[i][j++] = "双击查看";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "查无此结果");
					}
					toServer.flush();
					toServer.close();
					// fromServer.close();
					fromServerOb.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "学号", "姓名", "编号", "书名", "作者", "借书时间", "到期时间",
						"操作" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//查看还书情况
		JButton JBreturn = new JButton("\u67E5\u770B\u8FD8\u4E66\u60C5\u51B5");
		JBreturn.setContentAreaFilled(false);
		JBreturn.setFont(font);
		JBreturn.setBounds(609, 44, 170, 33);
		contentPane.add(JBreturn);
		JBreturn.setToolTipText("默认按还书时间排序");
		JBreturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					// DataInputStream fromServer=new
					// DataInputStream(socket.getInputStream());
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);
					toServer.writeInt(3);//子方式3
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					ObjectInputStream fromServerOb = new ObjectInputStream(
							socket.getInputStream());
					ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromServerOb
							.readObject();
					ob = new Object[list.size()][7];
					int i = 0, j = 0;
					if (list.size() != 0) {
						for (MstudentBean book : list) {
							if (j > 6)
								j = 0;
							ob[i][j++] = book.getSNo();
							ob[i][j++] = book.getSName();
							ob[i][j++] = book.getBno();
							ob[i][j++] = book.getBname();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getTime();
							ob[i][j++] = "双击查看";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "查无此结果");
					}
					toServer.flush();
					toServer.close();
					// fromServer.close();
					fromServerOb.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "学号", "姓名", "编号", "书名", "作者", "还书时间", "操作" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//滑板
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 87, 887, 474);
		contentPane.add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
//表格
		table = new JTable();
		scrollPane.setViewportView(table);
//删除所有预约过期
		JButton JBallDel = new JButton(
				"\u5220\u9664\u6240\u6709\u8FC7\u671F\u9884\u7EA6");
		JBallDel.setFont(new Font("楷体", Font.BOLD, 20));
		JBallDel.setContentAreaFilled(false);
		JBallDel.setBounds(656, 7, 218, 30);
		contentPane.add(JBallDel);
		JBallDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int isYes=JOptionPane.showConfirmDialog(null, "确定删除所有预约过期？！", "确定删除所有预约过期？！", JOptionPane.YES_NO_OPTION);
				// TODO Auto-generated method stub
				if (isYes==JOptionPane.YES_OPTION){
				try {
					Socket socket=new Socket(Login.ip,8181);
					DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
					toServer.writeInt(11);
					toServer.writeInt(5);
					DataInputStream fromServer=new DataInputStream(socket.getInputStream());
					int i=fromServer.readInt();
					//System.out.println(i);
					if (i==-1393754107){
						JOptionPane.showMessageDialog(null, "删除成功");
					}
					socket.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}}
		});

	}

	class TableAction implements MouseListener {
		public void mouseClicked(MouseEvent e) {
			int Mousercount = e.getClickCount();
			if (Mousercount == 2) {
				int r = table.getSelectedRow();
				//System.out.println(r);
				if (table.getValueAt(r, 6).equals("双击删除预约")) {
					String sno = table.getValueAt(r, 0).toString();
					String bno = table.getValueAt(r, 2).toString();
					String overdue = table.getValueAt(r, 5).toString();
					StudentInformation si = new StudentInformation(sno, bno,
							null, overdue, 1);
					Point p = frame.getLocation();
					int x = (int) p.getX() + 200;
					int y = (int) p.getY() + 50;
					si.show(x, y);
				} 
				//还书情况
				else if (table.getValueAt(r, 6).equals("双击查看")) {
					//System.out.println("yes");
					String sno = table.getValueAt(r, 0).toString();
					String bno = table.getValueAt(r, 2).toString();
					String time = table.getValueAt(r, 5).toString();
					StudentInformation si = new StudentInformation(sno, bno,
							time, null, 4);
					Point p = frame.getLocation();
					int x = (int) p.getX() + 200;
					int y = (int) p.getY() + 50;
					si.show(x, y);
				} 
				//借书逾期
				else if (table.getValueAt(r, 7).equals("双击查看")) {
					String sno = table.getValueAt(r, 0).toString();
					String bno = table.getValueAt(r, 2).toString();
					String overdue = table.getValueAt(r, 6).toString();
					String time = table.getValueAt(r, 5).toString();
					StudentInformation si = new StudentInformation(sno, bno,
							time, overdue, 3);
					Point p = frame.getLocation();
					int x = (int) p.getX() + 200;
					int y = (int) p.getY() + 50;
					si.show(x, y);
					;
				}
				//借书情况
				else if (table.getValueAt(r, 7).equals("双击查看详情")) {
					String sno = table.getValueAt(r, 0).toString();
					String bno = table.getValueAt(r, 2).toString();
					String overdue = table.getValueAt(r, 5).toString();
					String time = table.getValueAt(r, 6).toString();
					StudentInformation si = new StudentInformation(sno, bno,
							time, overdue, 2);
					Point p = frame.getLocation();
					int x = (int) p.getX() + 200;
					int y = (int) p.getY() + 50;
					si.show(x, y);
				}
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

	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("管理学生界面");
		frame.setBounds(x, y, 903, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
