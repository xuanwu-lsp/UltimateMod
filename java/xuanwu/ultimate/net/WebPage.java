package xuanwu.ultimate.net;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebPage
{
  private String pageUrl;
  private String pageEncode = "UTF8";
  
  public String getPageUrl()
  {
    return this.pageUrl;
  }
  
  public void setPageUrl(String pageUrl)
  {
    this.pageUrl = pageUrl;
  }
  
  public String getPageEncode()
  {
    return this.pageEncode;
  }
  
  public void setPageEncode(String pageEncode)
  {
    this.pageEncode = pageEncode;
  }
  
  public String getPageSource()
  {
    StringBuffer sb = new StringBuffer();
    try
    {
      URL url = new URL(this.pageUrl);
      
      BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), this.pageEncode));
      String line;
      while ((line = in.readLine()) != null) {
        sb.append(line);
      }
      in.close();
    }
    catch (Exception ex)
    {
      System.err.println(ex);
    }
    return sb.toString();
  }
  
  public String getPageSourceWithoutHtml()
  {
    String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
    String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
    String regEx_html = "<[^>]+>";
    String regEx_space = "\\s*|\t|\r|\n";
    String htmlStr = getPageSource();
    Pattern p_script = Pattern.compile("<script[^>]*?>[\\s\\S]*?<\\/script>", 2);
    Matcher m_script = p_script.matcher(htmlStr);
    htmlStr = m_script.replaceAll("");
    Pattern p_style = Pattern.compile("<style[^>]*?>[\\s\\S]*?<\\/style>", 2);
    Matcher m_style = p_style.matcher(htmlStr);
    htmlStr = m_style.replaceAll("");
    Pattern p_html = Pattern.compile("<[^>]+>", 2);
    Matcher m_html = p_html.matcher(htmlStr);
    htmlStr = m_html.replaceAll("");
    Pattern p_space = Pattern.compile("\\s*|\t|\r|\n", 2);
    Matcher m_space = p_space.matcher(htmlStr);
    htmlStr = m_space.replaceAll("");
    htmlStr = htmlStr.trim();
    htmlStr = htmlStr.replaceAll(" ", "");
    htmlStr = htmlStr.substring(0, htmlStr.indexOf("��") + 1);
    return htmlStr;
  }
}
