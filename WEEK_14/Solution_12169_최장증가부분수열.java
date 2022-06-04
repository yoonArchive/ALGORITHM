import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_12169_최장증가부분수열 {

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("12169.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int n = Integer.parseInt(br.readLine());
			int dp[][] = new int[2][n];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < n; i++)
				dp[0][i] = Integer.parseInt(st.nextToken());

			dp[1][0] = 1;
			int pre = dp[0][0];
			for (int i = 1; i < n; i++) {
				dp[1][i] = dp[0][i] > pre ? dp[1][i - 1] + 1 : dp[1][i - 1];
				pre = dp[0][i];
			}
			
			sb.append("#" + tc + " " + dp[1][n - 1]).append("\n");
		}
		System.out.println(sb.toString());

	}

}
