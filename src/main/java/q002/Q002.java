package q002;

import java.util.*;

/**
 * Q002 並べ替える
 *
 * dataListに "ID,名字" の形式で20個のデータがあります。
 * これをID順に並べて表示するプログラムを記述してください。
 *
 * dataListの定義を変更してはいけません。
 *
 *
[出力結果イメージ]
1,伊藤
2,井上
（省略）
9,清水
10,鈴木
11,高橋
（省略）
20,渡辺
 */
public class Q002 {
    /**
     * データ一覧
     */
    private static final String[] dataList = {
            "8,佐藤",
            "10,鈴木",
            "11,高橋",
            "12,田中",
            "20,渡辺",
            "1,伊藤",
            "18,山本",
            "13,中村",
            "5,小林",
            "3,加藤",
            "19,吉田",
            "17,山田",
            "7,佐々木",
            "16,山口",
            "6,斉藤",
            "15,松本",
            "2,井上",
            "4,木村",
            "14,林",
            "9,清水"
    };

	public static void main(String[] args) {

		int[] idList = new int[dataList.length] ;
		int i = 0;
   
		// IDを抽出
		for (String str: dataList){
			String[] cutStr = str.split(",", 0);
			idList[i] = Integer.parseInt(cutStr[0]);
			i++;
		}

		// IDをソート
		Arrays.sort(idList);

		// ソートしたID順に、dataListを表示
		for (int id: idList) {
			for (String ptintStr: dataList) {
				String[] cutStr = ptintStr.split(",", 0);
				if (id == Integer.parseInt(cutStr[0])) {
					System.out.println(ptintStr);
				}
			}
		}
	}
}
// 完成までの時間: 50分