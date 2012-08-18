/**
 * Created with IntelliJ IDEA.
 * User: Uncalled For
 * Date: 18/08/12
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class Main {

    public static void main(String[] args) {
        Board b = new Board(8,8,10);
        b.resetBoard();
        GameWindow g = new GameWindow("mine",300,300);
        g.add(b);
        g.setVisible(true);
    }
}
