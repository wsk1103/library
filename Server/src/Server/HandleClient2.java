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
			ContentSQL c = new ContentSQL();//�������ݿ�
			Statement stat = c.conn.createStatement();
			fromClient = new DataInputStream(
					socket.getInputStream());
			String num = fromClient.readUTF();
			int i=fromClient.readInt();
			//ѧ����
			if (i==1){
			//ͨ��ѧ�Ž��в�ѯ
			ResultSet res = stat
					.executeQuery("select sname,ssex,sstats "
							+ "from studentinformation,studentacount "
							+ "where (studentinformation.sno=studentacount.sno) and studentacount.sno='"
							+num+"'");
			//����ѯ��������л��洢��Arraylist��
			ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
			if (res.next()) {				
				StudentAcount s1 = new StudentAcount();//�Ѿ������л�
				String snum=res.getString(1);
				s1.setNum(snum);//����
				String ssex=res.getString(2);
				s1.setQuestion(ssex);//�Ա�
				int scount=res.getInt(3);
				s1.setStats(scount);//ʣ����Խ����
				list.add(s1);//��ӵ�Arraylist��
				toClient = new ObjectOutputStream(
						socket.getOutputStream());					
				toClient.writeObject(list);//���ݶ���
				//System.out.println(toClient.writeObject(studentAcount));
			}
			res.close();
			stat.close();
			}
			//����Ա��
			else if (i==2){
				ResultSet res = stat
						.executeQuery("select aname,asex "
								+ "from admitinformation "
								+ "where  ano='"
								+num+"'");
				//����ѯ��������л��洢��Arraylist��
				ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
				if (res.next()) {				
					StudentAcount s1 = new StudentAcount();//�Ѿ������л�
					String snum=res.getString(1);
					s1.setNum(snum);//����
					String ssex=res.getString(2);
					s1.setQuestion(ssex);//�Ա�
					list.add(s1);//��ӵ�Arraylist��
					toClient = new ObjectOutputStream(
							socket.getOutputStream());					
					toClient.writeObject(list);//���ݶ���
				}
				res.close();
				stat.close();
			}
			//��������Ա
			else if (i==3){
				ResultSet res = stat
						.executeQuery("select sname,ssex "
								+ "from superadmitinformation "
								+ "where  sno='"
								+num+"'");
				//����ѯ��������л��洢��Arraylist��
				ArrayList<StudentAcount> list=new ArrayList<StudentAcount>();
				if (res.next()) {				
					StudentAcount s1 = new StudentAcount();//�Ѿ������л�
					String snum=res.getString(1);
					s1.setNum(snum);//����
					String ssex=res.getString(2);
					s1.setQuestion(ssex);//�Ա�
					list.add(s1);//��ӵ�Arraylist��
					toClient = new ObjectOutputStream(
							socket.getOutputStream());					
					toClient.writeObject(list);//���ݶ���
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

