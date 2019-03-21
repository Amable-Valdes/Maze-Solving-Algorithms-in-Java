package model.algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Coordinates;
import model.Maze;
import model.MazePath;
import model.utils.DirectionsUtils;
import model.utils.MazeUtils;

public class Grassfire extends Algorithm {

	private static final int COUNT_BEGINING = 10;
	private int[][] maze = null;

	@Override
	protected Maze execute(Maze maze) {
		copyMaze(maze.getMaze());
		expand(maze.getStart(), maze.getEnd());
		Maze solution = recoverPath(maze.getMaze(), maze.getStart(), maze.getEnd());
		return solution;
	}

	private Maze recoverPath(int[][] originalMaze, Coordinates start, Coordinates end) {
		Maze solution = new Maze();
		int[][] mazeWithSteps = originalMaze;
		MazePath path = new MazePath();
		Coordinates position = new Coordinates(start.getX(), start.getY());
		List<Coordinates> possibleMovements = new ArrayList<Coordinates>();

		while (!position.equals(end)) {
			possibleMovements.add(new Coordinates(position.getX() + 1, position.getY()));
			possibleMovements.add(new Coordinates(position.getX() - 1, position.getY()));
			possibleMovements.add(new Coordinates(position.getX(), position.getY() + 1));
			possibleMovements.add(new Coordinates(position.getX(), position.getY() - 1));
			for (Coordinates posibleMovement : possibleMovements) {
				
				if (posibleMovement.getX() >= 0 && posibleMovement.getX() < maze.length && posibleMovement.getY() >= 0
						&& posibleMovement.getY() < maze[0].length && (maze[position.getX()][position.getY()]
								- 1) == maze[posibleMovement.getX()][posibleMovement.getY()]) {
					
					path.getMovementsInDirections().push(DirectionsUtils.translateMovement(
							posibleMovement.getX() - position.getX(), posibleMovement.getY() - position.getY()));
					path.getMovementsInCoordinates().push(posibleMovement);
					
					position.setX(posibleMovement.getX());
					position.setY(posibleMovement.getY());
					
					mazeWithSteps[position.getX()][position.getY()] = MazeUtils.STEP;
					
				}
			}
		}
		mazeWithSteps[position.getX()][position.getY()] = MazeUtils.END;
		
		solution.setMaze(mazeWithSteps);
		solution.setPath(path);
		return solution;
	}

	private void expand(Coordinates start, Coordinates end) {
		int iteration = COUNT_BEGINING;
		List<Coordinates> listCoordenadesToExpanse = new ArrayList<Coordinates>();
		List<Coordinates> lastCoordenadesExpanded = new ArrayList<Coordinates>();

		maze[end.getX()][end.getY()] = iteration;
		lastCoordenadesExpanded.add(end);
		iteration++;

		Coordinates newCoordinate = null;
		while (maze[start.getX()][start.getY()] == MazeUtils.START) {
			for (Coordinates current : lastCoordenadesExpanded) {
				maze[current.getX()][current.getY()] = iteration;

				newCoordinate = new Coordinates(current.getX() + 1, current.getY());
				if (validCoordinate(newCoordinate.getX(), newCoordinate.getY(), maze.length, maze[0].length)
						&& !listCoordenadesToExpanse.contains(newCoordinate)) {
					listCoordenadesToExpanse.add(newCoordinate);
				}

				newCoordinate = new Coordinates(current.getX() - 1, current.getY());
				if (validCoordinate(newCoordinate.getX(), newCoordinate.getY(), maze.length, maze[0].length)
						&& !listCoordenadesToExpanse.contains(newCoordinate)) {
					listCoordenadesToExpanse.add(newCoordinate);
				}

				newCoordinate = new Coordinates(current.getX(), current.getY() + 1);
				if (validCoordinate(newCoordinate.getX(), newCoordinate.getY(), maze.length, maze[0].length)
						&& !listCoordenadesToExpanse.contains(newCoordinate)) {
					listCoordenadesToExpanse.add(newCoordinate);
				}

				newCoordinate = new Coordinates(current.getX(), current.getY() - 1);
				if (validCoordinate(newCoordinate.getX(), newCoordinate.getY(), maze.length, maze[0].length)
						&& !listCoordenadesToExpanse.contains(newCoordinate)) {
					listCoordenadesToExpanse.add(newCoordinate);
				}
			}

			lastCoordenadesExpanded = new ArrayList<Coordinates>();

			for (Coordinates coordinateToExpanse : listCoordenadesToExpanse) {
				maze[coordinateToExpanse.getX()][coordinateToExpanse.getY()] = iteration;
				lastCoordenadesExpanded.add(coordinateToExpanse);
			}

			listCoordenadesToExpanse = new ArrayList<Coordinates>();
			iteration++;
		}
		maze[start.getX()][start.getY()] = iteration;
		
		// This line print the maze with the numbers in all positions at the end of the execution
//		MazeUtils.printMaze(maze);
	}

	private boolean validCoordinate(int current_X, int current_Y, int limit_X, int limit_Y) {
		if (current_X >= 0 && current_X < limit_X && current_Y >= 0 && current_Y < limit_Y
				&& maze[current_X][current_Y] != MazeUtils.OBSTACLE
				&& !(maze[current_X][current_Y] >= COUNT_BEGINING)) {
			return true;
		}
		return false;
	}

	private void copyMaze(int[][] maze) {
		this.maze = new int[maze.length][maze[0].length];
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				this.maze[i][j] = maze[i][j];
			}
		}
	}

}
