package GUI;

/**
 * @author wsk
 * @className:MyTable
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016��5��20������11:30:12
 */
import javax.swing.table.*;

public class MyTable extends DefaultTableModel {
	public MyTable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);// ����һ��Ҫ���Ǹ���Ĺ��췽����������ʵ��myTableModel
	}

	public boolean isCellEditable(int row, int column) {
		return false;// ����ķ��������� return true�ģ����ԾͿ��Ա༭��~~~
	}
}