package com.mygdx.game;

public class RoomRepresentation {
	private static int[][] floorArray;
	private int xRoomSize;
	private int yRoomSize;
	private int leftDoorLocation; // -1 for no door, must be no more than size
	private int rightDoorLocation; // same as above
	private int topDoorLocation; // ^
	private int bottomDoorLocation; // ^
	public static final int TILE_SIZE = 1;
	private static final int DEFAULT_ROOM_SIZE = 480;
	private Integer[][] roomRepresentation;
	public int getRoomXSize() {
		return xRoomSize;
	}
	public int getRoomYSize() {
		return yRoomSize;
	}
	public boolean leftDoor() {
		return leftDoorLocation==-1 ? false : true;
	}
	public boolean rightDoor() {
		return rightDoorLocation==-1 ? false : true;
	}
	public boolean topDoor() {
		return topDoorLocation==-1 ? false : true;
	}
	public boolean bottomDoor() {
		return bottomDoorLocation==-1 ? false : true;
	}
	private void blankRoom() {
		this.leftDoorLocation = -1;
		this.rightDoorLocation = -1;
		this.topDoorLocation = -1;
		this.bottomDoorLocation = -1;
		this.xRoomSize = DEFAULT_ROOM_SIZE;
		this.yRoomSize = DEFAULT_ROOM_SIZE;
		roomRepresentation = new Integer[xRoomSize][yRoomSize];
		for (int i = 0; i<xRoomSize; i++) {
			for (int j = 0; j<yRoomSize; j++) {
				roomRepresentation[j][i] = 0;
			}
			
		}
	}
	public RoomRepresentation(int id) {
		blankRoom();
		switch (id) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 9:
		case 11:
		case 13:
		case 15:
			topDoorLocation = (int) Math.round((Math.random()*(xRoomSize-1)));
			roomRepresentation[0][topDoorLocation] = 1;
			break;
		}
		switch (id) {
		case 2:
		case 3:
		case 6:
		case 7:
		case 10:
		case 11:
		case 14:
		case 15:
			rightDoorLocation = (int) Math.round((Math.random()*(xRoomSize-1)));
			roomRepresentation[rightDoorLocation][xRoomSize-1] = 1;
			break;
		}
		switch (id) {
		case 8:
		case 9:
		case 10:
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
			leftDoorLocation = (int) Math.round((Math.random()*(xRoomSize-1)));
			roomRepresentation[leftDoorLocation][0] = 1;
			break;
		}
		switch (id) {
		case 4:
		case 5:
		case 6:
		case 7:
		case 12:
		case 13:
		case 14:
		case 15:
			bottomDoorLocation = (int) Math.round((Math.random()*(xRoomSize-1)));
			roomRepresentation[yRoomSize-1][bottomDoorLocation] = 1;
			break;
		}
	}
	public String toString() {
		String s = "";
		for (int i = 0; i<xRoomSize; i++) {
			for (int j = 0; j<yRoomSize; j++) {
				s = s + roomRepresentation[i][j];
			}
			s = s + "\n";
		}
		return s;
	}
	public static void main(String[] args) {
		for (int i = 0; i<16; i++) {
			System.out.println(new RoomRepresentation(i));
		}
	}
}
