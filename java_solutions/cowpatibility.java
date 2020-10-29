import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class cowpatibility {
	public static HashMap<ArrayList<Integer>, Long> freq;
	public static ArrayList<ArrayList<Integer>> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("cowpatibility.in"));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][5];
		StringTokenizer st;
		for (int i=0; i<n; i++){
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<5; j++){
				int num = Integer.parseInt(st.nextToken());
				arr[i][j] = num;
			}
		}
		br.close();
		
		freq = new HashMap<ArrayList<Integer>, Long>();
		for (int i=0; i<arr.length; i++){
			int[] s = arr[i];
			list = new ArrayList<ArrayList<Integer>>();
			list.add(new ArrayList<Integer>());
			Arrays.sort(s);
			add(s, 0);
		}
		
		long count = 0;
		for (ArrayList<Integer> a: freq.keySet()){
			long num = freq.get(a);
			if (a.size()==1 || a.size()==3 || a.size()==5) {
				count += (num*(num-1))/2;
				//if (num!=0) System.out.println(a + " " + num);
			}
			else if (a.size()==2 || a.size()==4) {
				count -= (num*(num-1)/2);
				//if (num!=0) System.out.println(a + " " + num);
			}
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
		out.println((long) n*(long)(n-1)/2 - count);
		out.close();
	}
	public static void add(int[] s, int x){
		if (x==5) {
			for (ArrayList<Integer> b: list){
				if (b.size()==0) continue;
				if (freq.containsKey(b)){
					freq.put(b, freq.get(b)+1);
				}
				else freq.put(b, 1L);
			}
			return;
		}
		ArrayList<Integer> a = new ArrayList<Integer>();
		int sz = list.size();
		for (int i=0; i<sz; i++){
			a = new ArrayList<Integer>();
			for (int j=0; j<list.get(i).size(); j++) {
				a.add(list.get(i).get(j));
			}
			a.add(s[x]);
			list.add(a);
		}
		add(s, x+1);
	}
}
