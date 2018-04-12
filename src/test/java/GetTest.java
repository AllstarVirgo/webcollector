import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetTest {
    public String requestByGetMethod(String url){
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet(url);
            System.out.println("执行get请求:...."+get.getURI());
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                    System.out.println("响应状态码:"+ httpResponse.getStatusLine());
                    System.out.println("-------------------------------------------------");
                    System.out.println("响应内容:" + EntityUtils.toString(entity));
                    System.out.println("-------------------------------------------------");
                    return EntityUtils.toString(entity);
                }
            }
            finally{
                httpResponse.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }

    private CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }

    private void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }

    public void analysis(){
        try {
            Document doc= Jsoup.connect("http://www.slwr.gov.cn/swjgzfw/jsfp.asp").get();
            Elements contents=doc.select("tr");
            int n=contents.size();
            for (int i = 11; i <n ; i++) {
                Element e=contents.get(i);
                String record1=e.text();
                String[] array=record1.split(" ");
                String record2=e.child(5).child(0).toString();
                Pattern p=Pattern.compile("\\d+\\.\\d+");
                Matcher m=p.matcher(record2);
                if(m.find()) System.out.println(m.group());
                System.out.println(record2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void jsoupPost(){
        try {
            Document doc= Jsoup.connect("http://www.bjwater.gov.cn/eportal/ui?pageId=349049&moduleId=c0c024e49b714288977cc55f88779e5c").
                    data("time","2017-07-21").post();
            Elements elements=doc.select("tr");
            int n=elements.size();
            for (int i = 2; i < n-5; i++) {
                String[] strEles=elements.get(i).text().split(" ");
                System.out.println(Arrays.toString(strEles));
                System.out.println("------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        GetTest getTest=new GetTest();
        getTest.jsoupPost();
    }
}
