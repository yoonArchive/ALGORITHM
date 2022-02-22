import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_SW_1238_Contact {
	static ArrayList<ArrayList<Integer>> contact = new ArrayList<>();

	public static class contactInfo implements Comparable<contactInfo> {
		int num;
		int order;

		public contactInfo(int num, int order) {
			super();
			this.num = num;
			this.order = order;
		}
		@Override
		public int compareTo(contactInfo o) {
			if (this.order == o.order)
				return this.num - o.num;
			else
				return this.order - o.order;
		}
	}

	public static int call(int from, boolean[] isAnswered) {
		Queue<contactInfo> q = new LinkedList<>();
		ArrayList<contactInfo> orderList = new ArrayList<>();
		q.offer(new contactInfo(from, 1));
		orderList.add(new contactInfo(from, 1));
		isAnswered[from] = true;

		while (!q.isEmpty()) {
			contactInfo info = q.poll();
			for (int i = 0, size = contact.get(info.num).size(); i < size; i++) {
				int tmp = contact.get(info.num).get(i);
				if (!isAnswered[tmp]) {
					q.offer(new contactInfo(tmp, info.order + 1));
					orderList.add(new contactInfo(tmp, info.order + 1));
					isAnswered[tmp] = true;
				}
			}
		}
		Collections.sort(orderList);
		int lastIdx = orderList.size() - 1;
		return orderList.get(lastIdx).num;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_1238.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String line = "";
		int testcase = 1;
		while ((line = br.readLine()) != null) {
			contact.clear();
			StringTokenizer st = new StringTokenizer(line, " ");
			int length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			for (int i = 0; i <= length; i++)
				contact.add(new ArrayList<Integer>());
			st = new StringTokenizer(br.readLine(), " ");
			while (st.hasMoreTokens()) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				contact.get(from).add(to);
			}

			sb.append("#" + testcase + " " + call(start, new boolean[length + 1]) + "\n");
			testcase++;
		}
		System.out.println(sb.toString());

	}

}
