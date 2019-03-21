package model.algorithms;

import java.util.ArrayList;
import java.util.List;

import model.Maze;
import model.utils.DirectionsUtils;

public class A_Star extends Algorithm {

	@Override
	protected Maze execute(Maze maze) {
		try {
			return execution_A_Star(maze);
		} catch (Exception e) {
			return null;
		}
	}

	private Maze execution_A_Star(Maze maze) throws Exception {
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
//					if ( (  neighbor.getEuclideanDistance() * (neighbor.getPath().getMovementsInCoordinates().size() + 1) -20 ) < currentNode.getEuclideanDistance() * (currentNode.getPath().getMovementsInCoordinates().size() + 1)) {
					if ((neighbor.getEuclideanDistance() - 20)  < currentNode.getEuclideanDistance()) {
						for (Maze evaluated : evaluatedNodes) {
							if (neighbor.equals(evaluated)) {
								existInEvaluated = true;
							}
						}
						if (!existInEvaluated) {
							openNodes.add(neighbor);
						}
						existInEvaluated = false;
					}
				}
				openNodes.sort(null);
				neighborhood = new ArrayList<Maze>();
				
				//This line print the nodes evaluated and open in every iteration
//			System.out.println("nodos evaluated: " + evaluatedNodes.size());
//			System.out.println("nodos open: " + openNodes.size());
			}
		} catch (OutOfMemoryError e) {
			return currentNode;
		}

		return currentNode;
	}
}
