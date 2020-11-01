//2017 US Open Contest
//Problem 3. Modern Art

import java.io.*;
import java.util.*;

public class art2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("art2.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("art2.out")));
		
		int n = Integer.parseInt(br.readLine());
		int[] mins = new int[n+1];
		int[] maxs = new int[n+1];
		int[] m = new int[n];
		for (int i=0; i<n; i++){
			m[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(mins, -1);
		Arrays.fill(maxs, -1);
		
		br.close();
		for (int i=0; i<n; i++){
			int x = m[i];
			if (mins[x]==-1) mins[x] = i;
		}
		
		for (int i=n-1; i>=0; i--){
			int x = m[i];
			if (maxs[x]==-1) maxs[x] = i;
		}
		
		Stack<Integer> s = new Stack<Integer>();
		int res = 0;
		boolean b = false;
		for (int i=0; i<n; i++) {
			if (mins[m[i]]==i) {
				if (m[i]==0 && !s.isEmpty()){
					res = -1;
					break;
				}
				s.push(m[i]);
				if (m[i]==0) b = true;
				res = Math.max(res, s.size() + (b ? -1:0));
			}
			if (s.peek()!=m[i]) {
				res = -1;
				break;
			}
			if (maxs[m[i]]==i) {
				s.pop();
				b = false;
			}
		}
		out.println(res);
		out.close();
	}
}
