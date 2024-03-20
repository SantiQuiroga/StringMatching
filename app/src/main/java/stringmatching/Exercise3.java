package stringmatching;

public class Exercise3 {
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

  static void findSuffixPrefixMatch(String t, String p, int[] x) {
      int[] lps = computeTemporaryArray(p.toCharArray());
      for (int i : x) {
          String substring = t.substring(0, i);
          int j = 0;
          int k = 0;
          while (j < substring.length() && k < p.length()) {
              if (substring.charAt(j) == p.charAt(k)) {
                  j++;
                  k++;
              } else {
                  if (k != 0) {
                      k = lps[k - 1];
                  } else {
                      j++;
                  }
              }
          }
          System.out.println(k);
      }
  }

  public static void main(String[] args) {
      String t = "abba";
      String p = "bab";
      int[] x = {1, 2, 3, 4};
      findSuffixPrefixMatch(t, p, x);
  }
}
