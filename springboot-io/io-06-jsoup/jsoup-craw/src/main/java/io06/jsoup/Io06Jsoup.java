package io06.jsoup;


import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class Io06Jsoup implements CommandLineRunner {

	@Autowired
	ApplicationContext context;


	public static void main(String[] args) {
		SpringApplication.run(Io06Jsoup.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Connection connect = Jsoup.connect("https://www.taiwanlottery.com.tw/Lotto/BINGOBINGO/drawing.aspx");
		connect.followRedirects(true);
		connect.sslSocketFactory(getTrustAllSocketFactory());
		connect.ignoreHttpErrors(true);
		Document doc = connect.get();
		System.out.println("title = " + doc.title());

		String html = doc.body().html();
		System.out.println(html);

	}

	private static SSLSocketFactory getTrustAllSocketFactory() {
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
