import java.util.Arrays;

public class Room{
	private static final int ROOM_SIZE = 10;
	
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
	public boolean isValidDown(int x, int y){
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
		int[][] room = new int[ROOM_SIZE][ROOM_SIZE];
		int centre = ROOM_SIZE/2;
		room[centre][centre] = 15;
		int curRoom = 15;
		int[] axis = {centre, centre};
		for (int i=0; i<4; i++) {
		}
		if (room[centre-1][centre] == 0){
			//GEN ROOM BASED ON DOOR LOCATION
			int possibleRoom = (int)random(15);
			if (isValidUp(possibleRoom, curRoom)){
				room[centre-1][centre] = possibleRoom;

			}
			else{



			}
			}
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
