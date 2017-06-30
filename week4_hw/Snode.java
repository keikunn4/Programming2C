import java.io.*;
import java.util.*;

public class Snode {
    private String data;//ノードのデータ要素
    private int count;//このデータが入力された回数
    private Snode prev;//前のノード(の参照)
    private Snode next;//次のノード(の参照)
    
    public Snode(String x){
        data  = x;
        count = 1;
    }
    public Snode getPrev(){
        // ここに記述します
		return prev;
    }
    public Snode getNext(){
        // ここに記述します
		return next;
    }
    public void setPrev(Snode prev){
        // ここに記述します
		this.prev=prev;
    }
    public void setNext(Snode next){
        // ここに記述します
		this.next=next;
    }
    public String getData(){
        // ここに記述します
		return data;
    }
    public int getCount(){
        // ここに記述します
		return count;
    }
    public void increaseCount(){
        // ここに記述します
		count=count+1;
    }
    public void decreaseCount(){
        // ここに記述します
		count=count-1;
    }

}
