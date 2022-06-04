import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main_BJ_2636_치즈 {
	final static int AIR = -1;
	final static int BLANK = 0;
	final static int CHEESE = 1;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] board;
	static int R, C;
	static int remainCheese;

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.setIn(new FileInputStream("2636.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		Queue<Integer[]> cheeseQ = new LinkedList<>();

		int time = 0;
		while (true) {
			findAir();
			findCheeseEdge(cheeseQ);
			if (cheeseQ.isEmpty())
				break;
			meltCheese(cheeseQ);
			time++;
			reset();
		}

		sb.append(time).append("\n").append(remainCheese);
		System.out.println(sb.toString());
		br.close();
	}

	private static void meltCheese(Queue<Integer[]> cheeseQ) {
		int cnt = 0;
		while (!cheeseQ.isEmpty()) {
			Integer[] pair = cheeseQ.poll();
			board[pair[0]][pair[1]] = BLANK;
			cnt++;
		}
		remainCheese = cnt;
	}

	private static void findCheeseEdge(Queue<Integer[]> cheeseQ) {

		for (int x = 0; x < R; x++) {
			for (int y = 0; y < C; y++) {
				if (board[x][y] != CHEESE)
					continue;
				boolean isContacted = false;
				for (int i = 0; i < 4; i++) {
					int curX = x + dx[i];
					int curY = y + dy[i];
					if (board[curX][curY] == AIR) {
						isContacted = true;
						break;
					}
				}
				if (isContacted) {
					cheeseQ.add(new Integer[] { x, y });
				}
			}
		}

	}

	private static void findAir() {
		Queue<Integer[]> airQ = new LinkedList<>();
		airQ.add(new Integer[] { 0, 0 });
		board[0][0] = AIR;

		while (!airQ.isEmpty()) {
			Integer[] pair = airQ.poll();
			int x = pair[0];
			int y = pair[1];

			for (int i = 0; i < 4; i++) {
				int curX = x + dx[i];
				int curY = y + dy[i];
				if (curX < 0 || curY < 0 || curX >= R || curY >= C)
					continue;
				if (board[curX][curY] == BLANK) {
					board[curX][curY] = AIR;
					airQ.add(new Integer[] { curX, curY });
				}
			}
		}
	}

	private static void reset() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == AIR) {
					board[i][j] = BLANK;
				}
			}
		}
	}

}