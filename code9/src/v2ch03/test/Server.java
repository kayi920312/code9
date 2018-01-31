package v2ch03.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		
		Socket socket = null;
		
		try(ServerSocket serverSocket = new ServerSocket(8899)){
			
			while(true){
				socket = serverSocket.accept();
				System.out.println("客户端接入=="+socket.toString());
				new Thread(new ClientSocket(socket)).start();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

class ClientSocket implements Runnable{

	private final InputStream is;
	private final OutputStream os;
	private Socket client = null;
	
	public ClientSocket(Socket client) throws IOException{
		this.client = client;
		is = client.getInputStream();
		os = client.getOutputStream();
	}
	
	@Override
	public void run() {
		
		Scanner scanner = new Scanner(System.in);
		PrintWriter pw = new PrintWriter(os, true);
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String readContent = null;
		
		while(true){
			//read
			System.out.println("开始读入");
			try {
				while((readContent = br.readLine())!=null){
					if("WR".equals(readContent)) break;
					System.out.println(readContent);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			//write
			System.out.print("请输入: ");
			String writeContent = null;
			while(scanner.hasNextLine()){
				writeContent = scanner.nextLine();
				pw.println(writeContent);
				if("RW".equals(writeContent)) break;
			}
		}
		
	}	
	
}
