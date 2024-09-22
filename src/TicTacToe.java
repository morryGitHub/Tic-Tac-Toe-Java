import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

public class TicTacToe extends JComponent {
    public static final int FIELD_EMPTY = 0;
    public static final int FIELD_X = 10;
    public static final int FIELD_O = 200;
    public static final int count = 3;
    double[][] field;
    int[] winningLine = new int[4]; // To store x1, y1, x2, y2
    boolean isXturn;
    JFrame window;

    public TicTacToe(JFrame window) {
        this.window = window;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);
        field = new double[count][count];
        initGame();
    }

    public void initGame() {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                field[i][j] = FIELD_EMPTY;
            }
        }
        isXturn = true;
        winningLine[0] = winningLine[1] = winningLine[2] = winningLine[3] = 0; // Reset winning line
    }


    public void updateWindowTitle() {
        String message = isXturn ? "X's Turn" : "O's Turn";
        window.setTitle("TicTacToe - " + message);

    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.clearRect(0, 0, getWidth(), getHeight());
        drawGrid(graphics);
        drawXO(graphics);

        // Draw winning line if there is a winner
        if (winningLine[0] != 0 || winningLine[1] != 0 || winningLine[2] != 0 || winningLine[3] != 0) {
            Graphics2D g2d = (Graphics2D) graphics;
            g2d.setStroke(new BasicStroke(5)); // Set line thickness
            g2d.setColor(Color.RED); // Set color for the winning line
            g2d.drawLine(winningLine[0], winningLine[1], winningLine[2], winningLine[3]);
        }
    }


    void drawGrid(Graphics graphics) {
        int w = getWidth(); // ширина
        int h = getHeight(); // высота
        int dw = w / count;
        int dh = h / count;
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(2));
        graphics.setColor(Color.BLACK);
        for (int i = 1; i < count; i++) {
            graphics.drawLine(0, dh * i, w, dh * i); // горизонтальная линиия
            graphics.drawLine(dw * i, 0, dw * i, h); // вертикальная линиия
        }
    }

    @Override
    protected void processMouseEvent(MouseEvent mouseEvent) {
        super.processMouseEvent(mouseEvent);
        if (mouseEvent.getID() == MouseEvent.MOUSE_CLICKED) {
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            // переводим координаты в индексы в массиве field
            int i = (int) ((float) x / getWidth() * 3);
            int j = (int) ((float) y / getHeight() * 3);

            // проверка пустая ли ячейка
            if (field[i][j] == FIELD_EMPTY) {
                // Если ячейка пуста, выполняем ход
                field[i][j] = isXturn ? FIELD_X : FIELD_O;
                isXturn = !isXturn; // меняем ход
                updateWindowTitle();
                repaint(); // перерисовка элемента

                // Проверяем состояние игры
                int res = checkStatus();
                if (res != 0) {
                    // Проверка победы игрока O
                    if (res == FIELD_O * 3) {
                        window.setTitle("TicTacToe - " + "Player X Wins!");
                        JOptionPane.showMessageDialog(this, "Player O Wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                        isXturn = true;
                        updateWindowTitle();
                    }
                    // Проверка победы игрока X
                    else if (res == FIELD_X * 3) {
                        window.setTitle("TicTacToe - " + "Player X Wins!");
                        JOptionPane.showMessageDialog(this, "Player X Wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                         isXturn = true;
                        updateWindowTitle();
                    }
                    // Проверка ничьей
                    else if (res == -1) {
                        window.setTitle("TicTacToe - " + "It's a Draw!");
                        JOptionPane.showMessageDialog(this, "It's a Draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                         isXturn = true;
                        updateWindowTitle();
                    }

                    // Перезапускаем игру после завершения
                    initGame();
                    repaint();
                }
            }
        }
    }


    void drawX(int i, int j, Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(7)); // Устанавливаем толщину линий
        g2d.setColor(Color.BLACK);
        int dw = getWidth() / count;
        int dh = getHeight() / count;
        int padding = dw / 10; // Добавляем отступы для уменьшения размеров
        int x = i * dw + padding;
        int y = j * dh + padding;
        dw -= 2 * padding;
        dh -= 2 * padding;
        // линия от верхнего левого угла в правый нижний
        g2d.drawLine(x, y, x + dw, y + dh);
        // линия от левого нижнего угла до правого верхнего
        g2d.drawLine(x, y + dh, x + dw, y);
    }

    void drawO(int i, int j, Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setStroke(new BasicStroke(7)); // Устанавливаем толщину линии
        g2d.setColor(Color.BLACK);
        int dw = getWidth() / count;
        int dh = getHeight() / count;
        int padding = dw / 10; // Добавляем отступы для уменьшения размеров
        int x = i * dw + padding;
        int y = j * dh + padding;
        dw -= 2 * padding;
        dh -= 2 * padding;
        g2d.drawOval(x, y, dw, dh); // Рисуем круг с учетом отступов
    }

    void drawXO(Graphics graphics) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                if (field[i][j] == FIELD_X) {
                    drawX(i, j, graphics);
                } else if (field[i][j] == FIELD_O) {
                    drawO(i, j, graphics);
                }
            }
        }
    }

    int checkStatus() {
        int diag = 0;
        int diag2 = 0;
        boolean hasEmpty = false;

        // Check diagonals
        for (int i = 0; i < count; i++) {
            diag += (int) field[i][i];
            diag2 += (int) field[i][2 - i];
        }
        if (diag == FIELD_O * 3 || diag == FIELD_X * 3) {
            winningLine[0] = 0; // Start x
            winningLine[1] = 0; // Start y
            winningLine[2] = getWidth(); // End x
            winningLine[3] = getHeight(); // End y
            return diag;
        }
        if (diag2 == FIELD_O * 3 || diag2 == FIELD_X * 3) {
            winningLine[0] = 0; // Start x
            winningLine[1] = getHeight(); // Start y
            winningLine[2] = getWidth(); // End x
            winningLine[3] = 0; // End y
            return diag2;
        }

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            int check_i = 0, check_j = 0;
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == FIELD_EMPTY) {
                    hasEmpty = true; // Update hasEmpty if there's an empty cell
                }
                check_i += (int) field[i][j];
                check_j += (int) field[j][i];
            }
            // Check rows for win
            if (check_j == FIELD_O * 3 || check_j == FIELD_X * 3) {
                winningLine[0] = 0; // Start x
                winningLine[1] = (int) ((i + 0.5) * ((double) getHeight() / count)); // Start y (верхня частина рядка)
                winningLine[2] = getWidth(); // End x (правий край компонента)
                winningLine[3] = (int) ((i + 0.5) * ((double) getHeight() / count)); // End y (нижня частина рядка)
                return check_j;
            }
            // Check columns for win
            if (check_i == FIELD_O * 3 || check_i == FIELD_X * 3) {
                winningLine[0] = (int) ((i + 0.5) * ((double) getWidth() / count)); // Start x (лівий край стовпчика)
                winningLine[1] = 0; // Start y (верхня частина компонента)
                winningLine[2] = (int) ((i + 0.5) * ((double) getWidth() / count)); // End x (лівий край стовпчика)
                winningLine[3] = getHeight(); // End y (нижня частина компонента)
                return check_i;
            }


        }
        return hasEmpty ? 0 : -1; // Return 0 if there are empty cells, -1 if it's a draw
    }
}
