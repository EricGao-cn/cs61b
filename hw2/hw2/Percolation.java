package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int length;
    private int openNums;
    private boolean[][] grid;
    // disjointSet 下标n*n表示虚拟的顶， 下标n*n+1表示虚拟的底
    private WeightedQuickUnionUF disjointSet;
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        length = n;
        openNums = 0;
        grid = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = false;
            }
        }
        disjointSet = new WeightedQuickUnionUF(n * n + 2);
//        for (int i = 0; i < n; i++) {
//            disjointSet.union(n * n, i);
//            disjointSet.union(n * n + 1, n * n - i - 1);
//        }
    }

    private int posToNum(int row, int col) {
        return row * length + col;
    }
    private void connect(int row, int col) {
        if (row == 0) {
            disjointSet.union(length * length, col);
        }
        if (row == length - 1) {
            disjointSet.union(length * length + 1, posToNum(row, col));
        }
        if (row - 1 >= 0 && grid[row - 1][col]) {
            disjointSet.union(posToNum(row - 1, col), posToNum(row, col));
        }
        if (row + 1 < length && grid[row + 1][col]) {
            disjointSet.union(posToNum(row + 1, col), posToNum(row, col));
        }
        if (col - 1 >= 0 && grid[row][col - 1]) {
            disjointSet.union(posToNum(row, col - 1), posToNum(row, col));
        }
        if (col + 1 < length && grid[row][col + 1]) {
            disjointSet.union(posToNum(row, col + 1), posToNum(row, col));
        }
    }

    public void open(int row, int col) {
        if (!grid[row][col]) {
            grid[row][col] = true;
            openNums++;
            connect(row, col);
        }
    }
    public boolean isOpen(int row, int col) {
        return grid[row][col];
    }
    public boolean isFull(int row, int col) {
        return disjointSet.connected(posToNum(row, col), length * length);
    }
    public int numberOfOpenSites() {
        return openNums;
    }
    public boolean percolates() {
        return disjointSet.connected(length * length, length * length + 1);
    }

    public static void main(String[] args) {
        return;
    }

}
