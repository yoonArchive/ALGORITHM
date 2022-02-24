import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_BJ_10026_적록색약 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1 };

	public static class colorInfo {
		int r, c;
		char color;

		public colorInfo(int r, int c, char color) {
			super();
			this.r = r;
			this.c = c;
			this.color = color;
		}
	}

	public static void read(colorInfo[][] image, boolean[][] isVisited, int N, int r, int c, boolean iscolorBlind) {
		Queue<colorInfo> q = new LinkedList<>();
		q.offer(image[r][c]);
		isVisited[r][c] = true;
		char color = image[r][c].color;

		while (!q.isEmpty()) {
			colorInfo cur = q.poll();
			for (int i = 0, length = dr.length; i < length; i++) {
				int nr = cur.r + dr[i];
				int nc = cur.c + dc[i];
				if (nr < 0 || nr >= N || nc < 0 || nc >= N || isVisited[nr][nc]) continue;
				char curColor = image[nr][nc].color;
				if (iscolorBlind) {
					if (color == curColor || (color == 'R' && curColor == 'G') || (color == 'G' && curColor == 'R')) {
						q.offer(image[nr][nc]);
						isVisited[nr][nc] = true;
					}
				} else {
					if (color == curColor) {
						q.offer(image[nr][nc]);
						isVisited[nr][nc] = true;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		colorInfo image[][] = new colorInfo[N][N];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++)
				image[i][j] = new colorInfo(i, j, line.charAt(j));
		}

		for (int iter = 0; iter < 2; iter++) {
			boolean iscolorBlind = iter == 0 ? false : true;
			int sectionR = 0;
			int sectionG = 0;
			int sectionRG = 0;
			int sectionB = 0;
			boolean isVisited[][] = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isVisited[i][j]) {
						read(image, isVisited, N, i, j, iscolorBlind);
						char nowColor = image[i][j].color;
						if (iter == 0) { // 적록색약 x
							if (nowColor == 'R') sectionR++;
							else if (nowColor == 'G') sectionG++;
							else sectionB++;
						} else { // 적록색약
							if (nowColor == 'R' || nowColor == 'G') sectionRG++;
							else sectionB++;
						}
					}
				}
			}
			int part = iter == 0 ? sectionR + sectionG + sectionB : sectionRG + sectionB;
			sb.append(part + " ");
		}
		System.out.println(sb.toString());
	}
}
