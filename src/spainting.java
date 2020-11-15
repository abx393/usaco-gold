import java.util.*;
import java.io.*;

public class spainting {
	public static long MOD = 1000000007;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("spainting.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		br.close();
		
		long[] dp = new long[n+1];
		long[] ps = new long[n+1];
		dp[0] = 1;
		for (int i=1; i<k; i++) {
			dp[i] = (dp[i-1] * m + MOD) % MOD;
			ps[i] = (ps[i-1] + dp[i] + MOD) % MOD;
		}

		for (int i=k; i<=n; i++){
			dp[i] = (ps[i-1] - ps[i-k] + MOD) % MOD;
			dp[i] = (dp[i] * (m-1) + MOD) % MOD;
			ps[i] = (ps[i-1] + dp[i] + MOD) % MOD;
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("spainting.out")));
		out.println((pow(m, n) - dp[n] + MOD) % MOD);
		out.close();
	}

	public static long pow(long base, long exp) {
		long ans = 1;
		for (long i = 0; i < exp; i++) {
			ans = (ans * base + MOD) % MOD;
		}
		return ans;
	}
}
