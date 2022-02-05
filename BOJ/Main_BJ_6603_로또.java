package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6603 {
	static int k;
	static int arr[];
	static boolean isChoosed[];
	static StringBuilder sb;

	public static void choose(int index, int count) {
		if(count==6) {
			for(int i=0;i<k;i++) {
				if(isChoosed[i]) {
					sb.append(arr[i]+" ");
				}
			}
			sb.append("\n");
		}
		
		for(int i=index; i<k; i++) {
			isChoosed[i]=true;
			choose(i+1, count+1);
			isChoosed[i]=false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			k = Integer.parseInt(st.nextToken());
			arr=new int[k];
			isChoosed=new boolean[k];
			if (k == 0)
				break;
			for (int i = 0; i < k; i++) {
				arr[i]=Integer.parseInt(st.nextToken());
			}
			choose(0,0);
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
