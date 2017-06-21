public class Stack {
	private static final int DEFAULT_INIT_SIZE = 16;

	private int capacity;
	private int size;
	private String[] dataSet;

	public Stack() {
		this(DEFAULT_INIT_SIZE);
	}

	public Stack(int initSize) {
		capacity = initSize;
		size = 0;
		dataSet = new String[capacity];
	}

	public boolean isEmpty() {
		
		// ここにisEmptyの処理を書いてください
		return size==0;
	}

	public void push(String s) {
		// ここにpushの処理を書いてください
		if(size==capacity){
			doubleCapacity();
			dataSet[size]=s;
		}
		dataSet[size]=s;
		size = size+1;
	}

	public String pop() {
		
		// ここにpopの処理を書いてください
		if(isEmpty()==true){
			return"";
		}
		size = size-1;
		//System.out.println(dataSet[size]);
		return dataSet[size];
	}

	public String peek() {
		
		// ここにpeekの処理を書いてください
		if(isEmpty()==true){
			return"";
		}
		//System.out.println(dataSet[size-1]);
		return dataSet[size-1];
	}

	private void doubleCapacity() {
		
		// ここにdoubleCapacityの処理を書いてください
		
		String[] tmp = new String[capacity];
		capacity = capacity*2;
		System.arraycopy(dataSet,0,tmp,0,dataSet.length);
		dataSet = new String[capacity];
		for(int i=0; i<capacity/2; i++){
			dataSet[i] = tmp[i];
		}
		System.out.print("capacity: ");System.out.print(capacity/2);System.out.print(" -> ");System.out.println(capacity);
	}
	public void print(){
		System.out.print("[");
		for(int i=0; i<size; i++){
			System.out.print(dataSet[i]);
			System.out.print(" ");
		}
		System.out.println("]");
		System.out.print("size");System.out.println(size);
		System.out.print("capacity");System.out.println(capacity);
	}

}
