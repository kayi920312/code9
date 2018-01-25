// $Id$
package v2ch01.nio.demos.nio;

import java.nio.*;

public class UseFloatBuffer
{
	static public void main(String args[]) throws Exception {
		FloatBuffer buffer = FloatBuffer.allocate(10);

		System.out.println("postition: "+buffer.position()+", limit: "+buffer.limit());
		for (int i = 0; i < buffer.capacity(); ++i) {
			float f = (float) Math.sin((((float) i) / 10) * (2 * Math.PI));
			buffer.put(f);
			System.out.println("postition: "+buffer.position()+", limit: "+buffer.limit());
		}
		buffer.flip();
		System.out.println("postition: "+buffer.position()+", limit: "+buffer.limit());
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		while (buffer.hasRemaining()) {
			System.out.print(buffer.position()+" ");
			float f = buffer.get();
			System.out.println(f);
		}
	}
}
