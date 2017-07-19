import java.io.*;
import java.util.*;

public class Shape3d{

	public /* LinkedList or ArrayList*/ ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
    public /* LinkedList or ArrayList*/ LinkedList<Face> faceList = new LinkedList<Face>();

	public Shape3d(String fileName){
		//コンストラクタ
		//ファイルを読み込んでvertexListとfaceListに格納する
		//FileReader, StreamTokenizerを用いる
		//以前演習でやった例を参照すること
        ArrayList<String> input0 = new ArrayList<String>();
 
        try { // ファイル入出力の部分は，try .. catch で囲みます
            // ファイルをオープンするときは，FileReader を使います
            FileReader fr = new FileReader("cube.txt");
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
                    // 文字l列のときは sval, 数値 (double) のときは，nvalとします．
                    System.out.println(st.sval);
                    input0.add(st.sval);
                }else if(st.ttype == StreamTokenizer.TT_NUMBER) {
                    // 文字l列のときは sval, 数値 (double) のときは，nvalとします．
                    System.out.println(Double.toString(st.nval));
                    input0.add(Double.toString(st.nval));
                }
            }
            // ファイルを閉じます．
            fr.close();
            
            System.out.println("file successfully parsed.");
            System.out.println(input0.size());
            String[] input = (String[])input0.toArray();
            
            System.out.println("file successfully converted.");
            
            for(int i = 0; i < input.length; i += get_nargs(input, i) ){
                System.out.println(i);
                if(input[i].equals("f")){
                    int n_index = Integer.parseInt(input[i+1]);
                    int index[] = new int[n_index];
                    for (int j = 0; j<n_index; j++){
                        index[j] = Integer.parseInt(input[i+1+1+j]);
                    }
                    addFace(index);
                }
                if(input[i].equals("v")){
                    double x = Double.parseDouble(input[i+1]);
                    double y = Double.parseDouble(input[i+2]);
                    double z = Double.parseDouble(input[i+3]);
                    addVertex(x, y, z);
                }
            }
            
            
            
            
        } catch (FileNotFoundException e) { // 例外処理
            System.out.println ("this file was not found.");
        } catch (IOException e) {
            System.out.println ("Error: " + e);
        }
    }
    
	private void addVertex (double x, double y, double z){
		//Vertexを作成して、vertexListに格納する
        Vertex new_Vertex = new Vertex(x,y,z);
        vertexList.add(new_Vertex);
	}

	private void addFace (int[] index){
		// 頂点番号の配列が与えられたときに、Faceを新たに生成して
		// faceListに格納する
        int n = index.length;
        Vertex[] vertices = new Vertex[n];
        for (int i=0; i<n; i++){
            vertices[i] = vertexList.get(index[i]);
        }
        Face new_face  = new Face(vertices);
        faceList.add(new_face);
	}
    
    private int get_nargs(String[] input, int i){
        //f なら 1+3
        //g なら 1+1+含まれる点の数
        if(input[i].equals("f")) return 1+3;
        if(input[i].equals("g")) return 1+1+Integer.parseInt(input[i+1]);
        return 0;
        
    }

}
