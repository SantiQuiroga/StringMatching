package stringmatching;

public class Exercise2 {
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

    // This function implements the Knuth-Morris-Pratt (KMP) pattern matching algorithm
    static int kmp(char[] text, char[] pattern) {
        int[] lps = computeTemporaryArray(pattern); // Compute the LPS array
        int i = 0; // Index for text[]
        int j = 0; // Index for pattern[]
        int count = 0; // Count of occurrences of pattern
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
                count++; // Found pattern
                j = 0;
            }
        }
        return count; // Return the count of occurrences
    }

    // This function finds the longest prefix of the string that repeats maximum number of times
    static void findPrefix(String s) {
        int maxCount = 0; // Initialize result
        String maxPrefix = ""; // Initialize the maximum repeating prefix
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i); // Consider all prefixes one by one
            int count = kmp(s.toCharArray(), prefix.toCharArray()); // Count occurrences of the current prefix
            if (count >= maxCount && prefix.length() > maxPrefix.length()) {
                maxCount = count; // If count of current prefix is more, then update result
                maxPrefix = prefix;
            }
        }
        System.out.println(maxPrefix + " - " + maxCount); // Print the maximum repeating prefix with its count
    }

    // Driver method
    public static void main(String[] args) {
        String s = "abcabcabcabcghebabcabc"; // Input string
        findPrefix(s); // Call the function to find the longest repeating prefix
    }
}
