package game;

public class Pipe extends Item{

	public Pipe(String n, String d) {
		super(n, d);
	}
	@Override
	public void use() {
		Item wrench = Game.getFromInv("Magic Wrench");
		if(wrench != null) {
			Game.print("Using the Magic Wrench you fix the broken pipe!");
			Game.print("It sounds like the fountain's water is running now!");
		}
		else {
			Game.print("You dont have the right items.");
		}
	}

}
