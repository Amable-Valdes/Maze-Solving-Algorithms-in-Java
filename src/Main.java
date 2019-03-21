import model.Coordinates;
import model.Maze;
import model.MazePath;
import model.Problem;
import model.Solution;
import model.algorithms.A_Star;
import model.algorithms.Backtracking;
import model.algorithms.Grassfire;
import model.utils.MazeUtils;

public class Main {

	public static void main(String[] args) throws Exception {
		/*
		solveMaze("Maze_01","img/Maze_01.png", new Coordinates(1, 0), new Coordinates(20, 12));
		solveMaze("Maze_02","img/Maze_02.png", new Coordinates(9, 0), new Coordinates(154, 83));
		solveMazeOnlyGrassfire("Maze_03","img/Maze_03.png", new Coordinates(214,430), new Coordinates(215, 215));
		solveMazeOnlyGrassfire("Maze_04","img/Maze_04_Planta_de_El_Prado.png", new Coordinates(45,45), new Coordinates(390, 40));
		solveMaze("Maze_05","img/Maze_05_Casa_dei_Dioscuri.png", new Coordinates(25, 20), new Coordinates(425, 425));
		solveMazeOnlyGrassfire("Maze_06","img/Maze_06.png", new Coordinates(3, 0), new Coordinates(1800, 1798));
		*/
		
		for(int i = 0; i<11;i++) {
			long millis_Start = System.currentTimeMillis();
			solveMaze("Maze_0", "img/maze0.png", new Coordinates(2,13), new Coordinates(13, 13));

			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_1", "img/maze1.png", new Coordinates(1, 1), new Coordinates(13, 13));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_2", "img/maze2.png", new Coordinates(1, 1), new Coordinates(51, 27));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_3", "img/maze3.png", new Coordinates(1, 1), new Coordinates(51, 60));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_4", "img/maze4.png", new Coordinates(3, 85), new Coordinates(111, 5));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_5", "img/maze5.png", new Coordinates(59, 0), new Coordinates(93, 0));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_6", "img/maze6.png", new Coordinates(20, 33), new Coordinates(1, 40));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_7", "img/maze7.png", new Coordinates(5, 40), new Coordinates(3, 0));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_8", "img/maze8.png", new Coordinates(0, 11), new Coordinates(23, 0));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_9", "img/maze9.png", new Coordinates(0, 23), new Coordinates(7, 0));
			
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		for(int i = 0; i<10;i++) {
			long millis_Start = System.currentTimeMillis();
			
			solveMaze("Maze_10", "img/maze10.png", new Coordinates(0, 47), new Coordinates(60, 39));
			long millis_End = System.currentTimeMillis();
			System.out.println( (millis_End - millis_Start ));
		}
		
		
		
	}
	
	private static Problem solveMaze(String name, String url, Coordinates start, Coordinates end) throws Exception {
		System.out.println(name.toUpperCase());
		Maze maze = new Maze(url);
		maze.selectStart(start.getX(), start.getY());
		maze.selectEnd(end.getX(), end.getY());
		//maze.properties();
		
		//System.out.println(name.toUpperCase() + " WITH BACKTRACKING");
		Problem problem = new Problem(maze, new Backtracking());
		problem.solve();
		//problem.getSolution().printResults();
		MazeUtils.generateImageFromMatrix(problem.getSolution().getMazeSolution(),"Solution_" + name + "_A_Star");
		
		//System.out.println("");
		return problem;
	}
	
	private static Problem solveMazeWithBoth(String name, String url, Coordinates start, Coordinates end) throws Exception {
		System.out.println(name.toUpperCase());
		Maze maze = new Maze(url);
		maze.selectStart(start.getX(), start.getY());
		maze.selectEnd(end.getX(), end.getY());
		maze.properties();
		
		System.out.println(name.toUpperCase() + " WITH A*");
		Problem problem = new Problem(maze, new A_Star());
		problem.solve();
		problem.getSolution().printResults();
		MazeUtils.generateImageFromMatrix(problem.getSolution().getMazeSolution(),"Solution_" + name + "_A_Star");
		
		System.out.println("");
		
		System.out.println(name.toUpperCase() + " WITH GRASSFIRE");
		problem.setAlgorithm(new Grassfire());
		problem.solve();
		problem.getSolution().printResults();
		MazeUtils.generateImageFromMatrix(problem.getSolution().getMazeSolution(),"Solution_" + name + "_Grassfire");
		
		System.out.println("");
		System.out.println("___________________________________________________________________________");
		System.out.println("");
		return problem;
	}
	
	private static Problem solveMazeOnlyGrassfire(String name, String url, Coordinates start, Coordinates end) throws Exception {
		System.out.println(name.toUpperCase());
		Maze maze = new Maze(url);
		maze.selectStart(start.getX(), start.getY());
		maze.selectEnd(end.getX(), end.getY());
		maze.properties();
		
		System.out.println(name.toUpperCase() + " WITH GRASSFIRE");
		Problem problem = new Problem(maze, new Grassfire());
		problem.solve();
		problem.getSolution().printResults();
		MazeUtils.generateImageFromMatrix(problem.getSolution().getMazeSolution(),"Solution_" + name + "_Grassfire");
		
		System.out.println("");
		System.out.println("___________________________________________________________________________");
		System.out.println("");
		return problem;
	}
}
