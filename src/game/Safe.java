package game;

public class Safe extends Item{

	private static final long serialVersionUID = 1L;

	public Safe(String n, String d) {
		super(n, d);
	}
	
	@Override
	public void open() {
		Item combination = Game.getFromInv("combination");
		if(combination != null) {
			Game.print("Using the combination, you push through the mirror to find a diamond inside! Naturally you pocket the diamond");
			Item diamond = new Item("diamond", "shines faintly in the light");
			Game.addItemInv(diamond);
		}
		else {
			Game.print("You dont have the combination.");
		}
	}
}
