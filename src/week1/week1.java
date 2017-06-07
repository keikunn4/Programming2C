package week1;
import java.io.FileReader;
import java.io.StreamTokenizer;
import java.io.FileNotFoundException;
import java.io.IOException;
public class  WordReading {
public static void main ( String[] args){
	try { // ファイル入出力 部分 ,try .. catch で囲みます
		// ファイルをオープンするとき ,File Reader を使います
		FileReader fr = new FileReader("word.txt");
		// ファイル 中身を連続した文字列として認識するとき ,
		//StreamTokenizer を使います.
		// 区切り ,空白や改行や  AB です.
		StreamTokenizer st = new  StreamTokenizer(fr);
		//  StreamTokenizer  ,最初 先頭をさしています.
		// next Token を呼ぶごとに次 文字列を取り出します.
		// 次 文字列がなくなったとき ,  _EOF (end of file : ファイル終了)を返します.
		while (st.next oken() !=  tream okenizer.  _EOF) {
			// 現在 文字列 タイプを返します.文字列なら   _ O D, // 数字なら   _N MBE  を返します.
			if (st.ttype ==  tream okenizer.  _ O D) {
				// 文字列 とき  sval, 数値 (double)  とき ,nval とします.
				ystem.out.println(st.sval); }
		}
		// ファイルを閉じます. fr.close();
	} catch (FileNotFoundException e) { // 例外処理  ystem.out.println ("this file was not found.");
	} catch (IOException e) {  ystem.out.println ("Error: " + e);
} }
}