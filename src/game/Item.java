package game;

public class Item {
	private String name;
	private String desc;
	
	public Item(String n, String d) {
		name = n;
		desc = d;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public String toString() {
		return name + ", " + desc	;
	}
}

