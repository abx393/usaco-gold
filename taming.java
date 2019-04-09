import java.io.*;
import java.util.*;

public class taming {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("taming.in"));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] a = new int[n];
		for (int i=0; i<n; i++){
			a[i] = Integer.parseInt(st.nextToken());
		}
		br.close();
		int[][][] dp = new int[n][n][n];
		for (int[][] arr: dp) {
			for (int[] arr2: arr) Arrays.fill(arr2, Integer.MAX_VALUE);
		}
		
		for (int i=0; i<n; i++){
			if (a[i]==0) dp[i][i][0] = 0;
			else dp[i][i][0] = 1;
			for (int j=i+1; j<n; j++){
				if (a[j]==j-i) dp[i][j][0] = dp[i][j-1][0];
				else  dp[i][j][0] = dp[i][j-1][0]+1;
			}
		}
	
		for (int i=1; i<n; i++){
			for (int j=i; j<n; j++){ //endpoint
				for (int k=i-1; k<j; k++){ //midpoint
					dp[0][j][i] = Math.min(dp[0][k][i-1]+dp[k+1][j][0], dp[0][j][i]); 
				}
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));
		for (int i=0; i<n; i++) out.println(dp[0][n-1][i]);
		out.close();
		
	}
}
