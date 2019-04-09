import java.io.*;
import java.util.*;

public class hps3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("hps.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] moves = new int[n];
		for (int i=0; i<n; i++){
			char c = br.readLine().charAt(0);
			if (c=='P') moves[i] = 1;
			if (c=='S') moves[i] = 2;
		}
		//System.out.println(Arrays.toString(moves));
		br.close();
		
		int[][][] dp = new int[n+1][k+1][3];
		dp[1][0][moves[0]] = 1;
		for (int i=2; i<n+1; i++){
			int m = moves[i-1];
			for (int j=0; j<=k; j++){
				for (int a=0; a<3; a++){
					//System.out.println("check");
					dp[i][j][a] = Math.max(dp[i][j][a], dp[i-1][j][a]);
					if (j>0) dp[i][j][a] = Math.max(dp[i-1][j-1][(a+1)%3], dp[i][j][a]);
					if (j>0) dp[i][j][a] = Math.max(dp[i-1][j-1][(a+2)%3], dp[i][j][a]);
					dp[i][j][a] += (a==m ? 1: 0);
				}
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
		out.println(Math.max(Math.max(dp[n][k][0], dp[n][k][1]), dp[n][k][2]));
		out.close();
	}
}
