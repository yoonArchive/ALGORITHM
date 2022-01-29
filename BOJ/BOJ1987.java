package BOJ;

import java.io.*;
import java.util.*;

public class BOJ1987 {
	static int r;
	static int c;
	static int ans = 0;
	static char map[][];
	static boolean visit[];
	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void dfs(int x, int y, int count) throws IOException {
		visit[(int) map[x][y] - 'A'] = true;
		// bw.write(map[x][y]);
		for (int i = 0; i < dx.length; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
				if (visit[(int) map[nx][ny] - 'A'] == false)
					dfs(nx, ny, count + 1);
			}
		}

		visit[(int) map[x][y] - 'A'] = false;
		ans = Math.max(ans, count);
		// bw.write(Integer.toString(ans));
		// bw.newLine();
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		map = new char[r][c];
		for (int i = 0; i < r; i++) {
			str = br.readLine();
			for (int j = 0; j < c; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		visit = new boolean[26];
		dfs(0, 0, 0);

		// bw.write(Integer.toString(ans+1));
		br.close();
		bw.flush();
		bw.close();
	}
}
