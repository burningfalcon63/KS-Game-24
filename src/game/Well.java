package game;

public class Well extends Item{

	public Well(String n, String d) {
		super(n, d);
	}
	
	@Override
	public void open() {
		Item combination = Game.getFromInv("combination");
		if(combination != null) {
			Game.print("Tossing the coin in the well makes the water shimmer and brings a scepter to float at the surface");
			Game.print("Naturally you pick up the scepter");
			Item scepter = new Item("Fairy Scepter", "it looks like it could be important");
			Game.addItemInv(scepter);
		}
		else {
			Game.print("You dont have the combination.");
		}
	}
}
