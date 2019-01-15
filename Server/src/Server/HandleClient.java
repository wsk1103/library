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
			ContentSQL c = new ContentSQL();//连接数据库
			Statement stat = c.conn.createStatement();
			DataInputStream fromClient = new DataInputStream(
					socket.getInputStream());
			String Admit = fromClient.readUTF();//从客户端读取当前用户的身份
			//如果身份对应学生
			if (Admit.equals("学生")) {
				String num = fromClient.readUTF();//获取当前学号
				//通过学号查询
				ResultSet res = stat
						.executeQuery("select spassword from StudentAcount where sno='"
								+ num+"'");
				//如果该用户存在，则传递对应密码
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				else{// 没有该用户，传递密码为空
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				c.conn.close();
			}
			// 管理员断
			else if (Admit.equals("管理员")){
				String num = fromClient.readUTF();//获取id
				ResultSet res = stat
						.executeQuery("select apassword from AdmitAcount where ano='"+num+"'");
				//如果存在，传递密码
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				//如果不存在，传递“”
				else {
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				res.close();
				stat.close();
				c.conn.close();//关闭数据库连接
			}
			else if (Admit.equals("超级管理员")){
				String num = fromClient.readUTF();//获取id
				ResultSet res = stat
						.executeQuery("select spassword from superadmitacount where sno='"+num+"'");
				//如果存在，传递密码
				if (res.next()) {
					String s = res.getString(1);
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				} 
				//如果不存在，传递“”
				else {
					String s = "";
					DataOutputStream toClient = new DataOutputStream(
							socket.getOutputStream());
					toClient.writeUTF(s);
				}
				res.close();
				stat.close();
				c.conn.close();//关闭数据库连接
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
