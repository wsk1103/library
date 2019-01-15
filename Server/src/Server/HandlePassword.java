package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HandlePassword implements Runnable {
	// class HandlePassword implements Runnable {
	private Socket socket;
	private DataOutputStream toClientData;
	private DataInputStream fromClient;

	public HandlePassword(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {

			toClientData = new DataOutputStream(socket.getOutputStream());
			ContentSQL con = new ContentSQL();
			Statement stat = con.conn.createStatement();
			fromClient = new DataInputStream(socket.getInputStream());
			String Sid = fromClient.readUTF();
			if (Sid.equals("学生")) {
				int i = fromClient.readInt();
				//获取密保问题，用于显示
				if (i == 1) {
					String num = fromClient.readUTF();
					ResultSet res = stat
							.executeQuery("select squestion,sanswer from studentacount where sno='"
									+ num + "'");
					if (res.next()) {
						String question = res.getString(1);
						String answer = res.getString(2);
						if (question == null || question.isEmpty()) {
							toClientData.writeUTF("未设置密保问题,不需要填写答案");
							toClientData.writeUTF("");
						} else {
							toClientData.writeUTF(question);
							toClientData.writeUTF(answer);
						}
					} else {
						toClientData.writeUTF("未设置密保问题，直接修改密码");
						toClientData.writeUTF(null);
					}
					res.close();
				} 
				//更新密码
				else {
					try {
						String num = fromClient.readUTF();
						String password = fromClient.readUTF();
						PreparedStatement pre = con.conn
								.prepareStatement("update studentacount set spassword=?"
										+ " where sno=?");
						pre.setString(1, password);
						pre.setString(2, num);
						pre.executeUpdate();
						toClientData.writeInt(0);
						pre.close();
						// res.close();
					} catch (IOException e) {
						// TODO: handle exception
						toClientData.writeInt(2);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						// e.printStackTrace();
						toClientData.writeInt(2);
					}

				}
			} else if (Sid.equals("管理员")) {
				int i = fromClient.readInt();
				//用于显示密保问题
				if (i == 1) {
					String num = fromClient.readUTF();
					ResultSet res = stat
							.executeQuery("select aquestion,aanswer from admitacount where ano='"
									+ num + "'");
					if (res.next()) {
						String question = res.getString(1);
						String answer = res.getString(2);
						if (question == null || question.isEmpty()) {
							toClientData.writeUTF("未设置密保问题,不需要填写答案");
							toClientData.writeUTF("");
						} else {
							toClientData.writeUTF(question);
							toClientData.writeUTF(answer);
						}
					} else {
						toClientData.writeUTF("未设置密保问题，直接修改密码");
						toClientData.writeUTF(null);
					}
					res.close();
				}
				//更新密码
				else {
					try {
						String num = fromClient.readUTF();
						String password = fromClient.readUTF();
						PreparedStatement pre = con.conn
								.prepareStatement("update admitacount set apassword=?"
										+ " where ano=?");
						pre.setString(1, password);
						pre.setString(2, num);
						pre.executeUpdate();
						toClientData.writeInt(0);
						pre.close();
					} catch (IOException e) {
						// TODO: handle exception
						toClientData.writeInt(2);
					} catch (SQLException e) {
						toClientData.writeInt(2);
					}

				}
			} else if (Sid.equals("超级管理员")) {
				int i = fromClient.readInt();
				//显示密保问题
				if (i == 1) {
					String num = fromClient.readUTF();
					ResultSet res = stat
							.executeQuery("select squestion,sanswer from superadmitacount where sno='"
									+ num + "'");
					if (res.next()) {
						String question = res.getString(1);
						String answer = res.getString(2);
						if (question == null || question.isEmpty()) {
							toClientData.writeUTF("未设置密保问题,不需要填写答案");
							toClientData.writeUTF("");
						} else {
							toClientData.writeUTF(question);
							toClientData.writeUTF(answer);
						}
					} else {
						toClientData.writeUTF("未设置密保问题，直接修改密码");
						toClientData.writeUTF(null);
					}
					res.close();
				} 
				//更新密码
				else {
					try {
						String num = fromClient.readUTF();
						String password = fromClient.readUTF();
						PreparedStatement pre = con.conn
								.prepareStatement("update superadmitacount set spassword=?"
										+ " where sno=?");
						pre.setString(1, password);
						pre.setString(2, num);
						pre.executeUpdate();
						toClientData.writeInt(0);
						pre.close();
					} catch (IOException e) {
						// TODO: handle exception
						toClientData.writeInt(2);
					} catch (SQLException e) {
						toClientData.writeInt(2);
					}

				}
			}

			fromClient.close();
			toClientData.flush();
			toClientData.close();
			stat.close();
			con.conn.close();
			socket.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
