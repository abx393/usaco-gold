import java.io.*;
import java.util.*;

public class checklist {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("checklist.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int h = Integer.parseInt(st.nextToken());
		int g = Integer.parseInt(st.nextToken());
		
		int[][] ho = new int[h][2];
		int[][] gu = new int[g][2];
		
		for (int i=0; i<h; i++){
			st = new StringTokenizer(br.readLine());
			ho[i][0] = Integer.parseInt(st.nextToken());
			ho[i][1] = Integer.parseInt(st.nextToken());
		}
		for (int i=0; i<g; i++){
			st = new StringTokenizer(br.readLine());
			gu[i][0] = Integer.parseInt(st.nextToken());
			gu[i][1] = Integer.parseInt(st.nextToken());
		}
		br.close();
		
		long[][][] dp = new long[h+1][g+1][2];
		for (long[][] i: dp){
			for (long[] j: i) Arrays.fill(j, 1L << 60);
		}
		dp[1][0][0] = 0;
		for (int i=0; i<=h; i++){
			for (int j=0; j<=g; j++){
				for (int k=0; k<2; k++){
					if (i==0 && k==0) continue;
					if (j==0 && k==1) continue;
					int[] point;
					if (k==0) point = ho[i-1];
					else point = gu[j-1];
					
					//going to the next holstein
					if (i<h) dp[i+1][j][0] = Math.min(dp[i+1][j][0], dp[i][j][k] + distSquared(point, ho[i]));
					if (j<g) dp[i][j+1][1] = Math.min(dp[i][j+1][1], dp[i][j][k] + distSquared(point, gu[j]));
				}
			}
		}
		//System.out.println(toString(dp));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("checklist.out")));
		out.println(dp[h][g][0]);
		out.close();
	}
	public static String toString(long[][][] arr){
		String res = "";
		for (long[][] i: arr){
			for (long[] j: i){
				res += (j[0] + "," + j[1]  + "  ");
			}
			res += "\n";
		}
		return res;
	}
	public static long distSquared(int[] p1, int[] p2){
		return (long) (Math.pow(p1[0]-p2[0], 2) + Math.pow(p1[1]-p2[1], 2));
	}
}
