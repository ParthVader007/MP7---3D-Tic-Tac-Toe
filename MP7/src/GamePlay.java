import java.util.InputMismatchException;
import java.util.Scanner;

public class GamePlay {
	static Player player1;
	static Player player2;
	static Player[] players = new Player[2];
	static Board board = new Board();
	static int pturn = (int) Math.round(Math.random());
	static boolean gameEnded = false;
	
	private static void nextTurn() {
		pturn++;
		if (pturn % 2 == 0) {
			pturn = 0;
		}
	}
	
	private static String getInstructions() {
		return "There are three layers. \n"
				+ "Layer 'a', 'b', 'c' where 'a' \n"
				+ "faces you the user and 'c' is the farthest most layer. \n"
				+ "Each layer has an assignment position 1-9 similar to \n"
				+ "your phone dial pad. \n"
				+ "These are the commands: \n"
				+ "move (a, b, or c) (1 - 9) - makes  a move (Ex. move a 1)\n" 
				+ "help - get instructions \n" 
				+ "getb - prints all three layers of the board \n" 
				+ "gtba (a, b, or c) - prints the specified layer (Ex. gtba a)";
	}
	
	private static String getCommands() {
		return "These are the commands: \n"
				+ "move (a, b, or c) (1 - 9)- makes  a move (Ex. move a 1)\n" 
				+ "help - get instrusctions \n" 
				+ "getb - prints all three layers of the board \n" 
				+ "gtba (a, b, or c)- prints the specified layer (Ex. gtba a)";
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter player names.");
		for (int i = 0; i < 2; i++) {
			String name = scan.nextLine();
			players[i] = new Player(name);
		}
		System.out.println(getInstructions());
		while (!gameEnded) {
			System.out.printf("It is %s's turn.\n", players[pturn].getName());
			String userInput = scan.next();
			System.out.println("Userinput: " + userInput);
			if (userInput.equalsIgnoreCase("help")) { // get instructions
				System.out.println(getInstructions());
			} else if (userInput.equalsIgnoreCase("move")) { // move
				String layer = "";
				int space = 0;
				try {
					layer = scan.next();
					space = scan.nextInt();
				} catch (InputMismatchException ex) {
					System.out.println("Bad input.");
					continue;
				}
				System.out.println("Layer: " + layer);
				System.out.println("Space: " + space);
				while (!layer.equalsIgnoreCase("a") ^ !layer.equalsIgnoreCase("b") 
						^ !layer.equalsIgnoreCase("c") ^ !(space >= 1 && space <= 9)) {
					System.out.println("Please input a valid move. \n"
							+ "Layer should be a, b, or c and space should be "
							+ "an integer number between 1 and 9 (Ex. a 1)");
					layer = scan.next();
					space = scan.nextInt();
				}
				boolean movesuccess = board.makeMove(players[pturn], layer, space);
				System.out.println(board.getSpace(layer, space));
				System.out.println(movesuccess);
				if (!movesuccess) {
					System.out.println("That space is already taken.");
					continue;
				}
				nextTurn();
				gameEnded = board.getWinner() != null;
			} else if (userInput.equalsIgnoreCase("getb")) { // get board
				board.getBoard();
			} else if (userInput.equalsIgnoreCase("gtba")) { // get board at
				String layer = scan.next();
				while (!layer.equalsIgnoreCase("a") || !layer.equalsIgnoreCase("b") 
						|| !layer.equalsIgnoreCase("c")) {
					System.out.println("Please input a valid layer a, b, or c. (Ex. a)");
					layer = scan.next();
				}
				board.getBoardAt(layer);
			} else {
				System.out.println("Please input a valid command." + getCommands());
			}
		}
		nextTurn();
		System.out.printf("%s has won the game.", players[pturn].getName());
	}

}
