public static void reverse(int[] arr) {
		int n = arr.length;
		for(int i = 0; i < n/2; i++) {
			int temp = arr[i];
			arr[i] = arr[n-1-i];
			arr[n-1-i] = temp;
		
		}
	}
	public static int[] sumEvenOdd(int[] arr){
		int a[] = {0,0};
		for(int i = 0; i < arr.length; i++) {
			if(i%2 == 0) {
				a[0] += arr[i];
			}else{
				a[1] += arr[i];
			}
		}
		return a;
	}
	public static double average(int[] arr) {
		double x = 0;
		int n = arr.length;
		for(int i = 0; i < n; i++) {
			x += (double)arr[i]/n;
		}
		return x;
	}
	public static void moveValue(int arr[], int val) {
		int n = arr.length;
		for(int i = 0; i < n; i++) {
			if(arr[i] == val) {
				for(int j = i; j < n-1 ; j++) {
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
				}
				i--;
				n--;
			}
		}
	}
	public static int [][] transpose(int[][] arr){
		int rows = arr.length;
		int cols = arr[0].length;
		int [][] temp = new int[cols][rows];
		for(int i=0; i<rows; ++i){
			for(int j=0; j<cols; ++j){
				temp[j][i] = arr[i][j];
			}
		}
		return temp;
	}
	public static long fib(int n) {
		long f1 = 0, f2 =1, ans = f1+f2;
		if(n == 1) {
			return 0;
		}else {
			for(int i = 0; i < n-2; i++) {
				ans = f2+f1;
				f1 = f2;
				f2 = ans;
			}
			return ans;
		}
	}
