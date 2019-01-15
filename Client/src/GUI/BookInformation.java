package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class BookInformation {
	static JFrame frame=new JFrame();
	private JPanel contentPane;
	String Sno;//ѧ��
	String Sname;//
	String Sauthor;
	String Stranslator;
	String Ssort;
	String Spublish;
	String Snum;
	String Sstock;
	int Iacount;

	public BookInformation(String num, String no, String name, String author,
			String translator, String sort, String publish, String stock,
			int acount, int ok) {
		Student.information=1;
		Sno = no;
		Sname = name;
		Sauthor = author;
		Stranslator = translator;
		Ssort = sort;
		Spublish = publish;
		Snum = num;
		Sstock = stock;
		this.Iacount = acount;
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
		// ���
		JLabel JLno = new JLabel("\u7F16\u53F7\uFF1A");
		JLno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLno.setFont(new Font("����", Font.BOLD, 24));
		JLno.setBounds(186, 48, 126, 27);
		contentPane.add(JLno);
		JLabel JTno = new JLabel(no);
		JTno.setFont(new Font("����", Font.BOLD, 24));
		JTno.setBounds(322, 48, 112, 27);
		contentPane.add(JTno);
		// ����
		JLabel JLname = new JLabel("\u4E66\u540D\uFF1A");
		JLname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLname.setFont(new Font("����", Font.BOLD, 24));
		JLname.setBounds(23, 96, 126, 27);
		contentPane.add(JLname);

		JLabel JTname = new JLabel(name);
		JTname.setFont(new Font("����", Font.BOLD, 24));
		JTname.setBounds(159, 96, 437, 27);
		contentPane.add(JTname);
		// ������
		JLabel JLpublish = new JLabel("\u51FA\u7248\u793E\uFF1A");
		JLpublish.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpublish.setFont(new Font("����", Font.BOLD, 24));
		JLpublish.setBounds(10, 286, 139, 27);
		contentPane.add(JLpublish);
		JLabel JTpublish = new JLabel(publish);
		JTpublish.setFont(new Font("����", Font.BOLD, 24));
		JTpublish.setBounds(159, 286, 455, 27);
		contentPane.add(JTpublish);
		// ����
		JLabel JLauthor = new JLabel("\u4F5C\u8005\uFF1A");
		JLauthor.setHorizontalAlignment(SwingConstants.RIGHT);
		JLauthor.setFont(new Font("����", Font.BOLD, 24));
		JLauthor.setBounds(23, 144, 126, 27);
		contentPane.add(JLauthor);
		JLabel JTauthor = new JLabel(author);
		JTauthor.setFont(new Font("����", Font.BOLD, 24));
		JTauthor.setBounds(159, 144, 427, 27);
		contentPane.add(JTauthor);
		// ����
		JLabel JLtranslator = new JLabel("\u8BD1\u8005\uFF1A");
		JLtranslator.setHorizontalAlignment(SwingConstants.RIGHT);
		JLtranslator.setFont(new Font("����", Font.BOLD, 24));
		JLtranslator.setBounds(23, 192, 126, 27);
		contentPane.add(JLtranslator);
		JLabel JTtranslator = new JLabel(translator);
		JTtranslator.setFont(new Font("����", Font.BOLD, 24));
		JTtranslator.setBounds(159, 192, 416, 27);
		contentPane.add(JTtranslator);
		// ����
		JLabel JLsort = new JLabel("\u7C7B\u578B\uFF1A");
		JLsort.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsort.setFont(new Font("����", Font.BOLD, 24));
		JLsort.setBounds(23, 239, 126, 27);
		contentPane.add(JLsort);
		JLabel JTsort = new JLabel(sort);
		JTsort.setFont(new Font("����", Font.BOLD, 24));
		JTsort.setBounds(159, 239, 437, 27);
		contentPane.add(JTsort);
		if (ok == 1) {
			// ȷ��ԤԼ
			JButton JBsure = new JButton("\u786E\u5B9AԤԼ");
			JBsure.setFont(new Font("����", Font.BOLD, 20));
			JBsure.setBounds(318, 340, 150, 37);
			contentPane.add(JBsure);
			JBsure.setContentAreaFilled(false);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "ȷ��ԤԼ��","ȷ��ԤԼ��",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					Calendar now = Calendar.getInstance();
					int year = now.get(Calendar.YEAR);
					int month = now.get(Calendar.MONTH) + 1;
					int day = now.get(Calendar.DAY_OF_MONTH);
					String Syear = "" + year + "-" + month + "-" + day;
					try {
						Socket socket = new Socket(Login.ip, 8181);
						DataOutputStream toServer = new DataOutputStream(socket
								.getOutputStream());
						DataInputStream fromServer = new DataInputStream(socket
								.getInputStream());
						toServer.writeInt(6);//ԤԼ
						toServer.writeInt(1);
						toServer.writeUTF(Snum);
						toServer.writeUTF(Sno);
						int Istats = fromServer.readInt();
						int Inum = fromServer.readInt();
						if (Inum <= 0) {
							JOptionPane.showMessageDialog(null, "���鼮�Ѿ���������");
						} else if (Istats <= 0) {
							JOptionPane.showMessageDialog(null, "ʣ��ɽ��������");
						} else {
							toServer.writeUTF(Syear);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "ԤԼ�ɹ�");
								Student.JTcount.setText("" + (Iacount - 1));
								Student.count = Iacount - 1;
								frame.dispose();
							} else if (i == 2) {
								JOptionPane.showMessageDialog(null, "�Ѿ�ԤԼ�˸��鼮");
							}else if (i==3){
								JOptionPane.showMessageDialog(null, "�Ѿ����˸��鼮");
							}else if (i==4){
								JOptionPane.showMessageDialog(null, "���鼮�Ѿ��¼���");
							}
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						}
					} catch (IOException e) {
						// TODO: handle exception
					}
				}}
			});
			// ȡ��ԤԼ
			JButton JBrem = new JButton("����");
			JBrem.setFont(new Font("����", Font.BOLD, 20));
			JBrem.setBounds(159, 340, 150, 37);
			JBrem.setContentAreaFilled(false);
			contentPane.add(JBrem);
			JBrem.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		} else if (ok == 2) {
			// ȡ��ԤԼ
			JButton JBsure = new JButton("ȷ��ȡ��");
			JBsure.setFont(new Font("����", Font.BOLD, 20));
			JBsure.setBounds(318, 340, 150, 37);
			contentPane.add(JBsure);
			JBsure.setContentAreaFilled(false);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "ȷ��ȡ����","ȷ��ȡ����",JOptionPane.YES_NO_OPTION);
					if (isYES==JOptionPane.YES_OPTION){
					// TODO Auto-generated method stub
					try {
						Socket socket = new Socket(Login.ip, 8181);
						DataInputStream fromServer = new DataInputStream(socket
								.getInputStream());
						DataOutputStream toServer = new DataOutputStream(socket
								.getOutputStream());
						toServer.writeInt(7);//ȡ��ԤԼ
						toServer.writeUTF(Snum);
						toServer.writeUTF(Sno);
						int i = fromServer.readInt();
						if (i == 1) {
							JOptionPane.showMessageDialog(null, "ȡ���ɹ�");
							Student.JTcount.setText("" + (Iacount + 1));
							Student.count = Iacount + 1;
							frame.dispose();
						}else if (i==2){
							JOptionPane.showMessageDialog(null, "�Ѿ�ȡ���˸��鼮����ˢ��һ��");
							frame.dispose();
						}

						toServer.flush();
						toServer.close();
						fromServer.close();
						socket.close();
					} catch (IOException e) {
						// TODO: handle exception
					}
				}}
			});

			// ȡ��ԤԼ
			JButton JBrem = new JButton("����");
			JBrem.setFont(new Font("����", Font.BOLD, 20));
			JBrem.setBounds(159, 340, 150, 37);
			JBrem.setContentAreaFilled(false);
			contentPane.add(JBrem);
			JBrem.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		} else if (ok == 3) {
			// ȷ������
			JButton JBsure = new JButton("ȷ������");
			JBsure.setFont(new Font("����", Font.BOLD, 20));
			JBsure.setBounds(318, 340, 150, 37);
			contentPane.add(JBsure);
			JBsure.setContentAreaFilled(false);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int isYES=JOptionPane.showConfirmDialog(null, "ȷ�����裿","ȷ�����裿",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
					// TODO Auto-generated method stub
					try {
						Socket socket = new Socket(Login.ip, 8181);
						DataInputStream fromServer = new DataInputStream(socket
								.getInputStream());
						DataOutputStream toServer = new DataOutputStream(socket
								.getOutputStream());
						toServer.writeInt(8);//�������
						toServer.writeUTF(Login.JTnum.getText());
						toServer.writeInt(2);
						toServer.writeUTF(Sno);
						int i = fromServer.readInt();
						if (i == 1) {
							JOptionPane.showMessageDialog(null, "����ɹ�");
							frame.dispose();
						}else if (i==4)
							JOptionPane.showMessageDialog(null, "���鼮�Ѿ��¼�");
						toServer.flush();
						toServer.close();
						fromServer.close();
						socket.close();
					} catch (IOException e) {
						// TODO: handle exception
					}
				}}
			});

			// ȡ��ԤԼ
			JButton JBrem = new JButton("����");
			JBrem.setFont(new Font("����", Font.BOLD, 20));
			JBrem.setBounds(159, 340, 150, 37);
			JBrem.setContentAreaFilled(false);
			contentPane.add(JBrem);
			JBrem.addActionListener(new ActionListener() {

				
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					frame.dispose();
				}
			});
		} else if (ok == 4) {
			
		}
	}

	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("�鼮������Ϣ");
		frame.setBounds(x, y, 580, 450);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
