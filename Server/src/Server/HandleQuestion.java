package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandleQuestion implements Runnable {
	private Socket socket;
	private DataInputStream fromClient;
	private DataOutputStream toClient;

	public HandleQuestion(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL c = new ContentSQL();
			Statement stat = c.conn.createStatement();
			fromClient = new DataInputStream(socket.getInputStream());
			toClient = new DataOutputStream(socket.getOutputStream());
			String Sid = fromClient.readUTF();
			//学生
			if (Sid.equals("学生")) {
				String num = fromClient.readUTF();
				int a = fromClient.readInt();
				//获取密码，用于判断是否密码一致
				if (a == 2) {
					String sql = "select spassword from studentacount where sno="
							+ num;
					ResultSet res = stat.executeQuery(sql);
					if (res.next()) {
						String password = res.getString(1);
						toClient.writeUTF(password);
					}
					res.close();
				}
				//更新密保问题
				a = fromClient.readInt();
				if (a == 1) {
					String question = fromClient.readUTF();
					String answer = fromClient.readUTF();
					String sql = "begin tran sav update studentacount set squestion=? where sno=? "
							+ "if @@error!=0 rollback tran sav else begin "
							+ "update studentacount  set sanswer=?  where sno=? end commit tran "
							+ "if @@error!=0 rollback tran sav ";
					PreparedStatement pre = c.conn.prepareStatement(sql);
					pre.setString(1, question);
					pre.setString(2, num);
					pre.setString(3, answer);
					pre.setString(4, num);
					pre.executeUpdate();

					pre.close();
					toClient.writeInt(1);
				}
			} 
			//管理员
			else if (Sid.equals("管理员")) {
				String num = fromClient.readUTF();
				int a = fromClient.readInt();
				//获取密码
				if (a == 2) {
					String sql = "select apassword from admitacount where ano="
							+ num;
					ResultSet res = stat.executeQuery(sql);
					if (res.next()) {
						String password = res.getString(1);
						toClient.writeUTF(password);
					}
					res.close();
				}
				//更新密保问题
				a = fromClient.readInt();
				if (a == 1) {
					String question = fromClient.readUTF();
					String answer = fromClient.readUTF();
					String sql = "begin tran sav update admitacount set aquestion=? where ano=? "
							+ "if @@error!=0 rollback tran sav else begin "
							+ "update admitacount  set aanswer=?  where ano=? end commit tran "
							+ "if @@error!=0 rollback tran sav ";
					PreparedStatement pre = c.conn.prepareStatement(sql);
					pre.setString(1, question);
					pre.setString(2, num);
					pre.setString(3, answer);
					pre.setString(4, num);
					pre.executeUpdate();

					pre.close();
					toClient.writeInt(1);
				}
			} 
			//超级管理员
			else if (Sid.equals("超级管理员")) {
				String num = fromClient.readUTF();
				int a = fromClient.readInt();
				//密码
				if (a == 2) {
					String sql = "select spassword from superadmitacount where sno="
							+ num;
					ResultSet res = stat.executeQuery(sql);
					if (res.next()) {
						String password = res.getString(1);
						toClient.writeUTF(password);
						// toClient.writeInt(1);
					}
					res.close();
				}
				//更新密保问题
				a = fromClient.readInt();
				if (a == 1) {
					String question = fromClient.readUTF();
					String answer = fromClient.readUTF();
					String sql = "begin tran sav update superadmitacount set squestion=? where sno=? "
							+ "if @@error!=0 rollback tran sav else begin "
							+ "update superadmitacount  set sanswer=?  where sno=? end commit tran "
							+ "if @@error!=0 rollback tran sav ";
					PreparedStatement pre = c.conn.prepareStatement(sql);
					pre.setString(1, question);
					pre.setString(2, num);
					pre.setString(3, answer);
					pre.setString(4, num);
					pre.executeUpdate();

					pre.close();
					toClient.writeInt(1);
				}
			}
			stat.close();
			c.conn.close();
			toClient.flush();
			toClient.close();
			fromClient.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// try {
			// toClient.writeInt(2);
			// } catch (IOException e1) {
			// // TODO Auto-generated catch block
			// e1.printStackTrace();
			// }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			try {
				toClient.writeInt(2);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
