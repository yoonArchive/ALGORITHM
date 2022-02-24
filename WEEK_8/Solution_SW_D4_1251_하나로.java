import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SW_D4_1251_하나로 {

	public static class islandLoc {
		int x, y;

		public islandLoc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static class connectInfo implements Comparable<connectInfo> {
		int islandNum1, islandNum2;
		double pay;

		public connectInfo(int islandNum1, int islandNum2, double pay) {
			super();
			this.islandNum1 = islandNum1;
			this.islandNum2 = islandNum2;
			this.pay = pay;
		}

		@Override
		public int compareTo(connectInfo o) {
			if (this.pay > o.pay) return 1;
			else return -1;
		}
	}

	public static void makeSet(int[] parents, int N) {
		for (int i = 0; i < N; i++) 
			parents[i] = i;
	}

	public static int findSet(int[] parents, int a) {
		if (parents[a] == a) return a;
		return parents[a] = findSet(parents, parents[a]);
	}

	public static boolean union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		if (aRoot == bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_1251.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 섬의 개수
			islandLoc[] island = new islandLoc[N];
			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					if (i == 0)
						island[j] = new islandLoc(Integer.parseInt(st.nextToken()), 0);
					else
						island[j].y = Integer.parseInt(st.nextToken());
				}
			}
			double E = Double.parseDouble(br.readLine()); // 환경 부담 세율
			PriorityQueue<connectInfo> pq = new PriorityQueue<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					double pay = (Math.pow(Math.sqrt(Math.pow(island[i].x - island[j].x, 2) + Math.pow(island[i].y - island[j].y, 2)), 2)) * E;
					pq.add(new connectInfo(i, j, pay));
				}
			}
			int parents[] = new int[N];
			makeSet(parents, N);

			double ans = 0.0;
			int cnt = 0;
			while (!pq.isEmpty()) {
				connectInfo cur = pq.poll();
				if (union(parents, cur.islandNum1, cur.islandNum2)) {
					ans += cur.pay;
					cnt++;
				}
				if (cnt == N - 1)
					break;
			}
			sb.append("#" + tc + " " + Math.round(ans) + ("\n"));
		}
		System.out.println(sb.toString());

	}

}
