public class KMP {

    public static boolean search(String text,
                                 String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = computeLPS(pattern);

        int i = 0;
        int j = 0;

        while (i < n) {

            if (text.charAt(i) ==
                    pattern.charAt(j)) {

                i++;
                j++;
            }

            if (j == m) {

                return true;
            }

            else if (i < n &&
                    text.charAt(i) !=
                    pattern.charAt(j)) {

                if (j != 0) {

                    j = lps[j - 1];
                }

                else {

                    i++;
                }
            }
        }

        return false;
    }

    private static int[] computeLPS(
            String pattern) {

        int m = pattern.length();

        int[] lps = new int[m];

        int len = 0;
        int i = 1;

        while (i < m) {

            if (pattern.charAt(i) ==
                    pattern.charAt(len)) {

                len++;
                lps[i] = len;
                i++;
            }

            else {

                if (len != 0) {

                    len = lps[len - 1];
                }

                else {

                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}