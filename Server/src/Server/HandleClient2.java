package Server;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.util.*;

import bean.StudentAcount;

public class HandleClient2 implements Runnable{
	private Socket socket;
	private DataInputStream fromClient;
	private ObjectOutputStream toClient;
	
	public HandleClient2(Socket socket) {
		this.socket = socket;
		
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL c = new ContentSQL();//连接数据库
			Statement stat = c.conn.createStatement();
			fromClient = new DataInputStream(
					socket.getInputStream());
			String num = fromClient.readUTF();
			int i=fromClient.readInt();
			//学生端
			if (i==1){
			//通过学号进行查询
			ResultSet res = stat
					.executeQuery("select sname,ssex,sstats "
							+ "from studentinformation,studentacount "
							+ "where (studentinformation.sno=studentacount.sno) and studentacount.sno='"
							+num+"'");
			//将查询结果可序列化存储到Arraylist中
			ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
			if (res.next()) {				
				StudentAcount s1 = new StudentAcount();//已经可序列化
				String snum=res.getString(1);
				s1.setNum(snum);//姓名
				String ssex=res.getString(2);
				s1.setQuestion(ssex);//性别
				int scount=res.getInt(3);
				s1.setStats(scount);//剩余可以借次数
				list.add(s1);//添加到Arraylist中
				toClient = new ObjectOutputStream(
						socket.getOutputStream());					
				toClient.writeObject(list);//传递对象
				//System.out.println(toClient.writeObject(studentAcount));
			}
			res.close();
			stat.close();
			}
			//管理员断
			else if (i==2){
				ResultSet res = stat
						.executeQuery("select aname,asex "
								+ "from admitinformation "
								+ "where  ano='"
								+num+"'");
				//将查询结果可序列化存储到Arraylist中
				ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
				if (res.next()) {				
					StudentAcount s1 = new StudentAcount();//已经可序列化
					String snum=res.getString(1);
					s1.setNum(snum);//姓名
					String ssex=res.getString(2);
					s1.setQuestion(ssex);//性别
					list.add(s1);//添加到Arraylist中
					toClient = new ObjectOutputStream(
							socket.getOutputStream());					
					toClient.writeObject(list);//传递对象
				}
				res.close();
				stat.close();
			}
			//超级管理员
			else if (i==3){
				ResultSet res = stat
						.executeQuery("select sname,ssex "
								+ "from superadmitinformation "
								+ "where  sno='"
								+num+"'");
				//将查询结果可序列化存储到Arraylist中
				ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
				if (res.next()) {				
					StudentAcount s1 = new StudentAcount();//已经可序列化
					String snum=res.getString(1);
					s1.setNum(snum);//姓名
					String ssex=res.getString(2);
					s1.setQuestion(ssex);//性别
					list.add(s1);//添加到Arraylist中
					toClient = new ObjectOutputStream(
							socket.getOutputStream());					
					toClient.writeObject(list);//传递对象
				}
				res.close();
				stat.close();
			}
			
			//toClient.flush();
			toClient.close();
			fromClient.close();
			c.conn.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

