import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.*;

public class Read {
    public void reading() throws IOException {
        String str = null;
        try {
            str = new String(Files.readAllBytes(Paths.get("/Users/ilmirsarafutdinov/Desktop/pushkin.txt")));
            deletePunctuationMarks(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void deletePunctuationMarks(String str) {
        Map<String,Integer> dictionary = new HashMap<String,Integer>();

        Pattern pattern = Pattern.compile("\\?| |!|:|\n|,|;|\\.|-|—|…|«|»");
        String[] newStrings = pattern.split(str);
        List<String> words = Arrays.asList(newStrings);

        for (String word : words) {
            if (word != "") {
                String newWord = word.toLowerCase();
                dictionary.put(newWord, dictionary.getOrDefault(newWord, 0) + 1);
            }
        }

        System.out.println("Количество ВСЕХ слов: " + words.size());
        System.out.println("Количество всех УНИКАЛЬНЫХ слов: " + dictionary.size());

        Map<String,Integer> sortedDictionary100 = sortByValue(dictionary);

        System.out.println("100 часто встречающихся слов:\n" + sortedDictionary100);
    }

    private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        List<Map.Entry<String, Integer>> newList;
        newList = new LinkedList<Map.Entry<String, Integer>>();
        for (int i = 0; i < 100; i++) {
            newList.add(list.get(i));
        }

        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : newList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
}
