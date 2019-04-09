import java.util.*;
import java.io.*;
	
public class barnpainting {
	public static int n, k;
	public static ArrayList<Integer>[] adj;
	public static long[][] dp;
	public static boolean[] seen;
	public static long MOD = 1000000007L;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("barnpainting.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		adj = new ArrayList[n];
		for (int i=0; i<n; i++) adj[i] = new ArrayList<Integer>();
		
		for (int i=0; i<n-1; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			
			adj[a].add(b);
			adj[b].add(a);
		}
		
		dp = new long[n][3];
		for (long[] a: dp) Arrays.fill(a, -1);
		for (int i=0; i<k; i++){
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			dp[b][(c+1)%3] = dp[b][(c+2)%3] = 0;
		}
		br.close();
		long res = 1;
		seen = new boolean[n];
		for (int i=0; i<n; i++){
			if (seen[i]) continue;
			long sum = (solve(i, 0, i)+MOD)%MOD;
			sum = (sum+solve(i, 1, i)+MOD)%MOD;
			sum = (sum+solve(i, 2, i)+MOD)%MOD;
			
			res = (res*sum+MOD)%MOD;
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("barnpainting.out")));
		out.println(res);
		out.close();
	}
	public static long solve(int v, int c, int p){
		if (dp[v][c]>=0) {
			return dp[v][c];
		}
		seen[v] = true;
		long ans = 1;
		for (int i: adj[v]) {
			if (i==p) continue;
			long sum = solve(i, (c+1)%3, v);
			sum = (sum+solve(i,(c+2)%3, v)+MOD)%MOD;
			ans = (ans*sum+MOD)%MOD;
		}
		dp[v][c] = ans;
		return dp[v][c];
	}
}
