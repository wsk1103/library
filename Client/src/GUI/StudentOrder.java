package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;

import bean.book;

public class StudentOrder extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField JTfind;
	private JTable table;
	private JComboBox<Object> comboBox;
	Object[][] ob = new Object[0][8];
	String[] ss = { "编号", "书名", "作者", "译者", "出版社", "类型", "库存", "操作" };
	private JScrollPane scrollPane;
	private TableAction tableaction = new TableAction();
	Font font=new Font("楷体", Font.BOLD, 18);
	Font font2=new Font("楷体", Font.BOLD, 18);

	public StudentOrder() {
		setOpaque(false);
		setLayout(null);
		// 类型标签
		JLabel Find = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
		Find.setHorizontalAlignment(SwingConstants.LEFT);
		Find.setBounds(76, 10, 98, 27);
		Find.setFont(font2);
		add(Find);
		// 类型复选框
		String[] s = { "书名", "作者", "类型", "译者", "出版社","编号"};
		comboBox = new JComboBox<Object>(s);
		comboBox.setBackground(Color.white);
		comboBox.setBounds(184, 11, 87, 26);
		comboBox.setFont(font2);
		comboBox.setOpaque(true);
		add(comboBox);
		// 查找文本框
		JTfind = new JTextField();
		JTfind.setBounds(281, 11, 171, 27);
		JTfind.setFont(font2);
		add(JTfind);
		JTfind.setColumns(10);

		// 滑动文本框，用来存放表格
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 47, 994, 439);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);	
		add(scrollPane);

		table = new JTable(new MyTable(ob, ss));
		table.setFont(font2);
		table.setRowHeight(28);
		scrollPane.setViewportView(table);

		// 热门度排序
		JButton JBfind = new JButton("按热门度排序查询");
		JBfind.setToolTipText("支持模糊查询");
		JBfind.setContentAreaFilled(false);
		JBfind.setBounds(462, 10, 186, 28);
		JBfind.setFont(font2);
		add(JBfind);
		Student.frame.getRootPane().setDefaultButton(JBfind);
		JBfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(5);
					toServer.writeInt(1);
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					ObjectInputStream fromServer = new ObjectInputStream(socket
							.getInputStream());
					ArrayList<book> list = (ArrayList<book>) fromServer
							.readObject();
					int i = 0, j = 0;
					ob = new Object[list.size()][8];
					if (list.size() != 0) {
						for (book book : list) {
							if (j > 7) {
								j = 0;
							}
							ob[i][j++] = book.getNo();
							ob[i][j++] = book.getName();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getTranslator();
							ob[i][j++] = book.getPublsh();
							ob[i][j++] = book.getSort();
							ob[i][j++] = book.getStock();
							ob[i][j++] = "双击预约";
							i++;
						}

						table = new JTable(new MyTable(ob, ss));
						table.setFont(font);
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
//编号排序
		JButton JBfind2 = new JButton("按编号排序查询");
		JBfind2.setFont(font2);
		JBfind2.setBounds(668, 10, 186, 28);
		JBfind2.setToolTipText("支持模糊查询");
		JBfind2.setContentAreaFilled(false);
		add(JBfind2);

		JBfind2.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(5);//查询书籍
					toServer.writeInt(2);
					toServer.writeUTF(comboBox.getSelectedItem().toString());
					toServer.writeUTF(JTfind.getText());
					ObjectInputStream fromServer = new ObjectInputStream(socket
							.getInputStream());
					ArrayList<book> list = (ArrayList<book>) fromServer
							.readObject();
					int i = 0, j = 0;
					ob = new Object[list.size()][8];
					if (list.size() != 0) {
						for (book book : list) {
							if (j > 7) {
								j = 0;
							}
							ob[i][j++] = book.getNo();
							ob[i][j++] = book.getName();
							ob[i][j++] = book.getAuthor();
							ob[i][j++] = book.getTranslator();
							ob[i][j++] = book.getPublsh();
							ob[i][j++] = book.getSort();
							ob[i][j++] = book.getStock();
							ob[i][j++] = "双击预约";
							i++;
						}

						table = new JTable(new MyTable(ob, ss));
						table.setFont(font);
						table.setRowHeight(28);
						table.addMouseListener(tableaction);
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
		//时间排序
//		JButton JBfind3 = new JButton("按时间查询");
//		JBfind3.setToolTipText("\u652F\u6301\u6A21\u7CCA\u67E5\u8BE2");
//		JBfind3.setFont(new Font("楷体", Font.BOLD, 18));
//		JBfind3.setContentAreaFilled(false);
//		JBfind3.setBounds(772, 10, 129, 28);
//		add(JBfind3);
//		JBfind3.addActionListener(new ActionListener() {
//			
//			
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				try {
//					Socket socket = new Socket(Login.ip, 8181);
//					DataOutputStream toServer = new DataOutputStream(socket
//							.getOutputStream());
//					toServer.writeInt(5);//查询书籍
//					toServer.writeInt(3);
//					toServer.writeUTF(comboBox.getSelectedItem().toString());
//					toServer.writeUTF(JTfind.getText());
//					ObjectInputStream fromServer = new ObjectInputStream(socket
//							.getInputStream());
//					ArrayList<book> list = (ArrayList<book>) fromServer
//							.readObject();
//					int i = 0, j = 0;
//					ob = new Object[list.size()][8];
//					if (list.size() != 0) {
//						for (book book : list) {
//							if (j > 7) {
//								j = 0;
//							}
//							ob[i][j++] = book.getNo();
//							ob[i][j++] = book.getName();
//							ob[i][j++] = book.getAuthor();
//							ob[i][j++] = book.getTranslator();
//							ob[i][j++] = book.getPublsh();
//							ob[i][j++] = book.getSort();
//							ob[i][j++] = book.getStock();
//							ob[i][j++] = "双击预约";
//							i++;
//						}
//
//						table = new JTable(new MyTable(ob, ss));
//						table.setFont(font);
//						table.addMouseListener(tableaction);
//						table.setRowHeight(28);
//					} else {
//						JOptionPane.showMessageDialog(null, "未查询到结果");
//						ob = new Object[0][0];
//						table = new JTable(new MyTable(ob, ss));
//						table.setFont(font);
//					}
//					scrollPane.setViewportView(table);
//					toServer.flush();
//					toServer.close();
//					fromServer.close();
//					socket.close();
//				} catch (IOException e) {
//					// TODO: handle exception
//				} catch (ClassNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		});
	}

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
					Point p = Student.frame.getLocation();
					String Snum=Login.JTnum.getText();
					String Sstock=table.getValueAt(r, 6).toString();
					int acount=Student.count;
					int x = (int) p.getX();
					int y = (int) p.getY();
					BookInformation bookinformation = new BookInformation(Snum,
							BookNum, BookName, BookAuthor, BookTranslator,
							BookSort, BookPublish,Sstock,acount,1);
					bookinformation.show(x + 150, y + 150);
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
}
