package stringmatching;

public class Exercise1 {
  public static final int D = 256; // number of characters in the input alphabet

  static void search(String pat, String txt, int q) {
    int m = pat.length();
    int n = txt.length();
    int i; 
    int p = 0; // hash value for pattern
    int t = 0; // hash value for txt
    int h = 1;

    // The value of h would be "pow(d, M-1)%q"
    for (i = 0; i < m - 1; i++)
      h = (h * D) % q;

    // Calculate the hash value of pattern and first window of text
    for (i = 0; i < m; i++) {
      p = (D * p + pat.charAt(i)) % q;
      t = (D * t + txt.charAt(i)) % q;
    }

    // Slide the pattern over text one by one
    for (i = 0; i <= n - m; i++) {
      if (p == t && checkMatch(pat, txt, i)) {
        System.out.println("Pattern found at index " + i);
      }

      if (i < n - m) {
        t = (D * (t - txt.charAt(i) * h) + txt.charAt(i + m)) % q;
        if (t < 0)
          t = (t + q);
      }
    }
  }

  static boolean checkMatch(String pat, String txt, int index) {
    int m = pat.length();
    for (int j = 0; j < m; j++) {
      if (txt.charAt(index + j) != pat.charAt(j))
        return false;
    }
    return true;
  }

  public static void main(String[] args) {
      String txt = "Santi has a great Santi day";
      String pat = "Santi";
      int q = 101; // A prime number
      search(pat, txt, q);
  }
}
