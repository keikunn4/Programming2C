import java.io.*;
import java.util.*;


public class Slist {
    private Snode head;
    //OK
    public int countNode() {
        Snode node = head;
        int count =0;
        // すべてのノードの個数を返す.
		if(node==null){
			return count;
		}else{
			count++;
			while(node.getNext()!=null){
				count++;
				node=node.getNext();			}
		}
		return count;
    }
    //OK
    public Snode findNode(String x){
    	Snode node = head;
        Snode node_found = null;
        // データ x を持つデータがあれば、そのノードを返す．
        // なければ、null を返す.
		if(node!=null){// 先頭が存在する場合
			while(node!=null){
				if(node.getData().equals(x)){
					node_found=node;
					return node_found;
				}else{
					node=node.getNext();
				}
			}
		}
		return node_found;
        
    }
    //OK
    public Snode insert(String x){ 
        // データxをもつノードが存在しなければ，リストの先頭に挿入する.
        // もし，存在すれば，そのノードの count を１増やす．該当するノードを返す．
		if(findNode(x)==null){// データxをもつノードが存在しない場合
			Snode node_newdata=new Snode(x);
			if(head==null){// 先頭が存在しない場合
			}else{	
				head.setPrev(node_newdata);//現時点でのheadを更新
				node_newdata.setNext(head);//現時点でのheadをnode_newの真後ろに設定
			}
			head=node_newdata;
			return node_newdata;
		}else{// データxをもつノードが存在
			Snode node_newdata=findNode(x);
			node_newdata.increaseCount();
			return node_newdata;
		}
		
    }
    //OK
    public void remove(String x){
    	Snode node = head;
        Snode findNode = null;
        // データxを持つノードが存在しなければ何もしない．
        // ノードが存在すれば，そのノードのcount を１減らす．
        // もし，count が０になれば，そのノードをリストから削除する．
        // （注：先頭のノードを削除する場合には， head も書き換えること）
		if (findNode(x)!=null){//　データxを持つノードが存在
			Snode node_deldata=findNode(x);	
			if(node_deldata.getCount()==1){// count が1の場合
				if(node_deldata.getPrev()==null){// 先頭である場合
					if(node_deldata.getNext()==null){// 最後尾の場合
						head=null;
					}else{
						Snode node_nexthead=node_deldata.getNext();
						node_nexthead.setPrev(null);
						head=node_nexthead;
					}
				}else{// 先頭でない場合
					if(node_deldata.getNext()==null){// 最後尾の場合
						Snode node_prev=node_deldata.getPrev();
						node_prev.setNext(null);
					}else{
						Snode node_prev=node_deldata.getPrev();
						Snode node_next=node_deldata.getNext();
						node_prev.setNext(node_next);
						node_next.setPrev(node_prev);
					}
				}
			}
			else{//count が2以上の場合
				node_deldata.decreaseCount();
			}
		}
    }
    //OK
    public void printNodes(int minCount){
    	System.out.println("---------------------------------");
        int counter = countNode();
        System.out.println("Total Number of Nodes ="+counter);
        // データ要素を先頭から順番に調べ、
        // getCount() の値が minCount 以上であれば、以下のようにプリントする.
        // [ ] はプリントするノードの通し番号，( ) は count の個数である．
        // また、先頭に、全ノード数を書く.
		Snode node=head;
		int n=0;
		while(node!=null){
			if(node.getCount()>=minCount){
				System.out.println("Nodes["+n+"]:(count="+node.getCount()+")="+node.getData());
				node=node.getNext();
				n=n+1;
			}
		}
    }

    public void sort(){
        // //getCount()の値が大きい順にノードの並び変えを行う
        int nodeNum = countNode();
        if(nodeNum==0){// ノード数が0の場合
              
        }else{// ノード数が0でない場合
            // 使いやすいように配列にする
            Snode[] nodes = new Snode[nodeNum];
            Snode node = head;
            int n=0;
            while(node!=null){
                nodes[n]=node;
                node = node.getNext();
                n++;
            }
            // 2対比較を繰り返し、適宜交換
            for(int i=0;i<nodeNum-1;i++){
                for(int j=i+1;j<nodeNum;j++){
                    if(nodes[i].getCount()<nodes[j].getCount()){
                        Snode dummyNode =nodes[i];
                        nodes[i] =nodes[j];
                        nodes[j] = dummyNode;
                    }
                }
            }
            head = nodes[0];
            for(int i=0;i<nodeNum;i++) {
                if(i==0) {// 先頭な場合
                    nodes[i].setPrev(null);
                }
                else{// 先頭でない場合
                    nodes[i].setPrev(nodes[i-1]); 
                }
                if(i==nodeNum-1) { // 最後尾の場合
                    nodes[nodeNum-1].setNext(null); 
                }else{// 最後尾でない場合
                    nodes[i].setNext(nodes[i+1]); 
                }
            }    
		}
    }
}
