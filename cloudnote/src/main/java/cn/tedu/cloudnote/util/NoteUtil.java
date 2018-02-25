package cn.tedu.cloudnote.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

import javafx.scene.control.TreeTableRow;

public class NoteUtil {
	public static String md5(String src){
		try {
			MessageDigest md=MessageDigest.getInstance("MD5");
			byte[] output=md.digest(src.getBytes());
			//return new String(output);
			String ret =Base64.encodeBase64String(output);
			return ret;
		} catch (Exception e) {
			throw new Md5Exception("加密失败", e);
		}
	}
	//利用UUID生成主键值
	public static String createId(){
		String id=UUID.randomUUID().toString();
		//return id;
		return id.replace("-","");
	}
	public static void main(String[] args) {
		System.out.println(NoteUtil.createId());
	}
}










