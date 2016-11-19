package com.mygdx.game;

public class Floor {

	private static final int FLOOR_SIZE = 6;
	private static int[][] floor = new int[FLOOR_SIZE][FLOOR_SIZE];

	public static int getFloorInt(int x, int y){
		return floor[y][x];
	}
	public static void setFloorInt(int x, int y, int z){
		floor[y][x] = z;
	}

	public static int[][] getFloor() {
		return floor;
	}

	public static double random(int x) {
		return (double) x * Math.random();
	}
	public static boolean isValidUp(int x, int y){
		switch(x){
		case 1:
		case 3:
		case 5:
		case 7:
		case 9:
		case 11:
		case 13:
		case 15:
			int[] container = {4,5,6,7,12,13,14,15};
			for (int i : container){
				if (y == i)
					return false;

			}
			return true;
		default:
			return false;
		}
	}
	public static boolean isValidDown(int x, int y){
		switch(x){
		case 4:
		case 5:
		case 6:
		case 7:
		case 12:
		case 13:
		case 14:
		case 15:
			int[] container = {2,4,6,8,10,12,14};
			for (int i : container){
				if (y == i)
					return false;

			}
			return true;
		default:
			return false;
		}
	}
	public static boolean isValidLeft(int x, int y){
		switch(x){
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			int[] container = {1,4,5,8,9,12,13};
			for (int i : container){
				if (y == i)
					return false;

			}
			return true;
		default:
			return false;
		}
	}
	public static boolean isValidRight(int x, int y) {
		switch(x) {
		case 2:
		case 3:
		case 6:
		case 7:
		case 10:
		case 11:
		case 14:
		case 15:
			int[] container = {8,9,10,11,12,13,14,15};
			for (int i : container){
				if (y == i)
					return false;

			}
			return true;
		default:
			return false;

		}
	}

	public static void generate(){
		floor = new int[FLOOR_SIZE][FLOOR_SIZE];
		int centre = FLOOR_SIZE/2;
		System.out.println(centre);
		int targetX = centre;
		int targetY = centre;
		// look at the centre of the floors
		for (int i = 0; i<FLOOR_SIZE*FLOOR_SIZE*5; i++) {
			int possibleFloor = (int)(random(14)+1);
			if (i==0) {
				possibleFloor = 15;
				setFloorInt(targetX,targetY,possibleFloor);
			} else {
				targetX = (int)random(FLOOR_SIZE-1);
				targetY = (int)random(FLOOR_SIZE-1);
				if (getFloorInt(targetX,targetY)==0) {
					int upDownLeftRight = (int)random(4);
					switch (upDownLeftRight) {
					case 0:
						int targetConnectX = targetX;
						int targetConnectY = targetY-1;
						if (targetConnectY >= 0) {
							// if target is in the array
							if (isValidDown(getFloorInt(targetConnectX,
									targetConnectY),
									possibleFloor)) {
								// there's actually a way to connect 
								// these floors!
								setFloorInt(targetX,targetY,possibleFloor);
							}
						}
						break;
					case 1:
						targetConnectX = targetX;
						targetConnectY = targetY+1;
						if (targetConnectY < FLOOR_SIZE) {
							// if target is in the array
							if (isValidUp(getFloorInt(targetConnectX,
									targetConnectY),
									possibleFloor)) {
								// there's actually a way to connect 
								// these floors!
								setFloorInt(targetX,targetY,possibleFloor);
							}
						}
						break;
					case 2:
						targetConnectX = targetX-1;
						targetConnectY = targetY;
						if (targetConnectX >= 0) {
							// if target is in the array
							if (isValidRight(getFloorInt(targetConnectX,
									targetConnectY),
									possibleFloor)) {
								// there's actually a way to connect 
								// these floors!
								setFloorInt(targetX,targetY,possibleFloor);
							}
						}
						break;
					case 3:
					default:
						targetConnectX = targetX+1;
						targetConnectY = targetY;
						if (targetConnectX < FLOOR_SIZE) {
							// if target is in the array
							if (isValidLeft(getFloorInt(targetConnectX,
									targetConnectY),
									possibleFloor)) {
								// there's actually a way to connect 
								// these floors!
								setFloorInt(targetX,targetY,possibleFloor);
							}
						}
						break;
					}
				} 
			}
		}
		for (int[] row : floor) {
			for (int individualFloor : row) {
				String s = "";
				switch (individualFloor) {
				case 0:
					s = "  [-]  ";
					break;
				case 1:
					s = "  [^]  ";
					break;
				case 2:
					s = "  [-]--";
					break;
				case 3:
					s = "  [^]--";
					break;
				case 4:
					s = "  [/]  ";
					break;
				case 5:
					s = "  [|]  ";
					break;
				case 6:
					s = "  [/]--";
					break;
				case 7:
					s = "  [|]--";
					break;
				case 8:
					s = "--[-]  ";
					break;
				case 9:
					s = "--[^]  ";
					break;
				case 10:
					s = "--[-]--";
					break;
				case 11:
					s = "--[^]--";
					break;
				case 12:
					s = "--[/]  ";
					break;
				case 13:
					s = "--[|]  ";
					break;
				case 14:
					s = "--[/]--";
					break;
				case 15:
					s = "--[|]--";
					break;
				}
				System.out.print(s);
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		generate();
	}
}
