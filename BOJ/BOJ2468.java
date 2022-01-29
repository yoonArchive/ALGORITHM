package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2468 {
	static int map[][];
	static boolean visit[][];
	static int n;
	
	public static class Pair{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x=x;
			this.y=y;
		}
	}
	
	public static void bfs(int i, int j) {
		Queue<Pair> q=new LinkedList<>();
		q.offer(new Pair(i,j));
		int dx[]= {0,-1,0,1};
		int dy[]= {-1,0,1,0};
		
		while(!q.isEmpty()) {
			Pair p=q.poll();
			for(int t=0;t<dx.length;t++) {
				int nx=p.x+dx[t];
				int ny=p.y+dy[t];
				if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
				if(visit[nx][ny]==false) {
					q.offer(new Pair(nx,ny));
					visit[nx][ny]=true;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		n=Integer.parseInt(br.readLine());
		map=new int[n][n];
		visit=new boolean[n][n];
		int max=-1;
		for(int i=0;i<n;i++) {
			String str=br.readLine();
			StringTokenizer st=new StringTokenizer(str," ");
			for(int j=0;j<n;j++) {
				int temp=Integer.parseInt(st.nextToken());
				if(temp>=max) max=temp;
				map[i][j]=temp;
			}
		}
		
		int result[]=new int[max+1];
		int num=0;
		while(num<=max) {
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(map[i][j]<=num) {
						visit[i][j]=true;
					}
				}
			}
			int count=0;
			for(int i=0;i<n;i++) {
				for(int j=0;j<n;j++) {
					if(visit[i][j]==false) {
						bfs(i, j);
						count+=1;
					}
				}
			}
			result[num]=count;
			num+=1;
			for(int i=0;i<n;i++)
				Arrays.fill(visit[i],false);
		}
		Arrays.sort(result);
		
		bw.write(Integer.toString(result[max]));
		br.close();
		bw.flush();
		bw.close();
	}

}
