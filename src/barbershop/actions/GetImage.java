package barbershop.actions;



import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import org.apache.struts2.ServletActionContext;

public class GetImage{
/**
	 * 
	 */
private static final int WIDTH=70;//��֤��Ŀ��
private static final int HEIGHT=25;//��֤��ĸ߶�
//��дHttpServlet�е�service����
public void service() throws IOException {
	//����һ��ͼ�����ݻ�����
	//BufferedImage.TYPE_INT_RGB  ��ʾһ�����а�λRGB��ɫ�ķ���ͼ��
	System.out.println("������֤�뷽��");
	BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	Graphics g= image.getGraphics();//��ȡ���ʹ���
	g.setColor(new Color(255));//���ñ�����ɫ
	g.fillRect(0, 0, WIDTH, HEIGHT);//���ƻ����ߴ�
	Random random=new Random();//���������
	//��ʼ������֤��
	StringBuilder s=new StringBuilder();
	for(int i=0;i<5;i++){
		int num=random.nextInt(10);//�������0-9֮�������
		char c=(char)(random.nextInt(25)+65);//�������A-Z֮�����ĸ
		String[] str=new String[2];
		str[0]=String.valueOf(num);
		str[1]=String.valueOf(c);
		s.append(str[random.nextInt(2)]);
	}
	//�����ɵ���֤��ŵ���ǰ�û���session��
	ServletActionContext.getRequest().getSession().setAttribute("validatecode", s.toString());
		g.setColor(new Color(255,255,100));//������֤�����ɫ
		g.setFont(new Font(null,Font.ITALIC,20)); //������֤�����ɫ
		g.drawString(s.toString(), 4, 18);//����ָ�����ַ�����imageͼƬ����
		 
		for (int i=0;i<6;i++){
			g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
			g.drawLine(random.nextInt(WIDTH),random.nextInt(HEIGHT),random.nextInt(WIDTH),random.nextInt(HEIGHT));
		}
		ServletActionContext.getResponse().setContentType("image/jpeg");//��ʾ�������һ��ͼƬ����ʽ��jpeg��ʽ
		OutputStream out=ServletActionContext.getResponse().getOutputStream();//��ȡ�����
		ImageIO.write(image, "jpeg", out);//��ͼƬ���
		out.close();
	}
}


