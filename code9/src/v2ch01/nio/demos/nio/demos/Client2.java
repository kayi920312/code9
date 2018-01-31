package v2ch01.nio.demos.nio.demos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class Client2 {

	public static void main(String[] args) {
		client();
	}
	
	public static void client(){
		
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel socketChannel = null;
        
        try
        {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("10.4.91.99",8088));
 
            if(socketChannel.finishConnect())
            {
                int i=0;
                while(true)
                {
                    TimeUnit.SECONDS.sleep(3);
                    String info = "I'm "+i+++"-th information from client";
                    buffer.clear();
                    buffer.put(info.getBytes());
                    buffer.flip();
                    while(buffer.hasRemaining()){
                        System.out.println(buffer);
                        socketChannel.write(buffer);
                    }
                    
                    socketChannel.read(buffer);
                    buffer.flip();
                    while(buffer.hasRemaining()){
                    	System.out.print((char)buffer.get());
                    }
                }
            }
            
            /*if(socketChannel.finishConnect())
            {
                String info = "I'm information from client";
                buffer.clear();
                buffer.put(info.getBytes());
                buffer.flip();
                while(buffer.hasRemaining()){
                	socketChannel.write(buffer);
                }
                
                socketChannel.read(buffer);
                while(buffer.hasRemaining()){
                	System.out.print((char)buffer.get());
                }
            }*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
