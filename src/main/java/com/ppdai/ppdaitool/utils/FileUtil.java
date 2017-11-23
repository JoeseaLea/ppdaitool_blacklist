package com.ppdai.ppdaitool.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;

public class FileUtil {
	
	/**
	 * 保存文件
	 * @param filePath
	 * @param data
	 * @return
	 */
	public static boolean writeFile(String filePath, String data){
		OutputStreamWriter writer = null;
		boolean result = false;
		try{
			writer = new OutputStreamWriter(new FileOutputStream(new File(filePath)), "UTF-8");
			writer.write(data);
			writer.close();
			writer = null;
			
			result = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(writer!=null) {
				try {
					writer.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 读取文件
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String readFile(String filePath)throws Exception{
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
			String line = null;
			while((line=reader.readLine())!=null){
				sb.append(line);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(reader != null){
				reader.close();
			}
		}
		return sb.toString();
	}
	
	/**
	 * 保存验证码图片
	 * @param bufferedImage
	 */
	public static void saveValiCodeImg(BufferedImage bufferedImage, String valiCodeImgSavePath) {
		try {
			BufferedImage inputbig = new BufferedImage(100, 44, BufferedImage.TYPE_INT_BGR);
			inputbig.getGraphics().drawImage(bufferedImage, 0, 0, 100, 44, null); // 画图
	
			File file2 = new File(valiCodeImgSavePath); // 此目录保存缩小后的关键图
			if (file2.exists()) {
//				System.out.println("多级目录已经存在不需要创建！！");
			} else {
				// 如果要创建的多级目录不存在才需要创建。
				file2.mkdirs();
			}
			ImageIO.write(inputbig, "jpg", new File(valiCodeImgSavePath + "/valiCodeImg.jpg")); // 将其保存在C:/imageSort/targetPIC/下
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * 获取文件数据进行16进制编码的字符串
	 * @param filePath 文件路径
	 * @return
	 */
	public static byte[] getFileHex(String filePath) {
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(filePath);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = fileInputStream.read(buff)) != -1) {
				bos.write(buff, 0, len);
			}
			// 得到图片的字节数组
			byte[] result = bos.toByteArray();
			
			return result;
			
			/*String hs = "";
			String stmp = "";
			for (int n = 0; n < result.length; n++) {
				stmp = (Integer.toHexString(result[n] & 0XFF));
				if (stmp.length() == 1)
					hs = hs + "0" + stmp;
				else
					hs = hs + stmp;
			}
			return hs.toUpperCase();*/
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
