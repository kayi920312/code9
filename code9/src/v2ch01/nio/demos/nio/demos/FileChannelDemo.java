package v2ch01.nio.demos.nio.demos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class FileChannelDemo {

	public static void main(String[] args) {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		FileChannel fcin = null;
		FileChannel fcout = null;
		ByteBuffer buffer = null;
		File inFile = new File("D:\\nio\\dabiao.rar");
		File outFile = new File("D:\\nio\\dabiao2.rar");
		
		/* io */
		/*try{
			
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);
			byte[] io_buffer = new byte[1024];
			int io_read = 0;
			long start = new Date().getTime();
			while((io_read = fis.read(io_buffer))!=-1){
				fos.write(io_buffer, 0, io_read);
			}
			fos.flush();
			System.out.println("time: "+(new Date().getTime()-start));
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			outFile.delete();
		}*/
		
		
		/* nio */
		buffer = ByteBuffer.allocate(1024);
		try{
			
			fis = new FileInputStream(inFile);
			fos = new FileOutputStream(outFile);
			fcin = fis.getChannel();
			fcout = fos.getChannel();
			long start = new Date().getTime();
			
			while(true){
				buffer.clear();
				if((fcin.read(buffer))==-1){
					break;
				}
				buffer.flip();
				fcout.write(buffer);
			}
			System.out.println("time: "+(new Date().getTime()-start));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(fis!=null){
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fcin!=null){
				try {
					fcin.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fcout!=null){
				try {
					fcout.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			outFile.delete();
		}
		
	}

}
