import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MergeRightTest {

    private GameBoard gameBoard;

    @BeforeEach
    public void setUp() {
        gameBoard = new GameBoard(4);
    }

    @Test
    public void testMergeRightCase1() {
        int[][] initialBoardValues = {
                {0,1,1,0},
                {0,1,1,0},
                {0,1,1,0},
                {0,1,1,0}
        };

        int[][] expectedBoardValues = {
                {0,0,0,2},
                {0,0,0,2},
                {0,0,0,2},
                {0,0,0,2}
        };

        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveRight();
        CellElement[][] actualBoard = gameBoard.getBoard();

        assertBoardValues(expectedBoardValues, actualBoard);
    }

    @Test
    public void testMergeRightCase2() {
        System.out.println("Starting merge2");
        int[][] initialBoardValues = {
                {0,1,1,2},
                {0,1,1,4},
                {0,1,1,8},
                {0,1,1,16}
        };

        int[][] expectedBoardValues = {
                {0,0,0,4},
                {0,0,2,4},
                {0,0,2,8},
                {0,0,2,16}
        };

        initializeGameBoard(gameBoard, initialBoardValues);
        gameBoard.moveRight();
        gameBoard.moveRight();
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
    }


}
