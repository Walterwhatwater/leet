package my.leetcode.solution;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/decode-string/
 */
public class DecodeString {
    public String decodeString(String s) {
        StringBuilder result = new StringBuilder();
        if (s == null || s.length() == 0) {
            return result.toString();
        }

        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);

            if (!isDigit(c)) {
                result.append(c);
            } else {
                int count = 0;
                while (isDigit(s.charAt(i))) {
                    int num = s.charAt(i) - '0';
                    count = count * 10 + num;
                    ++i;
                }

                Stack<Character> stack = new Stack<>(); // 用于记录括号
                stack.push(s.charAt(i)); // 此时一定是左括号
                StringBuilder sub = new StringBuilder(); // 找到最外一层括号内的字符串用于递归
                while (!stack.isEmpty()) {
                    ++i;
                    char current = s.charAt(i);
                    if (current == ']') {
                        stack.pop();
                        if (!stack.isEmpty()) {
                            sub.append(current);
                        }
                    } else if (current == '[') {
                        stack.push(current);
                        sub.append(current);
                    } else {
                        sub.append(current);
                    }
                }

                String subResult = decodeString(sub.toString());
                while (count > 0) {
                    result.append(subResult);
                    --count;
                }
            }
        }

        return result.toString();
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
}
