package app;

import javax.swing.SwingUtilities;
import view.Login1;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login1::new);
    }
}
