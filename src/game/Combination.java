package game;

public class Combination extends Item{
	private static final long serialVersionUID = 1L;

	public Combination(String n, String d) {
		super(n, d);
	}

	@Override
	public void use() {
		Game.print("If you find a mirror try moving through it!");
	}
}
