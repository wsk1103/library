
package Server;

import java.sql.*;

/**
 * @author wsk
 * @className:ContentSQL
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月8日下午9:07:52
 */
public class ContentSQL {
	Connection conn;
	public ContentSQL() throws SQLException,ClassNotFoundException{
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		String url="jdbc.sqlserver://localhost:1433;DatabaseName=图书信息";
//		String name="sa";
//		String password="123456";
//		Connection con=DriverManager.getConnection(url, name, password);
		// 加载数据库驱动，注册到驱动管理器
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// 数据库连接字符串  锐捷地址10.94.102.154 每次都会改
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生网上图书借还系统";
		// 数据库用户名
		String username = "sa";
		// 数据库密码
		String password = "123456";
		// 创建Connection连接
		conn = DriverManager.getConnection(url,username,password);
//		System.out.println("successful");
//		Statement statement=conn.createStatement();
//		ResultSet res=statement.executeQuery("select sname,ssex,sstats from studentinformation,studentacount where (studentinformation.sno=studentacount.sno)");
//		while (res.next()){
//			System.out.println(res.getString(1)+" "+res.getString(2)+" "+res.getString(3));
//		}
//		conn.close();
	}
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		new ContentSQL();
//	}
}
