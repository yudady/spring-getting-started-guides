package io.github.yudady.ciphers;

import java.util.Map;
import java.util.TreeMap;
import javax.net.ssl.SSLServerSocketFactory;


/**
 * 列出 JVM 使用的 ciphers
 * <p>
 * 添加其他密碼
 * 您可能需要在 JVM 中有更多密碼。為此，您需要為您的 JDK 下載並安裝Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files。
 * <p>
 * 訪問http://www.oracle.com/technetwork/java/javase/downloads/index.html，搜索JCE並下載。
 * 確保您正在為您的 JDK 版本下載 JCE。
 * /tmp在臨時位置（例如）解壓縮下載。
 * 在lib/security您的JAVA_HOME. 取決於您運行的是 JDK 還是JAVA_HOME/lib/securityJREJAVA_HOME/jre/lib/security
 * 從該目錄備份 local_policy.jar 和 US_export_policy.jar jar。
 * 將 JCE 策略文件中的 local_policy.jar 和 US_export_policy.jar jar 複製到您的 lib/security 目錄中。
 * 再次運行 java 密碼。現在應該可以從您的兼容密碼列表中找到更多密碼。如果是這樣，請繼續執行後續步驟。
 * 確保密碼屬性存在於您的server.xml（如Git 克隆因 SSL 例程而失敗：SSL23_GET_SERVER_HELLO）。
 * 重新啟動存儲。
 */
public class Ciphers {
    public static void main(String[] args) throws Exception {
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        String[] defaultCiphers = ssf.getDefaultCipherSuites();
        String[] availableCiphers = ssf.getSupportedCipherSuites();

        TreeMap<String, Boolean> ciphers = new TreeMap<>();

        for (String availableCipher : availableCiphers) ciphers.put(availableCipher, Boolean.FALSE);

        for (String defaultCipher : defaultCiphers) ciphers.put(defaultCipher, Boolean.TRUE);

        System.out.println("Default\tCipher");
        ciphers.entrySet()
            .stream()
            .map(Map.Entry.class::cast)
            .forEach(cipher -> {
                if (Boolean.TRUE.equals(cipher.getValue())) System.out.print('*'); // default
                else System.out.print(' ');

                System.out.print('\t');
                System.out.println(cipher.getKey());
            });
    }
}