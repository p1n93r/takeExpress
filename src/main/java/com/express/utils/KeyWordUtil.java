package com.express.utils;

import java.util.*;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/12 13:41
 */
public class KeyWordUtil {
    private static Map<String, Object> dictionaryMap;
    public Map<String, Object> getDictionaryMap() {
        return dictionaryMap;
    }
    public static void setDictionaryMap(Map<String, Object> dictionaryMap) {
        dictionaryMap = dictionaryMap;
    }
    @SuppressWarnings("unchecked")
    public static   Map<String, Object> handleToMap(Set<String> wordSet) {
        if (wordSet == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>(wordSet.size());
        Map<String, Object> curMap = null;
        Iterator<String> ite = wordSet.iterator();
        while (ite.hasNext()) {
            String word = ite.next();
            curMap = map;
            int len = word.length();
            for (int i = 0; i < len; i++) {
                String key = String.valueOf(word.charAt(i));
                Map<String, Object> wordMap = (Map<String, Object>) curMap
                        .get(key);
                if (wordMap == null) {
                    wordMap = new HashMap<String, Object>();
                    wordMap.put("isEnd", "0");
                    curMap.put(key, wordMap);
                    curMap = wordMap;
                }
                else {
                    curMap = wordMap;
                }
                if (i == len - 1) {
                    curMap.put("isEnd", "1");
                }
            }
        }
        return map;
    }
    @SuppressWarnings("unchecked")//告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
    public static int checkWord(Map<String, Object> dictionaryMap,String text, int beginIndex) {
        if (dictionaryMap == null) {
            throw new RuntimeException("字典不能为空！");
        }
        boolean isEnd = false;
        int wordLength = 0;
        Map<String, Object> curMap = dictionaryMap;
        int len = text.length();
        for (int i = beginIndex; i < len; i++) {
            //返回指定索引处的字符。
            String key = String.valueOf(text.charAt(i));
            curMap = (Map<String, Object>) curMap.get(key);
            if (curMap == null) {
                break;
            }
            else {
                wordLength++;
                if ("1".equals(curMap.get("isEnd"))) {
                    isEnd = true;
                }
            }
        }
        if (!isEnd) {
            wordLength = 0;
        }
        return wordLength;
    }
    public static   Set<String> getWords(Map<String, Object> dictionaryMap,String text) {
        Set<String> wordSet = new HashSet<String>();
        int len = text.length();
        for (int i = 0; i < len; i++) {
            int wordLength = checkWord(dictionaryMap,text, i);
            if (wordLength > 0) {
                String word = text.substring(i, i + wordLength);
                wordSet.add(word);
                i = i + wordLength - 1;
            }
        }
        return wordSet;
    }
}
