import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Game started");

        JFrame window = new JFrame("TicTacToe"); //main menu
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // добавляем кнопку Х, чтобы закрыть окно
        window.setSize(400, 400); // размер окна
        window.setLayout(new BorderLayout()); // менеджиент компановки
        window.setLocationRelativeTo(null); // чтобы окно было по центру
        window.setVisible(true); // видимость окна
        TicTacToe game = new TicTacToe(window);
        window.add(game);

        System.out.println("Game over");
    }
}