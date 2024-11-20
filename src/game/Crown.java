package game;

public class Crown extends Item {

	private static final long serialVersionUID = 1L;
	public Crown(String n, String d) {
		super(n, d);
	}
	@Override
	public void use() {
		Item glasses = Game.getFromInv("Rose Tinted Glasses");
		Item crown = Game.getFromInv("Fairy Crown");
		if(glasses != null) {
			Game.print("Using the Rose Tinted Glasses you restore the Fairy Crown to its former glory!");
			Item glimmeringCrown = new Item("Fairy Crown", "it shines with an otherwordly glimmer");
			Game.addItemInv(glimmeringCrown);
			Game.removeItemInv(crown);
		}
		else {
			Game.print("You dont have the right items.");
		}
	}
}
