package io.github.yudady;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class App implements CommandLineRunner {

	@Autowired
	ApplicationContext context;


	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Connection connect = Jsoup.connect("https://www.taiwanlottery.com.tw/Lotto/BINGOBINGO/drawing.aspx");
		connect.followRedirects(true);
		connect.sslSocketFactory(socketFactory());
		connect.ignoreHttpErrors(true);
		Document doc = connect.get();
		System.out.println("title = " + doc.title());


//		String html = doc.body().html();
//		System.out.println("html = " + html);

		List<Data> datas = new ArrayList<>();
		Elements trElements = doc.select("td.thB>table.tableFull>tbody>tr");

		for (Element trElement : trElements) {
			List<String> tdTexts = trElement.select("td").eachText();
			if (tdTexts.size() == 0) continue;
			String num = tdTexts.get(0);
			if ("".equals(num)) continue;
			if (!num.matches("\\d+")) continue;


			System.out.println("========================");
			System.out.println(tdTexts);
			System.out.println("========================");
		}


//		Element targetElement = trElements.get(0);
//		System.out.println(trElements.html());
//		IntStream.range(1, 10).forEach(n -> System.out.println("========================"));


//		System.out.println(trElements.get(1));

//		for (Element trElement : trElements) {
//			if (trElement.firstElementChild().text().equals("")) continue;
//
//			String html = trElement.html();
//			System.out.println("html = " + html);
//			Document tdsDocument = Jsoup.parse(html);
//			Elements tdElements = tdsDocument.select("td");
//			List<String> texts = tdElements.eachText();
//			Data data = new Data(texts.get(0), texts.get(1), texts.get(2), texts.get(3), texts.get(4));
//			datas.add(data);
//		}
//
//		datas.forEach(System.out::println);
	}

	record Data(String peroid, String numbers, String bigNumber, String bigger, String odds) {
	}

	private SSLSocketFactory socketFactory() {
		TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
			public java.security.cert.X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
			}
		}};

		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
			return sslContext.getSocketFactory();
		} catch (NoSuchAlgorithmException | KeyManagementException e) {
			throw new RuntimeException("Failed to create a SSL socket factory", e);
		}
	}

}
