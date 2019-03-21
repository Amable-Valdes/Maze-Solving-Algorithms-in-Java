package model.algorithms;

import model.*;

public abstract class Algorithm{
	
	public Solution solveMaze(Maze maze) {
		
		long millis_Start = System.currentTimeMillis();
		
		Maze mazeSolution = execute(maze);
		
		long millis_End = System.currentTimeMillis();
		
		if(mazeSolution != null)
			return new Solution(mazeSolution.getMaze(), millis_End - millis_Start, mazeSolution.getPath());
		return new Solution(maze.getMaze(), millis_End - millis_Start , new MazePath());
	}
	
	protected abstract Maze execute(Maze maze);

}
