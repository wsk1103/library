package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class yan {
	private String[] str = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
			"K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
			"Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k",
			"m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y",
			"z", "2", "3", "4", "5", "6", "7", "8", "9" };
	static String[] arry = new String[50];
	public Random random = new Random();
	//public int cc;
	Random r=new Random();
	String s;

	public String getRandom() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			buffer.append(str[random.nextInt(str.length)]);
		}
		return buffer.toString();
	}

	public Color getColor() {
		return new Color(random.nextInt(255), random.nextInt(255),
				random.nextInt(255));
	}

	public Color getreversColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(),
				255 - c.getBlue());
	}

	public yan() throws IOException {
		int x = 100, y = 35;
		BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		g.setFont(new Font("����", Font.BOLD, 35));
		s = getRandom();
		String ss=s.toLowerCase();
		File folder = new File("Random");
		deleteFile(folder);
		if (!folder.exists())
			folder.mkdir();
		File f = new File("Random/" + ss + ".jpg");
		// File f2=new File("Random/i"+cc+".jpg");
		g.setColor(getColor());
		g.fillRect(0, 0, x, y);
		g.setColor(getreversColor(getColor()));
		g.drawString(s, 12, 28);
		for (int i = 0; i < r.nextInt(100); i++)
			g.drawRect(r.nextInt(x), r.nextInt(y), 1, 1);
		ImageIO.write(bi, "jpg", f);
	}
	// public static void main(String[] args) throws IOException {
	// new yan();
	// }
	public void deleteFile(File file) {
		// TODO Auto-generated method stub
		if (file.exists()) { // �ж��ļ��Ƿ����
			if (file.isFile()) { // �ж��Ƿ����ļ�
				file.delete(); // delete()���� ɾ��;
			} else if (file.isDirectory()) { // �����������һ��Ŀ¼
				File files[] = file.listFiles(); // ����Ŀ¼�����е��ļ� files[];
				for (int i = 0; i < files.length; i++) { // ����Ŀ¼�����е��ļ�
					this.deleteFile(files[i]); // ��ÿ���ļ� ������������е���
				}
			}
			file.delete();
		} else {
			System.out.println("��ɾ�����ļ������ڣ�" + '\n');
		}
	}
}
