package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] grid = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = scanner.nextInt();
            }
        }

        int[][] dp = new int[m][n];

        // Заповнення dp
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }

        // Заповнення dp для решти клітинок
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        // Побудова маршруту
        StringBuilder route = new StringBuilder();
        int i = m - 1;
        int j = n - 1;
        while (i > 0 || j > 0) {
            if (i == 0) {
                route.insert(0, 'R');
                j--;
            } else if (j == 0) {
                route.insert(0, 'F');
                i--;
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    route.insert(0, 'F');
                    i--;
                } else {
                    route.insert(0, 'R');
                    j--;
                }
            }
        }

        System.out.println(route);
    }
}
