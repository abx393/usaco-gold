import java.io.*;
import java.util.*;

public class sort {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("sort.in"));
		int n = Integer.parseInt(br.readLine());
		
		int[] a = new int[n];
		for (int i=0; i<n; i++){
			a[i] = Integer.parseInt(br.readLine());
		}
		br.close();
		
		boolean s = false;
		int count = 0;
		while (!s){
			count++;
			s = true;
			for (int i=0; i<n-1; i++){
				if (a[i+1]<a[i]){
					int tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
				}
			}
			for (int i=n-2; i>=0; i--){
				if (a[i+1]<a[i]){
					int tmp = a[i];
					a[i] = a[i+1];
					a[i+1] = tmp;
				}
			}
			for (int i=0; i<n-1; i++){
				if (a[i+1]<a[i]) s = false;
			}
		}
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
		out.println(count);
		out.close();		
	}
}
