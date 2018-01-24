package v2ch01;

import java.nio.file.Path;
import java.nio.file.Paths;

public class test {

	public static void main(String[] args) {
		Path path = Paths.get("ky").resolve("XX");
		System.out.println(path.toString());
	}

}
