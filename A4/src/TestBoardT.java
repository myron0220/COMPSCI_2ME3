/**
 * @File: TestBoardT.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: test for BoardT
 */

package src;

import org.junit.*;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

public class TestBoardT{

    private BoardT board1;

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

    @Before
    public void setUp() {
        this.board1 = new BoardT(4);

    }

    @After
    public void tearDown() {
        this.board1 = null;
    }

    @Test 
    public void testSetBoard() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[0][1] = 0;
        testArr[0][2] = 1024;
        board1.setBoard(testArr);
        assertTrue(board1.getBoard()[0][1] == 0);
        assertTrue(board1.getBoard()[0][2] == 1024);
    }

    @Test 
    public void testSetScore(){
        board1.setScore(5);
        assertEquals(board1.getScore(), 5);
    }

    @Test 
    public void getEmptyCells() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 1;
            }
        }
        testArr[0][0] = 0;
        board1.setBoard(testArr);

        ArrayList<CellT> expCells = new ArrayList<>();
        expCells.add(new CellT(0, 0));
        assertEquals(board1.getEmptyCells().get(0).getX(), expCells.get(0).getX());
        assertEquals(board1.getEmptyCells().get(0).getY(), expCells.get(0).getY());
    }

    @Test 
    public void testDown() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[0][0] = 1024;
        testArr[0][1] = 1024;
        testArr[0][2] = 2;
        testArr[0][3] = 2;
        board1.setBoard(testArr);
        board1.down();
        int[][] ans = board1.getBoard();
        assertTrue(ans[0][0] == 2048);
        assertTrue(ans[0][1] == 4);
    }

    @Test 
    public void testUp() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[0][0] = 1024;
        testArr[0][1] = 1024;
        testArr[0][2] = 2;
        testArr[0][3] = 2;
        board1.setBoard(testArr);
        board1.up();
        int[][] ans = board1.getBoard();
        assertTrue(ans[0][0] == 0);
        assertTrue(ans[0][1] == 0);
    }

    @Test 
    public void testLeft() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[0][0] = 1024;
        testArr[0][1] = 1024;
        testArr[0][2] = 2;
        testArr[0][3] = 2;
        board1.setBoard(testArr);
        board1.left();
        int[][] ans = board1.getBoard();
        assertTrue(ans[0][0] == 1024);
        assertTrue(ans[0][1] == 1024);
    }

    @Test 
    public void testRight() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[1][0] = 8;
        testArr[2][0] = 4;
        testArr[3][3] = 2;
        testArr[0][3] = 2;
        board1.setBoard(testArr);
        board1.right();
        int[][] ans = board1.getBoard();
        assertTrue(ans[3][3] == 4);
        assertTrue(ans[3][0] == 4);
    }

    @Test 
    public void testGenRand2Or4() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[1][0] = 8;
        testArr[2][0] = 4;
        testArr[3][3] = 2;
        testArr[0][3] = 2;
        board1.setBoard(testArr);
        // should not be changed.
        assertTrue(board1.getBoard()[2][0] == 4);
        assertTrue(board1.getBoard()[1][0] == 8);
        assertTrue(board1.getBoard()[3][3] == 2);
        assertTrue(board1.getBoard()[0][3] == 2);
    }

    @Test 
    public void testIsWin() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[1][0] = 8;
        testArr[2][0] = 4;
        testArr[3][3] = 2;
        testArr[0][3] = 2048;
        board1.setBoard(testArr);
        assertTrue(board1.isWin());
    }

    @Test 
    public void testIsLoose() {
        int[][] testArr = new int[board1.getSize()][];
        for (int x = 0; x < board1.getSize(); x++) {
            testArr[x] = new int[board1.getSize()];
            for (int y = 0; y < board1.getSize(); y++) {
                testArr[x][y] = 0;
            }
        }
        testArr[0][0] = 2;
        testArr[0][1] = 4;
        testArr[0][2] = 8;
        testArr[0][3] = 16;
        testArr[1][0] = 32;
        testArr[1][1] = 64;
        testArr[1][2] = 128;
        testArr[1][3] = 256;
        testArr[2][0] = 512;
        testArr[2][1] = 2;
        testArr[2][2] = 16;
        testArr[2][3] = 8;
        testArr[3][0] = 256;
        testArr[3][1] = 1024;
        testArr[3][2] = 256;
        testArr[3][3] = 512;
        board1.setBoard(testArr);
        assertTrue(board1.isLoose());
    }
}