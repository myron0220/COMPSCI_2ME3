/**
 * @File: UserInterface.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: controller module that handles inputs and links model and view module
 */

package src;

import java.util.InputMismatchException;
import java.util.Scanner;

// Note Citing: some of the methods are cited from 2020 Assignment4 to save time.
public class GameController {
    private BoardT model;
    private UserInterface view;
    private static GameController controller = null;
    private static Scanner keyboard = new Scanner(System.in);

    /**
     * @brief constructor
     * @param model - model module (BoardT)
     * @param view - view module (UseInterface)
     */
    public GameController(BoardT model, UserInterface view) {
        this.model = model;
        this.view = view;
    }

    /**
     * @brief public static method for obtaining a single instance
     * @return the single GameController object
     */
    public static GameController getInstance(BoardT model, UserInterface view) {
        if (controller == null) {
            controller = new GameController(model, view);
        }
        return controller;
    }

    /**
     * @brief takes the input from the user through keyboard
     * @return gameSize if the user input a number, otherwise return 4, which is the default game size.
     */
    public int readGameSizeInput() {
        if (keyboard.hasNextInt()) {
            int gameSize = keyboard.nextInt();
            return gameSize;
        } else {
            //jump over this line of input
            keyboard.nextLine();
            return 4;
        }
    }

    /**
     * @brief initialize the game
     * @param  size the game size for the new game
     */
    public void initializeGame(int size) {
        this.model = new BoardT(size);
    }

    /**
     * @brief runs the game
     */
    public void runGame(){
        displayWelcomeMessage();
        displayGameSizePrompt();
        initializeGame(readGameSizeInput());

        // winning test
//        int[][] testArr = new int[model.getSize()][];
//        for (int x = 0; x < model.getSize(); x++) {
//            testArr[x] = new int[model.getSize()];
//            for (int y = 0; y < model.getSize(); y++) {
//                testArr[x][y] = 0;
//            }
//        }
//        testArr[0][0] = 1024;
//        testArr[0][1] = 1024;
//        testArr[0][2] = 1024;
//        model.setBoard(testArr);
        // winning test

        for (int i = 0; i < 2; i++) {
            model.genRand2Or4();
        }
        displayBoard();

        do {
            // import proof correctness:
            // !isLoose() => exists at least one empty cell after move to the direction that can change current board.
            if (model.isLoose()) {
                displayLosingMessage();
                break;
            }
            try {
                // model.testMove();
                model.move(readUserMove());
            }
            catch (InputMismatchException e) {
                System.out.println("Not a valid direction. : )");
                System.out.println("Please re-enter: w, a, s, d");
                continue;
            }
            if (model.isWin()) {
                displayBoard();
                displayWinningMessage();
                break;
            }
            if (model.isGen24Flag()) {
                model.genRand2Or4();
            } else {
                System.out.println("NOT CHANGE");
            }
            displayBoard();
        } while (true);
        displayEndingMessage();
    }

    /**
     * @brief updates the view module to display a welcome message
     */
    public void displayWelcomeMessage(){
        view.printWelcomeMessage();
    }

    /**
     * @brief updates the view module to display the board
     */
    public void displayBoard(){
        view.printBoard(model.getBoard(), model.getScore());
    }

    /**
     * @brief updates the view module to display the winning message
     */
    public void displayWinningMessage(){
        view.printWinningMessage();
    }

    /**
     * @brief updates the view module to display the losing message
     */
    public void displayLosingMessage(){
        view.printLosingMessage(model.getScore());
    }

    /**
     * @brief updates the view module to display the ending message
     */
    public void displayEndingMessage(){
        view.printEndingMessage();
    }

    /**
     * @brief updates the view module to display the prompt for game size
     */
    public void displayGameSizePrompt(){
        view.printGameSizePrompt();
    }

    /**
     * @brief read the user's input from the keyboard
     * @return MoveDirection.UP if user enters "w"
     *         MoveDirection.LEFT if user enters "a"
     *         MoveDirection.DOWN if user enters "s"
     *         MoveDirection.RIGHT if user enters "d"
     *         exit to the terminal if user enters "exit"
     * @throws InputMismatchException if user's input is not these cases.
     */
    private MoveDirection readUserMove() throws InputMismatchException {
        String input = keyboard.next();
        input = input.toLowerCase();
        switch (input) {
            case "w":
                return MoveDirection.UP;
            case "a":
                return MoveDirection.LEFT;
            case "s":
                return MoveDirection.DOWN;
            case "d":
                return MoveDirection.RIGHT;
            case "exit":
                System.exit(0);
            default:
                throw new InputMismatchException("Please enter a direction: w, a, s, d");
        }
    }
}
