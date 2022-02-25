import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_7576_토마토 {

	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, 1, 0, -1 };
	static int M, N;

	public static class Pair {
		int r, c, breadth;

		public Pair(int r, int c, int breadth) {
			super();
			this.r = r;
			this.c = c;
			this.breadth = breadth;
		}
	}

	public static void spread(int[][] map, Queue<Pair> queue, boolean isRiped[][]) {
		while (!queue.isEmpty()) {
			Pair p = queue.poll();
			for (int i = 0, length = dr.length; i < length; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if (nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == -1 || isRiped[nr][nc])
					continue;
				isRiped[nr][nc] = true;
				map[nr][nc] = p.breadth + 1;
				queue.offer(new Pair(nr, nc, p.breadth + 1));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int map[][] = new int[M][N];
		Queue<Pair> queue = new LinkedList<>();
		boolean isRiped[][] = new boolean[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int nowTomato = Integer.parseInt(st.nextToken());
				map[i][j] = nowTomato;
				if (nowTomato == 1) {
					queue.offer(new Pair(i, j, 1));
					isRiped[i][j] = true;
				}
			}
		}

		spread(map, queue, isRiped);

		boolean isAllRiped = true;
		int max = Integer.MIN_VALUE;
		outer: for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0) {
					isAllRiped = false;
					break outer;
				}
				max = Math.max(max, map[i][j]);
			}
		}

		int result = isAllRiped ? max - 1 : -1;
		bw.write(Integer.toString(result));
		br.close();
		bw.flush();
		bw.close();
	}

}
