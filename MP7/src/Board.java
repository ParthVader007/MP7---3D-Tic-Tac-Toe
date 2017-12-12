/**
 * Handles all board related functions.
 * 
 * @author Parth Patel and a little Jaskirat Singh
 *
 */
public class Board {
	// layers are labeled as if game cube is looked at head on
	Player[][] frontLayer = new Player[3][3];
	Player[][] middleLayer = new Player[3][3];
	Player[][] backLayer = new Player[3][3];
	
	public boolean makeMove(final Player player, final String layer, final int index) {
		if (layer.equalsIgnoreCase("a")) {
			int rowIndex = findRow(index);
			int colIndex = findCol(index);
			
			if (frontLayer[rowIndex][colIndex] != null) {
				return false;
			} else { 
				frontLayer[rowIndex][colIndex] = player;
				return true;
			}
		} else if (layer.equalsIgnoreCase("b")) {
			int rowIndex = findRow(index);
			int colIndex = findCol(index);
			
			if (middleLayer[rowIndex][colIndex] == null) {
				middleLayer[rowIndex][colIndex] = player;
				return true;
			}
		} else if (layer.equalsIgnoreCase("c")) {
			int rowIndex = findRow(index);
			int colIndex = findCol(index);
			
			if (backLayer[rowIndex][colIndex] == null) {
				backLayer[rowIndex][colIndex] = player;
				return true;
			}
		}
		
		return false;
	}
	
	public int findCol(int index) {
		if ((index % 3) - 1 < 0) {
			return 2;
		}
		return (index % 3) - 1;
	}

	public int findRow(final int index) {
		if (index >= 0 && index <= 3) {
			return 0;
		}
		if (index >= 4 && index <= 6) {
			return 1;
		}
		return 2;
	}
	
	public String getSpace(final String layer, final int space) {
    	if (layer.equalsIgnoreCase("a")) {
			int rowIndex = findRow(space);
			int colIndex = findCol(space);
			if (frontLayer[rowIndex][colIndex] == null) {
				return null;
			}
			return frontLayer[rowIndex][colIndex].getName();
		} else if (layer.equalsIgnoreCase("b")) {
			int rowIndex = findRow(space);
			int colIndex = findCol(space);
			if (middleLayer[rowIndex][colIndex] == null) {
				return null;
			}
			return middleLayer[rowIndex][colIndex].getName();
		} else if (layer.equalsIgnoreCase("c")) {
			int rowIndex = findRow(space);
			int colIndex = findCol(space);
			if (backLayer[rowIndex][colIndex] == null) {
				return null;
			}
			return backLayer[rowIndex][colIndex].getName();
		}
    	return null;
    }
	
	public void getBoard() {
		System.out.println("Layer A");
		for (Player[] arr: frontLayer) {
			for (Player player: arr) {
				if (player == null) {
					System.out.println("    ");
					continue;
				}
				System.out.print(player.toString() + " ");
			}
			System.out.println();
		}
		
		System.out.println("\nLayer B");
		for (Player[] arr: middleLayer) {
			for (Player player: arr) {
				if (player == null) {
					System.out.println("    ");
					continue;
				}
				System.out.print(player.toString() + " ");
			}
			System.out.println();
		}
		
		System.out.println("\nLayer C");
		for (Player[] arr: backLayer) {
			for (Player player: arr) {
				if (player == null) {
					System.out.println("    ");
					continue;
				}
				System.out.print(player.toString() + " ");
			}
			System.out.println();
		}
	}
	
	public void getBoardAt(final String layer) {
		if (layer.equalsIgnoreCase("a")) {
			System.out.println("Layer A");
			for (Player[] arr: frontLayer) {
				for (Player player: arr) {
					if (player == null) {
						System.out.println("    ");
						continue;
					}
					System.out.print(player.toString() + " ");
				}
				System.out.println();
			}
		} else if (layer.equalsIgnoreCase("b")) {
			System.out.println("\nLayer B");
			for (Player[] arr: frontLayer) {
				for (Player player: arr) {
					if (player == null) {
						System.out.println("    ");
						continue;
					}
					System.out.print(player.toString() + " ");
				}
				System.out.println();
			}
		} else {
			System.out.println("\nLayer C");
			for (Player[] arr: frontLayer) {
				for (Player player: arr) {
					if (player == null) {
						System.out.println("    ");
						continue;
					}
					System.out.print(player.toString() + " ");
				}
				System.out.println();
			}
		}
	}
	
	private Player getWinnerForAFace(Player[][] x) {
		//The following for loop checks for horizontal wins
		for (int i = 0; i < 2; i++) {
				if (x[i][0] != null && x[i][0].equals(x[i][1]) 
						&& x[i][0].equals(frontLayer[i][2])) {
					return x[i][0];
				}
		}
		//This for loop checks for vertical wins. 
		for (int i = 0; i < 2; i++) {
			if (x[0][i] != null && x[0][i].equals(x[1][i]) 
					&& x[0][i].equals(x[2][i])) {
				return x[0][i];
			}
		}
		//This massive if statement checks for diagonal wins
		if (x[1][1] == null) {
			return null;
		}
		if (x[0][0] != null && x[0][0].equals(x[1][1]) && x[0][0].equals(x[2][2])) {
			return x[0][0];
		}
		if (x[0][2] != null && x[0][2].equals(x[1][1]) && x[0][2].equals(x[2][0])) {
			return x[0][2];
		}
		return null;
	}
	public Player getWinner() {
		if (getWinnerForAFace(frontLayer) != null) {
			return getWinnerForAFace(frontLayer);
		}
		if (getWinnerForAFace(middleLayer) != null) {
			return getWinnerForAFace(middleLayer);
		}
		if (getWinnerForAFace(backLayer) != null) {
			return getWinnerForAFace(backLayer);
		}
		// temp arrays for slices of the cubic board
		Player[][] tempArr = new Player[3][3];
		// top slice
		for (int i = 0; i < 3; i++) {
				tempArr[2][i] = frontLayer[0][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[1][i] = middleLayer[0][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[0][i] = backLayer[0][i];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// middle horizontal slice
		for (int i = 0; i < 3; i++) {
			tempArr[2][i] = frontLayer[1][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[1][i] = middleLayer[1][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[0][i] = backLayer[1][i];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// bottom slice
		for (int i = 0; i < 3; i++) {
			tempArr[2][i] = frontLayer[2][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[1][i] = middleLayer[2][i];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[0][i] = backLayer[2][i];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// left slice
		for (int i = 0; i < 3; i++) {
			tempArr[i][0] = frontLayer[i][0];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][1] = middleLayer[i][0];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][2] = backLayer[i][0];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// middle vertical slice
		for (int i = 0; i < 3; i++) {
			tempArr[i][0] = frontLayer[i][1];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][1] = middleLayer[i][1];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][2] = backLayer[i][1];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// right slice
		for (int i = 0; i < 3; i++) {
			tempArr[i][0] = frontLayer[i][2];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][1] = middleLayer[i][2];
		}
		for (int i = 0; i < 3; i++) {
			tempArr[i][2] = backLayer[i][2];
		}
		if (getWinnerForAFace(tempArr) != null) {
			return getWinnerForAFace(tempArr);
		}
		// check the 4 diagonals of the cube
		if (middleLayer[1][1] == null) {
			return null;
		}
		if (frontLayer[0][0] != null && frontLayer[0][0].equals(middleLayer[1][1]) 
				&& frontLayer[0][0].equals(backLayer[2][2])) {
			return frontLayer[0][0];
		}
		if (backLayer[0][2] != null && backLayer[0][2].equals(middleLayer[1][1]) 
				&& backLayer[0][2].equals(frontLayer[2][0])) {
			return backLayer[0][2];
		}
		if (frontLayer[0][2] != null && frontLayer[0][2].equals(middleLayer[1][1])
				&& frontLayer[0][2].equals(backLayer[2][0])) {
			return frontLayer[0][2];
		}
		if (backLayer[0][0] != null && backLayer[0][0].equals(middleLayer[1][1])
				&& backLayer[0][0].equals(frontLayer[2][2])) {
			return backLayer[0][0];
		}
		return null;
	}
}