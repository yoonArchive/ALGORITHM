package BOJ;

import java.io.*;
import java.util.*;

public class BOJ11725 {
	static int n;
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static ArrayList<ArrayList<Integer>> list;
	static boolean visit[];
	static int parent[];

	public static void dfs(int start) {
		visit[start] = true;
		for (int i = 0; i < list.get(start).size(); i++) {
			int num = list.get(start).get(i);
			if (visit[num] == false) {
				parent[num] = start;
				dfs(num);
			}
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		list = new ArrayList<>();
		for (int i = 0; i <= n; i++)
			list.add(new ArrayList<Integer>());

		for (int i = 1; i < n; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			list.get(a).add(b);
			list.get(b).add(a);
		}
		parent = new int[n + 1];
		visit = new boolean[n + 1];
		dfs(1);
		for (int i = 2; i <= n; i++) {
			bw.write(Integer.toString(parent[i]));
			bw.newLine();
		}

		br.close();
		bw.flush();
		bw.close();
	}
}
