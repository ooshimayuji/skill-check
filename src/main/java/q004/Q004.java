package q004;

/**
 * Q004 ソートアルゴリズム
 *
 * ListManagerクラスをnewして、小さい順に並び変えた上でcheckResult()を呼び出してください。
 *
 * 実装イメージ:
 * ListManager data = new ListManager();
 * // TODO 並び換え
 * data.checkResult();
 *
 * - ListManagerクラスを修正してはいけません
 * - ListManagerクラスの dataList を直接変更してはいけません
 * - ListManagerクラスの比較 compare と入れ替え exchange を使って実現してください
 */
public class Q004 {
    public static void main(String[] args) {
        // データ作成
        ListManager data = new ListManager();

        // データを昇順に並び替え
        // dataListサイズ分　大小判定と値の入れ替えを行う。
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < (data.size() - i - 1); j++) {
                if (data.compare(j, j + 1) == 1) {
                    data.exchange(j, j + 1);
                }
            }
        }

        // ソートが正しく行われたかをチェック
        data.checkResult();
    }
}

// 完成までの時間: 1時間 30分