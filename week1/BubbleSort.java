class BubbleSort {
    private int comparisonNum = 0; // # of comparison
    private int exchangeNum = 0; // # of exchange

    public int getComparisonNum() {
        return comparisonNum;
    }

    public int getExchangeNum() {
        return exchangeNum;
    }

    public int[] sort(int[] data) {
        comparisonNum = 0;
        exchangeNum = 0;

        // 配列の中身を順番に比較します
        for (int i = 0; i < data.length-1; i++) {
            for (int j = data.length-1; j > i; j--) {
                // 上の方が大きいときは値を入れ替えます
                if (data[j] < data[j - 1]) {
                    int tmp = data[j];
                    data[j] = data[j - 1];
                    data[j - 1] = tmp;
                    // 交換回数をカウント
                    exchangeNum++;
                }
                // 比較回数をカウント
                comparisonNum++;
            }
        }
        return data;
    }
}