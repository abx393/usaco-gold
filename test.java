
public class test {
	public static void main(String[] args) {
		///////////0, 1, 2, 3, 4
		int[] a = {1, 3, 5, 6, 9};
		System.out.println(bSearch(a, 2, 0, a.length-1));
	}
	public static int bSearch(int[] arr, int num, int min, int max){
		int mid=0;
		while (min<=max){
			mid = (min+max)/2;
			//System.out.println(min + " " + mid + " " + max);
			if (arr[mid]<num) {
				min = mid+1;
			} else if (arr[mid]>num){
				max = mid-1;
			} else {
				return mid;
			}
		}
		if (num>arr[mid]) return mid+1;
		else return mid;
	}
}
