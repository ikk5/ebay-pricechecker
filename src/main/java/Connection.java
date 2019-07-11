import XML.FindCompletedItemsResponse;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Properties;

public class Connection {
    private String ebay_url;
    private String appID;
    private String globalID;
    private HttpURLConnection con;

    public Connection(Map<String, String> urlParams, Properties properties) throws IOException {
        initProperties(properties);
        URL url = new URL(this.ebay_url + getParamsString(urlParams));
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setConnectTimeout(5000);
        con.setReadTimeout(5000);
        setRequestHeaders(con);
    }

    private void initProperties(Properties properties) throws IOException {
        ebay_url = properties.getProperty("PROD_URL");
        appID = properties.getProperty("PROD_APPNAME");
        globalID = properties.getProperty("EBAY_GLOBAL_ID");
        if (StringUtils.contains(appID, "your ebay App ID")) {
            throw new IOException("No app ID set in config.properties");
        }
    }

    public FindCompletedItemsResponse getResponse() throws IOException, JAXBException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

//        test(in);
//        return null;

        JAXBContext jaxbContext = JAXBContext.newInstance(FindCompletedItemsResponse.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

        FindCompletedItemsResponse findCompletedItemsResponse = (FindCompletedItemsResponse) jaxbUnmarshaller.unmarshal(in);
        in.close();

        return findCompletedItemsResponse;
    }

    private String getParamsString(Map<String, String> urlParams) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<String, String> entry : urlParams.entrySet()) {
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            result.append("&");
        }

        String resultString = result.toString();
        return resultString.length() > 0 ? resultString.substring(0, resultString.length() - 1) : resultString;
    }

    private void setRequestHeaders(HttpURLConnection con) {
        con.setRequestProperty("X-EBAY-SOA-GLOBAL-ID", globalID);
        con.setRequestProperty("X-EBAY-SOA-SERVICE-VERSION", "1.13.0");
        con.setRequestProperty("X-EBAY-SOA-SECURITY-APPNAME", appID);
        con.setRequestProperty("X-EBAY-SOA-OPERATION-NAME", "findCompletedItems");
    }

    private void test(BufferedReader in) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
    }
}
