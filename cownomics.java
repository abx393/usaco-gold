import java.io.*;
import java.util.*;

public class cownomics {
	public static final long MOD = 1000000007L;
	public static void main(String[] args) throws IOException {
		int base = 256;
		BufferedReader br = new BufferedReader(new FileReader("cownomics.in"));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cownomics.out")));
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		char[][] spotty = new char[n][m];
		char[][] plain = new char[n][m];
		long[] r = new long[n];
		
		for (int i=0; i<n; i++) spotty[i] = br.readLine().toCharArray();
		for (int i=0; i<n; i++) plain[i] = br.readLine().toCharArray();
		//System.out.println(Arrays.deepToString(spotty));
		//System.out.println(Arrays.deepToString(plain));
		for (int i=0; i<n; i++) r[i] = (long) Math.random() * 1000000000;
		
		br.close();
		//System.out.println(hash("TA", base));
		long[] hash1 = new long[n];
		long[] hash2 = new long[n];
		
		
		for (int i=0; i<n; i++){
			long hash = 0;
			for (int k=0; k<m; k++){
				hash += r[i] * spotty[i][k];
				hash1[k] = h;
			}
			
			out.println(len);
			out.close();
			return;
	
		}
	}
	public static long hash(String s, int base){
		long hash = 0;
		for (int j=0; j<s.length(); j++){
			hash += (s.charAt(j) * (long) Math.pow(base, s.length()-j-1))%MOD;
		}
		hash %= MOD;
		return hash;
	}
}
