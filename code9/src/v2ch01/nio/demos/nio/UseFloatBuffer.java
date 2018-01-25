// $Id$
package v2ch01.nio.demos.nio;

import java.nio.*;

public class UseFloatBuffer
{
	static public void main(String args[]) throws Exception {
		FloatBuffer buffer = FloatBuffer.allocate(10);

		for (int i = 0; i < buffer.capacity(); ++i) {
			float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
			buffer.put(f);
		}
		
		buffer.flip();
		
		for (int i = 0; i < 5; ++i) {
			buffer.put(11111);
		}

		
		while (buffer.hasRemaining()) {
			System.out.print(buffer.position()+" ");
			float f = buffer.get();
			System.out.println(f);
		}
	}
}
