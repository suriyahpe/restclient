package restclient;
import java.io.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * This class is the same as the ApacheHttpRestClient2 class, but with
 * fewer try/catch clauses, and fewer comments.
*/
public class Rest_client {

  public void rest(){
    
    HttpClient httpClient = new DefaultHttpClient();
    try {
      HttpGet httpGetRequest = new HttpGet("http://api.railwayapi.com/pnr_status/pnr/4543113368/apikey/AIzaSyAeGdSw8xayLV8PhYo68rbK6Tbj7RPvnfE/");
      HttpResponse httpResponse = httpClient.execute(httpGetRequest);

      System.out.println("----------------------------------------");
      System.out.println(httpResponse.getStatusLine());
      System.out.println("----------------------------------------");

      HttpEntity entity = httpResponse.getEntity();

      byte[] buffer = new byte[1024];
      if (entity != null) {
        InputStream inputStream = entity.getContent();        
        try {
          int bytesRead = 0;
          BufferedInputStream bis = new BufferedInputStream(inputStream);
          while ((bytesRead = bis.read(buffer)) != -1) {
            String chunk = new String(buffer, 0, bytesRead);
            System.out.println(chunk);
          }
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          try { inputStream.close(); } catch (Exception ignore) {}
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      httpClient.getConnectionManager().shutdown();
    }
  }
}

