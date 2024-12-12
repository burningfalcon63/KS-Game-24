package game;

public class Fountain extends Item {

	private static final long serialVersionUID = 1L;
	public Fountain(String n, String d) {
		super(n, d);
	}
	@Override
	public void open() {
		Item seed = Game.getFromInv("Miracle Seed");
		if(seed != null) {
			Game.print("Using the Miracle Seed you restore the Fairy Queen's garden its former glory!");
			Game.print("Here is your certificate!");
			Item certificate = new Item("Certificate", "Thank you for spending your afternoon at Patricia's daycare! Come back soon");
			Game.addItemInv(certificate);
		}
		else {
			Game.print("You dont have the Miracle Seed.");
		}
	}
}
