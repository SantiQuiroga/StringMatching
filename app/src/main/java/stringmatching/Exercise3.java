package stringmatching;

public class Exercise3 {
  // This function computes the Longest Prefix Suffix (LPS) array for the pattern
  static int[] computeTemporaryArray(char[] pattern) {
      int[] lps = new int[pattern.length]; // Array to hold the longest prefix suffix values for pattern
      int index = 0; // Initialize index of the pattern
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
      return lps; // Return the computed LPS array
  }

  // This function finds the longest prefix of the pattern that matches a suffix of a substring of the text
  static void findSuffixPrefixMatch(String t, String p, int[] x) {
      int[] lps = computeTemporaryArray(p.toCharArray()); // Compute the LPS array
      for (int i : x) {
          String substring = t.substring(0, i); // Consider all substrings of the text one by one
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
          System.out.println(k); // Print the length of the longest prefix of the pattern that matches a suffix of the substring
      }
  }

  // Driver method
  public static void main(String[] args) {
      String t = "abba"; // Input text
      String p = "bab"; // Pattern to be searched
      int[] x = {1, 2, 3, 4}; // Array of lengths of substrings to be considered
      findSuffixPrefixMatch(t, p, x); // Call the function to find the longest prefix of the pattern that matches a suffix of a substring of the text
  }
}
