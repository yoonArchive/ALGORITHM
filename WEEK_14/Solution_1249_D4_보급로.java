import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_1249_D4_보급로 {
	static int N;
	static int map[][];
	static int dr[] = { 1, 0, -1, 0 };
	static int dc[] = { 0, 1, 0, -1 };

	public static class Point implements Comparable<Point> {
		int r, c, time;

		public Point(int r, int c, int time) {
			super();
			this.r = r;
			this.c = c;
			this.time = time;
		}

		@Override
		public int compareTo(Point o) {
			return this.time - o.time;
		}

	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			sb.append("#" + tc + " " + getShortestPath()).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static int getShortestPath() {
		int minTime[][] = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(minTime[i], -1);

		PriorityQueue<Point> pq = new PriorityQueue<>();
		pq.offer(new Point(0, 0, 0));
		while (!pq.isEmpty()) {
			Point p = pq.poll();
			int r = p.r;
			int c = p.c;
			int time = p.time;
			if (r == N - 1 && c == N - 1)
				break;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) || minTime[nr][nc] != -1)
					continue;
				minTime[nr][nc] = time + map[nr][nc];
				pq.offer(new Point(nr, nc, minTime[nr][nc]));
			}
		}
		return minTime[N - 1][N - 1];
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < N;
	}

}
