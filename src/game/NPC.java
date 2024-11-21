package game;

public class NPC {
	private String name; // NPC's name
	private String desc; // NPC's description
	public NPC(String name, String desc) {
		this.name = name;
		this.desc = desc;
	}	
	
	public String getName() {
		return name;
	}
	
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getDesc() {
		return desc;
	}
	
	public void say(String dialog) {
		Game.print(name+": "+dialog);
	}
	
	public void talk() {
		Game.print("You can't talk to "+name+".");
	}
	
	public void response(int option) {
		// This method intentionally left blank.
	}
	
	public void getResponse(String[] options) {
		for(int i=0; i<options.length; i++) {
			Game.print("Option "+(i+1)+": "+options[i]);
		}
		Game.print("Enter an option (1-"+options.length+"):");
		int option = Game.scanner.nextInt();
		Game.scanner.nextLine(); // Flush input buffer
		response(option);
	}
}