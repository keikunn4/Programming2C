import java.io.FileWriter;
import java.io.IOException;

class TextWriting{
    public static void main(String[] args) {
        try {
            // ファイル書き込むときは，FileWriter を使います
            FileWriter filewriter = new FileWriter("自己紹介.txt");
            // filewriter.write(“書き込む内容”) でファイルに書き込むことができます
            filewriter.write("こんにちは！");
            filewriter.write("\n"); // “\n”は改行を表します
            filewriter.write("私はプログラミング応用の講義を受けています！");
            // ファイルを閉じます
            filewriter.close();
            System.out.println("ファイルへの書き込みが完了しました");
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}