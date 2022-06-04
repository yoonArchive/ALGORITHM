import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_SW_D4_최소스패닝트리 {

	public static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}

	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("swea3124.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				pq.add(new Edge(from, to, weight));
			}
			
			int parents[] = new int[V + 1];
			makeSet(parents, V);
			long total = 0;
			int count = 0;
			while (!pq.isEmpty()) {
				Edge edge = pq.poll();
				if (Union(parents, edge.from, edge.to)) {
					total += edge.weight;
					if (++count == V - 1) break;
				}
			}
			sb.append("#" + tc + " " + total).append("\n");
		}
		System.out.println(sb.toString());

	}

	private static void makeSet(int parents[], int V) {
		for (int i = 1; i <= V; i++)
			parents[i] = i;
	}

	private static int findSet(int parents[], int n) {
		if (n == parents[n])
			return n;
		return parents[n] = findSet(parents, parents[n]);
	}

	private static boolean Union(int parents[], int from, int to) {
		int fromRoot = findSet(parents, from);
		int toRoot = findSet(parents, to);
		if (fromRoot == toRoot)
			return false;
		parents[toRoot] = fromRoot;
		return true;
	}

}
