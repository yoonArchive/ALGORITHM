import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_5644_무선충전 {
	static int M, A, answer;
	static int[][] userMoveInfo;
	static ArrayList<BCinfo> BC;

	public static class BCinfo {
		int x; // 충전기 x 좌표
		int y; // 충전기 y 좌표
		int c; // 충전범위
		int p; // 처리량

		public BCinfo(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}
	}

	public static class userLoc {
		int x; 
		int y;

		public userLoc(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void userMove(userLoc user, int num) {
		switch (num) {
		case 0:
			break;
		case 1:
			user.y--;
			break;
		case 2:
			user.x++;
			break;
		case 3:
			user.y++;
			break;
		case 4:
			user.x--;
			break;
		}
	}

	public static void move() {
		int count = 0;
		
		userLoc userA = new userLoc(1, 1);
		userLoc userB = new userLoc(10, 10);

		ArrayList<Integer> userA_BC = new ArrayList<>();
		ArrayList<Integer> userB_BC = new ArrayList<>();

		while (true) {
			userA_BC.clear();
			userB_BC.clear();

			// 1. 현재 위치에서 충전 가능한지 확인
			int bc_x, bc_y, bc_c;
			
			for (int i = 0; i < A; i++) {
				bc_x = BC.get(i).x;
				bc_y = BC.get(i).y;
				bc_c = BC.get(i).c;

				if (Math.abs(bc_x - userA.x) + Math.abs(bc_y - userA.y) <= bc_c) // 범위내에 있으면 사용자의 BC 리스트에 추가
					userA_BC.add(i);

				if (Math.abs(bc_x - userB.x) + Math.abs(bc_y - userB.y) <= bc_c)
					userB_BC.add(i);
			}

			// 2. 충전량 구하기
			int maxCharge = 0; // 최대 충전량
			int tmp = 0; // 비교 위한 변수

			if (userA_BC.size() > 0 && userB_BC.size() == 0) {
				for (int i = 0; i< userA_BC.size(); i++) {
					tmp = BC.get(userA_BC.get(i)).p; //현재 좌표에서 가능한 BC 중 충전량이 가장 큰 것
					maxCharge = maxCharge > tmp ? maxCharge : tmp;
				}
			} 
			else if (userA_BC.size() == 0 && userB_BC.size() > 0) {
				for (int i = 0; i < userB_BC.size(); i++) {
					tmp = BC.get(userB_BC.get(i)).p;
					maxCharge = maxCharge > tmp ? maxCharge : tmp;
				}
			} 
			else {
				for (int i = 0; i < userA_BC.size(); i++) {
					for (int j = 0; j < userB_BC.size(); j++) {
						if (userA_BC.get(i) == userB_BC.get(j))	// 같은 BC인 경우
							tmp = BC.get(userA_BC.get(i)).p;
						else // 다른 BC인 경우
							tmp = BC.get(userA_BC.get(i)).p + BC.get(userB_BC.get(j)).p;

						maxCharge = maxCharge > tmp ? maxCharge : tmp;
					}
				}
			}
			answer += maxCharge;

			if (count == M)
				break;

			// 3. 사용자 이동
			userMove(userA, userMoveInfo[0][count]);
			userMove(userB, userMoveInfo[1][count]);
			count++;
		}

	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_5644.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken()); // 총 이동 시간
			A = Integer.parseInt(st.nextToken()); // BC의 개수
			userMoveInfo = new int[2][M];
			for (int i = 0; i < 2; i++) { // 0행: A, 1행: B
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < M; j++) {
					userMoveInfo[i][j] = Integer.parseInt(st.nextToken()); // 사용자 이동 정보
				}
			}

			BC = new ArrayList<>(); // 충전기 리스트
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				BC.add(new BCinfo(x, y, c, p));
			}
			
			answer = 0;
			move();
			sb.append("#"+tc+" "+answer+"\n");
		}
		System.out.println(sb.toString());
	}

}