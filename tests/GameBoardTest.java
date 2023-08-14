import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameBoardTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard(4); // Create a new GameBoard instance before each test
    }

    @Test
    public void testInitializeBoard() {
        CellElement[][] board = gameBoard.getBoard(); // Get the board from the GameBoard instance

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                CellElement cell = board[i][j];
                assertEquals(0, cell.getCellValue()); // Check if the cell value is initialized to 0
                assertTrue(cell.getCanMerge()); // Check if the canMerge flag is true
            }
        }
    }

    // Other test methods...
}
