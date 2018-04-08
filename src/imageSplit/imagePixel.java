package imageSplit;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.EventListener;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class imagePixel {
	
	// ���ļ�ȫ����ȡ�ļ�������������׺��
	private String getPrefix(String fileName) {
		if (fileName.contains(".")) {
			return fileName.substring(0,fileName.lastIndexOf("."));
		} else {
			return "1";
		}
	}

	// ��·����ȡ�ļ���׺��
	private String getSuffix(String path) {
		if (path.contains(".")) {
			return path.substring(path.lastIndexOf(".") + 1);
		} else {
			return "jpg";
		}
	}

	/**
	 * ������ �и�ͼƬ ����и�� 3*3
	 * 
	 * @param filePath
	 *            �ļ���
	 * @param row
	 *            ��
	 * @param col
	 *            ��
	 * @throws Exception
	 */
	public void splitImage(String filePath, int row, int col) throws Exception {
		File file = new File(filePath);
		System.out.println(file.getPath());
		System.out.println(file.getName());
		BufferedImage bi = ImageIO.read(file);
		
		if (row > 3 || row < 0 || col > 3 || col < 0) {
			throw new Exception("���� ���� �Ƿ�");
		}
		

		int width = bi.getWidth() / col;
		int height = bi.getHeight() / row;

		// �ָ�
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				BufferedImage t = bi.getSubimage(i * width, j * height, width, height);
				ImageIO.write(t, getSuffix(filePath), 
						new File(file.getParentFile().getPath() + "\\" + getPrefix(file.getName())+"-"+(j*col+i)+"."+getSuffix(filePath)));
			}
		}

	}

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		imagePixel ip = new imagePixel();
		
		int row=Integer.valueOf(s.nextLine());
		int col=Integer.valueOf(s.nextLine());;
		
		try {
			ip.splitImage(s.nextLine(), row, col);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
