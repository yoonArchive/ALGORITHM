import java.util.Scanner;

public class Solution_SW_1954_D2_달팽이_숫자2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int arr[][] = new int[N][N];

			int dr[] = { 0, 1, 0, -1 };
			int dc[] = { 1, 0, -1, 0 }; // 우 하 좌 상
			int dir = 0;

			int r = 0;
			int c = 0;

			int nr = r;
			int nc = c;

			arr[0][0] = 1;

			for (int i = 2; i <= N * N; i++) { // i를 배열 원소로 줄 것임

				nr = nr + dr[dir];
				nc = nc + dc[dir];

				if (nr >= 0 && nr < N && nc >= 0 && nc < N && arr[nr][nc] == 0) {
					arr[nr][nc] = i;

				}

				else {
					nr = r;
					nc = c;
					dir += 1;
					if (dir > 3)
						dir = 0;
					nr = nr + dr[dir];
					nc = nc + dc[dir];

					arr[nr][nc] = i;
				}

				r = nr;
				c = nc;

			}

			sb.append("#" + tc + "\n");
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sb.append(arr[i][j] + " ");
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		sc.close();
	}

}
