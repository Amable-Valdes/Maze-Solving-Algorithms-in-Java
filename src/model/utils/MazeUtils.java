package model.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MazeUtils {

	public static final int OBSTACLE = 0;
	public static final int FREE = 1;
	public static final int START = 2;
	public static final int END = 3;
	public static final int STEP = 4;
	
	public static void printMaze(int[][] maze) {
		if (maze == null) {
			System.out.println("El laberinto no ha sido cargado");
			return;
		}
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == OBSTACLE) {
					System.out.printf("%4s", "*");
				} else if (maze[i][j] == FREE){
					System.out.printf("%4s", " ");
				} else if (maze[i][j] == START){
					System.out.printf("%4s", "S");
				} else if (maze[i][j] == END){
					System.out.printf("%4s", "E");
				} else if (maze[i][j] == STEP){
					System.out.printf("%4s", "+");
				} else {
					System.out.printf("%4s", "" + maze[i][j]);
				}
			}
			System.out.println();
		}
	}

	public static int[][] generateMatrixFromImage(String url) throws Exception {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File(url));
		} catch (IOException e) {
			System.out.println("Imagen " + url + " no encontrada");
			throw new Exception();
		}

		int[][] maze = new int[img.getWidth()][img.getHeight()];
		Color color = null;

		for (int x = 0; x < img.getWidth(); x++) {
			for (int y = 0; y < img.getHeight(); y++) {
				color = new Color(img.getRGB(x, y));
				if (color.getRed() > 240 && color.getBlue() > 240 && color.getGreen() > 240) {
					maze[x][y] = FREE;
				} else {
					maze[x][y] = FREE;
					if (color.getRed() < 50 && color.getBlue() < 50 && color.getGreen() < 50) {
						maze[x][y] = OBSTACLE;
					}
				}
			}
		}
		return maze;
	}
	
	public static void generateImageFromMatrix(int[][] maze,String newFileName) {
		if (maze == null) {
			System.out.println("El laberinto no ha sido cargado");
			return;
		}
		
		BufferedImage newImage = new BufferedImage(maze.length, maze[0].length, BufferedImage.TYPE_BYTE_INDEXED);
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				if (maze[i][j] == OBSTACLE) {
					newImage.setRGB(i, j, new Color(0, 0, 0).getRGB());
				} else if (maze[i][j] == FREE){
					newImage.setRGB(i, j, new Color(255, 255, 255).getRGB());
				} else if (maze[i][j] == START){
					newImage.setRGB(i, j, new Color(0, 0, 255).getRGB());
				} else if (maze[i][j] == END){
					newImage.setRGB(i, j, new Color(0, 255, 0).getRGB());
				} else if (maze[i][j] == STEP){
					newImage.setRGB(i, j, new Color(255, 0, 0).getRGB());
				} else {
					newImage.setRGB(i, j, new Color(100, 100, 100).getRGB());
				}
			}
		}
		
		File output = new File(newFileName + ".png");
	    try {
			ImageIO.write(newImage, "png", output);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
