package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JMenuItem;

public class ForgetPassword {

	private JPanel contentPane;
	private JFrame frame;
	private JTextField JTanswer;
	private JPasswordField JTpassword;
	private JPasswordField JTnewpassword;
	private JPanel panel;
	private String Squestion;
	private String Sanswer;
	Font font=new Font("����", Font.BOLD,20);
	Font font2=new Font("����", Font.BOLD,16);

	public ForgetPassword() {
		Squestion = getJTquestion();
		frame = new JFrame();
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.getContentPane().setLayout(null);
		panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/05.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		panel.setOpaque(true);
		panel.setBounds(0, 0, 567, 366);
		contentPane.add(panel);
		panel.setLayout(null);
		// �ܱ�����
		JLabel JLquestion = new JLabel("\u5BC6\u4FDD\u95EE\u9898\uFF1A");
		JLquestion.setBounds(60, 46, 145, 33);
		JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
		JLquestion.setFont(font);
		panel.add(JLquestion);
		JLabel JTquestion = new JLabel(Squestion);
		JTquestion.setBounds(214, 46, 300, 33);
		JTquestion.setHorizontalAlignment(SwingConstants.LEFT);
		JTquestion.setFont(new Font("Serif", Font.BOLD, 18));
		panel.add(JTquestion);
		// �ܱ��� ��ǩ
		JLabel JLanswer = new JLabel("\u5BC6\u4FDD\u7B54\u6848\uFF1A");
		JLanswer.setBounds(60, 104, 145, 33);
		JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
		JLanswer.setFont(font);
		panel.add(JLanswer);
		JTanswer = new JTextField();
		JTanswer.setBounds(215, 105, 224, 33);
		JTanswer.setFont(new Font("Serif", Font.BOLD, 18));
		panel.add(JTanswer);
		JTanswer.setColumns(10);
		// ����
		JLabel JLpassword = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		JLpassword.setBounds(60, 167, 145, 33);
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setFont(font);
		panel.add(JLpassword);
		JTpassword = new JPasswordField();
		JTpassword.setFont(new Font("Serif", Font.BOLD, 18));
		JTpassword.setBounds(215, 168, 224, 33);
		JTpassword.setColumns(10);
		panel.add(JTpassword);
		// ȷ��������
		JLabel JLnewpassword = new JLabel(
				"\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		JLnewpassword.setBounds(60, 221, 145, 33);
		JLnewpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnewpassword.setFont(font);
		panel.add(JLnewpassword);
		JTnewpassword = new JPasswordField();
		JTnewpassword.setFont(new Font("Serif", Font.BOLD, 18));
		JTnewpassword.setBounds(215, 222, 224, 33);
		JTnewpassword.setColumns(10);
		panel.add(JTnewpassword);

		// ���ð�ť
		JButton rem = new JButton("\u91CD\u7F6E");
		rem.setBounds(151, 285, 102, 33);
		rem.setContentAreaFilled(false);
		rem.setFont(new Font("����", Font.BOLD, 18));
		panel.add(rem);
		rem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JTanswer.setText(null);
				JTpassword.setText(null);
				JTnewpassword.setText(null);
			}
		});
		// ȷ����ť
		JButton sure = new JButton("\u786E\u5B9A");
		sure.setBounds(263, 285, 102, 33);
		sure.setContentAreaFilled(false);
		sure.setFont(new Font("����", Font.BOLD, 18));
		panel.add(sure);
		frame.getRootPane().setDefaultButton(sure);
		sure.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				if (Sanswer.equals("")) {
					if (JTpassword.getText().isEmpty()
							|| JTnewpassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					} else if (!JTpassword.getText().equals(
							JTnewpassword.getText()))
						JOptionPane.showMessageDialog(null, "������������벻һ��");
					else if (!JTanswer.getText().equals(Sanswer))
						JOptionPane.showMessageDialog(null, "�𰸴���");

					else {
						// TODO Auto-generated method stub
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							toServer.writeInt(3);//�޸�����
							toServer.writeUTF(ForgetLoading.CBid.getSelectedItem().toString());
							toServer.writeInt(2);
							if (JTpassword.getText().length()>18){
								JOptionPane.showMessageDialog(null, "�������18���ַ�");
							}			
							else {
							toServer.writeUTF(ForgetLoading.JTnum.getText());
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								frame.dispose();
							} else
								JOptionPane.showMessageDialog(null,
										"�޸�ʧ��,���벻�ܴ���18���ַ���");
							}
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} else {
					if (JTanswer.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "�𰸲���Ϊ��");
					else if (JTpassword.getText().isEmpty()
							|| JTnewpassword.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "���벻��Ϊ��");
					} else if (!JTpassword.getText().equals(
							JTnewpassword.getText()))
						JOptionPane.showMessageDialog(null, "������������벻һ��");
					else if (!JTanswer.getText().equals(Sanswer))
						JOptionPane.showMessageDialog(null, "�𰸴���");

					else {
						// TODO Auto-generated method stub
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							toServer.writeInt(3);
							toServer.writeUTF(ForgetLoading.CBid.getSelectedItem().toString());
							toServer.writeInt(2);
							toServer.writeUTF(ForgetLoading.JTnum.getText());
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								frame.dispose();
							} else
								JOptionPane.showMessageDialog(null,
										"�޸�ʧ��,���벻�ܴ���18���ַ���");
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});

		// �˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 557, 21);
		panel.add(menuBar);
		//�û�
		JMenu mnUser = new JMenu("\u7528\u6237U");
		mnUser.setMnemonic('u');
		mnUser.setFont(new Font("����", Font.BOLD, 16));
		menuBar.add(mnUser);
		//����
		JMenuItem mnReturn = new JMenuItem("����");
		mnReturn.setFont(new Font("����", Font.BOLD, 16));
		mnReturn.setIcon(new ImageIcon("menu/return.gif"));
		mnReturn.setMnemonic('r');
		mnReturn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,ActionEvent.CTRL_MASK));
		mnUser.add(mnReturn);
		mnReturn.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Point p = frame.getLocation();
				int x = (int) p.getX();
				int y = (int) p.getY();
				ForgetLoading f = new ForgetLoading();
				f.show(x, y);
				frame.dispose();
			}
		});
		//�˳�
		JMenuItem JMIexit = new JMenuItem("\u9000\u51FA\u7CFB\u7EDF");
		JMIexit.setFont(new Font("����", Font.BOLD, 16));
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
		//����
		JMenuItem JMIabout = new JMenuItem("\u5173\u4E8E");
		JMIabout.setFont(new Font("����", Font.BOLD, 16));
		JMIabout.setIcon(new ImageIcon("menu/about.gif"));
		JMIabout.setMnemonic('a');
		JMIabout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
		mnUser.add(JMIabout);
		//����
		JMenu JMhelp = new JMenu("\u5E2E\u52A9H");
		JMhelp.setMnemonic('h');
		JMhelp.setFont(new Font("����", Font.BOLD, 16));
		menuBar.add(JMhelp);
		//��ȡ����
		JMenuItem JMIgethelp = new JMenuItem("\u83B7\u53D6\u5E2E\u52A9");
		JMIgethelp.setFont(new Font("����", Font.BOLD, 16));
		JMIgethelp.setIcon(new ImageIcon("menu/help"));
		JMIgethelp.setMnemonic('h');
		JMIgethelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		JMhelp.add(JMIgethelp);
		JMIgethelp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String string="���ʹ�ù��̳��������⣬��ӭ��ʱ������\nQQ:1261709167";
				UIManager.getDefaults().put("OptionPane.messageFont",new Font("����",Font.BOLD,16));
				JOptionPane.showMessageDialog(null, string,"����һ��˧�����ʾ",JOptionPane.PLAIN_MESSAGE);
			}
		});
	}

	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("��������");
		frame.setBounds(x, y, 573, 395);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}
//��ʼ����ֵ
	public String getJTquestion() {
		try {
			Socket socket = new Socket(Login.ip, 8181);
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());
			DataInputStream fromServer = new DataInputStream(
					socket.getInputStream());
			toServer.writeInt(3);//�޸�����
			toServer.writeUTF(ForgetLoading.CBid.getSelectedItem().toString());
			toServer.writeInt(1);
			toServer.writeUTF(ForgetLoading.JTnum.getText());
			Squestion = fromServer.readUTF();

			Sanswer = fromServer.readUTF();
			toServer.flush();
			toServer.close();
			fromServer.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Squestion;
	}

	// public static void main(String[] args) {
	// ForgetPassword f = new ForgetPassword();
	// f.show(300, 100);
	// }
}
