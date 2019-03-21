package model;

import model.algorithms.Algorithm;

public class Problem {

	private Maze maze;
	private Algorithm algorithm;
	private Solution solution;
	
	public Problem() {
	}
	
	public Problem(Maze maze) {
		this.maze = maze;
	}
	
	public Problem(Maze maze, Algorithm algorithm) {
		this(maze);
		this.algorithm = algorithm;
	}
	
	public void solve() {
		solution = algorithm.solveMaze(maze);
	}
	
	//Getters and setters
	
	public Maze getMaze() {
		return maze;
	}

	public void setMaze(Maze maze) {
		this.maze = maze;
		this.solution = null;
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}
	
	public void setAlgorithm(Algorithm algorithm) {
		this.algorithm = algorithm;
		this.solution = null;
	}

	public Solution getSolution() {
		return solution;
	}

}
