import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1263_D6_사람네트워크2 {
	static final int INF = 9999999;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("1263.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int network[][] = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int tmp = Integer.parseInt(st.nextToken());
					if (i != j && tmp == 0)
						network[i][j] = INF;
					else
						network[i][j] = tmp;
				}
			}
			for (int i = 0; i < N; i++) { // 경유지 : i
				for (int j = 0; j < N; j++) { // 출발지 : j
					if (i == j)
						continue;
					for (int k = 0; k < N; k++) { // 도착지 : k
						if (i == k || j == k)
							continue;
						if (network[j][k] > network[i][j] + network[i][k]) {
							network[j][k] = network[i][j] + network[i][k];
						}
					}
				}
			}
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < N; j++) {
					sum += network[i][j];
				}
				min = Math.min(min, sum);
			}
			sb.append("#" + tc + " " + min).append("\n");
		}
		System.out.println(sb.toString());

	}

}
