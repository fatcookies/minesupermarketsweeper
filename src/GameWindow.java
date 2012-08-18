import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: Uncalled For
 * Date: 18/08/12
 * Time: 14:37
 * To change this template use File | Settings | File Templates.
 */
public class GameWindow extends JFrame {

    public GameWindow(String title, int width, int height) {
        super(title);
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
    }
}
