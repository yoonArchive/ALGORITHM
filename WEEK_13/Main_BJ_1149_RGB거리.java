import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_BJ_1149_RGB거리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int cost[][] = new int[N][3];
		StringTokenizer st = null;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < 3; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
				if (i != 0)
					cost[i][j] += Math.min(cost[i - 1][j == 0 ? 2 : j - 1], cost[i - 1][(j + 1) % 3]);
			}
		}
		int minSum = Integer.MAX_VALUE;
		for (int j = 0; j < 3; j++)
			minSum = Math.min(minSum, cost[N - 1][j]);
		bw.write(Integer.toString(minSum));
		br.close();
		bw.flush();
		bw.close();

	}

}
