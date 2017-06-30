import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;

public class SlistMain {
    
    public static void main(String[] args){
        if (args.length == 0) { // ファイル名指定なし

            // 【1】の(3)はここにそのまま記述すればよい
			Slist fruitsList = new Slist();
			fruitsList.insert ( "Apple" ); // 一回ずつ入力
			fruitsList.insert ( "Banana" );
			fruitsList.insert ( "Orange" );
			fruitsList.sort ();
			fruitsList.printNodes(0);
			fruitsList.insert ( "Banana" ); // データを重複させる。
			fruitsList.insert ( "Banana" );
			fruitsList.insert ( "Apple" );
			fruitsList.sort ();
			fruitsList.printNodes(0);
			fruitsList.remove ( "Banana" ); // remove のチェック
			fruitsList.remove ( "Orange" );
			fruitsList.sort ();
			fruitsList.printNodes(0);
			for(int i=0;i<3;i++) { // 過剰に remove する
				 fruitsList.remove ( "Banana" );
				 fruitsList.remove ( "Orange" );
				 fruitsList.remove ( "Apple" );
			}
			fruitsList.sort ();
			fruitsList.printNodes(0);

        }else{
	        // ファイル名が args[0] として入力
            // 【2】はここに記述する
        	Slist txtList = new Slist();
        	try { // ファイル入出力の部分は，try .. catch で囲みます
	            // ファイルをオープンするときは，FileReader を使います
	            FileReader fr = new FileReader(args[0]);
	            // ファイルの中身を連続した文字列として認識するときは，StreamTokenizer を使います．
	            // 区切りは，空白や改行やTAB です．
	            StreamTokenizer st = new StreamTokenizer(fr);
	            // StreamTokenizer は，最初は先頭をさしています．
	            // nextToken を呼ぶごとに次の文字列を取り出します．
	            // 次の文字列がなくなったときは，TT_EOF (end of file : ファイル終了)を返します．
	            while (st.nextToken() != StreamTokenizer.TT_EOF) {
	                // 現在の文字列のタイプを返します．文字列なら TT_WORD,
	                // 数字なら TT_NUMBER を返します．
	                if (st.ttype == StreamTokenizer.TT_WORD) {
	                    // 文字列のときは sval, 数値 (double) のときは，nvalとします．
	                    txtList.insert( st.sval );
	                }
	            }
	            // ファイルを閉じます．
	            fr.close();
	        } catch (FileNotFoundException e) { // 例外処理
	            System.out.println ("this file was not found.");
	        } catch (IOException e) {
	            System.out.println ("Error: " + e);
	        }
        	txtList.sort ();
			txtList.printNodes(4);

        }
        
    }

}
