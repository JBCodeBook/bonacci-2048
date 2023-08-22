import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeDownTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard(4);
    }

    @Test
    public void testMergeDownCase1() {
        int[][] initialBoardValues = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0}
        };

        int[][] expectedBoardValues = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 2, 2, 0}
        };
        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveDown();
        CellElement[][] actualBoard = gameBoard.getBoard();

        assertBoardValues(expectedBoardValues, actualBoard);
    }

    @Test
    public void testMergeDownCase2() {
        int[][] initialBoardValues = {
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {0, 1, 1, 0}
        };

        int[][] expectedBoardValues = {
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 4, 4, 0}
        };
        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveDown();
        gameBoard.moveDown();
        CellElement[][] actualBoard = gameBoard.getBoard();

        assertBoardValues(expectedBoardValues, actualBoard);
    }

    private void initializeGameBoard(GameBoard gameBoard, int[][] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                gameBoard.getBoard()[i][j] = new CellElement(values[i][j], true);
            }
        }
    }

    private void assertBoardValues(int[][] expectedValues, CellElement[][] actualBoard) {
        gameBoard.displayBoard();
        for (int i = 0; i < expectedValues.length; i++) {
            for (int j = 0; j < expectedValues[i].length; j++) {
                assertEquals(expectedValues[i][j], actualBoard[i][j].getCellValue());
            }
        }
    }}
