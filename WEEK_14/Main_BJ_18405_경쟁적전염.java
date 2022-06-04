import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_18405_경쟁적전염 {
	static int[] dr = { -1, 0, 1, 0 };
	static int[] dc = { 0, -1, 0, 1, };
	static int[][] map;
	static int N, K, S, X, Y;

	public static class Virus implements Comparable<Virus> {
		int r, c, num, spreadTime;

		public Virus(int r, int c, int num, int spreadTime) {
			super();
			this.r = r;
			this.c = c;
			this.num = num;
			this.spreadTime = spreadTime;
		}

		@Override
		public int compareTo(Virus o) {
			if (this.spreadTime - o.spreadTime == 0)
				return this.num - o.num;
			return this.spreadTime - o.spreadTime;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		PriorityQueue<Virus> virusPQ = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j <= N; j++) {
				int curVirus = Integer.parseInt(st.nextToken());
				map[i][j] = curVirus;
				if (curVirus != 0)
					virusPQ.add(new Virus(i, j, curVirus, 0));
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		S = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		spreadVirus(virusPQ);

		bw.write(Integer.toString(map[X][Y]));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void spreadVirus(PriorityQueue<Virus> virusPQ) {
		int time = 0;
		while (!virusPQ.isEmpty()) {
			if (time == S)
				break;
			int size = virusPQ.size();
			while (size-- > 0) {
				Virus virus = virusPQ.poll();
				int r = virus.r;
				int c = virus.c;
				int num = virus.num;
				int spreadTime = virus.spreadTime;
				for (int d = 0, length = dr.length; d < length; d++) {
					int nr = r + dr[d];
					int nc = c + dc[d];
					if (!canSpread(nr, nc))
						continue;
					map[nr][nc] = num;
					virusPQ.add(new Virus(nr, nc, num, spreadTime + 1));
				}
			}
			++time;
		}
	}

	private static boolean canSpread(int r, int c) {
		if (r >= 1 && r <= N && c >= 1 && c <= N && map[r][c] == 0)
			return true;
		return false;
	}

}
