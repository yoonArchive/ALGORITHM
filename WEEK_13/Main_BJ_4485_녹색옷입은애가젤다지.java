import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_4485_녹색옷입은애가젤다지 {
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, -1, 0, 1 };
	static int N;

	public static class Rupee implements Comparable<Rupee> {
		int r, c, kRupee;

		public Rupee(int r, int c, int kRupee) {
			super();
			this.r = r;
			this.c = c;
			this.kRupee = kRupee;
		}

		@Override
		public int compareTo(Rupee o) {
			return this.kRupee - o.kRupee;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = 0;
		while (true) {
			++tc;
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;
			int map[][] = new int[N][N];
			StringTokenizer st = null;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem " + tc + ": " + move(map)).append("\n");
		}
		System.out.println(sb);

	}

	private static int move(int[][] map) {
		PriorityQueue<Rupee> pq = new PriorityQueue<>();
		pq.add(new Rupee(0, 0, map[0][0]));
		int res = 0;

		while (!pq.isEmpty()) {
			Rupee rupee = pq.poll();
			int r = rupee.r;
			int c = rupee.c;
			int kRupee = rupee.kRupee;
			if (r == N - 1 && c == N - 1) {
				res = kRupee;
				break;
			}
			for (int d = 0; d < dr.length; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) || map[nr][nc] == -1)
					continue;
				pq.add(new Rupee(nr, nc, kRupee + map[nr][nc]));
				map[nr][nc] = -1;
			}
		}
		return res;
	}

	private static boolean isIn(int nr, int nc) {
		if (nr >= 0 && nr < N && nc >= 0 && nc < N)
			return true;
		return false;
	}

}
