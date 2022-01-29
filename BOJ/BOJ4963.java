package BOJ;

import java.io.*;
import java.util.*;

public class BOJ4963 {
	static int map[][];
	static boolean visit[][];
	static Queue<Pair> q;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static class Pair {
		int x, y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void bfs(int i, int j) throws IOException {
		q = new LinkedList<>();
		q.offer(new Pair(i, j));
		visit[i][j] = true;
		int dx[] = { -1, 0, 1, -1, 1, -1, 0, 1 };
		int dy[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
		while (!q.isEmpty()) {
			Pair p = q.poll();
			for (int t = 0; t < dx.length; t++) {
				int nx = p.x + dx[t];
				int ny = p.y + dy[t];
				if (nx >= 0 && ny >= 0 && nx < map.length && ny < map[0].length) {
					if (map[nx][ny] == 1 && visit[nx][ny] == false) {
						q.offer(new Pair(nx, ny));
						visit[nx][ny] = true;
					}
				}
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			String str = br.readLine();
			if (str.equals("0 0"))
				break;
			StringTokenizer st = new StringTokenizer(str, " ");
			int w = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());

			map = new int[h][w];
			visit = new boolean[h][w];
			for (int i = 0; i < h; i++) {
				str = br.readLine();
				st = new StringTokenizer(str, " ");
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int count = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j] == 1 && visit[i][j] == false) {
						bfs(i, j);
						count += 1;
					}
				}
			}
			bw.write(Integer.toString(count));
			bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
