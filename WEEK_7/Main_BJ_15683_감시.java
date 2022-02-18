import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_15683_감시 {
	static int dr[] = { -1, 0, 1, 0 }; // 상 우 하 좌
	static int dc[] = { 0, 1, 0, -1 };
	static char office[][];
	static ArrayList<CCTV> cctvList = new ArrayList<>();
	static int N, M, ans = Integer.MAX_VALUE;

	public static class CCTV {
		char id;
		int r;
		int c;

		public CCTV(char id, int r, int c) {
			super();
			this.id = id;
			this.r = r;
			this.c = c;
		}

	}

	public static void copy(char[][] office, char[][] copyMap) {
		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				copyMap[i][j] = office[i][j];
	}

	public static void watch(int count, int noVisible, char[][] map) {
		if (count == cctvList.size()) { // 기저
			ans = Math.min(ans, noVisible);
			return;
		}

		char[][] copyMap = new char[N][M];
		int watchable = 0;

		CCTV cur = cctvList.get(count);
		switch (cur.id) {
		case '1':
			for (int dir = 0; dir < 4; dir++) {
				copy(map, copyMap);
				watchable = getWatchableArea(cur.r, cur.c, dir, copyMap);
				watch(count + 1, noVisible - watchable, copyMap); // 다음 cctv로
			}
			break;
		case '2':
			for (int dir = 0; dir < 2; dir++) {
				copy(map, copyMap);
				watchable = getWatchableArea(cur.r, cur.c, dir, copyMap)
						+ getWatchableArea(cur.r, cur.c, dir + 2, copyMap);
				watch(count + 1, noVisible - watchable, copyMap);
			}
			break;
		case '3':
			for (int dir = 0; dir < 4; dir++) {
				copy(map, copyMap);
				if (dir != 3)
					watchable = getWatchableArea(cur.r, cur.c, dir, copyMap)
							+ getWatchableArea(cur.r, cur.c, dir + 1, copyMap);
				else
					watchable = getWatchableArea(cur.r, cur.c, dir, copyMap)
							+ getWatchableArea(cur.r, cur.c, 0, copyMap);
				watch(count + 1, noVisible - watchable, copyMap);
			}
			break;
		case '4':
			for (int d1 = 0; d1 < 4; d1++) {
				for (int d2 = d1 + 1; d2 < 4; d2++) {
					for (int d3 = d2 + 1; d3 < 4; d3++) {
						copy(map, copyMap);
						watchable = getWatchableArea(cur.r, cur.c, d1, copyMap)
								+ getWatchableArea(cur.r, cur.c, d2, copyMap)
								+ getWatchableArea(cur.r, cur.c, d3, copyMap);
						watch(count + 1, noVisible - watchable, copyMap);
					}
				}
			}
			break;
		case '5':
			copy(map, copyMap);
			for (int dir = 0; dir < 4; dir++) {
				watchable += getWatchableArea(cur.r, cur.c, dir, copyMap);
			}
			watch(count + 1, noVisible - watchable, copyMap);
		}
	}

	public static int getWatchableArea(int r, int c, int dir, char[][] map) {

		int nr = r, nc = c;
		int watchable = 0;
		while (true) {
			nr += dr[dir];
			nc += dc[dir];
			if (nr < 0 || nc < 0 || nr >= N || nc >= M || map[nr][nc] == '6')
				break;
			if (map[nr][nc] == '0') {
				watchable++;
				map[nr][nc] = '#'; // 사각지대에서 감시 가능한 곳으로 변경
			}

		}
		return watchable;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		office = new char[N][M];
		cctvList = new ArrayList<>();
		int noVisible = 0; // 사각지대 개수 저장할 변수

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			line = line.replace(" ", "");
			for (int j = 0; j < M; j++) {
				char c = line.charAt(j);
				office[i][j] = c;
				if (c == '0')
					noVisible++;
				else if (c != '6') // CCTV 정보 저장
					cctvList.add(new CCTV(c, i, j));
			}
		}

		watch(0, noVisible, office);
		bw.write(Integer.toString(ans));
		br.close();
		bw.flush();
		bw.close();
	}

}
