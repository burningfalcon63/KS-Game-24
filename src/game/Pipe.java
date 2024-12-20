package game;

public class Pipe extends Item{

	private static final long serialVersionUID = 1L;
	public Pipe(String n, String d) {
		super(n, d);
	}
	@Override
	public void use() {
		Item wrench = Game.getFromInv("Magic Wrench");
		Item pipe = Game.getFromInv("Broken Pipe");
		if(wrench != null) {
			Game.print("Using the Magic Wrench you fix the broken pipe!");
			Game.removeItemInv(pipe);
			Game.print("It sounds like the fountain's water is running now!");
		}
		else {
			Game.print("Maybe you can fix the pipe with a wrench.");
		}
	}

}
