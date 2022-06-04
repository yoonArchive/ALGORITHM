import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_9205_맥주마시면서걸어가기 {
	static final String happyState = "happy";
	static final String sadState = "sad";

	public static class Pair {
		int r;
		int c;

		public Pair(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine());
			ArrayList<Pair> list = new ArrayList<>();
			StringTokenizer st = null;
			for (int i = 0; i < N + 2; i++) { // 집- 편의점-페스티벌 좌표를 큐에 넣는다.
				st = new StringTokenizer(br.readLine(), " ");
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				list.add(new Pair(r, c));
			}
			sb.append(goFestival(list)).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	public static String goFestival(ArrayList<Pair> list) {
		boolean isArrived = false;
		Queue<Pair> q = new LinkedList<>();
		q.offer(list.get(0)); // 집을 큐에 넣는다
		list.remove(0);

		Pair goal = list.get(list.size() - 1);

		while (!q.isEmpty()) {
			if (isArrived)
				return happyState;
			Pair pair = q.poll();
			int r = pair.r;
			int c = pair.c;
			int size = list.size() - 1;
			for (int i = size; i >= 0; i--) {
				if (Math.abs(list.get(i).r - r) + Math.abs(list.get(i).c - c) <= 1000) {
					Pair next = list.get(i);
					if (next.r == goal.r && next.c == goal.c)
						isArrived = true;
					q.offer(next);
					list.remove(i);
				}
			}
		}
		return sadState;
	}

}