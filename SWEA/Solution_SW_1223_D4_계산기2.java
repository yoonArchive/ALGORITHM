import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SW_D4_1223_계산기2 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_1223.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		for(int tc=1;tc<=10;tc++) {
			int length=Integer.parseInt(br.readLine());
			String str=br.readLine();
			int i=0;
			Stack<Integer> stack=new Stack<>();
			while(true) {
				if(i==length) break;
				
				char c=str.charAt(i);
				if(c=='+') {
					i++;
					continue;
				}
				else if(c=='*') {
					int tmp=stack.pop();
					int mul=tmp*(str.charAt(i+1)-'0');
					stack.push(mul);
					i++;
				}
				else { //숫자
					stack.push(c-'0');
				}
				i++;
			}
			
			int sum=0;
			while(!stack.isEmpty()) sum+=stack.pop();
			sb.append("#"+tc+" "+sum+"\n");		
		}
		System.out.println(sb.toString());
	}
}
