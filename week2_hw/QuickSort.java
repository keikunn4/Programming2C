class QuickSort {
	public static int[] sort(int[] data, int left, int right) {
		//*************
		if (right-left==0){return(data);}
		else{
			int n_pivot = left+(right-left)/2;
			int pivot = data[n_pivot];
			int l=left;
			int r=right;
			while(l<r){
				while(pivot>data[l] && l<r){l+=1;}
				while(pivot<data[r] && l<r){r-=1;}
				int temp = data[r];
				data[r] = data[l];
				data[l] = temp;
			}
			if(l==right){
				sort(data,left,right-1);
			}
			else if(l==left){
				sort(data,left+1,right);
			}
			else if(data[l]>pivot){
				sort(data,left,l-1);
				sort(data,l,right);
			}
			else{
				sort(data,left,l);
				sort(data,l+1,right);
			}

		}
		
		return(data);
	}
}
