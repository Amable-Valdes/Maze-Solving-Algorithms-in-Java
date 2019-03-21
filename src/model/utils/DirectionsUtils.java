package model.utils;

public class DirectionsUtils {
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int UP = -1;
	public static final int DOWN = 1;

	public static String translateMovement(int x, int y) {
		if(x == 0) {
			switch (y) {
			case UP:
				return "Up";
			case DOWN:
				return "Down";
			default:
				return "Unknow";
			}
		} else if(y == 0) {
			switch (x) {
			case RIGHT:
				return "Right";
			case LEFT:
				return "Left";
			default:
				return "Unknow";
			}
		} else {
			return "Unknow";
		}
	}
}
