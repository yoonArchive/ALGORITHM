import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW_7465_창용마을무리의개수_ver_BFS {
	static ArrayList<ArrayList<Integer>> network;
	static boolean[] isVisted;
	static int count;

	public static void bfs(int num) {
		Queue<Integer> q = new LinkedList<>();
		if (!isVisted[num]) {
			q.offer(num);
			isVisted[num] = true;
		} else
			return;

		while (!q.isEmpty()) {
			int pollNum = q.poll();
			for (int i = 0, size = network.get(pollNum).size(); i < size; i++) {
				int tmp = network.get(pollNum).get(i);
				if (!isVisted[tmp]) {
					isVisted[tmp] = true;
					q.offer(tmp);
				}
			}
		}
		count++;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_7465.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			network = new ArrayList<>();
			for (int i = 0; i <= N; i++)
				network.add(new ArrayList<Integer>());
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int person1 = Integer.parseInt(st.nextToken());
				int person2 = Integer.parseInt(st.nextToken());
				network.get(person1).add(person2);
				network.get(person2).add(person1);
			}
			count = 0;
			isVisted = new boolean[N + 1];
			for (int i = 1; i <= N; i++)
				bfs(i);
			sb.append("#" + tc + " " + count + "\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
