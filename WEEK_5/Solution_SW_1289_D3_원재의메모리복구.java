import java.util.Scanner;

class Solution_SW_1289_D3_원재의메모리복구 {
	public static void main(String args[]) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		for (int tc = 1; tc <= T; tc++) {
			char[] c = sc.next().toCharArray();
			char tmp = '0'; //비교를 위한 변수
			int cnt = 0;
			
			for (int i = 0; i < c.length; i++) {
				if (c[i] != tmp) {
					tmp = c[i];
					cnt+=1;
				}
			}
			
			System.out.println("#" + tc + " " + cnt);
		}
		sc.close();
	}
}
