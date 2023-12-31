import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeLeftTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard(4);
    }

    @Test
    public void testMergeLeftCase1() {
        int[][] initialBoardValues = {
                {0,1,1,0},
                {0,1,1,0},
                {0,1,1,0},
                {0,1,1,0}
        };

        int[][] expectedBoardValues = {
                {2,0,0,0},
                {2,0,0,0},
                {2,0,0,0},
                {2,0,0,0}
        };

        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveLeft();
        CellElement[][] actualBoard = gameBoard.getBoard();

        assertBoardValues(expectedBoardValues, actualBoard);
    }

    @Test
    public void testMergeLeftCase2() {
        int[][] initialBoardValues = {
                {0,1,1,2},
                {0,1,1,4},
                {0,1,1,8},
                {0,1,1,16}
        };

        int[][] expectedBoardValues = {
                {4,0,0,0},
                {2,4,0,0},
                {2,8,0,0},
                {2,16,0,0}
        };

        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveLeft();
        gameBoard.moveLeft();
        CellElement[][] actualBoard = gameBoard.getBoard();

        assertBoardValues(expectedBoardValues, actualBoard);
    }

    private void initializeGameBoard(GameBoard gameBoard, int[][] values) {
        CellElement[][] board = new CellElement[values.length][values[0].length];
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values[i].length; j++) {
                board[i][j] = new CellElement(values[i][j], true);
            }
        }
        gameBoard.setBoard(board);
    }

    private void assertBoardValues(int[][] expectedValues, CellElement[][] actualBoard) {
        for (int i = 0; i < expectedValues.length; i++) {
            for (int j = 0; j < expectedValues[i].length; j++) {
                assertEquals(expectedValues[i][j], actualBoard[i][j].getCellValue());
            }
        }
    }
}
