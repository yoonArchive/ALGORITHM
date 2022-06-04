import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_17471_게리맨더링 {
	static int[][] adjMatrix;
	static int population[];
	static ArrayList<Integer> sectionOne;
	static ArrayList<Integer> sectionTwo;
	static int N, minDiff;

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("BOJ17471.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		population = new int[N + 1];
		adjMatrix = new int[N + 1][N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i <= N; i++) {
			population[i] = Integer.parseInt(st.nextToken());
		}
		// 인접행렬 만들기
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int count = Integer.parseInt(st.nextToken());
			while (count-- > 0) {
				adjMatrix[i][Integer.parseInt(st.nextToken())] = 1;
			}
		}
		sectionOne = new ArrayList<>();
		sectionTwo = new ArrayList<>();
		minDiff = Integer.MAX_VALUE;
		for (int i = 1; i <= N/2; i++) {
			sectionOne.clear();
			combination(0, 1, i);
		}
		if (minDiff == Integer.MAX_VALUE)
			bw.write("-1");
		else
			bw.write(Integer.toString(minDiff));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void combination(int cnt, int start, int limit) {
		if (cnt == limit) {
			for (int i = 1; i <= N; i++) {
				if (sectionOne.indexOf(i) == -1)
					sectionTwo.add(i);
			}
			if (check(sectionOne) && check(sectionTwo)) { // 선거구를 올바르게 나눴다면 인구 차이를 구한다.
				minDiff = Math.min(minDiff, Math.abs(getSum(sectionOne) - getSum(sectionTwo)));
			}
			sectionTwo.clear();
			return;
		}
		for (int i = start; i <= N; i++) {
			sectionOne.add(i);
			combination(cnt + 1, i + 1, limit);
			sectionOne.remove(sectionOne.indexOf(i));
		}

	}

	private static boolean check(ArrayList<Integer> list) {
		// 리스트 원소가 모두 연결되어있는지 확인
		if (bfs(list) != list.size())
			return false;
		return true;
	}

	private static int bfs(ArrayList<Integer> list) {
		Queue<Integer> q = new LinkedList<>();
		int flag = 0;
		int start = list.get(0);
		q.offer(start);
		flag |= 1 << start;

		int count = 1;
		while (!q.isEmpty()) {
			int pre = q.poll();
			for (int i = 1; i <= N; i++) {
				if (adjMatrix[pre][i] == 1 && list.contains(i) && (flag & 1 << i) == 0) {
					q.offer(i);
					flag |= 1 << i;
					count++;
				}
			}
		}
		return count;
	}

	private static int getSum(ArrayList<Integer> list) {
		int sum = 0;
		for (int i = 0, length = list.size(); i < length; i++)
			sum += population[list.get(i)];
		return sum;
	}

}
