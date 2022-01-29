package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2206 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static int[] dx = {1,0, -1, 0 };
	static int[] dy = {0,1,0,-1 };

	public static class Pair {
		int x, y;
		int dis;
		int drill; // ���� Ƚ��

		public Pair(int x, int y, int dis, int drill) {
			this.x = x;
			this.y = y;
			this.dis = dis;
			this.drill = drill;
		}
	}

	public static int bfs(int x, int y) throws IOException{
		Queue<Pair> q = new LinkedList<>();
		q.offer(new Pair(x, y, 1, 0)); // (0, 0)���� ����
		visited[x][y] = 0; // ó�� ���� Ƚ��(visited�� ���� Ƚ���� ��Ÿ���� �迭)

		while (!q.isEmpty()) {
			Pair p = q.poll();
			bw.write(Integer.toString(p.x)+" " +Integer.toString(p.y)+" " +Integer.toString(p.dis)+" "+Integer.toString(p.drill));
			bw.newLine();

			if (p.x == N - 1 && p.y == M - 1)
				return p.dis; // ���������� �����ٸ� �����Ѵ�.

			for (int i = 0; i < dx.length; i++) {
				int nx = p.x + dx[i];
				int ny = p.y + dy[i];
				
				if(nx<0 || ny<0 || nx>= N || ny>=M) continue;
				if(visited[nx][ny]<=p.drill) continue; // ���� �� �μ� ���� ���� Ž���ߴٸ�, 
													   // ���� �μ��� �� �ʿ����� Ž���� �ʿ� ������ �ǹ�.
				
				if(map[nx][ny]==0) { //���� �ƴ� ��
					visited[nx][ny]=p.drill;
					q.offer(new Pair(nx,ny,p.dis+1, p.drill));
				}
				else {//���� ��
					if(p.drill==0) { // ���̱� ������ ���ݱ��� ���� �μ� Ƚ���� 0�̿��� ������ �� ����.
						visited[nx][ny]=p.drill+1;
						q.offer(new Pair(nx,ny,p.dis+1, p.drill+1));
					}
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new int[N][M];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(str.charAt(j)));
				visited[i][j] = Integer.MAX_VALUE;
			}
		}

		int ans = bfs(0, 0);
		bw.write(Integer.toString(ans));
		
		br.close();
		bw.flush();
		bw.close();
		
	}
}