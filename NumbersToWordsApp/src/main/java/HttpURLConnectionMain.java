 
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
 
public class HttpURLConnectionMain {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
	    HttpURLConnectionMain http = new HttpURLConnectionMain();
 
		System.out.println("Testing 1 - Send Http GET request "+args[0]);
		
		
		System.setProperty("java.net.useSystemProxies", "true");
		System.out.println("detecting proxies");
		List l = null;
		try {
		    l = ProxySelector.getDefault().select(new URI("http://google.com.au"));
		} 
		catch (URISyntaxException e) {
		    e.printStackTrace();
		}
		if (l != null) {
		    String proxyHost = null;
		    String proxyPort = null;
		    for (Iterator iter = l.iterator(); iter.hasNext();) {
		        java.net.Proxy proxy = (java.net.Proxy) iter.next();
		        System.out.println("proxy type: " + proxy.type());

		        InetSocketAddress addr = (InetSocketAddress) proxy.address();

		        if (addr == null) {
		            System.out.println("No Proxy");
		        } else {
		            System.getProperties().put("proxySet", "true");
		            proxyHost = addr.getHostName();
		            System.out.println("proxy hostname: " + addr.getHostName());
		            System.setProperty("http.proxyHost", addr.getHostName());
		            proxyPort = addr.getPort()+"";
		            System.out.println("proxy port: " + addr.getPort());
		            System.setProperty("http.proxyPort", Integer.toString(addr.getPort()));
		        }
		    }
		    
		    
		    http.sendGet(args, proxyHost, proxyPort);
		    
		    
		}
 
	}
 
	// HTTP GET request
	private void sendGet(String[] args, String proxyHost, String proxyPort) throws Exception {
 
 
		URL obj = new URL(args[0]);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
		
		if(proxyHost != null) {
		    System.setProperty("http.proxyHost", args[1]);
		}
		if(proxyHost != null) {
		    System.setProperty("http.proxyPort", args[2]);
		}
		
		
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + args[0]);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
	// HTTP POST request
 
}