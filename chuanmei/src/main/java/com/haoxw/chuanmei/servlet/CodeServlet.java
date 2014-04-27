package com.haoxw.chuanmei.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 验证码
 * 
 * @author xuewuhao
 * 
 */
public class CodeServlet extends HttpServlet {
	
	private static Logger logger = LoggerFactory.getLogger(CodeServlet.class);
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Random random = new Random();

		// 定义数组存放加减乘除四个运算符
		char[] arr = { '+', '-', '×', '÷' };

		// 生成10以内的随机整数num1
		int num1 = random.nextInt(10);

		// 生成一个0-3之间的随机整数operate
		int operate = random.nextInt(3);

		// 生成10以内的随机整数num1
		int num2 = random.nextInt(10);

		// 运算结果
		int result = 0;

		// 假定position值0/1/2分别代表"+","-","*"，计算前面操作数的运算结果
		switch (operate) {
		case 0:
			result = num1 + num2;
			break;
		case 1:
			result = num1 - num2;
			break;
		case 2:
			result = num1 * num2 ;
			break;
		}

		logger.info(num1 + "," + num2  + ", " + arr[operate]
				+ "," + result);

		// 将生成的验证码值(即运算结果的值)放到session中，以便于后台做验证。
		HttpSession session = request.getSession();
		session.setAttribute("randCode", result);

		int width = 80, height = 20;
		// 创建BufferedImage对象，设置图片的长度宽度和色彩。
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		OutputStream os = response.getOutputStream();
		// 取得Graphics对象，用来绘制图片
		Graphics g = image.getGraphics();
		// 绘制图片背景和文字,释放Graphics对象所占用的资源。
		g.setColor(getRandColor(200, 250));

		// 设置内容生成的位置
		g.fillRect(0, 0, width, height);
		// 设置内容的字体和大小 PLAIN
		g.setFont(new Font("Times New Roman", Font.ITALIC, 18));

		// 设置内容的颜色：主要为生成图片背景的线条
		g.setColor(getRandColor(160, 200));

		// 图片背景上随机生成155条线条，避免通过图片识别破解验证码
		for (int i = 0; i < 350; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}

		// 构造运算表达式
		String content = num1 + "" + arr[operate] + "" + num2  + "=?";

		// 设置写运算表达的颜色
		g.setColor(new Color(20 + random.nextInt(110),
				20 + random.nextInt(110), 20 + random.nextInt(110)));
		// 在指定位置绘制指定内容（即运算表达式）
		g.drawString(content, 5, 16);
		// 释放此图形的上下文以及它使用的所有系统资源，类似于关闭流
		g.dispose();

		// 通过ImageIO对象的write静态方法将图片输出。
		ImageIO.write(image, "JPEG", os);
		os.close();

	}

	/**
	 * 生成随机颜色
	 * 
	 * @param fc
	 * @param bc
	 * @return
	 */
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

}
