import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        final int BOARD_SIZE = 4;
        GameBoard gameBoard = new GameBoard(BOARD_SIZE);
        gameBoard.resetBoard();
        boolean gameCheck = true;

        while(gameCheck) {
            gameBoard.displayBoard();
            playerInput(gameBoard);
            gameCheck = false;
        }
    }

    private static void playerInput(GameBoard gameBoard) throws IOException {
        System.out.print("Player input 1 = up, 2 = down, 3 = left, 4 = right\n");
        int userInput = 0;
        boolean validInput = false;
        Scanner scanner = new Scanner(System.in);

        while (!validInput) {
            System.out.print("Please enter a number (up = 8, down = 2, left = 4, right = 6): \n");
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput >= 1 && userInput <= 4) {
                    validInput = true;
                } else {
                    System.out.println("Invalid input. Please enter 8, 2, 4, or 6.\n");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        System.out.println("You entered: " + userInput);
        playerMove(gameBoard, userInput);

        scanner.close();

    }

    private static void playerMove(GameBoard gameBoard, int userInput) {

        switch (userInput) {
            case 8:
                System.out.println("Moving up");
                break;
            case 2:
                System.out.println("Moving down");
                break;
            case 4:
                System.out.println("Moving left");
                gameBoard.moveLeft();
                break;
            case 6:
                System.out.println("Moving right");
                break;
            default:
                System.out.println("Unknown direction");
                break;
        }
    }
}

class GameBoard{
    private CellElement[][] board;
    int size = 0;

    public GameBoard(int size){
        this.size = size;
        board = new CellElement[size][size];
        initializeBoard();
    }

    public void initializeBoard() {

        for (CellElement[] arr : board){
            for(int i = 0; i <= arr.length - 1; i++){
                arr[i] = new CellElement(0, true);
            }
        }
    }

    public CellElement[][] getBoard() {
        return board;
    }

    public void displayBoard() {
        for(CellElement[] arr : board){
            System.out.print(" -----------------\n");
            for(CellElement idx : arr) {
                System.out.print(" | " + idx.getCellValue());
            }
            System.out.print(" | " + "\n");
        }
        System.out.print(" -----------------\n\n\n\n");
    }

    public void moveLeft() {
        for (CellElement[] arr : board) {
            ArrayList<Integer> tracker = new ArrayList<Integer>();
            for (int i = 1; i < arr.length; i++) {
                if (arr[i].getCellValue() > 0) { // Use '> 0' to avoid moving zeros
                    int idx = i - 1;
                    while (idx >= 0) {
                        if (arr[idx].getCellValue() == 0) {
                            arr[idx].setCellValue(arr[i].getCellValue());
                            arr[i].setCellValue(0);
                            i = idx; // Update the index to continue moving
                            idx--;
                        } else if (arr[idx].getCellValue() == arr[i].getCellValue() && arr[idx].getCanMerge()) {
                            arr[idx].setCellValue(arr[idx].getCellValue() + arr[i].getCellValue());
                            arr[i].setCellValue(0);
                            arr[idx].setCanMerge(false);
                            tracker.add(idx);
                            i = idx; // Update the index to continue moving
                            idx = -1; // Exit the while loop
                        } else {
                            idx = -1; // Exit the while loop
                        }
                    }
                }
            }
            for (int idx : tracker) {
                arr[idx].setCanMerge(true);
            }
            tracker.clear();
            displayBoard();
        }
    }


    public void resetBoard() {
        initializeBoard();
        resetValues();
    }

    private void resetValues() {
        Random random = new Random();

        for (CellElement[] arr : board) {
            int rowInserts = random.nextInt(size);
            for (int i = 0; i <= rowInserts - 1; i++) {
                arr[random.nextInt(size)].setCellValue((int) Math.pow(2, i));
            }
        }
    }

    public void setBoard(CellElement[][] initialBoard) {
        board = initialBoard;
    }
}

class CellElement {
    private int cellValue;
    private boolean canMerge;

    public CellElement(int cellValue, boolean canMerge){
        this.cellValue = cellValue;
        this.canMerge = canMerge;
    }

    public int getCellValue() {
        return cellValue;
    }

    public boolean getCanMerge() {
        return canMerge;
    }

    public void setCanMerge(boolean canMerge) {
        this.canMerge = canMerge;
    }

    public void setCellValue(int cellValue) {
        this.cellValue = cellValue;
    }

}

