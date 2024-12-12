package game;

public class Well extends Item{

	private static final long serialVersionUID = 1L;

	public Well(String n, String d) {
		super(n, d);
	}
	
	@Override
	public void open() {
		Item coin = Game.getFromInv("Gold Coin");
		if(coin != null) {
			Game.print("Tossing the coin in the well makes the water shimmer and an important message appears in your inventory");
			Game.print("Naturally you pick up the scepter");
			Item message = new Item("Message", "The idea for how this game kept changing so it ended up the barest of minimums I apologize.");
			Game.addItemInv(message);
		}
		else {
			Game.print("You dont have the combination.");
		}
	}
}
