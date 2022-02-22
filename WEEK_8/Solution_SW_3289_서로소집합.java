import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_3289_서로소집합 {

	public static void makeSet(int[] parents) {
		for (int i = 1, size = parents.length; i < size; i++)
			parents[i] = i;
	}

	public static int findSet(int[] parents, int num) {
		if (num == parents[num]) return num;
		return parents[num] = findSet(parents, parents[num]);
	}

	public static void union(int[] parents, int a, int b) {
		int aRoot = findSet(parents, a);
		int bRoot = findSet(parents, b);
		
		if (aRoot == bRoot) return;
		parents[bRoot] = aRoot;
		return;
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_3289.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int[] parents = new int[n + 1];
			makeSet(parents);
			StringBuilder result=new StringBuilder();
			for (int i = 0; i < m; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int calc = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				if (calc == 0) union(parents, a, b);
				else {
					if (findSet(parents, a) == findSet(parents, b)) result.append("1");
					else result.append("0");
				}
			}
			sb.append("#" + tc + " ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
