import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_BJ_15686_치킨배달 {
	static int N, M;
	static int[] chickenIdx;
	static ArrayList<Location> chickenList;
	static ArrayList<Location> houseList;
	static int min=Integer.MAX_VALUE;

	static class Location {
		int r, c;

		public Location(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static int getChickenDistance(int houseNum) {
		int chickenDist = 0;
		for (int i = 0; i < houseNum; i++) {
			int minDist=Integer.MAX_VALUE;
			for (int j = 0; j < M; j++) {
				int nowDist=Math.abs(houseList.get(i).c-chickenList.get(chickenIdx[j]).c)+Math.abs(houseList.get(i).r-chickenList.get(chickenIdx[j]).r);
				minDist=Math.min(minDist, nowDist);
			}
			chickenDist+=minDist;
		}
		return chickenDist;
	}

	public static void comb(int order[], int chickenNum, int houseNum, int count, int start) {
		if (count == M) {
			int dist=getChickenDistance(houseNum);
			min=Math.min(min, dist);
			return;
		}
		for (int i = start; i < chickenNum; i++) {
			chickenIdx[count] = order[i];
			comb(order, chickenNum, houseNum, count + 1, i + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int city[][] = new int[N][N];
		chickenList = new ArrayList<>();
		houseList = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				city[i][j] = cur;
				if (cur == 2)
					chickenList.add(new Location(i, j));
				else if (cur == 1)
					houseList.add(new Location(i, j));
			}
		}

		int chickenNum = chickenList.size();
		int houseNum = houseList.size();
		int order[] = new int[chickenNum];
		chickenIdx = new int[M];
		for (int i = 0; i < chickenNum; i++)
			order[i] = i;
		comb(order, chickenNum, houseNum, 0, 0);
		bw.write(Integer.toString(min));
		br.close();
		bw.flush();
		bw.close();

	}

}
