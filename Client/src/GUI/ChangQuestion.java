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
	Font font=new Font("����",Font.BOLD,16);
	Font font2=new Font("����",Font.BOLD,20);
	String Sid=Login.JTid.getSelectedItem().toString();
	public ChangQuestion() {
		Student.studentChangQuestion=1;
		Admit.studentChangQuestion=1;
		frame = new JFrame();
		frame.setResizable(false);
		// ���û����ʽ
		frame.getContentPane().setLayout(null);
		// ���屳��
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon img = new ImageIcon("image/01.jpg");
				Image a = img.getImage();
				g.drawImage(a, 0, 0, this.getWidth(), this.getHeight(), this);
			}
		};
		// panel��ʽ
		panel.setBounds(0, 0, 557, 362);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		// �˵���
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 557, 21);
		panel.add(menuBar);
		// �û�
		JMenu mmUser = new JMenu("�û�");
		mmUser.setMnemonic('u');
		menuBar.add(mmUser);
		mmUser.setFont(font);
		// �˳�
		JMenuItem mntmExit = new JMenuItem("�˳�ϵͳ");
		mntmExit.setIcon(new ImageIcon("menu/exit.gif"));
		mntmExit.setFont(font);
		mntmExit.setMnemonic('e');
		mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.CTRL_MASK));
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
		mmHelp.setMnemonic('h');
		mmHelp.setFont(font);
		menuBar.add(mmHelp);
		// ��ȡ����
		JMenuItem mntmGetHelp = new JMenuItem("��ȡ����");
		mntmGetHelp.setIcon(new ImageIcon("menu/help.gif"));
		mntmGetHelp.setFont(font);
		mntmGetHelp.setMnemonic('h');
		mntmGetHelp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
		mmHelp.add(mntmGetHelp);
		// ����
		JLabel JLpassword = new JLabel("��¼\u5BC6\u7801\uFF1A");
		JLpassword.setFont(font2);
		JLpassword.setHorizontalAlignment(SwingConstants.RIGHT);
		JLpassword.setBounds(26, 81, 177, 34);
		panel.add(JLpassword);

		JTpassword = new JPasswordField();
		JTpassword.setFont(new Font("Serif", Font.BOLD, 22));
		JTpassword.setBounds(213, 83, 233, 31);
		panel.add(JTpassword);
		// ���ܱ�����
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
		"\u6211\u4EEC\u7684\u56DE\u5FC6","�ҵ��ֻ�����" };
		JTquestion = new JComboBox(s);
		JTquestion.setFont(font2);
		JTquestion.setBackground(Color.white);
		JTquestion.setBounds(215, 148, 233, 31);
		panel.add(JTquestion);
		// ���ܱ������
		JLabel JLanswer = new JLabel("\u65B0\u5BC6\u4FDD\u7B54\u6848\uFF1A");
		JLanswer.setHorizontalAlignment(SwingConstants.RIGHT);
		JLanswer.setFont(font2);
		JLanswer.setBounds(26, 203, 177, 34);
		panel.add(JLanswer);

		JTanswer = new JTextField();
		JTanswer.setFont(new Font("����", Font.BOLD, 18));
		JTanswer.setBounds(215, 205, 233, 31);
		panel.add(JTanswer);
		JTanswer.setColumns(10);
		// ���ð�ť�ͼ���
		JButton rem = new JButton("\u91CD\u7F6E");
		rem.setContentAreaFilled(false);
		rem.setFont(new Font("����", Font.BOLD, 20));
		rem.setBounds(153, 266, 110, 34);
		panel.add(rem);
		rem.addActionListener(new ActionListener() {

			
			public void actionPerformed(ActionEvent arg0) {
				JTanswer.setText(null);
				JTpassword.setText(null);
			}
		});
		// �˳���ť�ͼ���
		JButton sure = new JButton("\u786E\u5B9A");
		sure.setContentAreaFilled(false);
		sure.setFont(new Font("����", Font.BOLD, 20));
		sure.setBounds(273, 266, 115, 34);
		panel.add(sure);
		frame.getRootPane().setDefaultButton(sure);
		//ȷ����ť����
		sure.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int isYES=JOptionPane.showConfirmDialog(null, "ȷ���޸ģ�","ȷ���޸ģ�",JOptionPane.YES_NO_OPTION);
				if (isYES==JOptionPane.YES_OPTION){
				String password=JTpassword.getText();//��ȡ��¼����
				String question=JTquestion.getSelectedItem().toString();//��ȡ����
				String answer=JTanswer.getText();//��ȡ��
				String num=Login.JTnum.getText();//��ȡ�û���
				if (password==null||password.isEmpty()){
					JOptionPane.showMessageDialog(null, "��¼���벻��Ϊ��");
				}
				else if (answer==null||answer.isEmpty()){
					JOptionPane.showMessageDialog(null, "�𰸲���Ϊ��");
				}
				else
					try {
					Socket socket=new Socket(Login.ip, 8181);//���ӵ��˿�8181
					DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());//�������
					DataInputStream fromServer=new DataInputStream(socket.getInputStream());//��������
					toServer.writeInt(4);//���һ��4����ʾ�������ִ���ʽ
					toServer.writeUTF(Sid);
					toServer.writeUTF(num);//����û���
					toServer.writeInt(2);//���2�����ڻ�ȡ��ȷ����
					String fromServerPassword=fromServer.readUTF();//��������
					 if (!password.equals(fromServerPassword)){
						JOptionPane.showMessageDialog(null, "��¼�������");
					}
					else {
						toServer.writeInt(1);//���1��ʾ�޸��ܱ�����ʹ�
						toServer.writeUTF(question);//���
						toServer.writeUTF(answer);
						int i=fromServer.readInt();//�������֣����Ϊ1���޸ĳɹ�
						if (i==1){
							JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
							frame.dispose();
						}
						else 
							JOptionPane.showMessageDialog(null, "�޸�ʧ��,���ܳ���20���ַ�");
					}
					 //�ر�
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

	// ��ʾ
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("�޸��ܱ�");
		frame.setBounds(x, y, 540, 380);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
