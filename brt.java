import java.io.*;

public class brt{
    public static void main(String[] args) throws IOException{
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    	PrintWriter writer = new PrintWriter(System.out);
        int n = Integer.parseInt(reader.readLine());
        String[] name = new String[n];
        double[] time1 = new double[n];
        double[] time2 = new double[n];
        for (int i = 0; i < n; i++) {
        	String[] values = reader.readLine().split(" ");
            name[i] = values[0];
            time1[i] = Double.parseDouble(values[1]);
            time2[i] = Double.parseDouble(values[2]);
        }
        double best = 1e9;
        String[] result = new String[4];
        for (int i = 0; i < n; i++) {
            String[] currentNames = new String[3];
            double[] currentTimes = {1e9,1e9,1e9};
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (time2[j] < currentTimes[0]) {
                    currentTimes[2] = currentTimes[1];
                    currentTimes[1] = currentTimes[0];
                    currentTimes[0] = time2[j];
                    currentNames[2] = currentNames[1];
                    currentNames[1] = currentNames[0];
                    currentNames[0] = name[j];
                } else if (time2[j] < currentTimes[1]) {
                    currentTimes[2] = currentTimes[1];
                    currentTimes[1] = time2[j];
                    currentNames[2] = currentNames[1];
                    currentNames[1] = name[j];
                } else if (time2[j] < currentTimes[2]) {
                    currentTimes[2] = time2[j];
                    currentNames[2] = name[j];
                }
            }
            double total = time1[i]+currentTimes[0]+currentTimes[1]+currentTimes[2];
            if (total < best) {
                best = total;
                result[0] = name[i];
                result[1] = currentNames[0];
                result[2] = currentNames[1];
                result[3] = currentNames[2];
            }
        }
        writer.println(best);
        writer.println(result[0]);
        writer.println(result[1]);
        writer.println(result[2]);
        writer.println(result[3]);
        writer.flush();
    }
}
