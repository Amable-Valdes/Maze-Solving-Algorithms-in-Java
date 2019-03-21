package model;

//import model.utils.MazeUtils;

public class Solution {
	
	private int[][] mazeSolution;
	private long time;
	private MazePath path;
	
	public Solution(int[][] mazeSolution, long time, MazePath mazePath) {
		this.mazeSolution = mazeSolution;
		this.time = time;
		this.path = mazePath;
	}

	public int[][] getMazeSolution() {
		return mazeSolution;
	}

	public long getTime() {
		return time;
	}

	public MazePath getPath() {
		return path;
	}
	
	public void printResults() {
		System.out.println("Results:");
		System.out.println("\tExecution time: " + time + " miliseconds");
		System.out.println("\tPath: " + path.getMovementsInDirections());
		System.out.println("\tNumber of steps: " + path.getMovementsInDirections().size());
//		MazeUtils.printMaze(mazeSolution);
	}
}
