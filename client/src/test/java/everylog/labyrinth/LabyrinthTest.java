package everylog.labyrinth;


import static core.september.everylog.labyrinth.LabyrinthSolverImpl.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import core.september.everylog.labyrinth.Labyrinth;
import core.september.everylog.labyrinth.LabyrinthImpl;
import core.september.everylog.labyrinth.LabyrinthSolverImpl;


public class LabyrinthTest {

	@Test
	public void testDirections() {
		
		//Labyrinth l = new LabyrinthImpl();
		LabyrinthSolverImpl s = new LabyrinthSolverImpl();
		assertThat("DOWN is not returned for UP", s.opposite(UP), equalTo(DOWN));
		assertThat("UP is not returned for DOWN", s.opposite(DOWN), equalTo(UP));
		assertThat("LEFT is not returned for RIGHT", s.opposite(RIGHT), equalTo(LEFT));
		assertThat("RIGHT is not returned for LEFT", s.opposite(LEFT), equalTo(RIGHT));
		
	}
	
	@Test
	public void testResolver() {
		
		//Labyrinth l = new LabyrinthImpl();
		LabyrinthSolverImpl s = new LabyrinthSolverImpl();
		Labyrinth l = new LabyrinthImpl();
		assertThat("Exit should exists", s.exitExists(l), equalTo(true));
		
	}
}
