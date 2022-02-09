import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_D4_1210_Ladder1_박기윤 {
	static int map[][] = new int[100][100];
	static int dr[] = { 0, 0, -1 }; // 좌우를 위보다 먼저 탐색해야함
	static int dc[] = { 1, -1, 0 };
	static int result;

	public static void search(int r, int c) {
		if (r == 99)
			r = r - 1;

		while (true) {
			if (r == 0) {
				result = c;
				break;
			}
			for (int i = 0; i < dr.length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];

				if (nc >= 0 && nc < 100 && map[nr][nc] == 1) {
					map[nr][nc] = 0;
					r = nr;
					c = nc;
					break;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_1210.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			int goal_idx = 0; // 도착 지점의 열 번호 (99, goal_idx) ->얘를 시작으로 백트래킹
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (i == 99 && map[i][j] == 2)
						goal_idx = j;
				}
			}
			search(99, goal_idx);
			sb.append("#" + tc + " " + result + "\n");
		}
		System.out.println(sb.toString());
	}
}
