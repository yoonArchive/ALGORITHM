import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D3_9229_한빈이와SpotMart {
	static int N, M;
	static int snack[];
	static int result;

	public static void choose(int count, int start, int weight) {
		if (weight > M)
			return;
		if (count == 2) {
			result = Math.max(result, weight);
			return;
		}
		for (int i = start; i < N; i++) {
			choose(count + 1, i + 1, weight + snack[i]);
		}
	}

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("sample_input (1).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken()); // 과자 개수
			M = Integer.parseInt(st.nextToken()); // 무게 합 제한
			snack = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				snack[i] = Integer.parseInt(st.nextToken());
			}
			result = -1;
			choose(0, 0, 0);
			sb.append("#" + t + " " + result + "\n");
		}
		System.out.println(sb.toString());

	}

}
