import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution_SW_1218_D3_괄호짝짓기_박기윤 {

	// { [ ( } ] ) 의 경우 유효하지 않다고 가정하고 문제풀기

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input (2).txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			int n = Integer.parseInt(br.readLine()); // 입력 길이
			String str = br.readLine();
			Stack<Character> stack = new Stack<>();

			int result = 0;

			for (int i = 0; i <= n; i++) {
				if (i == n) {
					if (stack.size() == 0)
						result = 1;
					else
						result = 0;
					break;
				}

				char c = str.charAt(i);
				if (c == '(' || c == '{' || c == '[' || c == '<') {
					stack.push(c);
					continue;
				} else {
					char open = stack.pop();
					if (c == ')') {
						if (open != '(') {
							result = 0;
							break;
						}
					} else if (c == '}') {
						if (open != '{') {
							result = 0;
							break;
						}
					} else if (c == ']') {
						if (open != '[') {
							result = 0;
							break;
						}
					} else {
						if (open != '<') {
							result = 0;
							break;
						}
					}
				}
			}
			sb.append("#" + tc + " " + result + "\n");

		}
		System.out.println(sb.toString());
		br.close();
	}

}
