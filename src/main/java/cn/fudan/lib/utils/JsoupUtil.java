package cn.fudan.lib.utils;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class JsoupUtil {
    private JsoupUtil () {
    }

    public static Document genDocument (String apiUrl, String cookie) throws IOException {
        return genConnection(apiUrl, cookie).get();
    }

    public static Connection.Response genResponse (String apiUrl, String cookie) throws IOException {
        return genConnection(apiUrl, cookie).execute();

    }

    public static Connection genConnection (String apiUrl, String cookie) throws IOException {
        Connection connection = Jsoup.connect(apiUrl)
                .ignoreContentType(true);
        connection.maxBodySize(0);
        return connection;
    }

    public static String getApiJsonData (String apiUrl, String cookie) throws Exception {
        Document doc = genDocument(apiUrl, cookie);return doc.text();
    }

}
