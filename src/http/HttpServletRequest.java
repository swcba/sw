package http;

import java.util.HashMap;

public class HttpServletRequest {

	private String method;
	private String requestURL;
	private String protocol;
	private HashMap<String,String> headerMap;


	

	public HttpServletRequest(String content) {
		//解析请求报文
		String[] lines = content.split("\r\n");
		for (int j = 0; j < lines.length; j++) {
			if(j==0) {
			String[] topLines = lines[j].split("\\s");
			 method = topLines [0];
			 requestURL = topLines [1];		
			 protocol = topLines[2];
		}else {
			String[] topLines = lines[j].split("\\s");
			
			
		}
			
		}
		
	}
	public String getMethod() {
		return method;
	}


	public void setMethod(String method) {
		this.method = method;
	}


	public String getRequestURL() {
		return requestURL;
	}


	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}


	public String getProtocol() {
		return protocol;
	}


	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getHeader(String header) {
		return headerMap.get(header);
	}
	public void setHeaderMap(HashMap<String,String> headerMap) {
		this.headerMap = headerMap;
	}
//设置URL
	public void setRequest(String requestURL) {
		this.requestURL=requestURL;
	}
	
	
}
