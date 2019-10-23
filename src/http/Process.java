package http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.jspsmart.upload.Request;

import http.servlet.TestServlet;

public class Process {
	//定义servlet容器
	private HashMap<String , HttpServlet> servletContainer = new HashMap<>();
	{
		//添加一个servlet
		servletContainer.put("hello.s", new TestServlet());
		
		
	}
	
	public void process(Socket socket) {

					InputStream in;
					OutputStream out;
					try {
					in = socket.getInputStream();
					out = socket.getOutputStream();
					//读取报文内容
					
					byte[] buffer = new byte[1024];
					int count = in.read(buffer);
					String content = new String(buffer,0,count);
					parseRequest(content);
					
					HttpServletRequest request = parseRequest(content);
					HttpServletResponse response =new HttpServletResponse(request,out);
					
					String rootPath= "D:/tomcat/webapps/photo";
					String filePath = request.getRequestURL();
					String diskPath= rootPath+filePath;
					//判断访问的文件是否存在
					/**
					 * 静态请求：对应这一个html,js,css；
					 * 动态请求：hello.s
					 * 非法404请求 即没有物理文件和虚拟文件
					 * 
					 */
					//System.out.println("客户端说："+new String(buffer,0,count));
					/*
					 * 页面不存在返回404
					 * 1，判断物理文件是否存在
					 * 2，判断虚拟路径中有没有该地址
					 */
					
					if(new File(diskPath).exists()==false) {
						//静态请求直接commit
						
					}else if(servletContainer.containsKey(filePath)) {
						//127.0.0.1:8080/hello.s
						
					//判断虚拟路径中有没有该地址（从容器中寻找）
						HttpServlet servlet = servletContainer.get(filePath);
						servlet.service(request, response);
						
					
					}else{
						//404
						response.setStatus(404, "Not Found");
						request.setRequestURL("/404.html");
					
					}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//解析请求报文
					
					//给予对应的响应
					
					
							
				}
			
	public HttpServletRequest parseRequest(String content) {
		
		return new HttpServletRequest(content);
		
	}

}
