/* Nuke2.java */
import java.io.*;

class Nuke2{

	public static void main(String[] args) throws Exception {
		BufferedReader keyboard;
		String inputLine;
		String result;

		keyboard = new BufferedReader(new InputStreamReader(System.in));
		inputLine = keyboard.readLine();
		result = inputLine.substring(0,1)+inputLine.substring(2);
		System.out.println(result);
	}
}