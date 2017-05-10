import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class Parser {

    public static Map<String, Integer> getMostFrequentlyWords(String url) {
        String sourceStr = getTextByUrl(url);
        String[] sourceAr = sourceStr.split(" ");
        int sourceArLength = sourceAr.length;
        HashMap<String, Integer> wordMap = new HashMap<>();
        for (int i = 0; i < sourceArLength; i++) {
            boolean isNew = true;
            for (String word : wordMap.keySet()) {
                if (sourceAr[i].equals(word)) {
                    wordMap.put(word, wordMap.get(word) + 1);
                    isNew = false;
                }
            }
            if (isNew)
                wordMap.put(sourceAr[i], 1);
        }

        return sortHashMapByValues(wordMap);
    }

    private static String getTextByUrl(String url) {

        String res = "";
        try {
            Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                    .get();
            Elements metaElements = doc.getElementsByTag("div");
            for (Element content : metaElements) {
                res += content.text().replaceAll(",", "").replaceAll("\\.", "").replaceAll(":", "").replaceAll(";", "")
                        .replaceAll("!", "").replaceAll("\\(", "").replaceAll("\\)", "").replaceAll("\\?", "").replaceAll("\\*", "")
                        .replaceAll("—", "").replaceAll("”", "").replaceAll("“", "");
            }
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private static LinkedHashMap<String, Integer> sortHashMapByValues(
            HashMap<String, Integer> passedMap) {
        List<String> mapKeys = new ArrayList<>(passedMap.keySet());
        List<Integer> mapValues = new ArrayList<>(passedMap.values());
        Collections.sort(mapValues);
        Collections.sort(mapKeys);

        LinkedHashMap<String, Integer> sortedMap =
                new LinkedHashMap<>();

        Iterator<Integer> valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Integer val = valueIt.next();
            Iterator<String> keyIt = mapKeys.iterator();

            while (keyIt.hasNext()) {
                String key = keyIt.next();
                Integer comp1 = passedMap.get(key);
                Integer comp2 = val;

                if (comp1.equals(comp2)) {
                    keyIt.remove();
                    sortedMap.put(key, val);
                    break;
                }
            }
        }
        return sortedMap;
    }
}
