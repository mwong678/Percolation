/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Percolation {

    private int[] grid;
    private int n, top, count;
    private boolean percolates;

    public Percolation(int n){
        // create n-by-n grid, with all sites blocked
        if (n <= 0){
            throw new java.lang.IllegalArgumentException();
        }
        int size = (n*n) + 1;
        this.n = n;
        this.top = size - 1;
        this.grid = new int[size];

        for (int i = 0;i < size - 1; i++) {
            this.grid[i] = -1;
        }
        this.grid[this.top] = this.top;
        //spot will be closed if negative

    }

    private int calculateIndex(int row, int col){
        if (row < 0 || row >= this.n){
            throw new java.lang.IllegalArgumentException();
        }
        if (col < 0 || row >= this.n){
            throw new java.lang.IllegalArgumentException();
        }

        return (row * this.n) + col;
    }

    public void open(int row, int col){
        // open site (row, col) if it is not open already
        if (isOpen(row, col)){
            return;
        }
        //open calculateIndex(row,col), then check for connections
        

    }

    public void union(int x, int y){
        //todo add to tree;
        //extra conditions if it is a top or bottom row
        //top: set to actual root
        //bottom: check if same value as actual root after setting,
        //set value of percolates
    }

    public boolean isOpen(int row, int col) {
        return this.grid[calculateIndex(row, col)] >= 0;
    }

    public boolean isFull(int row, int col) {
        return this.grid[calculateIndex(row, col)] <= 0;
    }

    public int numberOfOpenSites(){
        return count;
    }
    public boolean percolates(){
        return percolates;
    }

    public static void main(String[] args){
        // test client (optional)
        Percolation p = new Percolation(4);
        System.out.println(p);
    }
}
