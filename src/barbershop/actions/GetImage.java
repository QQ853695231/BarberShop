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
private static final int WIDTH=70;//验证码的宽度
private static final int HEIGHT=25;//验证码的高度
//重写HttpServlet中的service方法
public void service() throws IOException {
	//创建一个图像数据缓冲区
	//BufferedImage.TYPE_INT_RGB  表示一个具有八位RGB颜色的分量图像
	System.out.println("进入验证码方法");
	BufferedImage image=new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	Graphics g= image.getGraphics();//获取画笔工具
	g.setColor(new Color(255));//设置背景颜色
	g.fillRect(0, 0, WIDTH, HEIGHT);//绘制画布尺寸
	Random random=new Random();//定义随机数
	//开始绘制验证码
	StringBuilder s=new StringBuilder();
	for(int i=0;i<5;i++){
		int num=random.nextInt(10);//随机生成0-9之间的整数
		char c=(char)(random.nextInt(25)+65);//随机产生A-Z之间的字母
		String[] str=new String[2];
		str[0]=String.valueOf(num);
		str[1]=String.valueOf(c);
		s.append(str[random.nextInt(2)]);
	}
	//将生成的验证码放到当前用户的session中
	ServletActionContext.getRequest().getSession().setAttribute("validatecode", s.toString());
		g.setColor(new Color(255,255,100));//设置验证码的颜色
		g.setFont(new Font(null,Font.ITALIC,20)); //设置验证码的颜色
		g.drawString(s.toString(), 4, 18);//绘制指定的字符串到image图片上面
		 
		for (int i=0;i<6;i++){
			g.setColor(new Color(random.nextInt(256),random.nextInt(256),random.nextInt(256)));
			g.drawLine(random.nextInt(WIDTH),random.nextInt(HEIGHT),random.nextInt(WIDTH),random.nextInt(HEIGHT));
		}
		ServletActionContext.getResponse().setContentType("image/jpeg");//表示输出的是一张图片，格式是jpeg格式
		OutputStream out=ServletActionContext.getResponse().getOutputStream();//获取输出流
		ImageIO.write(image, "jpeg", out);//把图片输出
		out.close();
	}
}


