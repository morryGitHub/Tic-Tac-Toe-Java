<h1>TicTacToe Game</h1>

<p>This is a simple TicTacToe game implemented in Java using the Swing library for the graphical user interface. Players take turns to place their marks (X or O) on a 3x3 grid, aiming to be the first to form a horizontal, vertical, or diagonal line.</p>

<h2>Features</h2>
<ul>
    <li>Two-player gameplay</li>
    <li>Graphical interface using Java Swing</li>
    <li>Winning line indication</li>
    <li>Draw detection</li>
</ul>

<h2>Requirements</h2>
<ul>
    <li>Java Development Kit (JDK) 8 or higher</li>
    <li>An IDE or text editor for Java development (e.g., IntelliJ IDEA, Eclipse, or Visual Studio Code)</li>
</ul>

<h2>Getting Started</h2>
<p> 1. Clone the repository:
   <code> git clone https://github.com/yourusername/TicTacToe.git</code></p>
<p> 2. Navigate to the project directory:
   <code>cd TicTacToe</code></p>
<p> 3. Compile the Java file:
   <code> javac TicTacToe.java </code></p>
<p> 4. Run the game:
   <code> java TicTacToe</code></p>
<h2>How to Play</h2>
<ul>
    <li>Click on an empty cell to place your mark (X or O).</li>
    <li>The game alternates turns between the two players.</li>
    <li>The game announces the winner or if it's a draw, and automatically resets after the game ends.</li>
</ul>

<h2>Code Overview</h2>
<p>The main components of the code include:</p>
<ul>
    <li><code>TicTacToe</code> class: Implements the game logic and graphical user interface.</li>
    <li><code>initGame()</code>: Initializes the game state.</li>
    <li><code>paintComponent(Graphics graphics)</code>: Renders the game board and updates the display.</li>
    <li><code>processMouseEvent(MouseEvent mouseEvent)</code>: Handles player input.</li>
    <li><code>checkStatus()</code>: Evaluates the game state to determine if there's a winner or a draw.</li>
</ul>


</body>
</html>
