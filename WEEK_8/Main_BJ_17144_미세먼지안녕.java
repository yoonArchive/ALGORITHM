import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17144_미세먼지안녕 {
	static int R, C;
	static Pair[] airCleaner;
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, 1, 0, -1 };

	public static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}

	public static void dustSpread(int[][] room, Queue<Pair> dustLoc) { // 미세먼지 확산
		int[][] tmpRoom = new int[R][C];

		while (!dustLoc.isEmpty()) {
			Pair p = dustLoc.poll();
			int r = p.r;
			int c = p.c;
			int cnt = 0;
			int cur = room[r][c];

			for (int i = 0, length = dr.length; i < length; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr < 0 || nc < 0 || nr >= R || nc >= C || room[nr][nc] == -1)
					continue;
				tmpRoom[nr][nc] += cur / 5;
				cnt++;
			}
			tmpRoom[r][c] += cur - (cur / 5 * cnt);
		}
		copy(tmpRoom, room);
	}

	public static void cleanerWorking(int[][] room) {
		// 위쪽 - 반시계방향 순환
		int cleanerR = airCleaner[0].r, cleanerC = airCleaner[0].c;
		int tmp = room[0][C - 1];
		for (int i = 0; i < cleanerR; i++) room[i][C - 1] = room[i + 1][C - 1];
		for (int i = C - 1; i > 0; i--) room[cleanerR][i] = room[cleanerR][i - 1];
		room[cleanerR][1] = 0; // 공기청정기는 회전 x
		for (int i = cleanerR; i > 0; i--) room[i][cleanerC] = room[i - 1][cleanerC];
		for (int i = 0; i < C - 1; i++) room[0][i] = room[0][i + 1];
		room[0][C - 2] = tmp;

		// 아래쪽- 시계방향 순환
		cleanerR = airCleaner[1].r;
		cleanerC = airCleaner[1].c;
		tmp = room[cleanerR][C - 1];
		for (int i = C - 1; i > 0; i--) room[cleanerR][i] = room[cleanerR][i - 1];
		room[cleanerR][1] = 0; // 공기청정기는 회전 x
		for (int i = cleanerR; i < R - 1; i++) room[i][0] = room[i + 1][0];
		for (int i = 0; i < C - 1; i++) room[R - 1][i] = room[R - 1][i + 1];
		for (int i = R - 1; i > cleanerR; i--) room[i][C - 1] = room[i - 1][C - 1];
		room[cleanerR + 1][C - 1] = tmp;

		room[airCleaner[0].r][airCleaner[0].c] = -1; // 공기청정기 원래대로
		room[airCleaner[1].r][airCleaner[1].c] = -1;
	}

	public static void copy(int[][] tmp, int[][] room) { // room에 tmp를 복사
		for (int r = 0; r < R; r++)
			for (int c = 0; c < C; c++)
				room[r][c] = tmp[r][c];
		room[airCleaner[0].r][airCleaner[0].c] = -1;
		room[airCleaner[1].r][airCleaner[1].c] = -1;
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("input_17144.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken()); // 격자판 행 크기
		C = Integer.parseInt(st.nextToken()); // 격자판 열 크기
		int T = Integer.parseInt(st.nextToken()); // T초
		int room[][] = new int[R][C];
		
		airCleaner = new Pair[2];
		int acCnt = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < C; j++) {
				int val = Integer.parseInt(st.nextToken());
				room[i][j] = val;
				if (val == -1) {
					airCleaner[acCnt] = new Pair(i, j); // 공기청정기 좌표 저장
					acCnt++;
				}
			}
		}

		for (int time = 0; time < T; time++) {
			Queue<Pair> dustLoc = new LinkedList<>();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (room[i][j] > 0)
						dustLoc.add(new Pair(i, j)); // 미세먼지의 좌표를 큐에 담는다.
				}
			}
			dustSpread(room, dustLoc);
			cleanerWorking(room);
		}

		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] > 0)
					sum += room[i][j];
			}
		}

		bw.write(Integer.toString(sum));
		br.close();
		bw.flush();
		bw.close();

	}

}
