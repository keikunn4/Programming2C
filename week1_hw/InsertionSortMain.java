import java.io.StreamTokenizer;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

class InsertionSortMain {
    static final String INPUT_FILE_NAME = "data.txt";

    public static int countLine(String fileName) {
        int count = 0;
        try {
            FileReader fr = new FileReader(fileName);
            StreamTokenizer st = new StreamTokenizer(fr);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    count++;
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("text file \"" + fileName + "\" was not found.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return count;
    }

    public static int[] readInt(String fileName){
        int count = 0;
        int[] data = new int[countLine(fileName)];
        try {
            FileReader fr = new FileReader(fileName);
            StreamTokenizer st = new StreamTokenizer(fr);
            while (st.nextToken() != StreamTokenizer.TT_EOF) {
                if (st.ttype == StreamTokenizer.TT_NUMBER) {
                    data[count] = (int)st.nval;
                    count++;
                }
            }
            fr.close();
        } catch (FileNotFoundException e) {
            System.out.println("text file \"" + fileName + "\" was not found.");
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return data;
    }

    public static void main(String[] args) {
        int[] data = readInt(INPUT_FILE_NAME);
        InsertionSort is = new InsertionSort();
        data = is.sort(data);

        // ********** ここから編集してください **********
        // (1) 出力例に従って「学籍番号.txt」へと結果を出力する
        
        try {
            // ファイル書き込むときは，FileWriter を使います
            FileWriter filewriter = new FileWriter("03-170971.txt");
            // filewriter.write(“書き込む内容”) でファイルに書き込むことができます
            filewriter.write("-- Insertion Sort --\n");
            int n = data.length;
            for (int i = 0; i < n; i++) {
                filewriter.write( "[" + String.valueOf(i) + "]: " + String.valueOf(data[i]) + "\n" );
            }
            filewriter.write("# of comparison: " + String.valueOf(is.getComparisonNum()) + "\n"); // “\n”は改行を表します
            filewriter.write("# of comparison: " + String.valueOf(is.getExchangeNum()) + "\n");
            // ファイルを閉じます
            filewriter.close();
            System.out.println("ファイルへの書き込みが完了しました");
        } catch (IOException e) {
            System.out.println(e);
        }
        
        // ********** ここまで編集してください **********
    }
}
