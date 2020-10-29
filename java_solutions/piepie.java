import java.io.*;
import java.util.*;

public class piepie {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("piepie.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("piepie.out")));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int[] b = new int[n]; 
		Stat[] bsort = new Stat[n];
		int[] e = new int[n]; 
		Stat[] esort = new Stat[n];
		
		for (int i=0; i<2*n; i++){
			st = new StringTokenizer(br.readLine());
			if (i>=n) {
				b[i-n] = Integer.parseInt(st.nextToken());
				esort[i-n] = new Stat(i, Integer.parseInt(st.nextToken()));	
			}
			
			else {
				bsort[i] = new Stat(i, Integer.parseInt(st.nextToken()));
				e[i] = Integer.parseInt(st.nextToken());
			}
		}
		br.close();
		Arrays.sort(esort);
		Arrays.sort(bsort);
		
		
		LinkedList<Integer> q = new LinkedList<Integer>();

		for (int start=0; start<n; start++){
			q = new LinkedList<Integer>();
			q.add(start);
			
			boolean[] used = new boolean[2*n];
			long[] dist = new long[2*n];
			long minDist = Long.MAX_VALUE;
			used[start] = true;
			dist[start] = 1;
			
			while (!q.isEmpty()){
				int gift = q.removeFirst();
				if ((gift<n && esort[gift].val==0) || (gift>=n && bsort[gift].val==0)) {
					minDist = Math.min(minDist, dist[gift2]);
				}
				int min=0, max=0;
				if (gift2<n) {
					max = bSearch(esort, esort[gift].val+d, 0, 2*n);	
					for (int i=min; i<=max; i++){
						int j = esort[i].idx;
						if (i==max && esort[i].val>e[gift]+d) continue;
						//System.out.print("check");
						if (used[j]) continue;
						dist[j] = dist[gift]+1;
						used[j] = true;
						q.add(i);
					}
				} else {
					max = bSearch(bsort, bsort[gift].val+d, 0, 2*n);	
					for (int i=min; i<=max; i++){
						
						int j = bsort[i].idx;
						if (i==max && bsort[i].val>b[gift]+d) continue;
						//System.out.print("check");
						if (used[j]) continue;
						dist[j] = dist[gift]+1;
						used[j] = true;
						q.add(i);
					}
				}
				
				//System.out.println(Arrays.toString(dist));
			}
			minDist = (minDist==Integer.MAX_VALUE ? -1: minDist);
			out.println(minDist);
		}
		
		out.close();
	}
	public static int bSearch(Stat[] arr, int num, int min, int max){
		int mid=0;
		while (min<=max){
			mid = (min+max)/2;
			if (arr[mid].val<num) {
				min = mid+1;
			} else if (arr[mid].val>num){
				max = mid-1;
			} else {
				return mid;
			}
		}
		if (num>arr[mid].val) return mid+1;
		else return mid;
	}
}
class Stat implements Comparable<Stat> {
	public int idx, val;
	public Stat(int a, int b){
		idx = a;
		val = b;
	}
	public int compareTo(Stat that){
		return this.val - that.val;
	}
}
