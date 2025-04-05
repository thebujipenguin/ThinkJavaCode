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
        grid.turnOn(3, 1);
        grid.turnOn(4, 2);
        grid.turnOn(2, 3);
        grid.turnOn(3, 3);
        grid.turnOn(4, 3);
        grid.turnOn(7, 3);

        //indefinite glider gun
        grid.turnOn(25, 2);
        grid.turnOn(25, 3);
        grid.turnOn(26, 2);
        grid.turnOn(26, 3);
        grid.turnOn(23, 12);
        grid.turnOn(24, 12);
        grid.turnOn(25, 12);
        grid.turnOn(22, 13);
        grid.turnOn(26, 13);
        grid.turnOn(21, 14);
        grid.turnOn(27, 14);
        grid.turnOn(21, 15);
        grid.turnOn(27, 15);
        grid.turnOn(22, 16);
        grid.turnOn(26, 16);
        grid.turnOn(23, 17);
        grid.turnOn(25, 17);
        grid.turnOn(24, 18);
        grid.turnOn(24, 19);
        grid.turnOn(23, 20);
        grid.turnOn(25, 20);
        grid.turnOn(22, 22);
        grid.turnOn(23, 22);
        grid.turnOn(21, 23);
        grid.turnOn(23, 24);
        grid.turnOn(22, 24);
        grid.turnOn(21, 25);
        grid.turnOn(25, 35);
        grid.turnOn(26, 35);
        grid.turnOn(27, 35);
        grid.turnOn(24, 36);
        grid.turnOn(23, 37);
        grid.turnOn(25, 37);
        grid.turnOn(24, 38);
        grid.turnOn(22, 38);
        grid.turnOn(25, 38);
        grid.turnOn(24, 39);
        grid.turnOn(23, 40);
        grid.turnOn(22, 41);
        grid.turnOn(25, 41);
        grid.turnOn(26, 41);
        grid.turnOn(27, 41);
        grid.turnOn(25, 46);
        grid.turnOn(26, 46);
        grid.turnOn(27, 46);
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
        count += grid.test(r - 1, c - 1);
        count += grid.test(r - 1, c);
        count += grid.test(r - 1, c + 1);
        count += grid.test(r, c - 1);
        count += grid.test(r, c + 1);
        count += grid.test(r + 1, c - 1);
        count += grid.test(r + 1, c);
        count += grid.test(r + 1, c + 1);
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
        int rows = grid.numRows();
        int cols = grid.numCols();

        int[][] counts = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                counts[r][c] = countAlive(r, c);
            }
        }
        return counts;
    }

    /**
     * Updates each cell based on neighbor counts.
     * 
     * @param counts number of neighbors for each cell
     */
    private void updateGrid(int[][] counts) {
        int rows = grid.numRows();
        int cols = grid.numCols();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Cell cell = grid.getCell(r, c);
                updateCell(cell, counts[r][c]);
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
