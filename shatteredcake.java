import java.io.*;

public class shatteredcake{
	public static void main(String[] args) throws IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter writer = new PrintWriter(System.out);
		int fullwidth = Integer.parseInt(reader.readLine());
		int num = Integer.parseInt(reader.readLine());
		int total_area = 0;
		
		for (int i = 0; i < num; i++) {
			String[] values = reader.readLine().split(" ");
			total_area += Integer.parseInt(values[0]) * Integer.parseInt(values[1]);
		}
		
		int length = total_area / fullwidth;
		writer.println(length);
		writer.flush();
	}
}