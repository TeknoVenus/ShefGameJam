
public class Room{

	private static final int ROOM_SIZE = 10;
	private static int[][] room = new int[ROOM_SIZE][ROOM_SIZE];
	private static int[] axis = {ROOM_SIZE/2, ROOM_SIZE/2};

	public int getRoomInt(int x, int y){
		return room[x][y];
	}
	public static void setRoomInt(int x, int y, int z){
		room[x][y] = z;
	}

	public static int getAxisX(){
		return axis[0];
	}
	public static int getAxisY(){
		return axis[1];
	}

	public static void setAxisX(int x){
		axis[0] = x;
	}
	public static void setAxisY(int y){
		axis[1] = y;
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

	public static void populate(int axisX, int axisY){
		int[][] room = new int[ROOM_SIZE][ROOM_SIZE];
		int centre = ROOM_SIZE/2;
		while(true){
		int possibleRoom = (int)random(15);
		if (isValidUp(possibleRoom, room[axisY][axisX])){
			setRoomInt(getAxisY()-1,getAxisX(), possibleRoom);

	}
		possibleRoom = (int)random(15);
		if (isValidRight(possibleRoom, room[axisY][axisX])){
			setRoomInt(getAxisY(),getAxisX()+1, possibleRoom);
	}
		possibleRoom = (int)random(15);
		if (isValidDown(possibleRoom, room[axisY][axisX])){
			setRoomInt(getAxisY()+1,getAxisX(), possibleRoom);
	}
		possibleRoom = (int)random(15);
		if (isValidLeft(possibleRoom, room[axisY][axisX])) {
			setRoomInt(getAxisY(), getAxisX() - 1, possibleRoom);

		}
			int dice = (int) random(40);
			if (dice == 20)
				break;
			setAxisX(getAxisY());
			setAxisY(getAxisX());
		}
	}

	public static void generate(){
		int centre = ROOM_SIZE/2;
		setRoomInt(centre, centre, 15);


		populate(axis[0], axis[1]);
		for (int i=0;i<room.length;i++){
			for (int j=0;j<room[i].length; j++){
				System.out.print(room[i][j]);
				System.out.print(" ");
		}
		System.out.println();
		}
	}
	public static void main(String[] args) {
		generate();
	}
		
		
	
	
	}
