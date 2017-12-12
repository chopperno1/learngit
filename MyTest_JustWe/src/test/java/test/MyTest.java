package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyTest {

	public static void main(String[] args) {
		try {
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		
	}
	
	/**
	 * ����http�����ȡ���ؽ��
	 * 
	 * @param requestUrl
	 *            �����ַ
	 * @return
	 */
	public static String httpRequest(String requestUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(false);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod("GET");
			httpUrlConn.connect();

			// �����ص�������ת�����ַ���
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	/**
	 * ö��
	 * 
	 * @author Administrator
	 *
	 */
	public enum Color {

		RED, GREEN, BLANK, YELLOW

	}

	public static boolean isRed(Color color) {

		if (Color.RED.equals(color)) {

			return true;

		}

		return false;

	}

	public static void showColor(Color color) {

		switch (color) {

		case BLANK:

			System.out.println(color);

			break;

		case RED:

			System.out.println(color);

			break;

		default:

			System.out.println(color);

			break;
		}
	}
	
}
