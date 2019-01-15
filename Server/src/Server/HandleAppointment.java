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
 * @className:HandleAppointment
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月20日下午8:36:37
 */
public class HandleAppointment implements Runnable {
	Socket socket;

	public HandleAppointment(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL c = new ContentSQL();
			DataOutputStream toClient = new DataOutputStream(
					socket.getOutputStream());
			DataInputStream fromClient = new DataInputStream(
					socket.getInputStream());
			int i = fromClient.readInt();//判断采用哪种方式
			String sno = fromClient.readUTF();
			//预约
			if (i == 1) {
				String bno = fromClient.readUTF();
				Statement stat = c.conn.createStatement();
				int stats = 0;//可以借次数
				int stock = 0;//库存
				String sql;
				sql = "select sstats from studentacount where sno='" + sno
						+ "' ";
				ResultSet res = stat.executeQuery(sql);
				if (res.next()) {
					stats = res.getInt(1);
					toClient.writeInt(stats);
				}
				sql = "select bstock from book where bno='" + bno + "'";
				res = stat.executeQuery(sql);
				if (res.next()) {
					stock = res.getInt(1);
					toClient.writeInt(stock);
				}
				if (stats != 0 && stock != 0) {
					String time = fromClient.readUTF();
					sql = "select sno from studentorder where sno='" + sno
							+ "' and bno='" + bno + "'";
					res = stat.executeQuery(sql);
					if (res.next()) {//已经借了该书籍
						toClient.writeInt(2);
					} else {
						sql="select * from book where bno='"+bno+"'";
						res=stat.executeQuery(sql);
						if (!res.next())
							toClient.writeInt(4);//改书籍已经下架
						else {
						sql = "select sno from studentborrow where sno='" + sno
								+ "' and bno='" + bno + "'";
						res = stat.executeQuery(sql);
						if (res.next()) {//已经借了该书籍
							toClient.writeInt(3);
						} else {
							sql = "insert into studentorder(sno,bno,sorder,soverdue) values(?,?,?,?)";
							PreparedStatement pre = c.conn
									.prepareStatement(sql);
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							Date myDate;
							String overduetime;
							myDate = sdf.parse(time);
							Calendar cl = Calendar.getInstance();
							cl.setTime(myDate);
							cl.add(Calendar.DATE, 1);
							myDate = cl.getTime();
							overduetime = sdf.format(myDate);
							pre.setString(1, sno);
							pre.setString(2, bno);
							pre.setString(3, time);
							pre.setString(4,overduetime);
							pre.executeUpdate();
							sql = "begin tran ss "
									+ "update book set bstock=bstock-1 where bno=? "
									+ "if @@error!=0 rollback tran ss else begin "
									+ "update book set bacount=bacount+1 where bno=? end "
									+ "if @@error!=0 rollback tran ss else begin "
									+ "update studentacount set sstats=sstats-1 where sno=? end commit tran "
									+ "if @@error!=0 rollback tran ss  ";
							pre = c.conn.prepareStatement(sql);
							// System.out.println(sql);
							pre.setString(1, bno);
							pre.setString(2, bno);
							pre.setString(3, sno);
							pre.executeUpdate();
							// System.out.println("yes");
							toClient.writeInt(1);
							pre.close();
						}
						}
					}
				}
				stat.close();
				res.close();
			} 
			//查看预约情况
			else if (i == 2) {
				ObjectOutputStream toClientOb = new ObjectOutputStream(
						socket.getOutputStream());
				Statement stat = c.conn.createStatement();
				String sql = "select sno,book.bno,sorder,bname,bauthor,bpublish,bsort,btranslator,soverdue "
						+ "from studentorder,book where book.bno=studentorder.bno and sno='"
						+ sno + "' " + "order by sorder desc";
				ResultSet res = stat.executeQuery(sql);
				ArrayList<book> list = new ArrayList<book>();
				while (res.next()) {
					book b = new book();
					b.setNo(res.getString(2));
					b.setTime(res.getDate(3));
					b.setName(res.getString(4));
					b.setAuthor(res.getString(5));
					b.setPublis(res.getString(6));
					b.setSort(res.getString(7));
					b.setTranslator(res.getString(8));
					b.setOrderTime(res.getString(9));
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
