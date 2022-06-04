import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1600_말이되고픈원숭이 {
	final static int OBSTACLE = 1;
	final static int PLAIN = 0;
	static int K, W, H;
	static int board[][];
	static int dr[] = { -1, 0, 1, 0 };
	static int dc[] = { 0, -1, 0, 1 };
	static int horseDr[] = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int horseDc[] = { -2, -1, 1, 2, 2, 1, -1, -2 };

	public static class MoveInfo {
		int r, c, moveCnt, horseJump;

		public MoveInfo(int r, int c, int moveCnt, int horseJump) {
			super();
			this.r = r;
			this.c = c;
			this.moveCnt = moveCnt;
			this.horseJump = horseJump;
		}

	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("1600.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < W; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		bw.write(Integer.toString(moveMonkey()));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int moveMonkey() {
		Queue<MoveInfo> q = new LinkedList<>();
		boolean isVisited[][][] = new boolean[H][W][K + 1];

		q.offer(new MoveInfo(0, 0, 0, 0));
		isVisited[0][0][0] = true;
		int minCnt = -1;

		while (!q.isEmpty()) {
			MoveInfo info = q.poll();
			int r = info.r;
			int c = info.c;
			int cnt = info.moveCnt;
			int curHorseJump = info.horseJump;
			
			if (r == H - 1 && c == W - 1) {
				minCnt = cnt;
				if (r == 0 && c == 0)
					minCnt = 0;
				break;
			}
			
			int nCnt = cnt + 1;
			for (int i = 0, length = dr.length; i < length; i++) { //인접칸 이동
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (!canGo(nr, nc))
					continue;
				if (!isVisited[nr][nc][curHorseJump]) {
					q.offer(new MoveInfo(nr, nc, nCnt, curHorseJump));
					isVisited[nr][nc][curHorseJump] = true;
				}
			}

			if (curHorseJump < K) {
				for (int i = 0, length = horseDr.length; i < length; i++) { //말 점프
					int nr = r + horseDr[i];
					int nc = c + horseDc[i];
					if (!canGo(nr, nc))
						continue;
					if (!isVisited[nr][nc][curHorseJump + 1]) {
						q.offer(new MoveInfo(nr, nc, nCnt, curHorseJump + 1));
						isVisited[nr][nc][curHorseJump + 1] = true;
					}
				}
			}
		}
		if (minCnt == -1)
			return -1;
		return minCnt;
	}

	private static boolean canGo(int nr, int nc) {
		if (nr >= 0 && nc >= 0 && nr < H && nc < W && board[nr][nc] == PLAIN)
			return true;
		return false;
	}

}
