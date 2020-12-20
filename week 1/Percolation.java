import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int gridsize;
    private boolean[][] table;
    // include both top and bottom
    private WeightedQuickUnionUF wq;
    // include only top
    // avoid changing the parents of vortual bottom
    private WeightedQuickUnionUF wqTop;
    private int open;
    private int virtualTop;
    private int virtualBottom;


    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be positive");
        else {
            gridsize = n;
            // include the virtual grids, flat index
            wq = new WeightedQuickUnionUF(n * n + 2);
            wqTop = new WeightedQuickUnionUF(n * n + 1);
            table = new boolean[gridsize][gridsize];
            virtualBottom = gridsize * gridsize + 1;
            virtualTop = gridsize * gridsize;
            open = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    table[i][j] = false;
                }
            }
        }
    }

    // open the site (row, col) if it's closed
    public void open(int row, int col) {
        checkValid(row, col);
        int trow = row - 1;
        int tcol = col - 1;

        if (isOpen(row, col)) {
            return;
        }

        table[trow][tcol] = true;
        open += 1;


        // when open the top/bottom spot, need to connect it to the virtual
        int flatIndex = flat(row, col);
        if (row == 1) {
            wq.union(flatIndex, virtualTop);
            wqTop.union(flatIndex, virtualTop);
        }
        if (row == gridsize) {

            wq.union(flatIndex, virtualBottom);
        }
        // open the surrounding spot
        if (trow - 1 >= 0 && trow - 1 < gridsize && tcol >= 0 && tcol < gridsize && isOpen(row - 1,
                col)) {

            wq.union(flatIndex, flat(row - 1, col));
            wqTop.union(flatIndex, flat(row - 1, col));


        }
        if (trow + 1 >= 0 && trow + 1 < gridsize && tcol >= 0 && tcol < gridsize && isOpen(row + 1,
                col)) {

            wq.union(flat(row + 1, col), flatIndex);
            wqTop.union(flat(row + 1, col), flatIndex);


        }
        if (trow >= 0 && trow < gridsize && tcol - 1 >= 0 && tcol - 1 < gridsize && isOpen(row, col
                - 1)) {

            wq.union(flatIndex, flat(row, col - 1));
            wqTop.union(flatIndex, flat(row, col - 1));

        }
        if (trow >= 0 && trow < gridsize && tcol + 1 >= 0 && tcol + 1 < gridsize && isOpen(row,
                col
                        + 1)) {

            wq.union(flatIndex, flat(row, col + 1));
            wqTop.union(flatIndex, flat(row, col + 1));

        }

    }

    public boolean isOpen(int row, int col) {
        checkValid(row, col);
        return table[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        checkValid(row, col);

        return wqTop.find(virtualTop) == wqTop.find(flat(row, col));
    }

    public int numberOfOpenSites() {
        return open;
    }

    public boolean percolates() {
        return wq.find(virtualTop) == wq.find(virtualBottom);
    }

    private void checkValid(int row, int col) {
        if (row <= 0 || row > gridsize || col <= 0 || col > gridsize) {
            throw new IllegalArgumentException("Row/Col Illegal!");
        }
    }

    private int flat(int row, int col) {
        return (row - 1) * gridsize + (col - 1);
    }    
}
