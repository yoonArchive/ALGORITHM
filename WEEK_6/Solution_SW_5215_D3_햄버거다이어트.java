import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_SW_5215_D3_햄버거다이어트 {

	public static int[][] ingredient;
	public static int result;
	public static int N, L;

	public static void choose(int start, int score, int cal) {

		if (cal > L) // 제한을 넘어가면 return
			return;

		if (start == N) { // 현재 재료가 마지막 요소일 때 최대값으로 갱신
			result = Math.max(result, score);
			return;
		}

		// 현재 재료를 선택 안함
		choose(start + 1, score, cal);

		// 현재 재료를 선택
		choose(start + 1, score + ingredient[start][0], cal + ingredient[start][1]);
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken()); // 재료의 수
			L = Integer.parseInt(st.nextToken()); // 제한 칼로리

			ingredient = new int[N][2];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredient[i][0] = Integer.parseInt(st.nextToken());
				ingredient[i][1] = Integer.parseInt(st.nextToken());
			}

			result = 0;

			choose(0, 0, 0);

			bw.write("#" + tc + " " + result);
			bw.newLine();

		}
		br.close();
		bw.flush();
		bw.close();

	}
}
