package Server;

import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.io.*;

import bean.book;

/**
 * @author wsk
 * @className:Handlereturn
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月22日下午2:27:46
 */
public class HandleReturn implements Runnable {
	Socket socket;
	DataOutputStream toClient;
	DataInputStream fromClient;
	ObjectOutputStream toClientOb;

	public HandleReturn(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL c = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			toClient = new DataOutputStream(socket.getOutputStream());
			int i = fromClient.readInt();
			String sno = fromClient.readUTF();
			if (i == 1) {
				String bno = fromClient.readUTF();
				String time = fromClient.readUTF();

				Statement stat = c.conn.createStatement();
				String sql = "select sno from studentorder where sno='" + sno
						+ "' and bno='" + bno + "'";
				ResultSet res = stat.executeQuery(sql);
				if (res.next()) {
					int te = 2;//已经预约
					toClient.writeInt(te);
				} 
				//更新预约
				else {
					sql = "insert into studentorder(sno,bno,sorder) values(?,?,?) ";
					PreparedStatement pre = c.conn.prepareStatement(sql);
					pre.setString(1, sno);
					pre.setString(2, bno);
					pre.setString(3, time);
					pre.executeUpdate();
					sql = "begin tran ss "
							+ "update book set bstock=bstock-1 where bno=? "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update book set bacount=bacount+1 where bno=? end "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update studentacount set sstats=sstats-1 where sno=? end commit tran "
							+ "if @@error!=0 rollback tran ss  ";
					pre = c.conn.prepareStatement(sql);
					pre.setString(1, bno);
					pre.setString(2, bno);
					pre.setString(3, sno);
					pre.executeUpdate();
					toClient.writeInt(1);
					pre.close();
				}
			}
			//查询还书情况
			else if (i == 2) {
				ObjectOutputStream toClientOb = new ObjectOutputStream(
						socket.getOutputStream());
				Statement stat = c.conn.createStatement();
				String sql = "select sno,book.bno,sreturn,bname,bauthor,bpublish,bstock,bsort,btranslator "
						+ "from studentreturn,book where book.bno=studentreturn.bno and sno='"
						+ sno + "' order by sreturn desc ";
				ResultSet res = stat.executeQuery(sql);
				ArrayList<book> list = new ArrayList<book>();
				while (res.next()) {
					book b = new book();
					b.setNo(res.getString(2));
					b.setTime(res.getDate(3));
					b.setName(res.getString(4));
					b.setAuthor(res.getString(5));
					b.setPublis(res.getString(6));
					b.setSort(res.getString(8));
					b.setTranslator(res.getString(9));
					b.setStock(res.getInt(7));

					list.add(b);
				}
				toClientOb.writeObject(list);
			}
			toClient.flush();
			toClient.close();
			fromClient.close();
			c.conn.close();
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
