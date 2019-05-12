/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n, top, bottom;
    private final WeightedQuickUnionUF grid;
    private boolean[] sites;
    private int count;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        this.n = n + 1;
        int size = (this.n * this.n);
        this.top = 0;
        this.bottom = 1;
        this.grid = new WeightedQuickUnionUF(size);
        this.sites = new boolean[size];
    }

    private int calculateIndex(int row, int col) {
        return (row * this.n) + col;
    }

    private boolean inBounds(int row, int col) {
        if (row < 1 || row >= this.n) {
            return false;
        }
        if (col < 1 || col >= this.n) {
            return false;
        }
        return true;
    }

    public void open(int row, int col) {

        if (this.grid == null) return;
        // open site (row, col) if it is not open already

        if (row < 0 || row >= this.n) {
            throw new java.lang.IllegalArgumentException();
        }
        if (col < 0 || col >= this.n) {
            throw new java.lang.IllegalArgumentException();
        }

        if (isOpen(row, col)) {
            return;
        }

        int current = calculateIndex(row, col);
        this.sites[current] = true;
        this.count++;

        if (inBounds(row, col + 1) && isOpen(row, col + 1))
            union(calculateIndex(row, col + 1), current);
        if (inBounds(row + 1, col) && isOpen(row + 1, col))
            union(calculateIndex(row + 1, col), current);
        if (inBounds(row, col - 1) && isOpen(row, col - 1))
            union(calculateIndex(row, col - 1), current);
        if (inBounds(row - 1, col) && isOpen(row - 1, col))
            union(calculateIndex(row - 1, col), current);

        if (current >= this.n && current < 2 * this.n) {
            this.grid.union(current, this.top);
        }

        if (current >= this.n * (this.n - 1) && current < this.n * this.n) {
            this.grid.union(current, this.bottom);
        }
    }

    private void union(int p, int q) {
        this.grid.union(p, q);
        if ((p >= this.n && p < 2 * this.n) || (q >= this.n && q < 2 * this.n)) {
            this.grid.union(q, this.top);
        }

        if ((p >= this.n * (this.n - 1) && p < this.n * this.n) || (q >= this.n * (this.n - 1) && q < this.n * this.n)) {
            this.grid.union(q, this.bottom);
        }
    }



    public boolean isOpen(int row, int col) {

        if (row < 1 || row >= this.n) {
            throw new java.lang.IllegalArgumentException();
        }
        if (col < 1 || col >= this.n) {
            throw new java.lang.IllegalArgumentException();
        }
        return this.sites[calculateIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;
        return this.grid.connected(this.top, calculateIndex(row, col));
    }

    public int numberOfOpenSites() {
        return this.count;
    }
    public boolean percolates() {
        return this.grid.connected(this.top, this.bottom);
    }

    private void print() {
        for (int i = 0; i < this.n; i++) {
            for (int j = 0; j < this.n; j++) {
                if (this.sites[calculateIndex(i, j)] || calculateIndex(i, j) <= 1) {
                    System.out.print(this.grid.find(calculateIndex(i, j)) + "\t");
                } else {
                    System.out.print("-1\t");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printValue(int row, int col) {
        System.out.println(this.grid.find(calculateIndex(row, col)));
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(4);
        p.open(1, 2);
        p.print();

        p.open(2, 2);
        p.print();

        p.open(3, 2);
        p.print();
        p.printValue(0, 0);
        System.out.println("percolates: " + p.percolates());

        p.open(3, 3);
        p.print();
        p.printValue(0, 0);
        System.out.println("percolates: " + p.percolates());

        p.open(4, 2);
        p.print();
        p.printValue(0, 0);
        System.out.println("percolates: " + p.percolates());
    }
}