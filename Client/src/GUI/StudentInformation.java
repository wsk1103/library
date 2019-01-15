package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import bean.MstudentBean;
import bean.book;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;


public class StudentInformation{
	private JPanel contentPane;
	Font font=new Font("����", Font.BOLD, 20);
	static JFrame frame;
	private String Sno;//ѧ��
	private String sname;//����
	private String ssex;//�Ա�
	private String sclass;//�༶����
	private String sxi;//�༶����ϵ
	private String syuan;//�༶����Ժ
	private String sphone;//ѧ���绰
	private String Bno;//���ݱ��
	private String bname;//�鼮����
	private String bauthor;//����
	private String time;//����ʱ��
	private String overdue;//����ʱ��

	public StudentInformation(String sno,String bno,String time,String overdue,int i) {
		Admit.studentinformation=1;
		this.Sno=sno;
		this.Bno=bno;
		this.time=time;
		this.overdue=overdue;
		frame=new JFrame();
		Handle();//Ϊ����������ֵ
		contentPane = new JPanel(){
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
//ѧ��
		JLabel JLsno = new JLabel("\u5B66\u53F7:");
		JLsno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsno.setFont(font);
		JLsno.setBounds(277, 10, 68, 31);
		contentPane.add(JLsno);

		JLabel JTsno = new JLabel(sno);
		JTsno.setFont(font);
		JTsno.setBounds(355, 10, 137, 31);
		contentPane.add(JTsno);
//����
		JLabel JLsname = new JLabel("\u59D3\u540D:");
		JLsname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsname.setFont(font);
		JLsname.setBounds(52, 10, 68, 31);
		contentPane.add(JLsname);

		JLabel JTsname = new JLabel(sname);
		JTsname.setFont(font);
		JTsname.setBounds(130, 10, 165, 31);
		contentPane.add(JTsname);
//�༶
		JLabel JLclass = new JLabel("\u73ED\u7EA7:");
		JLclass.setHorizontalAlignment(SwingConstants.RIGHT);
		JLclass.setFont(font);
		JLclass.setBounds(52, 51, 68, 31);
		contentPane.add(JLclass);

		JLabel JTclass = new JLabel(sclass);
		JTclass.setFont(font);
		JTclass.setBounds(130, 51, 165, 31);
		contentPane.add(JTclass);
//�Ա�
		JLabel JLsex = new JLabel("\u6027\u522B:");
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setFont(font);
		JLsex.setBounds(277, 51, 68, 31);
		contentPane.add(JLsex);

		JLabel JTsex = new JLabel(ssex);
		JTsex.setFont(font);
		JTsex.setBounds(355, 51, 68, 31);
		contentPane.add(JTsex);
//ϵ
		JLabel JLxi = new JLabel("\u7CFB\u540D:");
		JLxi.setHorizontalAlignment(SwingConstants.RIGHT);
		JLxi.setFont(font);
		JLxi.setBounds(52, 92, 68, 31);
		contentPane.add(JLxi);

		JLabel JTxi = new JLabel(sxi);
		JTxi.setFont(font);
		JTxi.setBounds(130, 92, 385, 31);
		contentPane.add(JTxi);
//Ժ
		JLabel JLyuan = new JLabel("\u9662\u540D:");
		JLyuan.setHorizontalAlignment(SwingConstants.RIGHT);
		JLyuan.setFont(font);
		JLyuan.setBounds(52, 133, 68, 31);
		contentPane.add(JLyuan);

		JLabel JTyuan = new JLabel(syuan);
		JTyuan.setFont(font);
		JTyuan.setBounds(130, 133, 385, 31);
		contentPane.add(JTyuan);
//�绰
		JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
		JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
		JLphone.setFont(font);
		JLphone.setBounds(10, 180, 110, 31);
		contentPane.add(JLphone);

		JLabel JTphone = new JLabel(sphone);
		JTphone.setFont(font);
		JTphone.setBounds(130, 180, 349, 31);
		contentPane.add(JTphone);
//����
		JLabel JLbno = new JLabel("\u4E66\u7C4D\u7F16\u53F7:");
		JLbno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLbno.setFont(font);
		JLbno.setBounds(10, 221, 110, 31);
		contentPane.add(JLbno);

		JLabel JTbno = new JLabel(bno);
		JTbno.setFont(font);
		JTbno.setBounds(130, 221, 362, 31);
		contentPane.add(JTbno);
//����
		JLabel JLauthor = new JLabel("\u4E66\u7C4D\u4F5C\u8005:");
		JLauthor.setHorizontalAlignment(SwingConstants.RIGHT);
		JLauthor.setFont(font);
		JLauthor.setBounds(10, 303, 110, 31);
		contentPane.add(JLauthor);

		JLabel JTauthor = new JLabel(bauthor);
		JTauthor.setFont(font);
		JTauthor.setBounds(130, 303, 349, 31);
		contentPane.add(JTauthor);
//����
		JLabel JLbname = new JLabel("\u4E66\u7C4D\u540D\u79F0:");
		JLbname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLbname.setFont(font);
		JLbname.setBounds(10, 262, 110, 31);
		contentPane.add(JLbname);

		JLabel JTbname = new JLabel(bname);
		JTbname.setFont(font);
		JTbname.setBounds(130, 262, 349, 31);
		contentPane.add(JTbname);
		//������ԤԼ����
		if (i==1){
			//����ʱ��
			JLabel JLtime = new JLabel("����ʱ��:");
			JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
			JLtime.setFont(font);
			JLtime.setBounds(10, 344, 110, 31);
			contentPane.add(JLtime);

			JLabel JTtime = new JLabel(this.overdue);
			JTtime.setFont(font);
			JTtime.setBounds(130, 344, 293, 31);
			contentPane.add(JTtime);
//ɾ����ť
			JButton JBsure = new JButton("ȷ��ɾ��");
			JBsure.setFont(font);
			JBsure.setContentAreaFilled(false);
			JBsure.setBounds(195, 385, 130, 31);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					int isYes=JOptionPane.showConfirmDialog(null, "ȷ��ɾ������","ȷ��ɾ������",JOptionPane.YES_NO_OPTION);
					if (isYes==JOptionPane.YES_OPTION){
						// TODO Auto-generated method stub
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataInputStream fromServer = new DataInputStream(socket
									.getInputStream());
							DataOutputStream toServer = new DataOutputStream(socket
									.getOutputStream());
							toServer.writeInt(7);///��ʽ7
							toServer.writeUTF(Sno);
							toServer.writeUTF(Bno);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "ȡ���ɹ�");
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
		}
		//��������Ϊ��
		else if (i==2){
			//Ƿ��
				JLabel JLtime = new JLabel("Ƿ��(Ԫ):");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//Ӧ��ʱ��
				JLabel JLoverdue = new JLabel("Ӧ��ʱ��:");
				JLoverdue.setHorizontalAlignment(SwingConstants.RIGHT);
				JLoverdue.setFont(font);
				JLoverdue.setBounds(10, 385, 110, 31);
				contentPane.add(JLoverdue);

				JLabel JToverdue = new JLabel(this.overdue);
				JToverdue.setFont(font);
				JToverdue.setBounds(130, 385, 293, 31);
				contentPane.add(JToverdue);
//ȷ����ť
				JButton JBsure = new JButton("ȷ��");
				JBsure.setFont(font);
				JBsure.setContentAreaFilled(false);
				JBsure.setBounds(195, 438, 112, 31);
				contentPane.add(JBsure);
				frame.getRootPane().setDefaultButton(JBsure);
				JBsure.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
				});
			}
		//�����鿴�������
		else if (i==3){
			//����ʱ��
				JLabel JLtime = new JLabel("����ʱ��:");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//Ӧ��ʱ��
				JLabel JLoverdue = new JLabel("Ӧ��ʱ��:");
				JLoverdue.setHorizontalAlignment(SwingConstants.RIGHT);
				JLoverdue.setFont(font);
				JLoverdue.setBounds(10, 385, 110, 31);
				contentPane.add(JLoverdue);

				JLabel JToverdue = new JLabel(this.overdue);
				JToverdue.setFont(font);
				JToverdue.setBounds(130, 385, 293, 31);
				contentPane.add(JToverdue);
//ȷ����ť
				JButton JBsure = new JButton("ȷ��");
				JBsure.setFont(font);
				JBsure.setBounds(195, 438, 112, 31);
				contentPane.add(JBsure);
				frame.getRootPane().setDefaultButton(JBsure);
				JBsure.setContentAreaFilled(false);
				JBsure.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
				});
			}
		//�����鿴�������
		else if (i==4){
			//����ʱ��
				JLabel JLtime = new JLabel("����ʱ��:");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//ȷ����ť
				JButton JBsure = new JButton("ȷ��");
				JBsure.setFont(font);
				JBsure.setBounds(195, 385, 112, 31);
				contentPane.add(JBsure);
				JBsure.setContentAreaFilled(false);
				JBsure.addActionListener(new ActionListener() {

					
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						frame.dispose();
					}
				});
			}

	}
	//��ʼ������ֵ
	private void Handle(){
		try {
			Socket socket=new Socket(Login.ip,8181);
			DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
			toServer.writeInt(12);//ѧ����Ϣ
			toServer.writeUTF(Sno);
			toServer.writeUTF(Bno);

			ObjectInputStream fromServer=new ObjectInputStream(socket.getInputStream());
			ArrayList<MstudentBean> student=(ArrayList<MstudentBean>)fromServer.readObject();
			ArrayList<book> book=(ArrayList<book>)fromServer.readObject();
			for (MstudentBean ms: student){
				Sno=ms.getSNo();
				sname=ms.getSName();
				ssex=ms.getBno();
				sclass=ms.getBname();
				sphone=ms.getAuthor();
				sxi=ms.getTime();
				syuan=ms.getOrderTime();
			}
			for (book ms:book){
				Bno=ms.getNo();
				bname=ms.getName();
				bauthor=ms.getAuthor();
			}
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
	public void show(int x, int y) {
		Image biaoti = new ImageIcon("image/biaoti.gif").getImage();
		frame.setIconImage(biaoti);
		Image mouse = new ImageIcon("image/28.gif").getImage();
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(mouse,
				new Point(), null));
		frame.getContentPane().setFont(new Font("����", Font.PLAIN, 12));
		frame.setTitle("����ѧ������");
		frame.setBounds(x, y, 536, 518);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
