package v2ch01.nio.demos.nio.demos;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Client {

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
            	Selector selector = Selector.open();
            	SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, buffer);
            	while(true){
            		
            		if(selector.select(3000) == 0){
                        System.out.println("服务端未有消息===");
                        continue;
                    }
            		
            		Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
            		SelectionKey selectionKeyInner = null;
					while (iter.hasNext()) {
						selectionKeyInner = iter.next();
						if (selectionKeyInner.isReadable()) {
							readHandel(selectionKey);
						}
						if (selectionKeyInner.isWritable() && selectionKeyInner.isValid()) {
							writeHandel(selectionKey);
						}
						iter.remove();
					}
            	}
            }
            
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
	
	/* read */
	private static void readHandel(SelectionKey selectionKey){
		
	}
	
	/* write */
	private static void writeHandel(SelectionKey selectionKey){
		
	}
}
