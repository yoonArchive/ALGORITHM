import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_17143_낚시왕 {
	static int[][] deltas = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } }; // 제자리, 위, 아래, 오, 왼
	static int R, C, M, kingLoc, res;
	static Shark[][] map;
	static PriorityQueue<Shark> sharkQ;
	static int totalSize;

	public static class Shark implements Comparable<Shark> {
		int id, r, c, speed, dir, size;

		public Shark(int id, int r, int c, int speed, int dir, int size) {
			super();
			this.id = id;
			this.r = r;
			this.c = c;
			this.speed = speed;
			this.dir = dir;
			this.size = size;
		}

		@Override
		public int compareTo(Shark o) {
			return o.size - this.size;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new Shark[R][C]; // 매초마다 격자판 정보 저장할 배열
		sharkQ = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = new Shark(i + 1, r - 1, c - 1, s, d, z);
		}

		kingLoc = -1;
		while (++kingLoc != C) {
			fishing();
			update();
			moveShark();
		}

		bw.write(Integer.toString(totalSize));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void fishing() {
		int r = -1;
		while (++r < R) {
			if (map[r][kingLoc] != null) {
				int cursize = map[r][kingLoc].size;
				map[r][kingLoc] = null; // 가장 땅과 가까운 상어 잡기 완료
				totalSize += cursize;
				break;
			}
		}
	}

	private static void update() { // map을 보며 sharkQ를 업데이트
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != null) {
					sharkQ.offer(map[r][c]);
					map[r][c] = null;
				}
			}
		}
	}

	/**
	 * sharkQ에서 상어를 모두 꺼내며 이동 상어를 꺼낸 좌표에 이미 다른 상어가 있다면 잡아먹힌다(pq로 관리)
	 */
	private static void moveShark() {
		while (!sharkQ.isEmpty()) {
			Shark s = sharkQ.poll();
			int r = s.r;
			int c = s.c;
			int dir = s.dir;
			int speed = s.speed;

			while (speed-- > 0) {
				int nr = r + deltas[dir][0];
				int nc = c + deltas[dir][1];

				if (0 <= nr && 0 <= nc && nr < R && nc < C) {
					r = nr;
					c = nc;
				} else {// 경계를 벗어난 경우 방향 전환
					if (dir % 2 == 0)
						dir--;
					else
						dir++;
					speed++;
				}
			}
			s.r = r;
			s.c = c;
			s.dir = dir;

			if (map[r][c] == null) {
				map[r][c] = s;
			} else {
				continue;
			}
		}
	}

}
