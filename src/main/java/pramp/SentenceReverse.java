package pramp;

import java.util.ArrayList;
import java.util.List;

public class SentenceReverse {
    /*
    * You are given an array of characters arr that consists of sequences of characters separated by space characters.
    * Each space-delimited sequence of characters defines a word.
    * Implement a function reverseWords that reverses the order of the words in the array in the most efficient manner.
    * Explain your solution and analyze its time and space complexities.
    *
    * Example:

input:  arr = [ 'p', 'e', 'r', 'f', 'e', 'c', 't', '  ',
                'm', 'a', 'k', 'e', 's', '  ',
                'p', 'r', 'a', 'c', 't', 'i', 'c', 'e' ]

output: [ 'p', 'r', 'a', 'c', 't', 'i', 'c', 'e', '  ',
          'm', 'a', 'k', 'e', 's', '  ',
          'p', 'e', 'r', 'f', 'e', 'c', 't' ]
          *
Constraints:
[time limit] 5000ms
[input] array.character arr
0 ≤ arr.length ≤ 100
[output] array.character
    * */

    static char[] reverseWords(char[] arr) {
        // time O(n)
        // space O(n)
//        1. go through arr of chars
//        2. add char to string until meet ' '
//        3. add string to list of strings
//        4. reverse strings in list
//        5. convert list of string to char arr

        if (arr.length == 0)
            return new char[0];

        StringBuilder temp = new StringBuilder();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            char ch = arr[i];

            if (ch == ' ' || i == arr.length - 1) {
                if (i == arr.length - 1)
                    temp.append(ch);

                list.add(temp.toString());
                temp.setLength(0);
                continue;
            }

            temp.append(ch);
        }

        int l = 0;
        int r = list.size() - 1;
        while (l < r) {
            String tempStr = list.get(l);
            list.set(l, list.get(r));
            list.set(r, tempStr);
            l++;
            r--;
        }

        char[] res = new char[arr.length];
        int i = 0;
        for (int j = 0; j < list.size(); j++) {
            String s = list.get(j);
            for (char ch : s.toCharArray()) {
                res[i++] = ch;
            }

            if (j != list.size() - 1)
                res[i++] = ' ';
        }

        return res;
    }

    static char[] reverseWords2(char[] arr) {
        // time O(n)
        // space O(1)

        // 1. reverse whole arr
        // 2. after that reverse every word

        if (arr.length == 0)
            return new char[0];

        reverse(arr, 0, arr.length - 1);
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ') {
                reverse(arr, start, i - 1);
                start = i + 1;
            } else if (i == arr.length - 1) {
                reverse(arr, start, i);
            }
        }

        return arr;
    }

    private static void reverse(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            l++;
            r--;
        }
    }

    public static void main(String[] args) {
        reverseWords2(new char[]{'h', 'e', 'l', 'l', 'o'});
    }
}
