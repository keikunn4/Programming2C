public class Queue {
	private int capacity;
	private int size;
	private int front;
	private int rear;
	private String[] dataSet;

	public Queue(int initSize) {
		capacity = initSize;
		size = 0;
		front = 0;
		rear = -1;
		dataSet = new String[capacity];
	}

	public boolean isEmpty() {
		
		// ここにisEmptyの処理を書いてください
		return size==0;
	}

	public void insert(String s) {
		
			// ここにinsertの処理を書いてください
			if(size==capacity){
				doubleCapacity();
			}
			dataSet[(rear+1)%capacity]=s;
			size = size+1;
			rear = (rear+1)%capacity;
		
	}

	public String remove() {
		if(isEmpty()==true){
			return"";
		}
		size = size-1;
		String ans = dataSet[front];
		System.out.println(ans);
		front = (front+1)%capacity;
		return ans;
		// ここにremoveの処理を書いてください
		
	}

	public String peek() {
		if(isEmpty()==true){
			return"";
		}
		System.out.println(dataSet[front]);
		return dataSet[front];
		
	}

	private void doubleCapacity() {
		
		// ここにdoubleCapacityの処理を書いてください
		
		String[] tmp = new String[capacity];
		System.arraycopy(dataSet,0,tmp,0,dataSet.length);
		capacity = capacity*2;
		dataSet = new String[capacity];
		rear = front+size-1;
		for(int i=front; i<=front+size-1; i++){
			dataSet[i] = tmp[i%(capacity/2)];
		}
		System.out.print("capacity: ");System.out.print(capacity/2);System.out.print(" -> ");System.out.println(capacity);
	}

	public void print(){
		System.out.print("[");
		for(int i=front; i<=front+size-1; i++){
			System.out.print(dataSet[i%capacity]);
			System.out.print(" ");
		}
		System.out.println("]");
		System.out.print("size:");System.out.println(size);
		System.out.print("capacity:");System.out.println(capacity);
		System.out.print("front:");System.out.println(front);
		System.out.print("rear:");System.out.println(rear);
	}
}