import java.io.*;
import java.util.Arrays;

public class twofoureight {
	public static int[] nums;
	public static int[][] dp;
	public static int max = 0; 

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("248.in"));
		int n = Integer.parseInt(br.readLine());
		nums = new int[n];
		for (int i=0; i<n; i++) nums[i] = Integer.parseInt(br.readLine());
		br.close();
		dp = new int[n][n];
		for (int i=0; i<n; i++) Arrays.fill(dp[i], -1);
		
		solve(0, n-1);
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
		out.println(max);
		out.close();
	}

	public static int solve(int in, int out){
		if (in == out) return nums[in];
		if (dp[in][out] != -1) return dp[in][out];
		for (int i=in; i<out; i++){
			int x = solve(in, i);
			int y = solve(i+1, out);
			if (x == 0 || y == 0) continue;
			if (x != y) continue;
			dp[in][out] = x+1;
			max = Math.max(max, x+1);
			return x+1;
		}
		dp[in][out] = 0;
		return 0;
	}
}
