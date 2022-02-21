import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_1260_DFS와BFS {
	static ArrayList<ArrayList<Integer>> list;
	static StringBuilder sb = new StringBuilder();

	public static void dfs(int v, boolean isVisited[]) {
		isVisited[v] = true;
		sb.append(v + " ");
		for (int i = 0, size = list.get(v).size(); i < size; i++) {
			int tmp = list.get(v).get(i);
			if (!isVisited[tmp]) {
				isVisited[tmp] = true;
				dfs(tmp, isVisited);
			}
		}

	}

	public static void bfs(int start, boolean isVisited[]) {
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);
		isVisited[start] = true;
		sb.append(start + " ");

		while (!q.isEmpty()) {
			int v = q.poll();
			for (int i = 0, size = list.get(v).size(); i < size; i++) {
				int tmp = list.get(v).get(i);
				if (!isVisited[tmp]) {
					q.offer(tmp);
					isVisited[tmp] = true;
					sb.append(tmp + " ");
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());
		list = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			list.add(new ArrayList<Integer>());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int vertex1 = Integer.parseInt(st.nextToken());
			int vertex2 = Integer.parseInt(st.nextToken());
			list.get(vertex1).add(vertex2);
			list.get(vertex2).add(vertex1);
		}
		boolean isVisted[] = new boolean[N + 1];
		for (int i = 0; i < list.size(); i++)
			Collections.sort(list.get(i));

		dfs(V, isVisted);
		sb.append("\n");
		Arrays.fill(isVisted, false);
		bfs(V, isVisted);
		System.out.println(sb.toString());
	}

}
