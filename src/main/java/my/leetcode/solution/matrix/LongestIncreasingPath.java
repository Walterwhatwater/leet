package my.leetcode.solution.matrix;

/**
 * https://leetcode-cn.com/problems/longest-increasing-path-in-a-matrix/
 */
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[][] memo = new int[matrix.length][matrix[0].length];
        int result = 0;
        for (int row = 0; row < matrix.length; ++row) {
            for (int col = 0; col < matrix[0].length; ++col) {
                result = Math.max(result, dfs(matrix, row, col, memo));
            }
        }

        return result;
    }

    private int dfs(int[][] matrix, int row, int col, int[][] memo) {
        if (memo[row][col] > 0) {
            return memo[row][col];
        }

        memo[row][col] = 1;

        memo[row][col] = row + 1 < matrix.length && matrix[row + 1][col] > matrix[row][col] ?
                Math.max(memo[row][col], dfs(matrix, row + 1, col, memo) + 1) : memo[row][col];

        memo[row][col] = row - 1 >= 0 && matrix[row - 1][col] > matrix[row][col] ?
                Math.max(memo[row][col], dfs(matrix, row - 1, col, memo) + 1) : memo[row][col];

        memo[row][col] = col + 1 < matrix[0].length && matrix[row][col + 1] > matrix[row][col] ?
                Math.max(memo[row][col], dfs(matrix, row, col + 1, memo) + 1) : memo[row][col];

        memo[row][col] = col - 1 >= 0 && matrix[row][col - 1] > matrix[row][col] ?
                Math.max(memo[row][col], dfs(matrix, row, col - 1, memo) + 1) : memo[row][col];

        return memo[row][col];
    }
}
