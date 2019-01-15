package Server;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * @author wsk
 * @className:Server
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016��5��9������10:10:30
 */
public class LoginServer extends JFrame{
	
	static ServerSocket serversocket;
	static Socket socket;
	
	public static void main(String[] args) {
		//System.out.println("start loginServer");
		new LoginServer().show(450, 150);
		Handle();
	}

	public LoginServer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		setLayout(null);
		JButton open=new JButton("�򿪷�����");
		open.setBounds(250, 100, 100, 30);
//		add(open);
//		open.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "�򿪳ɹ�");
//				Handle();
//			}
//		});
		JButton close=new JButton("�رշ�����");
		close.setBounds(200, 150, 200, 30);
		close.setFont(new Font("����",Font.BOLD,20));
		add(close);
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					int i=close();
					if (i==0)
						JOptionPane.showMessageDialog(null, "�رճɹ�");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	public static void Handle(){
		System.out.println("start loginServer");
		try {
			 serversocket = new ServerSocket(8181);// ��ǰ�˿�8181
			// �������û�ͬʱʹ�ã�ʹ���߳�ʵ��
			while (true) {
				socket = serversocket.accept();
				DataInputStream test = new DataInputStream(
						socket.getInputStream());
				int i = test.readInt();
				System.out.println(i);
				if (i == 1) {
					System.out.println("��¼");
					HandleClient task = new HandleClient(socket);// �̴߳���
					new Thread(task).start();
				} else if (i == 3) {
					System.out.println("�޸�����");
					HandlePassword hp = new HandlePassword(socket);
					new Thread(hp).start();
				} else if (i == 2) {
					System.out.println("��¼��");
					HandleClient2 hc2 = new HandleClient2(socket);
					new Thread(hc2).start();
				} else if (i == 4) {
					System.out.println("�޸��ܱ�����");
					HandleQuestion hq = new HandleQuestion(socket);
					new Thread(hq).start();
				} else if (i == 5) {
					System.out.println("��ѯ�鼮");
					HandleOrder ho = new HandleOrder(socket);
					new Thread(ho).start();
				}else if (i==6){
					System.out.println("ԤԼ�鼮");
					HandleAppointment ha=new HandleAppointment(socket);
					new Thread(ha).start();
				}else if (i==7){
					System.out.println("ȡ��ԤԼ");
					HandleCancel hc=new HandleCancel(socket);
					new Thread(hc).start();
				}else if (i==8){
					System.out.println("�������");
					HandleBorrow hb=new HandleBorrow(socket);
					new Thread(hb).start();
				}else if (i==9){
					System.out.println("�������");
					HandleReturn hr=new HandleReturn(socket);
					new Thread(hr).start();
				}else if (i==10){
					System.out.println("���/�޸�/ɾ���鼮");
					HandleBook db=new HandleBook(socket);
					new Thread(db).start();
				}else if (i==11){
					System.out.println("����ѧ���鼮");
					HandleMStudent hms=new HandleMStudent(socket);
					new Thread(hms).start();
				}else if (i==12){
					System.out.println("ѧ����Ϣ");
					HandleStuent hs=new HandleStuent(socket);
					new Thread(hs).start();
				}else if (i==13){
					System.out.println("�鿴���޸�,���ӹ���Ա��ѧ����Ϣ");
					HandleInformation hi=new HandleInformation(socket);
					new Thread(hi).start();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static int close() throws IOException{
		//socket.close();
		System.exit(0);
		return 0;
	}
	
	public void show(int x, int y) {
		setTitle("ͼ��軹����ϵͳ������");
		setBounds(100, 100, 700, 300);
		setSize(600, 400);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
