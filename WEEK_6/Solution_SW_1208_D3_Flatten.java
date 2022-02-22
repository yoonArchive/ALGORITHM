import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_SW_1208_D3_Flatten_박기윤 {
	static int height[];

	public static int[] find_minmax() {
		int index_arr[] = new int[2];
		int min = 101;
		int max = 0;

		for (int i = 0; i < 100; i++) {
			if (height[i] < min) {
				index_arr[0] = i;
				min = height[i];
			}
			if (height[i] > max) {
				index_arr[1] = i;
				max = height[i];
			}
		}
		return index_arr;
	}

	public static int dump(int count) {
		int min_index = 0;
		int max_index = 0;
		for (int i = 0; i <= count; i++) {
			min_index = find_minmax()[0];
			max_index = find_minmax()[1];
			if (i == count)
				break;
			height[min_index] += 1;
			height[max_index] -= 1;
		}
		return (height[max_index] - height[min_index]);
	}

	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			int count = Integer.parseInt(br.readLine());
			height = new int[100];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				height[i] = Integer.parseInt(st.nextToken());
			}
			sb.append("#" + tc + " " + dump(count)+"\n");
		}
		System.out.println(sb.toString());
	}
}
