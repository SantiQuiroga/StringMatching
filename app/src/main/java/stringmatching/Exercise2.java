package stringmatching;

public class Exercise2 {
    static int[] computeTemporaryArray(char[] pattern) {
        int[] lps = new int[pattern.length];
        int index = 0;
        for (int i = 1; i < pattern.length;) {
            if (pattern[i] == pattern[index]) {
                lps[i] = index + 1;
                index++;
                i++;
            } else {
                if (index != 0) {
                    index = lps[index - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    static int kmp(char[] text, char[] pattern) {
        int[] lps = computeTemporaryArray(pattern);
        int i = 0;
        int j = 0;
        int count = 0;
        while (i < text.length && j < pattern.length) {
            if (text[i] == pattern[j]) {
                i++;
                j++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
            if (j == pattern.length) {
                count++;
                j = 0;
            }
        }
        return count;
    }

    static void findPrefix(String s) {
        int maxCount = 0;
        String maxPrefix = "";
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            int count = kmp(s.toCharArray(), prefix.toCharArray());
            if (count >= maxCount && prefix.length() > maxPrefix.length()) {
                maxCount = count;
                maxPrefix = prefix;
            }
        }
        System.out.println(maxPrefix + " - " + maxCount);
    }

    public static void main(String[] args) {
        String s = "abcabcabcabcghebabcabc";
        findPrefix(s);
    }
}
