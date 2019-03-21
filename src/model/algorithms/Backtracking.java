package model.algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Maze;
import model.utils.DirectionsUtils;

public class Backtracking extends Algorithm {

	@Override
	protected Maze execute(Maze maze) {
		try {
			return execution_backtracking(maze);
		} catch (Exception e) {
			return null;
		}
	}

	private Maze execution_backtracking(Maze maze) throws Exception {
		Maze currentNode = null;
		try {
			List<Maze> neighborhood = new ArrayList<Maze>();
			Maze newNeighbor = null;
			boolean existInEvaluated = false;

			List<Maze> evaluatedNodes = new ArrayList<Maze>();
			List<Maze> openNodes = new ArrayList<Maze>();
			openNodes.add(maze);

			while (!openNodes.isEmpty()) {
				currentNode = openNodes.get(0);
				if (currentNode.getRobotCurrentPosition().equals(currentNode.getEnd()))
					return currentNode;

				openNodes.remove(currentNode);
				evaluatedNodes.add(currentNode);

				newNeighbor = currentNode.copyMaze();
				newNeighbor.moveRobot(0, DirectionsUtils.UP);
				neighborhood.add(newNeighbor);

				newNeighbor = currentNode.copyMaze();
				newNeighbor.moveRobot(0, DirectionsUtils.DOWN);
				neighborhood.add(newNeighbor);
				
				newNeighbor = currentNode.copyMaze();
				newNeighbor.moveRobot(DirectionsUtils.RIGHT, 0);
				neighborhood.add(newNeighbor);
				
				
				newNeighbor = currentNode.copyMaze();
				newNeighbor.moveRobot(DirectionsUtils.LEFT, 0);
				neighborhood.add(newNeighbor);
				
				for (Maze neighbor : neighborhood) {
					for (Maze evaluated : evaluatedNodes) {
						if (neighbor.getRobotCurrentPosition().equals(evaluated.getRobotCurrentPosition())) {
							existInEvaluated = true;
						}
					}

					if (!existInEvaluated) {
						openNodes.add(0,neighbor);
					}
					existInEvaluated = false;
				}
				
				neighborhood = new ArrayList<Maze>();
			}

			
			
		} catch (OutOfMemoryError e) {
			return currentNode;
		}

		return currentNode;
	}
}
