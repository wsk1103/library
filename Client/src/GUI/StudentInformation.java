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
	Font font=new Font("楷体", Font.BOLD, 20);
	static JFrame frame;
	private String Sno;//学号
	private String sname;//姓名
	private String ssex;//性别
	private String sclass;//班级名称
	private String sxi;//班级所在系
	private String syuan;//班级所在院
	private String sphone;//学生电话
	private String Bno;//数据编号
	private String bname;//书籍名称
	private String bauthor;//作者
	private String time;//借书时间
	private String overdue;//逾期时间

	public StudentInformation(String sno,String bno,String time,String overdue,int i) {
		Admit.studentinformation=1;
		this.Sno=sno;
		this.Bno=bno;
		this.time=time;
		this.overdue=overdue;
		frame=new JFrame();
		Handle();//为各个变量赋值
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
//学号
		JLabel JLsno = new JLabel("\u5B66\u53F7:");
		JLsno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsno.setFont(font);
		JLsno.setBounds(277, 10, 68, 31);
		contentPane.add(JLsno);

		JLabel JTsno = new JLabel(sno);
		JTsno.setFont(font);
		JTsno.setBounds(355, 10, 137, 31);
		contentPane.add(JTsno);
//姓名
		JLabel JLsname = new JLabel("\u59D3\u540D:");
		JLsname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsname.setFont(font);
		JLsname.setBounds(52, 10, 68, 31);
		contentPane.add(JLsname);

		JLabel JTsname = new JLabel(sname);
		JTsname.setFont(font);
		JTsname.setBounds(130, 10, 165, 31);
		contentPane.add(JTsname);
//班级
		JLabel JLclass = new JLabel("\u73ED\u7EA7:");
		JLclass.setHorizontalAlignment(SwingConstants.RIGHT);
		JLclass.setFont(font);
		JLclass.setBounds(52, 51, 68, 31);
		contentPane.add(JLclass);

		JLabel JTclass = new JLabel(sclass);
		JTclass.setFont(font);
		JTclass.setBounds(130, 51, 165, 31);
		contentPane.add(JTclass);
//性别
		JLabel JLsex = new JLabel("\u6027\u522B:");
		JLsex.setHorizontalAlignment(SwingConstants.RIGHT);
		JLsex.setFont(font);
		JLsex.setBounds(277, 51, 68, 31);
		contentPane.add(JLsex);

		JLabel JTsex = new JLabel(ssex);
		JTsex.setFont(font);
		JTsex.setBounds(355, 51, 68, 31);
		contentPane.add(JTsex);
//系
		JLabel JLxi = new JLabel("\u7CFB\u540D:");
		JLxi.setHorizontalAlignment(SwingConstants.RIGHT);
		JLxi.setFont(font);
		JLxi.setBounds(52, 92, 68, 31);
		contentPane.add(JLxi);

		JLabel JTxi = new JLabel(sxi);
		JTxi.setFont(font);
		JTxi.setBounds(130, 92, 385, 31);
		contentPane.add(JTxi);
//院
		JLabel JLyuan = new JLabel("\u9662\u540D:");
		JLyuan.setHorizontalAlignment(SwingConstants.RIGHT);
		JLyuan.setFont(font);
		JLyuan.setBounds(52, 133, 68, 31);
		contentPane.add(JLyuan);

		JLabel JTyuan = new JLabel(syuan);
		JTyuan.setFont(font);
		JTyuan.setBounds(130, 133, 385, 31);
		contentPane.add(JTyuan);
//电话
		JLabel JLphone = new JLabel("\u624B\u673A\u53F7\u7801:");
		JLphone.setHorizontalAlignment(SwingConstants.RIGHT);
		JLphone.setFont(font);
		JLphone.setBounds(10, 180, 110, 31);
		contentPane.add(JLphone);

		JLabel JTphone = new JLabel(sphone);
		JTphone.setFont(font);
		JTphone.setBounds(130, 180, 349, 31);
		contentPane.add(JTphone);
//书编号
		JLabel JLbno = new JLabel("\u4E66\u7C4D\u7F16\u53F7:");
		JLbno.setHorizontalAlignment(SwingConstants.RIGHT);
		JLbno.setFont(font);
		JLbno.setBounds(10, 221, 110, 31);
		contentPane.add(JLbno);

		JLabel JTbno = new JLabel(bno);
		JTbno.setFont(font);
		JTbno.setBounds(130, 221, 362, 31);
		contentPane.add(JTbno);
//作者
		JLabel JLauthor = new JLabel("\u4E66\u7C4D\u4F5C\u8005:");
		JLauthor.setHorizontalAlignment(SwingConstants.RIGHT);
		JLauthor.setFont(font);
		JLauthor.setBounds(10, 303, 110, 31);
		contentPane.add(JLauthor);

		JLabel JTauthor = new JLabel(bauthor);
		JTauthor.setFont(font);
		JTauthor.setBounds(130, 303, 349, 31);
		contentPane.add(JTauthor);
//名称
		JLabel JLbname = new JLabel("\u4E66\u7C4D\u540D\u79F0:");
		JLbname.setHorizontalAlignment(SwingConstants.RIGHT);
		JLbname.setFont(font);
		JLbname.setBounds(10, 262, 110, 31);
		contentPane.add(JLbname);

		JLabel JTbname = new JLabel(bname);
		JTbname.setFont(font);
		JTbname.setBounds(130, 262, 349, 31);
		contentPane.add(JTbname);
		//监听了预约过期
		if (i==1){
			//到期时间
			JLabel JLtime = new JLabel("到期时间:");
			JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
			JLtime.setFont(font);
			JLtime.setBounds(10, 344, 110, 31);
			contentPane.add(JLtime);

			JLabel JTtime = new JLabel(this.overdue);
			JTtime.setFont(font);
			JTtime.setBounds(130, 344, 293, 31);
			contentPane.add(JTtime);
//删除按钮
			JButton JBsure = new JButton("确定删除");
			JBsure.setFont(font);
			JBsure.setContentAreaFilled(false);
			JBsure.setBounds(195, 385, 130, 31);
			contentPane.add(JBsure);
			frame.getRootPane().setDefaultButton(JBsure);
			JBsure.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
					int isYes=JOptionPane.showConfirmDialog(null, "确定删除！？","确定删除？！",JOptionPane.YES_NO_OPTION);
					if (isYes==JOptionPane.YES_OPTION){
						// TODO Auto-generated method stub
						try {
							Socket socket = new Socket(Login.ip, 8181);
							DataInputStream fromServer = new DataInputStream(socket
									.getInputStream());
							DataOutputStream toServer = new DataOutputStream(socket
									.getOutputStream());
							toServer.writeInt(7);///方式7
							toServer.writeUTF(Sno);
							toServer.writeUTF(Bno);
							int i = fromServer.readInt();
							if (i == 1) {
								JOptionPane.showMessageDialog(null, "取消成功");
								frame.dispose();
							}else if (i==2){
								JOptionPane.showMessageDialog(null, "已经取消了该书籍，请刷新一下");
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
		//监听借书为还
		else if (i==2){
			//欠费
				JLabel JLtime = new JLabel("欠费(元):");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//应还时间
				JLabel JLoverdue = new JLabel("应还时间:");
				JLoverdue.setHorizontalAlignment(SwingConstants.RIGHT);
				JLoverdue.setFont(font);
				JLoverdue.setBounds(10, 385, 110, 31);
				contentPane.add(JLoverdue);

				JLabel JToverdue = new JLabel(this.overdue);
				JToverdue.setFont(font);
				JToverdue.setBounds(130, 385, 293, 31);
				contentPane.add(JToverdue);
//确定按钮
				JButton JBsure = new JButton("确定");
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
		//监听查看借书情况
		else if (i==3){
			//借书时间
				JLabel JLtime = new JLabel("借书时间:");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//应还时间
				JLabel JLoverdue = new JLabel("应还时间:");
				JLoverdue.setHorizontalAlignment(SwingConstants.RIGHT);
				JLoverdue.setFont(font);
				JLoverdue.setBounds(10, 385, 110, 31);
				contentPane.add(JLoverdue);

				JLabel JToverdue = new JLabel(this.overdue);
				JToverdue.setFont(font);
				JToverdue.setBounds(130, 385, 293, 31);
				contentPane.add(JToverdue);
//确定按钮
				JButton JBsure = new JButton("确定");
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
		//监听查看还书情况
		else if (i==4){
			//还书时间
				JLabel JLtime = new JLabel("还书时间:");
				JLtime.setHorizontalAlignment(SwingConstants.RIGHT);
				JLtime.setFont(font);
				JLtime.setBounds(10, 344, 110, 31);
				contentPane.add(JLtime);

				JLabel JTtime = new JLabel(this.time);
				JTtime.setFont(font);
				JTtime.setBounds(130, 344, 293, 31);
				contentPane.add(JTtime);
//确定按钮
				JButton JBsure = new JButton("确定");
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
	//初始化各种值
	private void Handle(){
		try {
			Socket socket=new Socket(Login.ip,8181);
			DataOutputStream toServer=new DataOutputStream(socket.getOutputStream());
			toServer.writeInt(12);//学生信息
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
		frame.getContentPane().setFont(new Font("楷体", Font.PLAIN, 12));
		frame.setTitle("管理学生界面");
		frame.setBounds(x, y, 536, 518);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);

	}
}
