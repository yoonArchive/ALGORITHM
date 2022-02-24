import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_1753_최단경로 {

	private static final int INF = Integer.MAX_VALUE;

	public static class Vertex implements Comparable<Vertex> {
		int to;
		int weight;

		public Vertex(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Vertex o) {
			return this.weight - o.weight;
		}
	}

	public static void dijkstra(int start, ArrayList<ArrayList<Vertex>> list, int[] distance, int V) {
		PriorityQueue<Vertex> pq = new PriorityQueue<>(); //최단 거리를 갖는 노드를 찾는다
		boolean isVisited[] = new boolean[V + 1];

		pq.offer(new Vertex(start, 0));
		distance[start] = 0;

		while (!pq.isEmpty()) {
			Vertex cur = pq.poll(); //현재 방문한 정점
			if (isVisited[cur.to]) continue;  //이미 방문한 정점의 경우
			
			for (Vertex v : list.get(cur.to)) { //현재 정점과 인접한 정점들을 방문하며 최단 거리 갱신
				if (distance[v.to] > distance[cur.to] + v.weight) {
					distance[v.to] = distance[cur.to] + v.weight;
					pq.offer(new Vertex(v.to, distance[v.to])); //최단 거리 갱신했으면 우선순위 큐에 삽입
				}
			}
			isVisited[cur.to] = true;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String str = br.readLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int V = Integer.parseInt(st.nextToken()); // 정점 개수
		int E = Integer.parseInt(st.nextToken()); // 간선 개수
		int K = Integer.parseInt(br.readLine()); // 시작 정점

		ArrayList<ArrayList<Vertex>> list = new ArrayList<>();
		for (int i = 0; i <= V; i++)
			list.add(new ArrayList<Vertex>());

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list.get(from).add(new Vertex(to, weight));
		}

		int distance[] = new int[V + 1];
		Arrays.fill(distance, INF);

		dijkstra(K, list, distance, V);

		for (int i = 1; i <= V; i++) {
			if (distance[i] == INF) sb.append("INF\n");
			else sb.append(distance[i] + "\n");
		}
		System.out.println(sb.toString());

	}

}
