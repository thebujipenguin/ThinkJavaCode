import javax.swing.JFrame;

/**
 * Conway's Game of Life.
 * 
 * @author Chris Mayfield
 * @version 7.1.0
 */
public class Conway {

    private GridCanvas grid;

    /**
     * Creates a grid with two Blinkers.
     */
    public Conway() {
        grid = new GridCanvas(50, 100, 20);

        //glider
        /*grid.turnOn(3, 1);
        grid.turnOn(4, 2);
        grid.turnOn(2, 3);
        grid.turnOn(3, 3);
        grid.turnOn(4, 3);
        grid.turnOn(7, 3);*/

        //indefinite glider gun
        grid.turnOn(15, 11);
        grid.turnOn(15, 12);
        grid.turnOn(16, 11);
        grid.turnOn(16, 12);

        grid.turnOn(13, 23);
        grid.turnOn(13, 24);
        grid.turnOn(14, 22);
        grid.turnOn(14, 26);
        grid.turnOn(15, 21);
        grid.turnOn(15, 27);
        grid.turnOn(16, 21);
        grid.turnOn(16, 25);
        grid.turnOn(16, 27);
        grid.turnOn(16, 28);
        grid.turnOn(17, 21);
        grid.turnOn(17, 27);
        grid.turnOn(18, 22);
        grid.turnOn(18, 26);
        grid.turnOn(19, 23);
        grid.turnOn(19, 24);

        grid.turnOn(11, 35);
        grid.turnOn(12, 33);
        grid.turnOn(12, 35);
        grid.turnOn(13, 31);
        grid.turnOn(13, 32);
        grid.turnOn(14, 31);
        grid.turnOn(14, 32);
        grid.turnOn(15, 31);
        grid.turnOn(15, 32);
        grid.turnOn(16, 33);
        grid.turnOn(16, 35);
        grid.turnOn(17, 35);

        grid.turnOn(13, 45);
        grid.turnOn(13, 46);
        grid.turnOn(14, 45);
        grid.turnOn(14, 46);
    }

    /**
     * Counts the number of live neighbors around a cell.
     * 
     * @param r row index
     * @param c column index
     * @return number of live neighbors
     */
    private int countAlive(int r, int c) {
        int count = 0;
        //check every neighbor around cell at (r,c) with helper method test
        count += grid.test(r - 1, c - 1); // top-left
        count += grid.test(r - 1, c);     // top
        count += grid.test(r - 1, c + 1); // top-right
        count += grid.test(r, c - 1);     // left
        count += grid.test(r, c + 1);     // right
        count += grid.test(r + 1, c - 1); // bottom-left
        count += grid.test(r + 1, c);     // bottom
        count += grid.test(r + 1, c + 1); // bottom-right
        return count;
    }

    /**
     * Apply the update rules of Conway's Game of Life.
     * 
     * @param cell the cell to update
     * @param count number of live neighbors
     */
    private static void updateCell(Cell cell, int count) {
        if (cell.isOn()) {
            if (count < 2 || count > 3) {
                // Any live cell with fewer than two live neighbors dies,
                // as if by underpopulation.
                // Any live cell with more than three live neighbors dies,
                // as if by overpopulation.
                cell.turnOff();
            }
        } else {
            if (count == 3) {
                // Any dead cell with exactly three live neighbors
                // becomes a live cell, as if by reproduction.
                cell.turnOn();
            }
        }
    }

    /**
     * Counts the neighbors before changing anything.
     * 
     * @return number of neighbors for each cell
     */
    private int[][] countNeighbors() {
        int rows = grid.numRows();//get number of rows
        int cols = grid.numCols();//get number of columns

        int[][] counts = new int[rows][cols];//create new 2d array to hold neighbor counts
        for (int r = 0; r < rows; r++) {//loop through cells
            for (int c = 0; c < cols; c++) {
                counts[r][c] = countAlive(r, c);//use helper function to count how many are alive for cell (r,c)
            }
        }
        return counts;//return the the full grid
    }

    /**
     * Updates each cell based on neighbor counts.
     * 
     * @param counts number of neighbors for each cell
     */
    private void updateGrid(int[][] counts) {
        int rows = grid.numRows();//get number of rows and columns
        int cols = grid.numCols();

        for (int r = 0; r < rows; r++) {//loop through grid
            for (int c = 0; c < cols; c++) {
                Cell cell = grid.getCell(r, c);//get current Cell object at position (r,c)
                updateCell(cell, counts[r][c]);//call helper function to determine behavior of cell based on neighbors
            }
        }
    }

    /**
     * Simulates one round of Conway's Game of Life.
     */
    public void update() {
        int[][] counts = countNeighbors();
        updateGrid(counts);
    }

    /**
     * The simulation loop.
     */
    private void mainloop() {
        while (true) {

            // update the drawing
            this.update();
            grid.repaint();

            // delay the simulation
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // do nothing
            }
        }
    }

    /**
     * Creates and runs the simulation.
     * 
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        String title = "Conway's Game of Life";
        Conway game = new Conway();
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(game.grid);
        frame.pack();
        frame.setVisible(true);
        game.mainloop();
    }

}
