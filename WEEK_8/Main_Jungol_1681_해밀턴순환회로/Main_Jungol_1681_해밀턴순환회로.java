import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main_Jungol_1681_해밀턴순환회로 {

	static int min;

	public static void deliver(int[][] cost, boolean[] isVisited, int cnt, int idx, int sum, int N) {

		if (sum > min) return;

		if (cnt == N - 1) {
			if (cost[idx][0] != 0) {
				sum += cost[idx][0];
				if (min > sum) min = sum;
			}
			return;
		}

		for (int i = 1; i < N; i++) {
			if (i == idx || cost[idx][i] == 0 || isVisited[i])
				continue;

			isVisited[i] = true;
			deliver(cost, isVisited, cnt + 1, i, sum + cost[idx][i], N);
			isVisited[i] = false;

		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());
		int[][] cost = new int[N][N];
		boolean[] isVisited = new boolean[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				cost[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		deliver(cost, isVisited, 0, 0, 0, N);
		bw.write(Integer.toString(min));
		br.close();
		bw.flush();
		bw.close();
	}

}