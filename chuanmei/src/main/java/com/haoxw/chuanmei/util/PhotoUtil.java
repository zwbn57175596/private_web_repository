package com.haoxw.chuanmei.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mortennobel.imagescaling.AdvancedResizeOp;
import com.mortennobel.imagescaling.ResampleOp;

/**
 * 图片上传工具类 单例
 * 
 * @author xuewuhao
 * 
 */
public class PhotoUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(PhotoUtil.class);
	private static PhotoUtil instance = null;

	private PhotoUtil() {

	}

	public static PhotoUtil getInstance() {
		if (instance == null) {
			instance = new PhotoUtil();
		}
		return instance;
	}

	private static String resultPhoto = "";
	public static HashMap<String, String> header;
	static {
		header = new HashMap<String, String>();
		header.put("Content-Type", "image");
	}

	/**
	 * 图片上传
	 * 
	 * @param in
	 * @param photoType
	 *            jpg/png/bmp/gif
	 * @param ok_width
	 * @param ok_height
	 * @return
	 * @throws Exception
	 */
	public static String uploadImgProdByInputStream(java.io.InputStream in,
			String photoType, int ok_width, int ok_height,String path) throws Exception {
		// 图片保存路径
		FileOutputStream fos = null;
		StringBuffer savefilepath = new StringBuffer();
		String yearmonth = DateUtil.date2Str(new Date(), "yyyyMM");
		savefilepath.append(path).append("/").append(yearmonth).append("/");
		String filename = Uuid.getUUID() + "." + photoType;
		try {
			in = cut(in, photoType, ok_width, ok_height);
			long start = System.currentTimeMillis();
			File savefile = new File(savefilepath.toString());
			if (!savefile.exists()) {
				savefile.mkdirs();
			}
			fos = new FileOutputStream(savefilepath.toString() + filename);
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = in.read(bytes)) != -1) {
				fos.write(bytes, 0, read);
			}
			resultPhoto = "/resources/upload/"+yearmonth+"/" + filename;
			long end = System.currentTimeMillis();
			logger.info(" upload image time [ " + (end - start) + " ] url = "
					+ resultPhoto);
		} catch (Exception e) {
			logger.error("upload image error , name [ " + resultPhoto + " ]", e);
			throw e;
		} finally {
			if (in != null) {
				in.close();
			}
			if (fos != null) {
				fos.close();
			}
		}

		return resultPhoto;
	}

	/**
	 * 放大缩小图片到指定宽和高
	 * 
	 * @param image
	 *            Image to scale
	 * @param width
	 *            Width of image
	 * @param height
	 *            Height of image
	 * @return Scaled image file
	 */
	public static BufferedImage scale(BufferedImage image, int width, int height) {
		ResampleOp resampleOp = new ResampleOp(width, height);
		resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
		return resampleOp.filter(image, null);
	}

	/**
	 * 放大缩小图片到指定宽和高
	 * 
	 * @param imageReader
	 *            ImageReader to scale
	 * @param width
	 *            Width of image
	 * @param height
	 *            Height of image
	 * @return Scaled image file
	 */
	public static BufferedImage scale(ImageReader imageReader, int width,
			int height) {
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = imageReader.read(0);
		} catch (Exception e) {
			logger.error("scale imageReader error", e);
		}
		ResampleOp resampleOp = new ResampleOp(width, height);
		resampleOp.setUnsharpenMask(AdvancedResizeOp.UnsharpenMask.Normal);
		return resampleOp.filter(bufferedImage, null);
	}

	/**
	 * 图片压缩 裁剪
	 * 
	 * @param is
	 * @param photoType
	 */
	public static InputStream cut(java.io.InputStream is, String photoType,
			int ok_width, int ok_height) {
		InputStream isNew = null;
		try {
			Iterator<ImageReader> it = ImageIO
					.getImageReadersByFormatName(photoType);
			ImageReader reader = it.next();
			ImageInputStream iis = ImageIO.createImageInputStream(is);
			reader.setInput(iis, true);
			int width = reader.getWidth(0);
			int height = reader.getHeight(0);
			// 不做处理
			if (width <= ok_width && height <= ok_height) {
				BufferedImage bi = reader.read(0);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(bi, photoType, os);
				isNew = new ByteArrayInputStream(os.toByteArray());
			}
			// 金箍棒形(不压缩 宽不变 高度从居中裁剪)
			else if (width <= ok_width && height > ok_height) {
				ImageReadParam param = reader.getDefaultReadParam();
				Rectangle rect = new Rectangle(0, (height - ok_height) / 2,
						width, ok_height);
				param.setSourceRegion(rect);
				BufferedImage bi = reader.read(0, param);
				ByteArrayOutputStream os = new ByteArrayOutputStream();
				ImageIO.write(bi, photoType, os);
				isNew = new ByteArrayInputStream(os.toByteArray());
			}
			// 金箍棒横着放的 或者 又宽又长的
			else {
				int new_w = ok_width;
				int new_h = (int) Math.round(ok_width * (height * 1.0 / width));
				if (new_h <= ok_height) {
					// 压缩即可 不需要再裁减
					BufferedImage bi = scale(reader, new_w, new_h);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(bi, photoType, os);
					isNew = new ByteArrayInputStream(os.toByteArray());
				} else {
					// 先压缩
					BufferedImage bi = scale(reader, new_w, new_h);
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					ImageIO.write(bi, photoType, os);
					isNew = new ByteArrayInputStream(os.toByteArray());
					// 再裁剪
					iis = ImageIO.createImageInputStream(isNew);
					reader.setInput(iis, true);
					ImageReadParam param2 = reader.getDefaultReadParam();
					Rectangle rect2 = new Rectangle(0, (new_h - ok_height) / 2,
							new_w, ok_height);
					param2.setSourceRegion(rect2);
					BufferedImage bi2 = reader.read(0, param2);
					ByteArrayOutputStream os2 = new ByteArrayOutputStream();
					ImageIO.write(bi2, photoType, os2);
					isNew = new ByteArrayInputStream(os2.toByteArray());
				}
			}

		} catch (IOException e) {
			isNew = is;
			logger.error("", e);
		}
		return isNew;
	}

	public static void main(String a[]) throws Exception {
		PhotoUtil pu = PhotoUtil.getInstance();
		File f = new File("d:/6.jpg");
		FileInputStream in = null;
		long m = System.currentTimeMillis();
		for (int i = 0; i < 100; i++) {
			in = new FileInputStream(f);
			String url = pu.uploadImgProdByInputStream(in, "jpg",
					(1 + (int) (Math.random() * 600)),
					(1 + (int) (Math.random() * 800)),"d:/test");
			System.out.println("url=" + url);
		}
		long b = System.currentTimeMillis();
		System.out.println("use time=" + (b - m));
	}
}
