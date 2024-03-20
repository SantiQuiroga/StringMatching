package stringmatching;

public class Exercise1 {
  public static final int D = 256; // The number of characters in the input alphabet

  // This function implements the Rabin-Karp algorithm for pattern searching
  static void search(String pat, String txt, int q) {
    int m = pat.length(); // Length of the pattern
    int n = txt.length(); // Length of the text
    int i; 
    int p = 0; // Hash value for the pattern
    int t = 0; // Hash value for the current window of text
    int h = 1; // Hash multiplier

    // Calculate the hash multiplier
    for (i = 0; i < m - 1; i++)
      h = (h * D) % q;

    // Calculate the initial hash values for the pattern and the first window of text
    for (i = 0; i < m; i++) {
      p = (D * p + pat.charAt(i)) % q;
      t = (D * t + txt.charAt(i)) % q;
    }

    // Slide the pattern over the text one by one
    for (i = 0; i <= n - m; i++) {
      // Check if the hash values of the pattern and the current window of text match
      // If they match, perform a character-by-character check to confirm the match
      if (p == t && checkMatch(pat, txt, i)) {
        System.out.println("Pattern found at index " + i);
      }

      // Calculate the hash value for the next window of text
      if (i < n - m) {
        t = (D * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
        if (t < 0)
          t = (t + q);
      }
    }
}

// This function checks if the characters of the pattern match with the characters of the current window of text
static boolean checkMatch(String pat, String txt, int index) {
  int m = pat.length(); // Length of the pattern
  for (int j = 0; j < m; j++) {
    // Compare each character of the pattern with the corresponding character in the text
    // If any characters don't match, return false
    if (txt.charAt(index + j) != pat.charAt(j))
      return false;
  }
  // All characters match, return true
  return true;
}

  // Driver method
  public static void main(String[] args) {
      String txt = "Santi has a great Santi day"; // Input text
      String pat = "Santi"; // Pattern to be searched
      int q = 101; // A prime number
      search(pat, txt, q); // Call the function to search the pattern in the text
  }
}
