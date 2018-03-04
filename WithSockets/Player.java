import java.util.ArrayList;
import java.util.List;

public class Player {
	int xPos;
	int yPos;
	int direction;
	List<String> items;
	
	public Player(int _xPos, int _yPos) {
		this.xPos = _xPos;
		this.yPos = _yPos;
		this.direction = 0;
		items = new ArrayList<String>();
	}
	
	public void move(int steps) {
		this.xPos += (int)Math.sin(Math.PI*direction/2) * steps;
		this.yPos += (int)Math.cos(Math.PI*direction/2) * steps;
	}

	public void turn(int rotation) {
		direction = Math.abs(direction + rotation % 4);
	}
	
	public void pickUp(String item) {
		items.add(item);
	}
	
	public void putDown(String item) {
		if(items.contains(item))
			items.remove(item);
	}
	
	public String view() {
		StringBuilder sb = new StringBuilder();
		sb.append("Position: " + xPos + ", " + yPos + "\n");
		sb.append("Items: " + items.toString());
		
		return sb.toString();
	}
}
