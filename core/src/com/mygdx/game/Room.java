public class Level{
	private final ROOM_SIZE
	
	public double random(int x){
		 return (double)x * Math.random();
	
	public void generate(){
		int[][] room = new int[ROOM_SIZE][ROOM_SIZE];
		int centre = ROOM_SIZE/2;
		room[centre][centre] = 15;
		
		if (room[centre+1][centre] == 0){
			//GEN ROOM BAESED ON DOOR LOCATION
			double prob = random(4);
		}
			
		
		
	
	
	}
}