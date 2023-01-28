package stack.medium;

import java.util.ArrayDeque;
import java.util.Deque;

// https://leetcode.com/problems/decode-string/description/
public class DecodeString_394 {
    public String decodeString(String s) {
        // time O(n)
        // space O(n)

        Deque<String> words = new ArrayDeque<>();
        Deque<Integer> numbers = new ArrayDeque<>();
        StringBuilder word = new StringBuilder();
        int num = 0;
        for(char ch : s.toCharArray()) {
            if(Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            } else if(ch == '[') {
                words.addFirst(word.toString());
                word = new StringBuilder();
                numbers.addFirst(num);
                num = 0;
            } else if(ch == ']') {
                StringBuilder temp = word;
                word = new StringBuilder(words.removeFirst());
                int count = numbers.removeFirst();
                while(count-- != 0)
                    word.append(temp);
            } else
                word.append(ch);
        }

        return word.toString();
    }
}
