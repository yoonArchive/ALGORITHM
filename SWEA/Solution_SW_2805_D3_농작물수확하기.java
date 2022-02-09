import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_SW_2805_D3_농작물수확하기 {

	static int[][] map = new int[49][49];

	public static void main(String[] args) throws IOException {
		//System.setIn(new FileInputStream("input (5).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			int sum = 0;
			int start = N / 2; // 수확 시작 열
			int cnt = 1; // 해당 행에서 수확할 수 있는 농작물 수

			for (int i = 0; i < N; i++) {
				String str = br.readLine();

				for (int j = 0; j < N; j++) {
					map[i][j] = str.charAt(j) - '0';
					if (i != N / 2) {
						if (j >= start + cnt) 
							break;
						if (j < start) //j증가
							continue;

					}
					sum += map[i][j]; 
				}

				// 윗부분~중간-1
				if (i < N / 2) {
					start--; 
					cnt = cnt + 2;
					
				}
				// 중간~아랫부분
				else if (i >= N / 2) {
					start++;
					cnt = cnt - 2;
				}
			}
			sb.append("#" + tc + " " + sum + "\n");
		}
		System.out.println(sb.toString());

	}

}