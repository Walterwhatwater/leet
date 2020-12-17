package my.leetcode.solution;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode-cn.com/problems/restore-ip-addresses/
 */
public class RestoreIP {
    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() < 4 || s.length() > 12) {
            return new LinkedList<>();
        }

        List<String> result = new LinkedList<>();
        backtrack(result, new LinkedList<>(), s, 0);
        return result;
    }

    private void backtrack(List<String> result, List<String> segments, String s, int start) {
        if (segments.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", segments));
            }
            return;
        }

        for (int length = 1; length <= 3 && start + length <= s.length(); ++length) {
            String segment = s.substring(start, start + length);
            if (isValid(segment)) {
                segments.add(segment);
                backtrack(result, new LinkedList<>(segments), s, start + length);
                segments.remove(segments.size() - 1);
            }
        }
    }

    private boolean isValid(String segment) {
        if (segment == null || segment.length() < 1 || segment.length() > 3) {
            return false;
        }

        if (segment.charAt(0) == '0') {
            return segment.length() == 1;
        }

        int value = Integer.parseInt(segment);
        return value > 0 && value <= 255;
    }
}
