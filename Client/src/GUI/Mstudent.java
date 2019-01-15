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
	String[] Sfind = { "ѧ��ѧ��", "ѧ������", "�鼮���", "�鼮����", "�鼮����", "�鼮����", "�鼮������",
			"�鼮����", "����ʱ��" };
	Object[][] ob = new Object[30][8];
	Font font = new Font("����", Font.BOLD, 20);
	Font font2 = new Font("����", Font.BOLD, 16);

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
//���ҷ�ʽ��ǩ
		JLabel JLfind = new JLabel("\u67E5\u8BE2\u65B9\u5F0F:");
		JLfind.setHorizontalAlignment(SwingConstants.RIGHT);
		JLfind.setFont(new Font("����", Font.BOLD, 20));
		JLfind.setBounds(10, 10, 113, 24);
		contentPane.add(JLfind);
//���ҷ�ʽ
		comboBox = new JComboBox(Sfind);
		comboBox.setBackground(Color.white);
		comboBox.setFont(font);
		comboBox.setBounds(133, 10, 120, 30);
		contentPane.add(comboBox);
//����--key
		JTfind = new JTextField();
		JTfind.setFont(font);
		JTfind.setBounds(260, 10, 270, 30);
		contentPane.add(JTfind);
		JTfind.setColumns(10);
//����ԤԼ����
		JButton JBapp = new JButton("\u5904\u7406\u8FC7\u671F\u9884\u7EA6");
		JBapp.setContentAreaFilled(false);
		JBapp.setFont(font);
		JBapp.setBounds(20, 44, 170, 33);
		contentPane.add(JBapp);
		JBapp.setToolTipText("Ĭ�ϰ�����ʱ������");
		JBapp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);//��ʽ11
					toServer.writeInt(1);//�ӷ�ʽ1
					toServer.writeUTF(comboBox.getSelectedItem().toString());//���ݲ��ҵķ�ʽ
					toServer.writeUTF(JTfind.getText());//����key
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

							ob[i][j++] = "˫��ɾ��ԤԼ";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "���޴˽��");
					}
					toServer.flush();
					toServer.close();
					fromServer.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "ѧ��", "����", "���", "����", "����", "ԤԼ����ʱ��", "����" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);//�������
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);//������ʾ
			}
		});
//������ڴ���
		JButton JBorerdue = new JButton("\u5904\u7406\u903E\u671F\u672A\u8FD8");
		JBorerdue.setContentAreaFilled(false);
		JBorerdue.setFont(font);
		JBorerdue.setBounds(216, 45, 170, 33);
		contentPane.add(JBorerdue);
		JBorerdue.setToolTipText("Ĭ�ϰ�����ʱ������");
		JBorerdue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);//��11
					toServer.writeInt(4);//�ӷ�ʽ4
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
							//��ȡ�����˼���
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
								ob[i][j++] =money;//ÿ��0.1Ԫ
							//System.out.println(ob[i][j]);
							else
								ob[i][j++] = 0;
							ob[i][j++] = "˫���鿴����";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "���޴˽��");
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
				String[] ss = { "ѧ��", "����", "���", "����", "����", "���鵽��ʱ��",
						"Ƿ��/(Ԫ)", "����" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//�鿴�������
		JButton JBborrow = new JButton("\u67E5\u770B\u501F\u4E66\u60C5\u51B5");
		JBborrow.setContentAreaFilled(false);
		JBborrow.setFont(font);
		JBborrow.setBounds(411, 44, 170, 33);
		contentPane.add(JBborrow);
		JBborrow.setToolTipText("Ĭ�ϰ�����ʱ������");
		JBborrow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					Socket socket = new Socket(Login.ip, 8181);
					DataOutputStream toServer = new DataOutputStream(socket
							.getOutputStream());
					toServer.writeInt(11);
					toServer.writeInt(2);//�ӷ�ʽ2
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
							ob[i][j++] = "˫���鿴";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "���޴˽��");
					}
					toServer.flush();
					toServer.close();
					// fromServer.close();
					fromServerOb.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "ѧ��", "����", "���", "����", "����", "����ʱ��", "����ʱ��",
						"����" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//�鿴�������
		JButton JBreturn = new JButton("\u67E5\u770B\u8FD8\u4E66\u60C5\u51B5");
		JBreturn.setContentAreaFilled(false);
		JBreturn.setFont(font);
		JBreturn.setBounds(609, 44, 170, 33);
		contentPane.add(JBreturn);
		JBreturn.setToolTipText("Ĭ�ϰ�����ʱ������");
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
					toServer.writeInt(3);//�ӷ�ʽ3
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
							ob[i][j++] = "˫���鿴";
							i++;
						}
					} else {
						ob = new Object[0][7];
						JOptionPane.showMessageDialog(null, "���޴˽��");
					}
					toServer.flush();
					toServer.close();
					// fromServer.close();
					fromServerOb.close();
					socket.close();
				} catch (Exception e) {
					// TODO: handle exception
				}
				String[] ss = { "ѧ��", "����", "���", "����", "����", "����ʱ��", "����" };
				table = new JTable(new MyTable(ob, ss));
				table.addMouseListener(tableaction);
				table.setRowHeight(26);
				table.setFont(font2);
				scrollPane.setViewportView(table);
			}
		});
//����
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 87, 887, 474);
		contentPane.add(scrollPane);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
//���
		table = new JTable();
		scrollPane.setViewportView(table);
//ɾ������ԤԼ����
		JButton JBallDel = new JButton(
				"\u5220\u9664\u6240\u6709\u8FC7\u671F\u9884\u7EA6");
		JBallDel.setFont(new Font("����", Font.BOLD, 20));
		JBallDel.setContentAreaFilled(false);
		JBallDel.setBounds(656, 7, 218, 30);
		contentPane.add(JBallDel);
		JBallDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int isYes=JOptionPane.showConfirmDialog(null, "ȷ��ɾ������ԤԼ���ڣ���", "ȷ��ɾ������ԤԼ���ڣ���", JOptionPane.YES_NO_OPTION);
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
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
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
				if (table.getValueAt(r, 6).equals("˫��ɾ��ԤԼ")) {
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
				//�������
				else if (table.getValueAt(r, 6).equals("˫���鿴")) {
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
				//��������
				else if (table.getValueAt(r, 7).equals("˫���鿴")) {
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
				//�������
				else if (table.getValueAt(r, 7).equals("˫���鿴����")) {
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
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("����ѧ������");
		frame.setBounds(x, y, 903, 600);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
