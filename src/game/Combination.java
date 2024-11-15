package game;

public class Combination extends Item{
	public Combination(String n, String d) {
		super(n, d);
	}

	@Override
	public void use() {
		Game.print("If you find a mirror try moving through it!");
	}
}
