import java.io.*;
import java.util.*;

public class radio {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("radio.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int x2 = Integer.parseInt(st.nextToken());
		int a2 = x2;
		int y2 = Integer.parseInt(st.nextToken());
		int b2 = y2;
		String path1 = br.readLine(), path2 = br.readLine();
		br.close();
		
		int[][] dp = new int[n+1][m+1];
		for (int[] i : dp) Arrays.fill(i, Integer.MAX_VALUE);
		dp[0][0] = 0;
		for (int i=0; i<=n; i++) {
			x2 = a2;
			y2 = b2;
			for (int j=0; j<=m; j++) {
				int energy = sqdist(x1, y1, x2, y2);
				if (i > 0 && j > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + energy);
				if (i > 0) dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + energy);
				if (j > 0) dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + energy);
				char move = ' ';
				if (j < m) move = path2.charAt(j);
				if (move == 'N') y2++;
				else if (move == 'S') y2--;
				else if (move == 'W') x2--;
				else x2++;
			}
			char move = ' ';
			if (i < n) move = path1.charAt(i);
			if (move == 'N') y1++;
			else if (move == 'S') y1--;
			else if (move == 'W') x1--;
			else x1++;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
		out.println(dp[n][m]);
		out.close();
	}

	public static int sqdist(int a, int b, int c, int d) {
		return (c-a) * (c-a) + (b-d) * (b-d);
	}
}
