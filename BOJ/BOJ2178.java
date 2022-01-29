package BOJ;

import java.io.*;
import java.util.*;

public class BOJ2178 {
	static int map[][];
	static int bfs_arr[][];
	
	public static class Pair{ //ť�� ��ǥ�� �־��ֱ� ���� Ŭ����
		int x,y;
		public Pair(int x, int y){
			this.x=x;
			this.y=y;
		}
	}
	
	public static void main(String[] args) throws IOException{	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out));
		String str=br.readLine();
		StringTokenizer st=new StringTokenizer(str, " ");
		int n=Integer.parseInt(st.nextToken());
		int m=Integer.parseInt(st.nextToken());
		map=new int[n+1][m+1];
		for(int i=1;i<=n;i++) {
			str=br.readLine();
			for(int j=1;j<=m;j++) {
				map[i][j]=Character.getNumericValue(str.charAt(j-1));
			}
		}
		
		bfs_arr=new int[n+1][m+1];
		for(int i = 0; i < bfs_arr.length; i++) { //2�����迭 -1�� ä���
            Arrays.fill(bfs_arr[i], -1); //visited�迭 �ʿ� ���� bfs_arr ���� -1�̸� �湮���� �ʾҴٴ� ��
        }

		int dx[]= {-1,0,1,0};
		int dy[]= {0,-1,0,1};
		
		Queue<Pair> q=new LinkedList<>();
		q.offer(new Pair(1,1));
		bfs_arr[1][1]=1;
		while(!q.isEmpty()) {
			Pair p=q.poll(); //���� ��ǥ p
			for(int i=0;i<4;i++) { 
				int nx=p.x+dx[i];
				int ny=p.y+dy[i];
				if(nx==0 || ny==0 || nx ==n+1 || ny==m+1) { //������ �Ѿ�� ���
					continue;
				}
				if(map[nx][ny]==0 || bfs_arr[nx][ny]!=-1) { //�̵��� �� ���� ĭ(0)�̰ų� �̹� �湮�� ĭ�̶��
					continue;
				}
				q.offer(new Pair(nx,ny));
				bfs_arr[nx][ny]=bfs_arr[p.x][p.y]+1;
			}
		}
		
		bw.write(Integer.toString(bfs_arr[n][m]));
		br.close();
		bw.flush();
		bw.close();		
	}
}
