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
		g.setFont(new Font("隶书", Font.BOLD, 35));
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
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 删除;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}
}
