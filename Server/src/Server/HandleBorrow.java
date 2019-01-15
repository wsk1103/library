package Server;

import java.io.*;
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

import bean.book;

/**
 * @author wsk
 * @className:HandleBrrow
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月21日下午8:17:27
 */
public class HandleBorrow implements Runnable {
	Socket socket;
	DataInputStream fromClient;
	DataOutputStream toClient;
	ObjectOutputStream toClientOb;

	public HandleBorrow(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL con = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			toClient = new DataOutputStream(socket.getOutputStream());
			String num = fromClient.readUTF();
			int i = fromClient.readInt();
			//查询借书情况
			if (i == 1) {
				String sql = "select book.bno,bname,bauthor,btranslator,bpublish,bsort,sborrow,soverdue "
						+ "from studentborrow,book where studentborrow.bno=book.bno and sno='"
						+ num + "' order by sborrow desc";
				Statement stat = con.conn.createStatement();
				ResultSet res = stat.executeQuery(sql);
				ArrayList<book> list = new ArrayList<book>();
				while (res.next()) {
					book book = new book();
					book.setNo(res.getString(1));
					book.setName(res.getString(2));
					book.setAuthor(res.getString(3));
					book.setTranslator(res.getString(4));
					book.setPublis(res.getString(5));
					book.setSort(res.getString(6));
					book.setTime(res.getDate(7));
					book.setOrderTime(res.getString(8));
					list.add(book);
				}
				toClientOb = new ObjectOutputStream(socket.getOutputStream());
				toClientOb.writeObject(list);
				// 10.94.69.28
				res.close();
				stat.close();
			}
			//更新借书
			else if (i == 2) {
				String no = fromClient.readUTF();
				String sql = "select * from book where bno='" + no + "'";
				Statement stat = con.conn.createStatement();
				ResultSet res = stat.executeQuery(sql);
				if (!res.next())//如果该书籍已经下架
					toClient.writeInt(4);
				else {
					Date date = new Date();
					DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
					String time = format.format(date);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date myDate;
					String overdue;
					myDate = sdf.parse(time);
					Calendar cl = Calendar.getInstance();
					cl.setTime(myDate);
					cl.add(Calendar.DATE, 30);
					myDate = cl.getTime();
					overdue = sdf.format(myDate);
					sql = "begin tran ss "
							+ "update studentborrow set sborrow= ? where sno= ? and bno= ? "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update studentborrow set soverdue=? where bno=? and sno=? end "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update book set bacount=bacount+1 where bno=? end commit tran "
							+ "if @@error!=0 rollback tran ss ";
					PreparedStatement pre = con.conn.prepareStatement(sql);
					pre.setString(1, time);
					pre.setString(2, num);
					pre.setString(3, no);
					pre.setString(4, overdue);
					pre.setString(5, no);
					pre.setString(6, num);
					pre.setString(7, no);
					pre.executeUpdate();

					toClient.writeInt(1);
					pre.close();
				}
				stat.close();
				res.close();
			}
			
			fromClient.close();
			toClient.close();
			con.conn.close();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
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
