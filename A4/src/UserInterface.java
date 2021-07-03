/**
 * @File: UserInterface.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: view module that displays the status of the game
 */

package src;

import java.util.Arrays;

// Note Citing: some of the print methods are cited from 2020 Assignment4 to save time.
public class UserInterface {
    private static UserInterface visual = null;

    /**
     * @brief constructor
     */
    private UserInterface(){}

    /**
     * @brief public static method for obtaining a single instance
     * @return an UserInterface object
     */
    public static UserInterface getInstance() {
        if (visual == null) {
            return visual = new UserInterface();
        }
        return visual;
    }

    /**
     * @brief display a welcome message
     */
    public void printWelcomeMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("                 Welcome to 2048                 ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a prompt asking user to give a board size
     */
    public void printGameSizePrompt(){
        System.out.println("                     New game                    ");
        System.out.println("                 -----------------               ");
        System.out.println("");
        System.out.println("                Choose a game size:              ");
        System.out.println("            Please enter a small number          ");
        System.out.println("         Note: if you input is NOT a number,     ");
        System.out.println("      a default game of size 4 will be started.  ");
    }

    /**
     * @brief Display the board on the screen
     * @param board - the game board 2d array
     * @param score - the game score
     */
    public void printBoard(int[][] board, int score){
        System.out.println("-------------------------------------------------");
        System.out.println("Current Score: " + score);
        System.out.println("Current Board: ");
        int[][] boardDisplay = transpose(mirrorLeftRight(board));
        System.out.println(Arrays.deepToString(boardDisplay).replace("], ", "]\n"));
        System.out.println("Please enter next move direction: w, a, s, d     ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message after user winning
     */
    public void printWinningMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("          Congratulation !!! You win!!!          ");
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message after user loosing
     * @param score the game score
     */
    public void printLosingMessage(int score){
        System.out.println("-------------------------------------------------");
        System.out.println("   Cannot move to any direction, you loose!      ");
        System.out.println("         Final Score:        " + score);
        System.out.println("-------------------------------------------------");
    }

    /**
     * @brief display a message after the game end
     */
    public void printEndingMessage(){
        System.out.println("-------------------------------------------------");
        System.out.println("             Thank You For Playing !!!           ");
        System.out.println("-------------------------------------------------");
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
}
