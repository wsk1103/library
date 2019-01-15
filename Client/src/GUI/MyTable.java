package GUI;

/**
 * @author wsk
 * @className:MyTable
 * @version 2014
 * @ModifiedBy wsk
 * @Copyright sk.w
 * @date 2016年5月20日上午11:30:12
 */
import javax.swing.table.*;

public class MyTable extends DefaultTableModel {
	public MyTable(Object[][] data, Object[] columnNames) {
		super(data, columnNames);// 这里一定要覆盖父类的构造方法，否则不能实例myTableModel
	}

	public boolean isCellEditable(int row, int column) {
		return false;// 父类的方法里面是 return true的，所以就可以编辑了~~~
	}
}