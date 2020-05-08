package q005;

import java.io.*;
import java.util.*;

/**
 * Q005 データクラスと様々な集計
 *
 * 以下のファイルを読み込んで、WorkDataクラスのインスタンスを作成してください。
 * resources/q005/data.txt
 * (先頭行はタイトルなので読み取りをスキップする)
 *
 * 読み込んだデータを以下で集計して出力してください。
 * (1) 役職別の合計作業時間
 * (2) Pコード別の合計作業時間
 * (3) 社員番号別の合計作業時間
 * 上記項目内での出力順は問いません。
 *
 * 作業時間は "xx時間xx分" の形式にしてください。
 * また、WorkDataクラスは自由に修正してください。
 *
[出力イメージ]
部長: xx時間xx分
課長: xx時間xx分
一般: xx時間xx分
Z-7-31100: xx時間xx分
I-7-31100: xx時間xx分
T-7-30002: xx時間xx分
（省略）
194033: xx時間xx分
195052: xx時間xx分
195066: xx時間xx分
（省略）
 */
public class Q005 {

    /**
     * データファイルを開く
     * resources/q005/data.txt
     */
    private static InputStream openDataFile() {
        return Q005.class.getResourceAsStream("data.txt");
    }

    /**
     * 時間表示を"xx時間xx分"に変換する
     */
    private static String convertTime(int time) {
        int hour = time / 60;
        int min = time % 60;
        return String.format("%4d", hour) + "時間" + String.format("%02d", min) + "分";
    }

    public static void main(String[] args) {

        // ファイルの各行を設定するWorkDataのList
        List<WorkData> dataList = new ArrayList<WorkData>();

        // ファイル読み込み、分割結果を、WorkDataに設定
        try (BufferedReader in = new BufferedReader(new InputStreamReader(openDataFile()))) {
            
            String line;
            while ((line = in.readLine()) != null) {
                // ヘッダーを読み飛ばす
                if (line.startsWith("社員番号")) {
                    continue;
                }
                String[] info = line.split(",");
                dataList.add(new WorkData(info[0], info[1], info[2], info[3], Integer.parseInt(info[4])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 加算の一時保存用
        int workTime = 0;
        // (1) 役職別の合計時間加算用
        Map<String, Integer> positionMap = new LinkedHashMap<String, Integer>();
        // (2) Pコード別の合計時間加算
        Map<String, Integer> pCodeMap = new LinkedHashMap<String, Integer>();
        // (3) 社員番号別の合計時間加算
        Map<String, Integer> numberMap = new LinkedHashMap<String, Integer>();

        // ファイル内容をループして、(1)~(3)を加算していく
        for (int i = 0; i < dataList.size(); i++) {
            // (1) 役職別の合計作業時間
            if (positionMap.containsKey(dataList.get(i).getPosition())) {
                workTime = positionMap.get(dataList.get(i).getPosition()) + dataList.get(i).getWorkTime();
                positionMap.put(dataList.get(i).getPosition(), workTime);
            } else {
                positionMap.put(dataList.get(i).getPosition(), dataList.get(i).getWorkTime());
            }

            // (2) Pコード別の合計作業時間
            if (pCodeMap.containsKey(dataList.get(i).getpCode())) {
                workTime = pCodeMap.get(dataList.get(i).getpCode()) + dataList.get(i).getWorkTime();
                pCodeMap.put(dataList.get(i).getpCode(), workTime);
            } else {
                pCodeMap.put(dataList.get(i).getpCode(), dataList.get(i).getWorkTime());
            }

            // (3) 社員番号別の合計作業時間
            if (numberMap.containsKey(dataList.get(i).getNumber())) {
                workTime = numberMap.get(dataList.get(i).getNumber()) + dataList.get(i).getWorkTime();
                numberMap.put(dataList.get(i).getNumber(), workTime);
            } else {
                numberMap.put(dataList.get(i).getNumber(), dataList.get(i).getWorkTime());
            }
        }

        // (1) 役職別の合計作業時間を表示
        for (String key : positionMap.keySet()) {
            System.out.println(key + ": " + convertTime(positionMap.get(key)));
        }

        // (2) Pコード別の合計作業時間を表示        
        for (String key : pCodeMap.keySet()) {
            System.out.println(key + ": " + convertTime(pCodeMap.get(key)));
        }

        // (3) 社員番号別の合計作業を表示
        for (String key : numberMap.keySet()) {
            System.out.println(key + ": " + convertTime(numberMap.get(key)));
        }
    }
}
// 完成までの時間: 2時間 30分