/**
 * @File: CellT.java
 * @Author: Mingzhe Wang - wangm235
 * @Date: Apr 14, 2021
 * @Description: a CellT ADT that used to act like a pair class to record the coordinate.
 */

package src;

/**
 * @brief a class used to act like a pair of coordinates.
 */
public class CellT {
    private final int x;
    private final int y;

    /**
     * @brief constructor
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public CellT(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @brief getter for the x coordinate
     * @return x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * @brief getter for the y coordinate
     * @return y coordinate
     */
    public int getY() {
        return y;
    }
}
