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
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public void open() {
		Game.print("You can't open that!");
	}
	
	public void use() {
		Game.print("You can't use that!");
	}
	
	@Override
	public String toString() {
		return name + ", " + desc	;
	}
}

