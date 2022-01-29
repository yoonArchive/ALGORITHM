package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2667 {
	static int map[][];
	static int result_arr[][];
	
	public static class Pair{
		int x,y;
		public Pair(int x, int y) {
			this.x=x;
			this.y=y;
			
		}
	}

	public static void main(String args[]) throws IOException{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		int n=Integer.parseInt(br.readLine());
		map=new int[n+1][n+1];
		for(int i=1;i<=n;i++) {
			String str=br.readLine();
			for(int j=1;j<=n;j++) {
				map[i][j]=Character.getNumericValue(str.charAt(j-1));
			}
		}
		result_arr=new int[n+1][n+1];
		for(int i=0; i<result_arr.length;i++) {
			Arrays.fill(result_arr[i], -1);
		}
		Queue<Pair> q=new LinkedList<>();
		int dx[]= {-1,0,1,0};
		int dy[]= {0,-1,0,1};
		ArrayList<Integer> list=new ArrayList<Integer>();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n;j++) {
				if(map[i][j]==0) {
					continue;
				}
				if(map[i][j]==1 && result_arr[i][j]==-1) {
					int num=1;
					q.offer(new Pair(i,j));
					result_arr[i][j]=num;
					while(!q.isEmpty()) {					
						Pair p=q.poll();
						for(int w=0;w<4;w++) {
							int nx=p.x+dx[w];
							int ny=p.y+dy[w];
							if(nx==0 || ny==0 || nx==n+1 || ny==n+1) continue;
							if(map[nx][ny]==0 || result_arr[nx][ny]!=-1) continue;
							num=num+1;
							q.offer(new Pair(nx,ny));
							result_arr[nx][ny]=num;
						}
					}
					list.add(num);
				}
			}
		}
		Collections.sort(list);
		bw.write(Integer.toString(list.size()));
		bw.newLine();
		for(int i=0;i<list.size();i++) {
			bw.write(Integer.toString(list.get(i)));
			bw.newLine();
		}
		br.close();
		bw.flush();
		bw.close();
	}
}
