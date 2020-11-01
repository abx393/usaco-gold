import java.io.*;
import java.util.*;
	
public class cbarn2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cbarn2.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		long[] a = new long[n];
		for (int i=0; i<n; i++) a[i] = Long.parseLong(br.readLine());
		br.close();
		
		long[][] dp = new long[n][k];
		
		long[] orig = new long[n];
		for (int i=0; i<n; i++) orig[i] = a[i];
		
		long res = Long.MAX_VALUE;
		for (int start = 0; start<n; start++){ 
			for (int i=0; i<n; i++) Arrays.fill(dp[i], Long.MAX_VALUE);
			for (int i=0; i<n; i++) a[i] = orig[(start+i)%n];
			
			long sum = 0;
			long ps = 0; //prefix sum
			for (int i=n-1; i>=0; i--){
				dp[i][0] = ps;
				sum += a[i];
				ps += sum;
			}
			
			for (int i=1; i<k; i++){
				for (int j=n-1; j>0; j--){
					sum = 0;
					ps = 0;
					for (int b=j-1; b>=0; b--){
						dp[b][i] = Math.min(dp[b][i], dp[j][i-1]+ps);
						sum += a[b];
						ps += sum;
					}
				}
			}
			res = Math.min(res, dp[0][k-1]);
		}
				
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cbarn2.out")));
		out.println(res);
		out.close();
	}
}
