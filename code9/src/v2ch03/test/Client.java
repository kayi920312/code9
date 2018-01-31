package v2ch03.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {
		
		InputStream is = null;
		OutputStream os = null;
		
		try(Socket client = new Socket()){
			client.connect(new InetSocketAddress("localhost", 8899), 5000);
			is = client.getInputStream();
			os = client.getOutputStream();
			
			Scanner scanner = new Scanner(System.in);
			PrintWriter pw = new PrintWriter(os, true);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String readContent = null;
			
			while(true){
				//write
				String writeContent = null;
				System.out.print("请输入: ");
				while(scanner.hasNextLine()){
					writeContent = scanner.nextLine();
					pw.println(writeContent);
					if("WR".equals(writeContent)) break;
				}
				
				//read
				System.out.println("开始读入");
				try {
					while((readContent = br.readLine())!=null){
						if("RW".equals(readContent)) break;
						System.out.println(readContent);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
