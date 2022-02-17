import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_2563_색종이 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int paper = Integer.parseInt(br.readLine());
		boolean[][] isCovered = new boolean[100][100];
		StringTokenizer st;
		int count=0;
		for (int i = 0; i < paper; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());	
			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					if(isCovered[j][k]) continue;
					else {
						isCovered[j][k] = true;
						count++;
					}
				}
			}
		}
		bw.write(Integer.toString(count));
		br.close();
		bw.flush();
		bw.close();

	}

}
