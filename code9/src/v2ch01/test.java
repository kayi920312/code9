package v2ch01;

import java.util.concurrent.TimeUnit;

public class test {

	public static void main(String[] args) {
		while(true){
			try {
				TimeUnit.SECONDS.sleep(2);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("打印");
		}
	}

}
