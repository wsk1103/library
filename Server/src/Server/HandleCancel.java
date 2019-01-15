package Server;

import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import bean.book;

/**
 * @author wsk
 * @className:HandleCancel
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月21日下午5:29:26
 */
public class HandleCancel implements Runnable {
	Socket socket;
	DataInputStream fromClient;
	DataOutputStream toClient;
	ObjectOutputStream toClientOb;

	public HandleCancel(Socket socket) {
		// TODO Auto-generated constructor stub
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL con = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			toClient = new DataOutputStream(socket.getOutputStream());
			String sno = fromClient.readUTF();
			String bno = fromClient.readUTF();
			String sql;
			sql = "select * from studentorder where sno='" + sno
					+ "' and bno='" + bno + "' ";
			Statement stat = con.conn.createStatement();
			ResultSet res = stat.executeQuery(sql);
			//已经取消预约了
			if (!res.next()) {
				toClient.writeInt(2);
			} 
			//取消预约
			else {
				sql = "begin tran ss "
						+ "delete from studentorder where sno= ? and bno= ? "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update book set bstock=bstock+1 where bno= ? end "
						+ "if @@error!=0 rollback tran ss else begin "
						+ "update studentacount set sstats=sstats+1 where sno= ? end commit tran "
						+ "if @@error!=0 rollback tran ss";
				PreparedStatement pre = con.conn.prepareStatement(sql);
				pre.setString(1, sno);
				pre.setString(2, bno);
				pre.setString(3, bno);
				pre.setString(4, sno);
				pre.executeUpdate();
				toClient.writeInt(1);
				pre.close();
			}
			stat.close();
			res.close();
			toClient.flush();
			toClient.close();
			fromClient.close();
			con.conn.close();
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
