package model;

import java.util.Arrays;

import model.utils.DirectionsUtils;
import model.utils.MazeUtils;

public class Maze implements Comparable<Maze> {

	private int[][] maze;
	private Coordinates start;
	private Coordinates end;
	private Coordinates robotCurrentPosition;
	private MazePath path;

	public Maze() {
	}

	public Maze(String url) throws Exception {
		maze = MazeUtils.generateMatrixFromImage(url);
		path = new MazePath();
	}

	public void properties() {
		System.out.println("Maze properties:");
		if (maze == null) {
			System.out.println("\tNO MAZE - MAZE WAS NOT LOADED");
			return;
		}
		System.out.println("\tDimensions: " + maze.length + "x" + maze[0].length);

		if (start == null) {
			System.out.println("\tWithout start");
		} else {
			System.out.println("\tStart at coordinates (" + start.getX() + "," + start.getY() + ")");
		}

		if (start == null) {
			System.out.println("\tWithout end");
		} else {
			System.out.println("\tEnd at coordinates (" + end.getX() + "," + end.getY() + ")");
		}
	}

	public void printMaze() {
		MazeUtils.printMaze(maze);
	}

	public void setMaze(int[][] newMaze) {
		maze = newMaze;
		start = null;
		end = null;
	}

	public int[][] getMaze() {
		return maze;
	}

	public void selectStart(int x, int y) throws Exception {
		if (start != null) {
			maze[start.getX()][start.getY()] = MazeUtils.FREE;
		}
		if (maze[x][y] != MazeUtils.OBSTACLE) {
			maze[x][y] = MazeUtils.START;
			start = new Coordinates(x, y);
			robotCurrentPosition = new Coordinates(x, y);
		} else {
			System.out.println("You can't select that position. There is a obstacle");
			maze[x][y] = MazeUtils.START;
			MazeUtils.printMaze(maze);
			throw new Exception();
		}
	}

	public void selectEnd(int x, int y) throws Exception {
		if (end != null) {
			maze[end.getX()][end.getY()] = MazeUtils.FREE;
		}
		if (maze[x][y] != MazeUtils.OBSTACLE) {
			maze[x][y] = MazeUtils.END;
			end = new Coordinates(x, y);
		} else {
			System.out.println("You can't select that position. There is a obstacle");
			maze[x][y] = MazeUtils.END;
			MazeUtils.printMaze(maze);
			throw new Exception();
		}
	}

	public Maze copyMaze() {
		Maze newMaze = new Maze();
		int[][] newMatrixMaze = new int[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				newMatrixMaze[i][j] = maze[i][j];
			}
		}
		newMaze.maze = newMatrixMaze;
		newMaze.start = new Coordinates(start.getX(), start.getY());
		newMaze.end = new Coordinates(end.getX(), end.getY());
		newMaze.robotCurrentPosition = new Coordinates(robotCurrentPosition.getX(), robotCurrentPosition.getY());
		newMaze.path = path.copyPath();
		return newMaze;
	}
	
	public void setPath(MazePath path) {
		this.path = path;
	}

	public MazePath getPath() {
		return path;
	}

	public Coordinates getStart() {
		return start;
	}

	public Coordinates getEnd() {
		return end;
	}

	public double getEuclideanDistance() throws Exception {
		if (start == null || end == null) {
			System.out.println("No start or end");
			throw new Exception();
		}
		
		double a = (robotCurrentPosition.getX() - end.getX());
		double b = (robotCurrentPosition.getY() - end.getY());
		return Math.sqrt(a*a + b*b);
	}

	public void setRobotCurrentPosition(Coordinates robotCurrentPosition) {
		this.robotCurrentPosition = robotCurrentPosition;
	}

	public Coordinates getRobotCurrentPosition() {
		return robotCurrentPosition;
	}

	public void moveRobot(int x, int y) {
		try {
			if ( ((getRobotCurrentPosition().getX() + x) < 0) ||
					((getRobotCurrentPosition().getX() + x) > maze.length) ||
					maze[getRobotCurrentPosition().getX() + x][getRobotCurrentPosition().getY()] == MazeUtils.OBSTACLE) {
				return;
			} else {
				getRobotCurrentPosition().setX(getRobotCurrentPosition().getX() + x);
			}
			if (((getRobotCurrentPosition().getY() + y) < 0) ||
					((getRobotCurrentPosition().getY() + y) > maze[0].length) ||
					maze[getRobotCurrentPosition().getX()][getRobotCurrentPosition().getY() + y] == MazeUtils.OBSTACLE) {
				return;
			} else {
				getRobotCurrentPosition().setY(getRobotCurrentPosition().getY() + y);
			}
			maze[getRobotCurrentPosition().getX()][getRobotCurrentPosition().getY()] = MazeUtils.STEP;
			getPath().getMovementsInCoordinates().push(robotCurrentPosition.copyCoordinates());
			getPath().getMovementsInDirections().push(DirectionsUtils.translateMovement(x, y));
		}catch (Exception e) {
			return;
		}
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(maze);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maze other = (Maze) obj;
		if (!Arrays.deepEquals(maze, other.maze))
			return false;
		return true;
	}

	@Override
	public int compareTo(Maze o) {
		double thisMaze = 0;
		double otherMaze = 0;
		try {
			thisMaze = this.getEuclideanDistance();
			otherMaze = o.getEuclideanDistance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (thisMaze > otherMaze) {
			return 1;
		}
		if (thisMaze == otherMaze) {
			return 0;
		}
		return -1;
	}
}
