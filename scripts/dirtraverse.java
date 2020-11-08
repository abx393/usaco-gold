import java.io.*;
import java.util.*;
	
public class dirtraverse {
	public static long filedist = 0;
	public static long[] dist;
	public static ArrayList<Integer> files;
	public static boolean[] isf;
	public static ArrayList<Integer>[] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("dirtraverse.in"));
		int n = Integer.parseInt(br.readLine());
		files = new ArrayList<Integer>();
		isf = new boolean[n];
		adj = new ArrayList[n];
		for (int i = 0; i < n; i++) adj[i] = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			int sz = Integer.parseInt(st.nextToken());
			if (sz == 0) {
				files.add(i);
				isf[i] = true;
			} else {
				for (int j = 0; j < sz; j++) {
					int idx = Integer.parseInt(st.nextToken()) - 1;
					
					// ADD REAL FILE PATH LENGTH
					adj[idx].add(i);
				}
			}
		}
		br.close();
		
		dist = new long[n];
		long min = Long.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			if (isf[i]) continue;
			Arrays.fill(dist, Long.MAX_VALUE);
			dist[i] = 0;
			filedist = 0;
			dfs(i);
			for (int f : files) {
				filedist += dist[f];
			}
			min = Math.min(min, filedist);
		}
		System.out.println(min);
	}

	public static void dfs(int idx) {
		for (int i : adj[idx]) {
			if (dist[i] == Long.MAX_VALUE) {
				dist[i] = Math.min(dist[i], dist[idx] + 1);
				dfs(i);
			}
		}
	}
}
