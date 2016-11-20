package com.mygdx.game;

public class Floor {
	private static final int FLOOR_SIZE = 20;
	private static int[][] floorArray = new int[FLOOR_SIZE][FLOOR_SIZE];
	public static final int ENTIRE_FLOOR_GEN_ATTEMPTS = 250;
	public static RoomRepresentation activeRoom;
	// location in the array the active room is in
	private static int activeX;
	private static int activeY;
	
	public static void setRoom(RoomRepresentation newRoom) {
		activeRoom = newRoom;
	}

	public static RoomRepresentation getRoom() {
		return activeRoom;
	}
	
	public static int getActiveRoomX() {
		return activeX;
	}
	
	public static int getActiveRoomY() {
		return activeY;
	}
	
	// this actually does move along if it can so
	// do not use for just queries
	public static int moveRoomRight() {
		if (activeX < FLOOR_SIZE-1) {
			if (isValidLeftRight(activeRoom.getID(),
					floorArray[activeX+1][activeY])) {
				int newX = activeX+1;
				activeX = newX;
				return floorArray[newX][activeY];
			}
		}
		return -1;
	}
	public static int moveRoomLeft() {
		if (activeX > 0) {
			if (isValidLeftRight(floorArray[activeX-1][activeY],
					activeRoom.getID())) {
				int newX = activeX-1;
				activeX = newX;
				return floorArray[newX][activeY];
			}
		}
		return -1;
	}	
	public static int moveRoomUp() {
		if (activeY > 0) {
			if (isValidBelowUp(activeRoom.getID(),
					floorArray[activeX][activeY-1])) {
				int newY = activeY-1;
				activeY = newY;
				return floorArray[activeX][newY];
			}
		}
		return -1;
	}
	public static int moveRoomDown() {
		if (activeY < FLOOR_SIZE-1) {
			if (isValidBelowUp(floorArray[activeX][activeY+1],
					activeRoom.getID())) {
				int newY = activeY+1;
				activeY = newY;
				return floorArray[activeX][newY];
			}
		}
		return -1;
	}	
	
	public static int getFloorInt(int x, int y){
		return floorArray[y][x];
	}
	public static void setFloorInt(int x, int y, int z){
		floorArray[y][x] = z;
	}

	public static int getStartFloorInt() {
		activeX = FLOOR_SIZE/2;
		activeY = FLOOR_SIZE/2;
		return floorArray[FLOOR_SIZE/2][FLOOR_SIZE/2];
	}
	
	public static int[][] getFloor() {
		return floorArray;
	}

	public static double random(int x) {
		return (double) x * Math.random();
	}
	public static boolean isValidBelowUp(int below, int above){
		switch(below){
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
				if (above == i)
					return true;

			}
			return false;
		default:
			return false;
		}
	}
	
	public static boolean isValidLeftRight(int left, int right) {
		switch(left) {
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
				if (right == i)
					return true;
			}
			return false;
		default:
			return false;
		}
	}

	public static void generate(){
		floorArray = new int[FLOOR_SIZE][FLOOR_SIZE];
		int centre = FLOOR_SIZE/2;
		System.out.println(centre);
		int targetX = centre;
		int targetY = centre;
		// look at the centre of the floors
		for (int i = 0; 
				i<FLOOR_SIZE*FLOOR_SIZE*ENTIRE_FLOOR_GEN_ATTEMPTS; i++) {
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
							if (isValidBelowUp(possibleFloor,
									getFloorInt(targetConnectX,
									targetConnectY))) {
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
							if (isValidBelowUp(getFloorInt(targetConnectX,
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
							if (isValidLeftRight(getFloorInt(targetConnectX,
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
							if (isValidLeftRight(possibleFloor,
									getFloorInt(targetConnectX,
									targetConnectY))) {
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
		for (int[] row : floorArray) {
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
