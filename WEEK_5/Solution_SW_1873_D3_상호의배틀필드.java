
import java.io.*;
import java.util.StringTokenizer;

public class Solution_SW_1873_D3_상호의배틀필드 {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int H = Integer.parseInt(st.nextToken());
			int W = Integer.parseInt(st.nextToken());
			String[][] map = new String[H][W];

			int x = 0;
			int y = 0; // 전차 초기 위치

			for (int i = 0; i < H; i++) {
				String str = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = str.substring(j, j + 1);
					if (map[i][j].equals("^") || map[i][j].equals("v") || map[i][j].equals("<") || map[i][j].equals(">")) {
						x = j;
						y = i;
					}
				}
			}

			int N = Integer.parseInt(br.readLine());
			String str = br.readLine();

			for (int i = 0; i < N; i++) {
				int tmp_x = 0, tmp_y = 0; // 이동시킬 위치 변수

				switch (str.substring(i, i + 1)) {
				case "U": // 입력이 U이면
					map[y][x] = "^"; // 전차가 바라보는 방향을 위로 바꿈
					tmp_y = y - 1; // 한 칸 위
					if (tmp_y >= 0 && map[tmp_y][x].equals(".")) {
						map[tmp_y][x] = "^";
						map[y][x] = "."; //이동
						y = tmp_y; // 전차 위치 갱신
					}
					break;
				case "D":
					map[y][x] = "v";
					tmp_y = y + 1;
					if (tmp_y < H && map[tmp_y][x].equals(".")) {
						map[tmp_y][x] = "v";
						map[y][x] = ".";
						y = tmp_y;
					}
					break;
				case "L":
					map[y][x] = "<";
					tmp_x = x - 1;
					if (tmp_x >= 0 && map[y][tmp_x].equals(".")) {
						map[y][tmp_x] = "<";
						map[y][x] = ".";
						x = tmp_x;
					}
					break;
				case "R":
					map[y][x] = ">";
					tmp_x = x + 1;
					if (tmp_x < W && map[y][tmp_x].equals(".")) {
						map[y][tmp_x] = ">";
						map[y][x] = ".";
						x = tmp_x;
					}
					break;
					
				case "S": // 포탄 발사
					switch (map[y][x]) {
					case "^": // 방향: 위
						for (int j = y - 1; j >= 0; j--) {
							if (map[j][x].equals("*")) { // 벽돌로 만들어진 벽이면
								map[j][x] = "."; // 그 자리는 평지가 된다
								break;
							} else if (map[j][x].equals("#")) { // 강철로 만들어진 벽이면
								break; // 아무일도 일어나지 않음
							}
						}
						break;
					case "v":
						for (int j = y + 1; j < H; j++) {
							if (map[j][x].equals("*")) {
								map[j][x] = ".";
								break;
							} else if (map[j][x].equals("#")) {
								break;
							}
						}
						break;
					case "<":
						for (int j = x - 1; j >= 0; j--) {
							if (map[y][j].equals("*")) {
								map[y][j] = ".";
								break;
							} else if (map[y][j].equals("#")) {
								break;
							}
						}
						break;
					case ">":
						for (int j = x + 1; j < W; j++) {
							if (map[y][j].equals("*")) {
								map[y][j] = ".";
								break;
							} else if (map[y][j].equals("#")) {
								break;
							}
						}
						break;
					}
					break;
				}
			}

			StringBuilder sb = new StringBuilder();
			sb.append("#" + tc + " ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
		br.close();
	}
}