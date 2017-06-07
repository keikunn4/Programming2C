class InsertionSort {
    private int comparisonNum = 0; // # of comparison
    private int exchangeNum = 0; // # of exchange

    public int getComparisonNum() {
        return comparisonNum;
    }

    public int getExchangeNum() {
        return exchangeNum;
    }

    public int[] sort(int[] data) {
        // ********** ここから編集してください **********

        // (1) 挿入ソートにより配列dataを昇順にするプログラムを書く
        // (2) その際，比較回数と交換回数を正しい位置でカウントしてcomparisonNum, exchangeNumに格納する
        
        int n = data.length;
        int j;
        for (int i = 1; i < n; i++) {
            int tmp = data[i];
            
            if (data[i - 1] > tmp) {
                j = i;
                do {
                    data[j] = data[j - 1];//一つ前にずらす
                    comparisonNum++;
                    exchangeNum++;
                    j--;
                                    } while (j > 0 && data[j - 1] > tmp);
                
                if (j!=0) comparisonNum++;//先頭まで移動しなければ、比較回数は交換回数よりも一回多い
                
                data[j] = tmp;
            }
        }
        return data;
        
        // ********** ここまで編集してください **********
    }
}
