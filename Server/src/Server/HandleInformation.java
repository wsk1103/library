package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.MstudentBean;

/**
 * @author wsk
 * @className:HandleInformation
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月27日下午1:04:44
 */
public class HandleInformation implements Runnable {
	private Socket socket;
	private DataInputStream fromClient;
	private ObjectOutputStream toClientOb;
	private DataOutputStream toClient;
	private ObjectInputStream fromClientOb;

	public HandleInformation(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL con = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			int i = fromClient.readInt();
			toClient = new DataOutputStream(socket.getOutputStream());
			Statement stat = con.conn.createStatement();
			ResultSet res = null;
			String sql = null;
			// 查询基本信息
			if (i == 1) {
				String type = fromClient.readUTF();// 查询方式
				String key = fromClient.readUTF();// 关键字
				int id = fromClient.readInt();// 身份1-学生,2-管理员
				// if (type.equals("学号/工号")){
				if (id == 1) {
					sql = "select * from studentinformation,class,xi,studentacount "
							+ "where studentinformation.no=class.no and class.cno=xi.cno and studentinformation.sno=studentacount.sno ";
					if (type.equals("学号/工号"))
						sql += "and studentinformation.sno like '%" + key
								+ "%'";
					else if (type.equals("姓名"))
						sql += "and sname like '%" + key + "%'";
					res = stat.executeQuery(sql);
					ArrayList<MstudentBean> listStudent = new ArrayList<MstudentBean>();
					while (res.next()) {
						MstudentBean ms = new MstudentBean();
						ms.setSNo(res.getString(1));
						ms.setSName(res.getString(2));
						ms.setBno(res.getString(4));// 性别
						ms.setBname(res.getString(9));// 班级
						ms.setAuthor(res.getString(12));// 系
						ms.setOrderTime(res.getString(14));// 院
						ms.setTime(res.getString(7));// 手机号码
						listStudent.add(ms);
					}
					toClientOb = new ObjectOutputStream(
							socket.getOutputStream());
					toClientOb.writeObject(listStudent);

				} else if (id == 2) {
					sql = "select * from admitinformation ";
					if (type.equals("学号/工号"))
						sql += "where ano like '%" + key + "%' ";
					else if (type.equals("姓名"))
						sql += "where aname like '%" + key + "%' ";
					res = stat.executeQuery(sql);
					ArrayList<MstudentBean> listAdmit = new ArrayList<MstudentBean>();
					while (res.next()) {
						MstudentBean ms = new MstudentBean();
						ms.setSNo(res.getString(1));
						ms.setSName(res.getString(2));
						ms.setBno(res.getString(3));// 性别
						ms.setTime(res.getString(4));// 手机号码
						ms.setOrderTime(res.getString(6));// email
						ms.setAuthor(res.getString(5));// 身份证号码
						listAdmit.add(ms);
					}
					toClientOb = new ObjectOutputStream(
							socket.getOutputStream());
					toClientOb.writeObject(listAdmit);
				}
			}
			// }
			// 修改,管理员基本信息
			else if (i == 2) {
				fromClient = new DataInputStream(socket.getInputStream());
				// int a=fromClient.readInt();//1-修改，2-添加
				fromClientOb = new ObjectInputStream(socket.getInputStream());
				ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromClientOb
						.readObject();
				String no = null;
				String name = null;
				String sex = null;
				String phone = null;
				String email = null;
				String id = null;
				// if (a==1){
				for (MstudentBean ms : list) {
					no = ms.getSNo();
					name = ms.getSName();
					sex = ms.getBno();
					phone = ms.getTime();
					email = ms.getOrderTime();
					id = ms.getAuthor();
				}
				sql = "begin tran ss "
						+ "update admitinformation set aname=? where ano=? "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update admitinformation set asex=? where ano=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update admitinformation set aemail=? where ano=? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update admitinformation set aphone=? where ano=? end "
						+ "if @@error!=0 rollback tran ss  else begin "
						+ "update admitinformation set aid=? where ano=? end commit tran "
						+ "if @@error!=0 rollback tran ss ";
				PreparedStatement pre = con.conn.prepareStatement(sql);
				pre.setString(1, name);
				pre.setString(2, no);
				pre.setString(3, sex);
				pre.setString(4, no);
				pre.setString(5, email);
				pre.setString(6, no);
				pre.setString(7, phone);
				pre.setString(8, no);
				pre.setString(9, id);
				pre.setString(10, no);
				pre.executeUpdate();
				toClient = new DataOutputStream(socket.getOutputStream());
				toClient.writeInt(1);
				pre.close();
			}

			// 添加新用户
			else if (i == 3) {
				int id = fromClient.readInt();
				if (id == 1) {
					String no = fromClient.readUTF();
					sql = "select * from studentacount where sno='" + no + "'";
					res = stat.executeQuery(sql);
					if (res.next())
						toClient.writeInt(2);
					else {
						sql = "select sid from studentinformation where sno='"
								+ no + "'";
						res = stat.executeQuery(sql);
						if (res.next()) {
							String password = res.getString(1);
							password = password.substring(
									password.length() - 4, password.length());
							sql = "insert into studentacount(sno,spassword) values(?,?)";
							PreparedStatement pre = con.conn
									.prepareStatement(sql);
							pre.setString(1, no);
							pre.setString(2, password);
							pre.executeUpdate();
							toClient.writeInt(1);
						} else
							toClient.writeInt(0);// 该账户不存在
					}
				} else if (id == 2) {
					String name = null;
					String sex = null;
					String phone = null;
					String email = null;
					String aid = null;
					String no = null;
					fromClientOb = new ObjectInputStream(
							socket.getInputStream());
					ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromClientOb
							.readObject();
					for (MstudentBean ms : list) {
						no = ms.getSNo();
						name = ms.getSName();
						sex = ms.getBno();
						phone = ms.getTime();
						email = ms.getOrderTime();
						aid = ms.getAuthor();
						System.out.println(2);
					}
					sql = "select ano from admitinformation where ano='" + no
							+ "'";
					res = stat.executeQuery(sql);
					if (res.next()) {
						toClient.writeInt(0);
					} else {
						String passwordd = aid.substring(aid.length() - 4,
								aid.length());
						sql = "begin tran "
								+ "insert into admitinformation(ano,aname,asex,aphone,aid,aemail) values(?,?,?,?,?,?) "
								+ "if @@error!=0 rollback tran ss else begin "
								+ "insert into admitacount(ano,apassword) values(?,?) end commit tran ";
						PreparedStatement pre = con.conn.prepareStatement(sql);
						pre.setString(1, no);
						pre.setString(2, name);
						pre.setString(3, sex);
						pre.setString(4, phone);
						pre.setString(5, aid);
						pre.setString(6, email);
						pre.setString(7, no);
						pre.setString(8, passwordd);
						pre.executeUpdate();
						toClient.writeInt(1);
					}
				}
			}
			// 查看账户密码等
			else if (i == 4) {
				int id = fromClient.readInt();// 身份1-学生,2-管理员
				String password = null, question = null, answer = null;
				String no = fromClient.readUTF();
				if (id == 1)
					sql = "select * from studentacount where sno='" + no + "'";
				else if (id == 2)
					sql = "select * from admitacount where ano='" + no + "'";
				res = stat.executeQuery(sql);
				if (res.next()) {
					password = res.getString(2);
					question = res.getString(3);
					answer = res.getString(4);
				}
				if (question == null)
					question = "没有设置密保问题";
				if (answer == null)
					answer = "没有设置密保问题";
				toClient = new DataOutputStream(socket.getOutputStream());
				toClient.writeUTF(password);
				toClient.writeUTF(question);
				toClient.writeUTF(answer);
			}
			else if (i==5){
				String oldid=fromClient.readUTF();
				String name = null;
				String sex = null;
				String phone = null;
				String email = null;
				String aid = null;
				String no = null;
				fromClientOb = new ObjectInputStream(
						socket.getInputStream());
				ArrayList<MstudentBean> list = (ArrayList<MstudentBean>) fromClientOb
						.readObject();
				for (MstudentBean ms : list) {
					no = ms.getSNo();
					name = ms.getSName();
					sex = ms.getBno();
					phone = ms.getTime();
					email = ms.getOrderTime();
					aid = ms.getAuthor();
				}
				sql = "select sno from superadmitinformation where sno='" + no
						+ "'";
				res = stat.executeQuery(sql);
				if (res.next()) {
					toClient.writeInt(0);
				} else {
					String passwordd = aid.substring(aid.length() - 4,
							aid.length());
					sql = "begin tran "
							+ "insert into superadmitinformation(sno,sname,ssex,sphone,sid,semail) values(?,?,?,?,?,?) "
							+ "if @@error!=0 rollback tran "
							+ "else begin "
							+ "insert into superadmitacount(sno,spassword) values(?,?) "
							+ "if @@error!=0 rollback tran "
							+ "else begin "
							+ "delete superadmitinformation where sno='"+oldid+"' end "
							+ "end commit tran ";
					PreparedStatement pre = con.conn.prepareStatement(sql);
					pre.setString(1, no);
					pre.setString(2, name);
					pre.setString(3, sex);
					pre.setString(4, phone);
					pre.setString(5, aid);
					pre.setString(6, email);
					pre.setString(7, no);
					pre.setString(8, passwordd);
					pre.executeUpdate();
					toClient.writeInt(1);
				} 
			}
			stat.close();
			res.close();
			con.conn.close();
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
