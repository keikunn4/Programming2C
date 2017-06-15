import java.io.*;

class TowerApp {
    static int nDisks; 

    public TowerApp(int inputNum){
        nDisks = inputNum;
    }

    public static void doTowers(char from, char to, char inter) {
        doTowersEach(nDisks, from, to, inter);
    }

    public static void doTowersEach(int topN, char from, char to, char inter) {   
        if (topN==1){
            System.out.println("Move disk 1 from "+from+" to "+to);
        }
        else{ 
            doTowersEach(topN-1,from,inter,to);
            System.out.println("Move disk "+String.valueOf(topN)+" from "+from+" to "+to);
            doTowersEach(topN-1,inter,to,from);
        }
    }
}
