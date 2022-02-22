import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_7465_창용마을무리의개수_ver_UnionFind {

	public static int makeSet(int[] parents, int relationCnt) {
		for (int i = 1, size = parents.length; i < size; i++) {
			parents[i] = i;
			relationCnt++;
		}
		return relationCnt;
	}

	public static int findSet(int pNum, int[] parents) {
		if (parents[pNum] == pNum) return pNum;
		return parents[pNum] = findSet(parents[pNum], parents);
	}

	public static int union(int a, int b, int relationCnt, int[] parents) {
		int aRoot = findSet(a, parents);
		int bRoot = findSet(b, parents);
		if (aRoot == bRoot)
			return relationCnt;
		parents[bRoot] = aRoot;
		return --relationCnt;
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
			int parents[] = new int[N + 1];
			int relationCnt = 0;
			relationCnt = makeSet(parents, relationCnt);
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int person1 = Integer.parseInt(st.nextToken());
				int person2 = Integer.parseInt(st.nextToken());
				relationCnt = union(person1, person2, relationCnt, parents);
			}
			sb.append("#" + tc + " " + relationCnt + "\n");
		}
		System.out.println(sb.toString());

	}

}
