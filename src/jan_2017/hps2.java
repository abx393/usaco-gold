import java.io.*;
import java.util.*;

public class hps2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		char[] moves = new char[n];
		for (int i=0; i<n; i++) {
			char c = br.readLine().charAt(0);
			if (c=='H') moves[i] = 0;
			if (c=='P') moves[i] = 1;
			if (c=='S') moves[i] = 2;
		}
		br.close();
		
		int[][][] dp = new int[n+1][k+1][3];
		for (int i=1; i<=n; i++) {
			for (int j=0; j<=k; j++) {
				for (int last=0; last<3; last++) {
					int x = (last + 1) % 3;
					int y = (x+1) % 3;
					if (j > 0) dp[i][j][last] = Math.max(dp[i-1][j-1][x], dp[i-1][j-1][y]) + (last == moves[i-1] ? 1 : 0);
					dp[i][j][last] = Math.max(dp[i][j][last], dp[i-1][j][last] + (last == moves[i-1] ? 1 : 0));
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		out.println(Math.max(Math.max(dp[n][k][0], dp[n][k][1]), dp[n][k][2]));
		out.close();
	}
}
