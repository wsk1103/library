package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleClient implements Runnable {
	private Socket socket;

	public HandleClient(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
			
		try {
			ContentSQL c = new ContentSQL();//�������ݿ�
			Statement stat = c.conn.createStatement();
			DataInputStream fromClient = new DataInputStream(
					socket.getInputStream());
			String Admit = fromClient.readUTF();//�ӿͻ��˶�ȡ��ǰ�û������
			//�����ݶ�Ӧѧ��
			if (Admit.equals("ѧ��")) {
				String num = fromClient.readUTF();//��ȡ��ǰѧ��
				//ͨ��ѧ�Ų�ѯ
				ResultSet res = stat
						.executeQuery("select spassword from StudentAcount where sno='"
								+ num+"'");
				//������û����ڣ��򴫵ݶ�Ӧ����
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				else{// û�и��û�����������Ϊ��
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				c.conn.close();
			}
			// ����Ա��
			else if (Admit.equals("����Ա")){
				String num = fromClient.readUTF();//��ȡid
				ResultSet res = stat
						.executeQuery("select apassword from AdmitAcount where ano='"+num+"'");
				//������ڣ���������
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				//��������ڣ����ݡ���
				else {
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				res.close();
				stat.close();
				c.conn.close();//�ر����ݿ�����
			}
			else if (Admit.equals("��������Ա")){
				String num = fromClient.readUTF();//��ȡid
				ResultSet res = stat
						.executeQuery("select spassword from superadmitacount where sno='"+num+"'");
				//������ڣ���������
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				//��������ڣ����ݡ���
				else {
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				res.close();
				stat.close();
				c.conn.close();//�ر����ݿ�����
			}
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
