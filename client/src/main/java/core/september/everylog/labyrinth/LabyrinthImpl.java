package core.september.everylog.labyrinth;

public class LabyrinthImpl implements Labyrinth {
	
	private final static String wall = "\u25A0";
	private final static String nowall = "\u25A1";
	private final static String person = "\u2689";
	
	private boolean[][] walls = new boolean[][]{
			{ true, true, true, true, false, true, true, true },
			{ true, false, true, false, false, false, false, true },
			{ true, false, true, true, true, false, false, true },
			{ true, false, true, false, false, true, false, true },
			{ true, false, true, true, false, false, false, true },
			{ true, false, false, true, false, true, false, true },
			{ true, true, false, false, false, false, false, true },
			{ true, true, true, true, true, true, true, true } };
	/** Starting position */
	private int currentX = 3, currentY = 3;
	
	
	 @Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int row=0; row<walls.length; row++) {
			for(int col = 0; col<walls[row].length;col++) {
				String rapp = currentX == col && currentY == row ? person :
					walls[row][col] ? wall : nowall;
				sb.append(rapp);
			}
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}
	public LabyrinthImpl() {
	}
	public LabyrinthImpl(boolean[][] walls, int currentX, int currentY) {
		this.walls = walls;
		this.currentX = currentX;
		this.currentY = currentY;
	}
	private boolean isCovered(int x, int y){
		if (y < 0 || y >= walls.length) {
			return false;
		}
		return x >= 0 && x < walls[y].length;
	}
	@Override
	public boolean isOutside() {
		return !isCovered(currentX, currentY);
	}
	@Override
	public boolean tryMove(int direction) {
		int newX = currentX, newY = currentY;
		switch (direction) {
		case 0:
			newY--;
			break;
		case 1:
			newX++;
			break;
		case 2:
			newY++;
			break;
		case 3:
			newX--;
			break;
		default:
			throw new RuntimeException("Wrong direction: " + direction);
		}
		if (isCovered(newX,newY) && walls[newY][newX]) {
			return false;
		}
		currentX = newX;
		currentY = newY;
		System.out.println(this.toString());
		return true;
	}
	@Override
	public boolean[] wallsAround() {
		boolean[] around = new boolean[4];
		around[0] = isCovered(currentX, currentY - 1) ? walls[currentY -
		                                                      1][currentX] : false;
		                                                      around[1] = isCovered(currentX + 1, currentY) ? walls[currentY][currentX
		                                                                                                                     + 1] : false;
		                                                      around[2] = isCovered(currentX, currentY + 1) ? walls[currentY +
		                                                                                                            1][currentX] : false;
		                                                                                                            around[3] = isCovered(currentX - 1, currentY) ? walls[currentY][currentX
		                                                                                                                                                                           - 1] : false;
		                                                                                                            return around;
	}
}