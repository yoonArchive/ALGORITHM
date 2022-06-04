import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_14502_연구소 {
	final static int BLANK = 0;
	final static int WALL = 1;
	final static int VIRUS = 2;
	static int N, M, maxSafeZone;
	static int map[][];
	static ArrayList<Pair> virusList;
	static ArrayList<Pair> blankList;
	static int dr[] = { 0, 1, 0, -1 };
	static int dc[] = { 1, 0, -1, 0 };

	public static class Pair {
		int r, c;

		public Pair(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		virusList = new ArrayList<>();
		blankList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
				if (tmp == VIRUS)
					virusList.add(new Pair(i, j));
				else if (tmp == BLANK)
					blankList.add(new Pair(i, j));
			}
		}
		int blankCnt = blankList.size();
		buildWall(0, 0, 3, blankCnt);
		bw.write(Integer.toString(maxSafeZone));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void buildWall(int count, int start, int limit, int size) {
		if (count == limit) {
			spread();
			return;
		}
		for (int i = start; i < size; i++) {
			Pair p = blankList.get(i);
			map[p.r][p.c] = WALL;
			buildWall(count + 1, i + 1, limit, size);
			map[p.r][p.c] = BLANK;
		}
	}

	private static void spread() {
		Queue<Pair> virusQ = new LinkedList<>();
		for (int i = 0, length = virusList.size(); i < length; i++) {
			virusQ.offer(virusList.get(i));
		}
		int[][] copyMap = new int[N][M];
		copyMap = copy(copyMap);
		while (!virusQ.isEmpty()) {
			Pair p = virusQ.poll();
			int r = p.r;
			int c = p.c;
			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				if (!isIn(nr, nc) || copyMap[nr][nc] != BLANK)
					continue;
				copyMap[nr][nc] = VIRUS;
				virusQ.offer(new Pair(nr, nc));
			}
		}
		countSafeZone(copyMap);
	}

	private static void countSafeZone(int[][] copyMap) {
		int count = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copyMap[i][j] == BLANK)
					count++;
			}
		}
		maxSafeZone = Math.max(maxSafeZone, count);
	}

	private static int[][] copy(int[][] copyMap) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyMap[i][j] = map[i][j];
			}
		}
		return copyMap;
	}

	private static boolean isIn(int nr, int nc) {
		return nr >= 0 && nc >= 0 && nr < N && nc < M;
	}
}
