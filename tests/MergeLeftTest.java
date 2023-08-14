import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeLeftTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        CellElement[][] initialBoard = {
                {new CellElement(0, true), new CellElement(2, true), new CellElement(0, true), new CellElement(2, true)},
                {new CellElement(1, true), new CellElement(1, true), new CellElement(0, true), new CellElement(0, true)},
                {new CellElement(0, true), new CellElement(0, true), new CellElement(0, true), new CellElement(0, true)},
                {new CellElement(8, true), new CellElement(8, true), new CellElement(8, true), new CellElement(8, true)}
        };

        gameBoard = new GameBoard(4);
        gameBoard.setBoard(initialBoard);
    }

    @Test
    public void testMergeLeft() {
        CellElement[][] expectedBoard = {
                {new CellElement(4, true), new CellElement(0, true), new CellElement(0, true), new CellElement(0, true)},
                {new CellElement(2, true), new CellElement(0, true), new CellElement(0, true), new CellElement(0, true)},
                {new CellElement(0, true), new CellElement(0, true), new CellElement(0, true), new CellElement(0, true)},
                {new CellElement(16, true), new CellElement(16, true), new CellElement(0, true), new CellElement(0, true)}
        };

        gameBoard.moveLeft(); // Perform the operation you want to test
        CellElement[][] actualBoard = gameBoard.getBoard();

        // Compare the actual board with the expected board
        for (int i = 0; i < expectedBoard.length; i++) {
            for (int j = 0; j < expectedBoard[i].length; j++) {
                assertEquals(expectedBoard[i][j].getCellValue(), actualBoard[i][j].getCellValue());
                assertEquals(expectedBoard[i][j].getCanMerge(), actualBoard[i][j].getCanMerge());
            }
        }
    }
}
