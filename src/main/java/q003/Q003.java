package q003;

import java.io.InputStream;
import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * Q003 集計と並べ替え
 *
 * 以下のデータファイルを読み込んで、出現する単語ごとに数をカウントし、アルファベット辞書順に並び変えて出力してください。
 * resources/q003/data.txt
 * 単語の条件は以下となります
 * - "I"以外は全て小文字で扱う（"My"と"my"は同じく"my"として扱う）
 * - 単数形と複数形のように少しでも文字列が異れば別単語として扱う（"dream"と"dreams"は別単語）
 * - アポストロフィーやハイフン付の単語は1単語として扱う（"isn't"や"dead-end"）
 *
 * 出力形式:単語=数
 *
[出力イメージ]
（省略）
highest=1
I=3
if=2
ignorance=1
（省略）

 * 参考
 * http://eikaiwa.dmm.com/blog/4690/
 */
public class Q003 {
    /**
     * データファイルを開く
     * resources/q003/data.txt
     */
    private static InputStream openDataFile() {
        return Q003.class.getResourceAsStream("data.txt");
    }

    public static void main(String[] args) {

        try (BufferedReader in = new BufferedReader(new InputStreamReader(openDataFile(), "utf-8"))){
            String line;
            Map<String, Integer> wordMap = new HashMap<>();
            int wordNum = 0;

            while((line = in.readLine()) != null) {
                // 1行ずつ単語毎に区切る
                String[] words = line.split(" ", 0);
                for (String str: words) {
                    // I以外は、小文字に変換
                    if (str != "I") {
                        str = str.toLowerCase();
                    }
                    // 記号を削除する
                    str = str.replaceAll("[?.,;]","");

                    // アルファベットを含む場合のみカウントする
                    Pattern pattern = Pattern.compile(".*[a-zA-Z].*");
                    Matcher matcher = pattern.matcher(str);
                    if(matcher.matches() == true){
                    
                        // 単語を配列のキーに保持する
                        // 既に登録済の単語の場合は、カウントを加算
                        if (!wordMap.containsKey(str)) {
                            wordMap.put(str, 1);
                            wordNum++;
                            System.out.println(str);
                        } else {
                            wordMap.put(str, wordMap.get(str)+1);
                        }
                    }
                }
            }
            
            // 単語（キー）を取得して、ソートする
            String[] keyList = new String[wordNum];
            int i=0;
            for (String key : wordMap.keySet()) {
                keyList[i] = key;
                i++;
            }
            Arrays.sort(keyList);
            // アルファベット順に表示
            for (String key: keyList) {
                System.out.println(key + "=" + wordMap.get(key));
            }
        } catch (Exception e){ 
            e.printStackTrace();
        }
    }
}
// 完成までの時間: 3時間30分