package model;

import java.util.Stack;

public class MazePath {

	private Stack<String> movementsInDirections;
	private Stack<Coordinates> movementsInCoordinates;
	
	public MazePath() {
		setMovementsInCoordinates(new Stack<Coordinates>());
		setMovementsInDirections(new Stack<String>());
	}

	public Stack<String> getMovementsInDirections() {
		return movementsInDirections;
	}

	public void setMovementsInDirections(Stack<String> movementsInDirections) {
		this.movementsInDirections = movementsInDirections;
	}

	public Stack<Coordinates> getMovementsInCoordinates() {
		return movementsInCoordinates;
	}

	public void setMovementsInCoordinates(Stack<Coordinates> movementsInCoordinates) {
		this.movementsInCoordinates = movementsInCoordinates;
	}

	public MazePath copyPath() {
		MazePath newPath = new MazePath();
		for (int i = 0; i < movementsInCoordinates.size(); i++) {
			newPath.getMovementsInCoordinates().push(movementsInCoordinates.get(i));
		}
		for (int i = 0; i < movementsInDirections.size(); i++) {
			newPath.getMovementsInDirections().push(movementsInDirections.get(i));
		}
		return newPath;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((movementsInCoordinates == null) ? 0 : movementsInCoordinates.hashCode());
		result = prime * result + ((movementsInDirections == null) ? 0 : movementsInDirections.hashCode());
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
		MazePath other = (MazePath) obj;
		if (movementsInCoordinates == null) {
			if (other.movementsInCoordinates != null)
				return false;
		} else if (!movementsInCoordinates.equals(other.movementsInCoordinates))
			return false;
		if (movementsInDirections == null) {
			if (other.movementsInDirections != null)
				return false;
		} else if (!movementsInDirections.equals(other.movementsInDirections))
			return false;
		return true;
	}
	
	
}
