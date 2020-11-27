import java.util.*;
import java.io.*;

public class hps {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[] moves = new char[n];
		for (int i = 0; i < n; i++) moves[i] = br.readLine().charAt(0);
		br.close();
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('H', 0);
		map.put('P', 1);
		map.put('S', 2);
		
		long[][][] dp = new long[n+1][k+1][3];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= k; j++) {
				for (int x = 0; x < 3; x++) {
					if (j == 0) {
						dp[i][j][x] = dp[i-1][j][x] + (x == map.get(moves[i-1]) ? 1 : 0);
					} else {
						int y = (x+1) % 3;
						int z = (x+2) % 3;
						dp[i][j][x] = max(dp[i-1][j][x], dp[i-1][j-1][y], dp[i-1][j-1][z]) +
                                      (x == map.get(moves[i-1]) ? 1 : 0);
					}
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		out.println(max(dp[n][k][0], dp[n][k][1], dp[n][k][2]));
		out.close();
	}

	public static long max(long x, long y, long z){
		return Math.max(x, Math.max(y, z));
	}
}
