import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_3307_D3_최장증가부분수열 {
	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("3307.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int numbers[] = new int[N];
			int lcs[] = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				numbers[i] = Integer.parseInt(st.nextToken());

			int max = 0;
			for (int i = 0; i < N; i++) {
				lcs[i] = 1;
				for (int j = 0; j < i; j++) {
					if (numbers[i] > numbers[j] && lcs[i] < lcs[j] + 1)
						lcs[i] = lcs[j] + 1;
				}
				max = Math.max(max, lcs[i]);
			}
			sb.append("#" + tc + " " + max).append("\n");
		}
		System.out.println(sb.toString());
	}
}
