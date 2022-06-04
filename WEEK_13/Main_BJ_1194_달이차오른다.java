import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1194_달이차오른다 {
	static int N, M;
	static char[][] map;
	static boolean[][][] visited;
	static int[][] DELTAS = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visited = new boolean[N][M][(int) Math.pow(2, 6)];

		int startR = 0, startC = 0;
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == '0') {
					startR = i;
					startC = j;
				}
			}
		}

		bw.write(Integer.toString(mazeGame(startR, startC)));
		br.close();
		bw.flush();
		bw.close();
	}

	private static int mazeGame(int sr, int sc) {
		Queue<int[]> q = new LinkedList<>();
		q.offer(new int[] { sr, sc, 0 });
		visited[sr][sc][0] = true;

		int moveCnt = 0;
		while (!q.isEmpty()) {
			int qSize = q.size();
			while (qSize-- > 0) {
				int[] pair = q.poll();
				int r = pair[0];
				int c = pair[1];

				if (map[r][c] == '1') { // 목적지 도착
					return moveCnt;
				}

				for (int d = 0; d < 4; d++) {
					int nr = r + DELTAS[d][0];
					int nc = c + DELTAS[d][1];
					int key = pair[2];
					
					if (!isIn(nr, nc) || map[nr][nc] == '#' || visited[nr][nc][key])
						continue;
					char nowVal = map[nr][nc];
					if (nowVal >= 'a' && nowVal <= 'f') { // 열쇠 발견
						key |= (1 << (nowVal - 'a'));
					} else if (nowVal >= 'A' && nowVal <= 'F') { // 문 발견
						if ((key & 1 << (nowVal - 'A')) == 0) // 문에 해당하는 키가 없다면
							continue;
					}
					q.offer(new int[] { nr, nc, key });
					visited[nr][nc][key] = true;
				}
			}
			moveCnt++;
		}
		
		return -1;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}

}