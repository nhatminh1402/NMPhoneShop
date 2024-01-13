package dao;

import java.io.IOException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import bean.SanPhamBEAN;

public class mainTest {

	public static void main(String[] args) {
		SanPhamBEAN sp = new SanPhamBEAN("1", "ios", 0, 0);
		Gson json = new Gson();// chúng ta dùng thư viện Gson để chuyển đối tượng sang json
		String information = json.toJson(sp);
		System.out.println(information);

//		 Locale localeVN = new Locale("vi", "VN");
//		 NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
//		 String str1 = currencyVN.format(50000000);
//		 String x = str1.replaceAll("[₫]", "VND");
//
//        System.out.println(x);

	}

}
