import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Uncalled For
 * Date: 18/08/12
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class Cell extends JButton {

    private boolean isMine;
    private boolean beenClicked;
    private boolean isFlagged;
    private int x,y;

    public Cell(int x, int y) {
        this.isMine = isMine;
        this.x = x;
        this.y = y;
        beenClicked = false;
        isFlagged = false;
    }

    public void setMine() {
        isMine = true;
       // setText("x"); // cheat mode
    }

    public boolean isMine() {
        return isMine;
    }

    public boolean isRevealed() {
        return beenClicked;
    }

    public int getx() {
        return x;
    }

    public int gety() {
        return y;
    }

    public void reveal() {
        beenClicked = true;
        if(isMine) {
            setText("x");
        } else {
            setEnabled(false);
        }

    }

    public boolean flag() {
        isFlagged = !isFlagged;
        if(isFlagged) {
            setText("|");
        } else {
           setText("");
        }
        return isFlagged;
    }

    @Override
    public String toString() {
     return x + "," + y;
    }
}
