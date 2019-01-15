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
 * @date 2016年5月9日下午10:10:30
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
		JButton open=new JButton("打开服务器");
		open.setBounds(250, 100, 100, 30);
//		add(open);
//		open.addActionListener(new ActionListener() {
//			
//			public void actionPerformed(ActionEvent arg0) {
//				// TODO Auto-generated method stub
//				JOptionPane.showMessageDialog(null, "打开成功");
//				Handle();
//			}
//		});
		JButton close=new JButton("关闭服务器");
		close.setBounds(200, 150, 200, 30);
		close.setFont(new Font("楷体",Font.BOLD,20));
		add(close);
		close.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					int i=close();
					if (i==0)
						JOptionPane.showMessageDialog(null, "关闭成功");
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
			 serversocket = new ServerSocket(8181);// 当前端口8181
			// 允许多个用户同时使用，使用线程实现
			while (true) {
				socket = serversocket.accept();
				DataInputStream test = new DataInputStream(
						socket.getInputStream());
				int i = test.readInt();
				System.out.println(i);
				if (i == 1) {
					System.out.println("登录");
					HandleClient task = new HandleClient(socket);// 线程处理
					new Thread(task).start();
				} else if (i == 3) {
					System.out.println("修改密码");
					HandlePassword hp = new HandlePassword(socket);
					new Thread(hp).start();
				} else if (i == 2) {
					System.out.println("登录后");
					HandleClient2 hc2 = new HandleClient2(socket);
					new Thread(hc2).start();
				} else if (i == 4) {
					System.out.println("修改密保问题");
					HandleQuestion hq = new HandleQuestion(socket);
					new Thread(hq).start();
				} else if (i == 5) {
					System.out.println("查询书籍");
					HandleOrder ho = new HandleOrder(socket);
					new Thread(ho).start();
				}else if (i==6){
					System.out.println("预约书籍");
					HandleAppointment ha=new HandleAppointment(socket);
					new Thread(ha).start();
				}else if (i==7){
					System.out.println("取消预约");
					HandleCancel hc=new HandleCancel(socket);
					new Thread(hc).start();
				}else if (i==8){
					System.out.println("借书情况");
					HandleBorrow hb=new HandleBorrow(socket);
					new Thread(hb).start();
				}else if (i==9){
					System.out.println("还书情况");
					HandleReturn hr=new HandleReturn(socket);
					new Thread(hr).start();
				}else if (i==10){
					System.out.println("添加/修改/删除书籍");
					HandleBook db=new HandleBook(socket);
					new Thread(db).start();
				}else if (i==11){
					System.out.println("管理学生书籍");
					HandleMStudent hms=new HandleMStudent(socket);
					new Thread(hms).start();
				}else if (i==12){
					System.out.println("学生信息");
					HandleStuent hs=new HandleStuent(socket);
					new Thread(hs).start();
				}else if (i==13){
					System.out.println("查看，修改,增加管理员，学生信息");
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
		setTitle("图书借还管理系统服务器");
		setBounds(100, 100, 700, 300);
		setSize(600, 400);
		setLocation(x, y);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}
