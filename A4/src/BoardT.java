/**
 * @File: UserInterface.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: a model module for storing the state and status of the game
 */

package src;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

// Note: all game operations' direction is based on the user's view.
//       all array/matrix operations' direction is based on the coordinate.
//
//     user's view and coordinate
//     y ^
//       |
//       |
//       |
//      o ------------->
//                     x
public class BoardT {
    private int[][] board;
    private int score;
    private boolean gen24Flag;

    /**
     * @brief constructor
     * @details generates a board with 0 sore and an empty board array of the input size
     * @param gameSize intended board size
     */
    public BoardT(int gameSize) {
        this.board = new int[gameSize][];
        for (int x = 0; x < gameSize; x++) {
            this.board[x] = new int[gameSize];
            for (int y = 0; y < gameSize; y++) {
                board[x][y] = 0;
            }
        }
        this.score = 0;
        this.gen24Flag = true;
    }

    /**
     * @brief set the board array of an BoardT object
     * @param board a new board array
     */
    public void setBoard(int[][] board) {
        for (int x = 0; x < board.length; x++) {
            this.board[x] = new int[board.length];
            for (int y = 0; y < board.length; y++) {
                this.board[x][y] = board[x][y];
            }
        }
    }

    /**
     * @brief set the score of an BoardT object
     * @param score a new score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @brief a getter for board field
     * @return a new 2D array that represents the current board
     */
    public int[][] getBoard() {
        int[][] newBoard = new int[board.length][];
        for (int x = 0; x < board.length; x++) {
            newBoard[x] = new int[board.length];
            for (int y = 0; y < board.length; y++) {
                newBoard[x][y] = board[x][y];
            }
        }
        return newBoard;
    }

    /**
     * @brief a getter for score field
     * @return the current game score
     */
    public int getScore() {
        return score;
    }

    /**
     * @brief a getter for game size
     * @return the current game size
     */
    public int getSize() {
        return board.length;
    }

    /**
     * @brief get all empty (with number 0) cells (pairs of coordinate of the current board)
     * @return all cells (pairs of coordinate of the current board)
     */
    public ArrayList<CellT> getEmptyCells() {
        ArrayList<CellT> cells = new ArrayList<>();
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] == 0) {
                    CellT cell = new CellT(x, y);
                    cells.add(cell);
                }
            }
        }
        return cells;
    }

    /**
     * @brief a getter for the gen24Flag field
     * @return the current gen24Flag value
     */
    public boolean isGen24Flag() {
        return gen24Flag;
    }

    // return a new board[][] representing the board after move down.
    // direction based on user's view
    /**
     * @brief simulate the process of the user's moveDown operation
     * @return a new 2D array represents the board after the operation
     */
    private int[][] moveDown() {
        int[][] newBoard = new int[board.length][];
        for (int x = 0; x < board.length; x++) {
            LinkedList<Integer> curColumn = new LinkedList<>();
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] > 0) {
                    curColumn.add(board[x][y]);
                }
            }

            LinkedList<Integer> newColumn = new LinkedList<>();
            while (curColumn.size() >= 2) {
                int firstNum = curColumn.pop();
                int secondNum = curColumn.peek();
                if (firstNum == secondNum) {
                    newColumn.add(firstNum * 2);
                    // may need update the score here
                    score += (firstNum * 2);
                    curColumn.pop();
                } else {
                    newColumn.add(firstNum);
                }
            }
            // if there is only one num
            newColumn.addAll(curColumn);

            newBoard[x] = new int[board.length];
            for (int y = 0; y < board.length; y++) {
                if (newColumn.isEmpty()) {
                    newBoard[x][y] = 0;
                } else {
                    newBoard[x][y] = newColumn.pop();
                }
            }
        }

//        test if statement
//        System.out.println("newBoard");
//        System.out.println(Arrays.deepToString(newBoard).replace("], ", "]\n"));
//        System.out.println("board");
//        System.out.println(Arrays.deepToString(board).replace("], ", "]\n"));
        if (compareTwoInt2DArr(board, newBoard)) {
            this.gen24Flag = false;
//            System.out.println("NOT CHANGE");
        } else {
            this.gen24Flag = true;
        }
        return newBoard;
    }

    // direction based on user's view
    /**
     * @brief a wrapper for moveDown
     * @details the down operation is corresponding to:
     *          call moveDown to update the current board
     */
    public void down() {
        board = moveDown();
    }

    /**
     * @brief a wrapper for moveUp
     * @details the up operation is corresponding to:
     *          1. mirrorLeftRight of the current board
     *          2. call moveDown()
     *          3. reverse the mirror
     */
    // direction based on user's view
    public void up() {
        board = mirrorLeftRight(board);
        board = mirrorLeftRight(moveDown());
    }

    /**
     * @brief a wrapper for moveLeft
     * @details the left operation is corresponding to:
     *          1. transpose of the current board
     *          2. call moveDown()
     *          3. reverse the transpose
     */
    // direction based on user's view
    public void left() {
        board = transpose(board);
        board = transpose(moveDown());
    }

    /**
     * @brief a wrapper for moveRight
     * @details the right operation is corresponding to:
     *          1. transpose of the current board
     *          2. mirrorLeftRight of the current board
     *          3. call moveDown()
     *          4. reverse the mirrorLeftRight
     *          5. reverse the transpose
     */
    // direction based on user's view
    public void right() {
        board = transpose(board);
        board = mirrorLeftRight(board);
        board = transpose(mirrorLeftRight(moveDown()));
    }

    /**
     * @brief simulate a random user's movement only for test reason.
     */
    public void testMove() {
        Random random = new Random();
        double testValue = random.nextDouble();
        if (testValue < 0.25) {
            move(MoveDirection.LEFT);
        }
        else if (testValue < 0.5) {
            move(MoveDirection.RIGHT);
        }
        else if (testValue < 0.75) {
            move(MoveDirection.UP);
        }
        else {
            move(MoveDirection.DOWN);
        }
    }

    /**
     * @brief a move detector to find the correct move bases on user's input
     * @param moveDirection a enum object
     */
    // a wrapper to make move easy
    public void move(MoveDirection moveDirection) {
        switch (moveDirection) {
            case UP:
                System.out.println("move up");
                up();
                break;
            case DOWN:
                System.out.println("move down");
                down();
                break;
            case LEFT:
                System.out.println("move left");
                left();
                break;
            case RIGHT:
                System.out.println("move right");
                right();
                break;
        }
    }

    /**
     * @brief generate 2 or 4 with possibility of 90% and 10%, respectively in the empty cells
     */
    public void genRand2Or4() {
        Random rand = new Random();
        ArrayList<CellT> emptyCells =  getEmptyCells();
        CellT selectedCell = emptyCells.get(rand.nextInt(emptyCells.size()));
        if (rand.nextDouble() >= 0.9) {
            board[selectedCell.getX()][selectedCell.getY()] = 4;
        } else {
            board[selectedCell.getX()][selectedCell.getY()] = 2;
        }
    }

    /**
     * @brief detect if the user is winning
     * @return  true, if win;
     *          false, otherwise.
     */
    public boolean isWin() {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board.length; y++) {
                if (board[x][y] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @brief detect if the user is loosing
     * @return  true, if loose
     *          false, otherwise
     */
    public boolean isLoose() {
        BoardT refBoardT = new BoardT(board.length);
        refBoardT.setBoard(board);

        int[][] oldBoard = refBoardT.getBoard();

        // if moving up change the board, not loose. Game continues.
        refBoardT.up();
        int[][] newBoard = refBoardT.getBoard();
        if (!compareTwoInt2DArr(oldBoard, newBoard)) {
            return false;
        }
        // if moving down change the board, not loose. Game continues.
        refBoardT.down();
        newBoard = refBoardT.getBoard();
        if (!compareTwoInt2DArr(oldBoard, newBoard)) {
            return false;
        }
        // if moving left change the board, not loose. Game continues.
        refBoardT.left();
        newBoard = refBoardT.getBoard();
        if (!compareTwoInt2DArr(oldBoard, newBoard)) {
            return false;
        }
        // if moving right change the board, not loose. Game continues.
        refBoardT.right();
        newBoard = refBoardT.getBoard();
        if (!compareTwoInt2DArr(oldBoard, newBoard)) {
            return false;
        }
        // if all directions cannot change the board, loose. Game ends.
        return true;
    }

    // local function
    // direction based on coordinate
    private static int[][] transpose(int[][] inputArr) {
        int[][] outputArr = new int[inputArr.length][];

        for (int x = 0; x < inputArr.length; ++x) {
            outputArr[x] = new int[inputArr[0].length];
            for (int y = 0; y < inputArr[0].length; ++y) {
                outputArr[x][y] = inputArr[y][x];
            }
        }

        return outputArr;
    }

    // local function
    // direction based on coordinate
    private static int[][] mirrorLeftRight(int[][] inputArr) {
        int[][] outputArr = new int[inputArr.length][];

        for (int x = 0; x < inputArr.length; ++x) {
            outputArr[x] = new int[inputArr[0].length];
            for (int y = 0; y < inputArr[0].length; ++y) {
                outputArr[x][y] = inputArr[x][inputArr.length - y - 1];
            }
        }

        return outputArr;
    }

    private boolean compareTwoInt2DArr(int[][] arr1, int[][] arr2) throws IllegalArgumentException {
        if (arr1.length != arr2.length) {
            throw new IllegalArgumentException("Two arrays' lengths are different");
        }
        for (int x = 0; x < arr1.length; x++) {
            if (!Arrays.equals(arr1[x], arr2[x])) {
                return false;
            }
        }
        return true;
    }
}
