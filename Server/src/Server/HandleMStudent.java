package Server;

import java.io.*;
import java.net.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bean.MstudentBean;

/**
 * @author wsk
 * @className:HandleMStudent
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月24日上午11:44:03
 */
public class HandleMStudent implements Runnable {
	Socket socket;
	DataInputStream fromClient;
	ObjectOutputStream toClientOb;

	public HandleMStudent(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			ContentSQL con = new ContentSQL();
			fromClient = new DataInputStream(socket.getInputStream());
			toClientOb = new ObjectOutputStream(socket.getOutputStream());
			int i = fromClient.readInt();
			Statement stat = con.conn.createStatement();
			ResultSet res;
			String sql = null;
			//s删除所有预约过期
			if (i == 5) {
				sql = "select sno,bno from studentorder where soverdue<CONVERT(varchar(100),GETDATE(),23)";
				res = stat.executeQuery(sql);
				while (res.next()) {
					String sno = res.getString(1);
					String bno = res.getString(2);
					sql = "begin tran ss "
							+ "delete from studentorder where sno= ? and bno= ? "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update book set bstock=bstock+1 where bno=? end "
							+ "if @@error!=0 rollback tran ss else begin "
							+ "update studentacount set sstats=sstats+1 where sno=? end commit tran "
							+ "if @@error!=0 rollback tran ss  ";
					PreparedStatement pre = con.conn.prepareStatement(sql);
					pre.setString(1, sno);
					pre.setString(2, bno);
					pre.setString(3, bno);
					pre.setString(4, sno);
					pre.executeUpdate();
				}
				toClientOb.writeInt(1);

			} else {
				String type = fromClient.readUTF();
				String key = fromClient.readUTF();
				String sorder = " and book.bno=Studentorder.bno and soverdue<CONVERT(varchar(100),GETDATE(),23) order by soverdue desc ";
				String sborrow = " and book.bno=Studentborrow.bno order by sborrow desc ";
				String sborrow2 = " and book.bno=Studentborrow.bno and soverdue<CONVERT(varchar(100),GETDATE(),23) order by soverdue desc ";
				String sreturn = " and book.bno=StudentReturn.bno order by sreturn desc ";
				//单个查询过期
				if (i == 1) {
					sql = "select * from studentorder,book,StudentInformation "
							+ "where StudentInformation.sno=studentorder.sno and ";
				} else if (i == 2 || i == 4) {
					sql = "select * from studentborrow,book,StudentInformation "
							+ "where StudentInformation.sno=studentborrow.sno and ";
				} else if (i == 3) {
					sql = "select * from studentreturn,book,StudentInformation "
							+ "where StudentInformation.sno=studentreturn.sno and ";
				}
				if (type.equals("学生学号")) {
					sql += "StudentInformation.sno like '%" + key + "%' ";
				} else if (type.equals("学生姓名")) {
					sql += "sname like '%" + key + "%'";
				} else if (type.equals("书籍名称")) {
					sql += "bname like '%" + key + "%' ";
				} else if (type.equals("书籍作者"))
					sql += "bauthor like '%" + key + "%' ";
				else if (type.equals("书籍类型"))
					sql += "bsort like '%" + key + "%' ";
				else if (type.equals("书籍译者"))
					sql += "btranslator like '%" + key + "%' ";
				else if (type.equals("书籍出版社"))
					sql += "bpublish like '%" + key + "%' ";
				// System.out.println("type");
				else if (type.equals("书籍编号"))
					sql += "book.bno like '%" + key + "%' ";
				else if (type.equals("进货时间"))
					sql += "bintime like '%" + key + "%' ";
				ArrayList<MstudentBean> list = new ArrayList<MstudentBean>();
				if (i == 1)
					sql += sorder;
				else if (i == 2)
					sql += sborrow;
				else if (i == 3)
					sql += sreturn;
				else if (i == 4)
					sql += sborrow2;
				res = stat.executeQuery(sql);
				//预约过期，借书时间，借书逾期
				if (i == 1 || i == 2 || i == 4)
					while (res.next()) {
						MstudentBean ms = new MstudentBean();
						ms.setSNo(res.getString(1));
						ms.setTime(res.getString(3));
						ms.setOrderTime(res.getString(4));
						ms.setBno(res.getString(5));
						ms.setBname(res.getString(6));
						ms.setAuthor(res.getString(7));
						ms.setSName(res.getString(15));
						list.add(ms);
					}//还书时间
				else if (i == 3) {
					while (res.next()) {
						MstudentBean ms = new MstudentBean();
						ms.setSNo(res.getString(1));
						ms.setSName(res.getString(14));
						ms.setTime(res.getString(3));
						// ms.setOrderTime(res.getString(4));
						ms.setBno(res.getString(4));
						ms.setBname(res.getString(5));
						ms.setAuthor(res.getString(6));
						list.add(ms);
					}
				}
				toClientOb.writeObject(list);
			}
			stat.close();
			res.close();
			con.conn.close();
			toClientOb.flush();
			toClientOb.close();
			fromClient.close();
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
