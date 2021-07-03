/**
 * @File: Demo.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: Runs the game (client code)
 */

package src;

public class Demo {
    public static void main(String[] args) {
        GameController game = GameController.getInstance(new BoardT(4), UserInterface.getInstance());
        game.runGame();
//        BoardT board = new BoardT(4);
//        UserInterface boardView = new UserInterface();
//        int[][] testArr = new int[board.getSize()][];
//        for (int x = 0; x < board.getSize(); x++) {
//            testArr[x] = new int[board.getSize()];
//            for (int y = 0; y < board.getSize(); y++) {
//                testArr[x][y] = 0;
//            }
//        }
//        testArr[0][0] = 2;
//        testArr[0][1] = 4;
//        testArr[0][2] = 4;
//        testArr[0][3] = 2;
//        testArr[1][0] = 4;
//        testArr[3][3] = 2;
//        board.setBoard(testArr);
//        boardView.printBoard(board.getBoard(), board.getScore());
////        board.down();
////        board.up();
////        board.left();
//        board.right();
//        board.genRand2Or4();
//        boardView.printBoard(board.getBoard(), board.getScore());
    }
}
