
package Server;

import java.sql.*;

/**
 * @author wsk
 * @className:ContentSQL
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016��5��8������9:07:52
 */
public class ContentSQL {
	Connection conn;
	public ContentSQL() throws SQLException,ClassNotFoundException{
//		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//		String url="jdbc.sqlserver://localhost:1433;DatabaseName=ͼ����Ϣ";
//		String name="sa";
//		String password="123456";
//		Connection con=DriverManager.getConnection(url, name, password);
		// �������ݿ�������ע�ᵽ����������
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		// ���ݿ������ַ���  ��ݵ�ַ10.94.102.154 ÿ�ζ����
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ѧ������ͼ��軹ϵͳ";
		// ���ݿ��û���
		String username = "sa";
		// ���ݿ�����
		String password = "123456";
		// ����Connection����
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
