import java.io.*;
import java.util.*;

public class bphoto {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("bphoto.in"));
		int n = Integer.parseInt(br.readLine());
		State[] cows = new State[n];
		for (int i=0; i<n; i++){
			cows[i] = new State(i, Integer.parseInt(br.readLine()));
		}
		br.close();
		Arrays.sort(cows);
		BIT bit = new BIT(n);
		int res = 0;
		
		int left=0, right=0;
		for (int i=0; i<n; i++){
			State s = cows[i];
			left = bit.query(s.index-1);
			right = i - left;
			res += (2*Math.min(left, right)<Math.max(left, right)) ? 1:0;
			bit.update(s.index, 1);
		}
		
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bphoto.out")));
		out.println(res);
		out.close();
	}
}
class State implements Comparable<State>{
	public int index, val;
	public State(int a, int b){
		index = a;
		val = b;
	}
	public int compareTo(State that){
		return that.val-this.val;
	}
}
class BIT {
	public int[] sum;
	public int n;
	public BIT(int n) {
		sum = new int[n+1];
		this.n = n;
	}
	public void update(int index, int val){
		index++;
		while (index<=n){
			sum[index] += val;
			index += index & -index;
		}
	}
	public int query(int index){
		index++;
		int res = 0;
		while (index>0) {
			res += sum[index];
			index -= index & -index;
		}
		return res;
	}
}
