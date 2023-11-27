package common;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encryptor {
	public static String ecrypt(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String enrtext; // Kết quả trả về

		// Thuật toán mã hóa theo từng ký tự
		byte[] srctextbyte = text.getBytes("UTF-8");

		// Truyền vào tên thuật toán cần mã hóa
		// Lớp này giúp mã hóa
		MessageDigest msd = MessageDigest.getInstance("MD5");

		// Tiến hành mã hóa thành mãng các byte mới
		byte[] enrtextbyte = msd.digest(srctextbyte);

		BigInteger big = new BigInteger(1, enrtextbyte);

		enrtext = big.toString(16);
		return enrtext;
	}

}
