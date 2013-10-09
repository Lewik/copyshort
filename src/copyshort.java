/**
 * Created with IntelliJ IDEA.
 * User: Lev
 * Date: 08.10.13
 * Time: 15:30
 * To change this template use File | Settings | File Templates.
 */


import com.google.gson.JsonParser;

import java.net.MalformedURLException;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class copyshort {
    public static void main(String[] args) {
        String clip = SystemClipboardDataManipulate.getClipboardData();

        try {
            new URL(clip);
        } catch (MalformedURLException malformedURLException) {
            // malformedURLException.printStackTrace();
        }

        try {
            String shorten = copyshort.sendPost(clip);
            System.out.println(shorten);
            SystemClipboardDataManipulate.setClipboardData(shorten);
        } catch (Exception e) {
            System.out.println("copyshort error");
            System.out.println(e.toString());
        }


    }


    private static String sendPost(String clip) throws Exception {

        String url = "https://www.googleapis.com/urlshortener/v1/url";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");

        String urlParameters = "{\"longUrl\": \"" + clip + "\"}";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        //int responseCode = con.getResponseCode();
        //System.out.println("\nSending 'POST' request to URL : " + url);
        //System.out.println("Post parameters : " + urlParameters);
        //System.out.println("Response Code : " + responseCode);


        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream())
        );

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        String answer = new JsonParser()
                .parse(response.toString())
                .getAsJsonObject()
                .get("id")
                .toString();
        System.out.println(answer);
        answer = answer.substring(1, answer.length() - 1);
        return answer;
    }

}
