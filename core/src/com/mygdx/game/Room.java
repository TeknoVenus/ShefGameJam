package com.mygdx.game;

public class Room {

	private static final int ROOM_SIZE = 10;
	private static int[][] room = new int[ROOM_SIZE][ROOM_SIZE];

	public static int getRoomInt(int x, int y){
		return room[y][x];
	}
	public static void setRoomInt(int x, int y, int z){
		room[y][x] = z;
	}

	public static int[][] getRoom() {
		return room;
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
		room = new int[ROOM_SIZE][ROOM_SIZE];
		int centre = ROOM_SIZE/2;
		int targetX = centre;
		int targetY = centre;
		// look at the centre of the rooms
		for (int i = 0; i<ROOM_SIZE*ROOM_SIZE*5; i++) {
			int possibleRoom = (int)(random(14)+1);
			if (i==0) {
				setRoomInt(targetX,targetY,possibleRoom);
			} else {
				targetX = (int)random(ROOM_SIZE-1);
				targetY = (int)random(ROOM_SIZE-1);
				int upDownLeftRight = (int)random(4);
				switch (upDownLeftRight) {
					case 0:
						int targetConnectX = targetX;
						int targetConnectY = targetY-1;
						if (targetConnectY >= 0) {
							// if target is in the array
							if (isValidUp(getRoomInt(targetConnectX,
									targetConnectY),
									possibleRoom)) {
								// there's actually a way to connect 
								// these rooms!
								setRoomInt(targetX,targetY,possibleRoom);
							}
						}
						break;
					case 1:
						targetConnectX = targetX;
						targetConnectY = targetY+1;
						if (targetConnectY < ROOM_SIZE) {
							// if target is in the array
							if (isValidDown(getRoomInt(targetConnectX,
									targetConnectY),
									possibleRoom)) {
								// there's actually a way to connect 
								// these rooms!
								setRoomInt(targetX,targetY,possibleRoom);
							}
							}
						break;
					case 2:
						targetConnectX = targetX-1;
						targetConnectY = targetY;
						if (targetConnectX >= 0) {
							// if target is in the array
								if (isValidLeft(getRoomInt(targetConnectX,
										targetConnectY),
										possibleRoom)) {
									// there's actually a way to connect 
									// these rooms!
									setRoomInt(targetX,targetY,possibleRoom);
								}
							}
						break;
					case 3:
					default:
						targetConnectX = targetX+1;
						targetConnectY = targetY;
						if (targetConnectX < ROOM_SIZE) {
							// if target is in the array
							if (isValidUp(getRoomInt(targetConnectX,
										targetConnectY),
										possibleRoom)) {
									// there's actually a way to connect 
									// these rooms!
									setRoomInt(targetX,targetY,possibleRoom);
								}
							}
						break;
				}
			}
			for (int[] row : room) {
				for (int individualRoom : row) {
					System.out.print(individualRoom);
				}
				System.out.println();
			}
		}
	}

	public static void main(String[] args) {
		generate();
	}
}
