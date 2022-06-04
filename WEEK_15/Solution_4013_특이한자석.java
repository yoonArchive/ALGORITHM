import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_4013_특이한자석 {
	static final int RIGHT = 2;
	static final int LEFT = 6;
	static int K, rotateCnt;
	static int[][] magnet;
	static Queue<RotateMagnet> RotateMagnetQ;

	public static class RotateMagnet {
		int id, dir;

		public RotateMagnet(int id, int dir) {
			super();
			this.id = id;
			this.dir = dir;
		}

	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("4013.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine()); // 회전 수
			magnet = new int[5][8];

			StringTokenizer st = null;
			for (int i = 1; i <= 4; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			RotateMagnetQ = new LinkedList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int magnetNum = Integer.parseInt(st.nextToken());
				int rotateDir = Integer.parseInt(st.nextToken());

				searchLeft(magnetNum - 1, rotateDir, magnet[magnetNum][LEFT]);
				searchRight(magnetNum + 1, rotateDir, magnet[magnetNum][RIGHT]);

				rotate(magnetNum, rotateDir);
				while (!RotateMagnetQ.isEmpty()) {
					RotateMagnet r = RotateMagnetQ.poll();
					rotate(r.id, r.dir);
				}
			}

			int sum = 0;
			int mul = 1;
			for (int i = 1; i < 5; i++) {
				sum += mul * magnet[i][0];
				mul *= 2;
			}
			
			sb.append("#" + tc + " " + sum).append("\n");
		}
		System.out.println(sb.toString());
	}

	private static void searchLeft(int magnetNum, int rotateDir, int leftVal) { 
		if (magnetNum >= 1 && leftVal != magnet[magnetNum][RIGHT]) {
			int leftDir = rotateDir * -1;
			RotateMagnetQ.offer(new RotateMagnet(magnetNum, leftDir));
			searchLeft(magnetNum - 1, leftDir, magnet[magnetNum][LEFT]);
		}
	}

	private static void searchRight(int magnetNum, int rotateDir, int rightVal) {
		if (magnetNum <= 4 && rightVal != magnet[magnetNum][LEFT]) {
			int rightDir = rotateDir * -1;
			RotateMagnetQ.offer(new RotateMagnet(magnetNum, rightDir));
			searchRight(magnetNum + 1, rightDir, magnet[magnetNum][RIGHT]);
		}
	}

	private static void rotate(int magnetNum, int rotateDir) {
		if (rotateDir == -1) { // 반시계방향
			int tmp = magnet[magnetNum][0];
			for (int i = 0; i < 7; i++) {
				magnet[magnetNum][i] = magnet[magnetNum][i + 1];
			}
			magnet[magnetNum][7] = tmp;
		} else { // 시계방향
			int tmp = magnet[magnetNum][7];
			for (int i = 7; i >= 1; i--) {
				magnet[magnetNum][i] = magnet[magnetNum][i - 1];
			}
			magnet[magnetNum][0] = tmp;
		}
	}

}
