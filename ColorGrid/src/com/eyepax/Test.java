package com.eyepax;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class Test {
	
	private static Color[][] colorGrid;
	private static ArrayList<ColorNode> checkedArrayList;
	private static ArrayList<ColorNode> largerstArrayList;
	private static ArrayList<ColorNode> temlArrayList;
	private static int gridRaws = 2;
	private static int gridColumns = 2;

	public static void main(String[] args) {
		generateColorGrid(gridRaws,gridColumns);
		printColor();
		getLargestColorGrid();
		printLargestGrid();
	}
	
	public static void getLargestColorGrid() {
		checkedArrayList = new ArrayList<>();
		largerstArrayList = new ArrayList<>();
		temlArrayList = new ArrayList<>();
		
		for(int i=0; i<colorGrid.length;i++) {
			for(int j=0; j<colorGrid[i].length; j++) {
				System.out.println("row : Col - "+i+" : "+j);
				ColorNode nodec = new ColorNode(i, j);
				if(!checkContainsinAlreadyCheckedList(nodec)) {
					checkedArrayList.add(new ColorNode(i, j));
					temlArrayList.add(new ColorNode(i, j));
					if(checkAdjacentVertically(i,j)) {
						temlArrayList.add(new ColorNode(i+1, j));
					}
					if(checkAdjacentHorizontically(i,j)) {
						temlArrayList.add(new ColorNode(i, j+1));
					}
					if(temlArrayList.size() >1) {
						checkTempAdjacency(temlArrayList);
					}else {
						temlArrayList = new ArrayList<>();
					}
					if(largerstArrayList.size()<temlArrayList.size()) {
						largerstArrayList = temlArrayList;
					}
				}
			}
		}
		
	}
	
	public static void checkTempAdjacency(ArrayList<ColorNode> tempList) {
		 
		for(int i=0; i<tempList.size(); i++) {
			ColorNode node = tempList.get(i);
			int raw= node.getRaw();
			int col = node.getCol();
			
			if(!checkContainsinAlreadyCheckedList(new ColorNode(raw, col))) {
				checkedArrayList.add(new ColorNode(raw, col));
				if(checkAdjacentVertically(raw,col)) {
					temlArrayList.add(new ColorNode(raw+1, col));
				}
				if(checkAdjacentHorizontically(node.getRaw(), node.getCol())) {
					temlArrayList.add(new ColorNode(raw, col+1));
				}
			}
		}
		
		/*if(tempList.size() != temlArrayList.size()) {
			checkTempAdjacency(temlArrayList);
		}*/
		
	}
	
	public static boolean checkAdjacentVertically(int raw, int col) {
		boolean isTrue= false;
		if(gridRaws>raw+1 && gridColumns>col) {
			Color firstCol = colorGrid[raw][col];
			Color secondCol = colorGrid[raw+1][col];
			if((firstCol.getRed()== secondCol.getRed())&& (firstCol.getGreen()== secondCol.getGreen()) && (firstCol.getBlue()== secondCol.getBlue())) {
				isTrue = true;
			}else {
				isTrue = false;
			}
		}
		return isTrue;
	}
	
	public static boolean checkAdjacentHorizontically(int raw, int col) {
		boolean isTrue= false;
		if(gridRaws>raw && gridColumns>col+1) {
			Color firstCol = colorGrid[raw][col];
			Color secondCol = colorGrid[raw][col+1];
			if((firstCol.getRed()== secondCol.getRed())&& (firstCol.getGreen()== secondCol.getGreen()) && (firstCol.getBlue()== secondCol.getBlue())) {
				isTrue = true;
			}else {
				isTrue =  false;
			}
		}
		return isTrue;
	}
	
	/*public static boolean checkIsAdjacent(Color firstCol, Color secondCol) {
		if((firstCol.getRed()== secondCol.getRed())&& (firstCol.getGreen()== secondCol.getGreen()) && (firstCol.getBlue()== secondCol.getBlue())) {
			return true;
		}else {
			return false;
		}
	}*/

	public static void generateColorGrid(int rows, int col) {
		colorGrid = new Color[rows][col];
		Random rand = new Random();
		int r = 0;
		int g = 0;
		int b = 0;
		/*for(int i=0; i<rows; i++) {
			for(int j=0; j<col; j++) {
				r = rand.nextInt(256);
				g = rand.nextInt(256);
				b = rand.nextInt(256);
				colorGrid[i][j] = new Color(r, g, b);
			}
		}*/
		
		colorGrid[0][0] = new Color(158, 143, 34);
		colorGrid[0][1] = new Color(150, 143, 34);
		colorGrid[1][0] = new Color(150, 143, 34);
		colorGrid[1][1] = new Color(150, 143, 34);
	}
	
	public static void printColor() {
		for(int i=0; i<colorGrid.length; i++) {
			for(int j=0; j<colorGrid[i].length; j++) {
				System.out.println(colorGrid[i][j]);
			}
		}
	}
	
	public static void printLargestGrid() {
		System.out.println("Largest Grid");
		for(int i=0; i<largerstArrayList.size();i++) {
			System.out.println(largerstArrayList.get(i).getRaw() + " : "+largerstArrayList.get(i).getCol());
		}
	}
	
	public static boolean checkContainsinAlreadyCheckedList(ColorNode node) {
		boolean isTrue = false;
		for(int i=0; i<checkedArrayList.size();i++) {
			if(checkedArrayList.get(i).getCol() == node.getCol() && checkedArrayList.get(i).getRaw() == node.getRaw()) {
				isTrue = true;
			}
			if(isTrue)
				break;
		}
		System.out.println("Existance : "+isTrue);
		return isTrue;
	}
}

