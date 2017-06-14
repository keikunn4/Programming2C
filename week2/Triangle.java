public class Triangle {
	
	public static int triangle(int n){
		if(n==1){   // 基底条件．この場合だけ再帰しない．
			return  1;
			}
		else{
			return(n + triangle(n-1));
	      }
	}

}
