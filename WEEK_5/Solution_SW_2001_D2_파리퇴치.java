import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_2001_D2_파리퇴치 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int arr[][] = new int[n][n]; // 파리 개수 배열
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				st = new StringTokenizer(str, " ");
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int max = 0;
			int sum = 0;
			for (int r = 0; r <= n - m; r++) {
				for (int c = 0; c <= n - m; c++) {
					sum = 0;
					int nr = r;
					int nc = c;

					while (true) {
						if (nc >= c + m) {
							if (nr >= r + m - 1)
								break;
							nc = c;
							nr++;
						}
						sum += arr[nr][nc];
						nc++;
					}
					max = Math.max(max, sum);
				}
			}
			sb.append("#" + tc + " " + max+"\n");
		}
		System.out.println(sb.toString());
	}
}
