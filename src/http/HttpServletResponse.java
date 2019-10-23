package http;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class HttpServletResponse {
	
	private static WebXmlParser webXmlParse = new WebXmlParser("web.xml");
	
	private HttpServletRequest request;
	private OutputStream out;
	
	//状态码字段
	private int status=200;
	//状态码描述信息
	private String messge="ok";
	

	public HttpServletResponse(HttpServletRequest request, OutputStream out) {
		super();
		this.request = request;
		this.out = out;
	}


	public void commit() throws IOException {
		
		String suffix = request.getRequestURL().substring(request.getRequestURL().lastIndexOf(".")+1);
		String contentType = webXmlParse.getContentType(suffix) ;
		
		
		String responseStr = "HTTP/1.1 200 OK\r\n";
		
		responseStr +="Content-Type: "+contentType+"\r\n";
		
		responseStr +="\r\n";//空行
		
		//responseStr +="<h1> hello world</h1>";
		
		out.write(responseStr.getBytes());
		
		//根据请求的路径返回对应的文件 html
		
		String rootPath= "D:/tomcat/webapps/photo";
		String filePath = request.getRequestURL();
		String diskPath= rootPath+filePath;
		//判断访问的文件是否存在
		if(new File(diskPath).exists()==false) {
			diskPath=rootPath + "/404.html";
		}
		FileInputStream fis = new FileInputStream(diskPath);
		int count;
		byte[] buffer =new byte[1024];
		//向浏览器发送报文
		while((count = fis.read(buffer ))>0) {
			
			out.write(buffer,0,count);
		}
		fis.close();
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status,String messge) {
		this.status = status;
		this.messge=messge;
	}
	
}
