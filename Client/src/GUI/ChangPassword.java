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
	Font font2=new Font("����", Font.BOLD, 16) ;
	Font font3=new Font("����", Font.BOLD, 20) ;
	
	//��ȡ�ܱ�����
	public void setQuestion() {
		try {
			Socket socket = new Socket(Login.ip, 8181);
			DataOutputStream toServer = new DataOutputStream(
					socket.getOutputStream());
			String num = Login.JTnum.getText();
			System.out.println(Sid);
			toServer.writeInt(3);//д��3��ʾ���ö�Ӧ�ķ���
			toServer.writeUTF(Sid);
			toServer.writeInt(1);//д��1���û�ȡ�ܱ�����
			toServer.writeUTF(num);
			DataInputStream fromServer = new DataInputStream(
					socket.getInputStream());
			question = fromServer.readUTF();
			answer = fromServer.readUTF();
			//�ر�
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
		frame.setResizable(false);//�����޷����
		// ����JFrame�Ĳ��ָ�ʽ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		// ����
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

		// �˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 552, 21);
		contentPane.add(menuBar);
		// �û�
		JMenu mmUser = new JMenu("�û�");
		mmUser.setMnemonic('u');
		mmUser.setFont(font2);
		menuBar.add(mmUser);
		// �˳�
		JMenuItem mntmExit = new JMenuItem("�˳�ϵͳ");
		mntmExit.setFont(font2);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
		mntmExit.setIcon(new ImageIcon("menu/exit.gif"));
		mmUser.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���˳���","ȷ���˳���",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				System.exit(0);}
			}
		});
		// ����
		JMenu mmHelp = new JMenu("����");
		mmHelp.setFont(font2);
		menuBar.add(mmHelp);
		mmHelp.setMnemonic('h');
		// ��ȡ����
		JMenuItem mntmGetHelp = new JMenuItem("��ȡ����");
		mntmGetHelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGetHelp.setFont(font2);
		mntmGetHelp.setMnemonic('h');
		mntmGetHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mmHelp.add(mntmGetHelp);
		
		// �ܱ�����
		JLabel JLquestion = new JLabel("�ܱ����⣺");
		JLquestion.setHorizontalAlignment(SwingConstants.RIGHT);
		JLquestion.setBounds(42, 34, 166, 34);
		JLquestion.setFont(font3);
		panel.add(JLquestion);

		JLabel JTquestion = new JLabel(question);
		JTquestion.setBounds(234, 34, 308, 34);
		JTquestion.setFont(font3);
		panel.add(JTquestion);
		// �ܱ���
		JLabel JLanswer = new JLabel("�ܱ��𰸣�");
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
		// ������
		JLabel JLpassword = new JLabel("�����룺");
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setBounds(42, 143, 166, 27);
		JLpassword.setFont(font3);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setBounds(234, 143, 215, 26);
		JTpassword.setFont(font);
		panel.add(JTpassword);
		// ȷ��������
		JLabel JLnewpassword = new JLabel("ȷ�����룺");
		JLnewpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLnewpassword.setBounds(42, 196, 166, 25);
		JLnewpassword.setFont(font3);
		panel.add(JLnewpassword);

		JTnewpassword = new JPasswordField();
		JTnewpassword.setBounds(234, 197, 215, 27);
		JTnewpassword.setFont(font);
		panel.add(JTnewpassword);
		// ���ð�ť�ͼ���
		JButton rem = new JButton("����");
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
		// ȷ����ť�Լ�����
		JButton sure = new JButton(" \u786E\u5B9A ");
		sure.setBounds(280, 248, 114, 34);
		sure.setContentAreaFilled(false);
		sure.setFont(font);
		panel.add(sure);
		frame.getRootPane().setDefaultButton(sure);
		sure.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�","ȷ���޸ģ�",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				String password = JTpassword.getText();
				String newpassword = JTnewpassword.getText();
				// TODO Auto-generated method stub
				if (answer == null || answer.isEmpty()) {
					if (password.equals(null) || newpassword.equals(null))
						JOptionPane.showMessageDialog(null, "��д������");
					if (!password.equals(newpassword)) {
						JOptionPane.showMessageDialog(null, "���벻һ��");
					}
					else {
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							String num = Login.JTnum.getText();
							toServer.writeInt(3);//д��3 ���ô����ܱ��ķ���
							toServer.writeUTF(Sid);
							toServer.writeInt(2);//д��2 �����޸��ܱ��ķ���
							toServer.writeUTF(num);
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							//�������ֵΪ0���ʾ�޸ĳɹ�
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "�޸�ʧ��,���Ȳ��ܳ���20���ַ�");
							}
							//�ر�
							toServer.flush();
							toServer.close();
							fromServer.close();
							socket.close();
						} catch (IOException e) {
							// TODO: handle exception
						}
					}
				} 
				//�û��Ѿ��������ܱ�
				else {
					if (JTanswer.getText() == null
							|| JTanswer.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "����д��");
						
					}  else if (password.isEmpty() || password == null
							|| newpassword.isEmpty() || newpassword == null)
						JOptionPane.showMessageDialog(null, "��д������");
					else if (!JTanswer.getText().equals(answer)) {
						JOptionPane.showMessageDialog(null, "�𰸴���");
					}
					else if (!password.equals(newpassword)) {
						JOptionPane.showMessageDialog(null, "���벻һ��");
					} 
					else {
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataOutputStream toServer = new DataOutputStream(
									socket.getOutputStream());
							DataInputStream fromServer = new DataInputStream(
									socket.getInputStream());
							String num = Login.JTnum.getText();
							toServer.writeInt(3);//д��3 ���ô����ܱ��ķ���
							toServer.writeUTF(Sid);
							toServer.writeInt(2);//д��2 �����޸�
							toServer.writeUTF(num);
							toServer.writeUTF(JTpassword.getText());
							int i = fromServer.readInt();
							if (i == 0) {
								JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
								frame.dispose();
							} else {
								JOptionPane.showMessageDialog(null, "�޸�ʧ��,���Ȳ��ܳ���20���ַ�");
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

	// ��ʾ
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("�޸�����");
		frame.setBounds(x, y, 552, 370);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
