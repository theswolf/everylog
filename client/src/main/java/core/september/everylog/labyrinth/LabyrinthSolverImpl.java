package core.september.everylog.labyrinth;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LabyrinthSolverImpl implements LabyrinthSolver {

	public final static int UP=0;
	public final static int RIGHT=1;
	public final static int DOWN=2;
	public final static int LEFT=3;
	
	private enum Dirs{
		UP(LabyrinthSolverImpl.UP),
		RIGHT(LabyrinthSolverImpl.RIGHT),
		DOWN(LabyrinthSolverImpl.DOWN),
		LEFT(LabyrinthSolverImpl.LEFT);
		
		private int dir;
		private Dirs(int dir) {
			this.dir = dir;
		}
		
		public int getDirection() {
			return this.dir;
		}
		
		public static Dirs fromDirection(int direction) {
			for(Dirs dir: Dirs.values()) {
				if(direction == dir.getDirection()) {
					return dir;
				}
			}
			return null;
		}
	}

	private static int lastMove = -1;
	private static Map<Integer,List<Integer>> history = new HashMap<Integer, List<Integer>>();

	@Override
	public boolean exitExists(Labyrinth l) {
		// TODO Auto-generated method stub
		if(l.isOutside() ) return true;
		return tryMoveDir(l, UP) || tryMoveDir(l, RIGHT) || tryMoveDir(l, DOWN) || tryMoveDir(l, LEFT);

	}

	public int opposite(int direction){
		if(direction < 0) return direction;
		return (direction % 4 + 2) % 4;
	}
	

	private void log(int direction) {
		
		System.out.println("Moved "+Dirs.fromDirection(direction).name());
	}
	
	
	private boolean tryMoveDir(Labyrinth l,int direction) {
		if(opposite(lastMove)!=direction && l.tryMove(direction)) {
			//log(direction);
			lastMove = direction;
			if( exitExists(l)) return true;
		}
		else {
			if(l.tryMove(opposite(direction))) {
				//log(opposite(direction));
				lastMove = opposite(direction);
			}
			
			
		}
		return false;
	}


}
