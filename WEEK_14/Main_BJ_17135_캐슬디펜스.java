import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_BJ_17135_캐슬디펜스 {
	final static int BLANK = 0;
	final static int ENEMY = 1;
	static int N, M, D, enemyCnt, curEnemyCnt, maxAttackedCnt;
	static int board[][], copyBoard[][];
	static int defenderLoc[];
	static ArrayList<Enemy> enemyLoc, copyEnemyLoc;

	public static class Enemy implements Comparable<Enemy> {
		int r, c, distance;

		public Enemy(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		public Enemy(int r, int c, int distance) {
			super();
			this.r = r;
			this.c = c;
			this.distance = distance;
		}

		@Override
		public int hashCode() {
			return r;
		}

		@Override
		public boolean equals(Object obj) {
			Enemy enemy = (Enemy) obj;
			if (enemy.r == this.r && enemy.c == this.c)
				return true;
			return false;
		}

		@Override
		public int compareTo(Enemy o) {
			if (this.distance == o.distance)
				return this.c - o.c;
			return this.distance - o.distance;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		enemyLoc = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < M; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				board[i][j] = tmp;
				if (tmp == ENEMY) {
					enemyCnt++;
					enemyLoc.add(new Enemy(i, j)); // 초기 적의 위치 저장
				}
			}
		}
		defenderLoc = new int[3];
		setDefender(0, 0);
		bw.write(Integer.toString(maxAttackedCnt));
		br.close();
		bw.flush();
		bw.close();

	}

	private static void setDefender(int count, int start) {
		if (count == 3) {
			castleDefense();
			return;
		}
		for (int i = start; i < M; i++) {
			defenderLoc[count] = i;
			setDefender(count + 1, i + 1);
		}

	}

	private static void castleDefense() {
		boolean isGameEnd = false;
		int attackedCnt = 0;
		copyBoard = new int[N][M];
		copyBoard = copy();
		copyEnemyLoc = new ArrayList<>();
		copyEnemyLoc.addAll(enemyLoc);
		curEnemyCnt = enemyCnt;
		HashSet<Enemy> enemySet = new HashSet<>();

		while (true) {
			if (isGameEnd)
				break;
			for (int i = 0; i < 3; i++) {
				int loc = defenderLoc[i]; // 궁수의 위치
				Enemy target = getTarget(loc);
				if (target.r == -1 && target.c == -1) // 공격할 수 있는 적이 없는 경우
					continue;
				enemySet.add(target);
			}
			Iterator<Enemy> iter = enemySet.iterator();
			while (iter.hasNext()) {
				Enemy p = iter.next();
				copyEnemyLoc.remove(copyEnemyLoc.indexOf(p));
				copyBoard[p.r][p.c] = BLANK;
				curEnemyCnt--;
				attackedCnt++;
			}
			enemySet.clear();
			moveDown();
			if (curEnemyCnt == 0) {
				isGameEnd = true;
				break;
			}
		}
		maxAttackedCnt = Math.max(maxAttackedCnt, attackedCnt);
	}

	private static void moveDown() {
		for (int i = copyEnemyLoc.size() - 1; i >= 0; i--) {
			Enemy e = copyEnemyLoc.get(i);
			int r = e.r;
			int c = e.c;
			if (r == N - 1) { // 한 칸 아래가 성이 있는 칸이라면 게임에서 제외
				copyEnemyLoc.remove(copyEnemyLoc.indexOf(e));
				curEnemyCnt--;
				copyBoard[r][c] = BLANK;
				continue;
			}
			copyEnemyLoc.set(i, new Enemy(r + 1, c));
			copyBoard[r][c] = BLANK;
			copyBoard[r + 1][c] = ENEMY;
		}
	}

	private static Enemy getTarget(int defenderC) {
		PriorityQueue<Enemy> pq = new PriorityQueue<>();
		for (Enemy e : copyEnemyLoc) {
			int r = e.r;
			int c = e.c;
			int dist = getDistance(N, defenderC, r, c);
			if (dist <= D)
				pq.offer(new Enemy(r, c, dist));
		}

		if (pq.size() == 0) // pq가 비어있다는 것은 거리가 D 이하인 적이 없다는 것을 의미
			return new Enemy(-1, -1);

		// 거리가 D 이하인 적 중 가장 가까운 적을 찾는다.
		Enemy target = pq.poll();
		return new Enemy(target.r, target.c);
	}

	private static int getDistance(int dr, int dc, int tr, int tc) {
		return Math.abs(dr - tr) + Math.abs(dc - tc);
	}

	private static int[][] copy() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copyBoard[i][j] = board[i][j];
			}
		}
		return copyBoard;
	}

}
