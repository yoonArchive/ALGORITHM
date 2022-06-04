import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_Jungol_2557_회전초밥고 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());

		int[] dishes = new int[n + k - 1];
		int[] count = new int[d + 1];
		HashSet<Integer> set = new HashSet<>();

		int max = 0;
		for (int i = 0; i < n; i++)
			dishes[i] = Integer.parseInt(br.readLine());

		for (int i = n, length = dishes.length; i < length; i++)
			dishes[i] = dishes[i % n];

		set.add(c);
		count[c]++; // 쿠폰으로 먹는 초밥

		for (int i = 0; i < k; i++) { // 초기 세팅
			count[dishes[i]]++;
			set.add(dishes[i]);
		}
		max = Math.max(max, set.size());

		for (int i = k, length = dishes.length; i < length; i++) {
			count[dishes[i]]++;
			set.add(dishes[i]);
			count[dishes[i - k]]--;
			if (count[dishes[i - k]] == 0)
				set.remove(dishes[i - k]);
			max = Math.max(max, set.size());
		}

		bw.write(Integer.toString(max));
		br.close();
		bw.flush();
		bw.close();
	}

}