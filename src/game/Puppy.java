package game;

public class Puppy extends NPC {
	public Puppy() {
		super("puppy", "A hideous puppy wags his tail.");
	}
	int chat = 1;
	@Override
	public void talk() {
		if(chat == 1) {
			say("Hi! I'm an adorable puppy!");
			String[] options = {
					"Yes you are! Who's a good boy?",
					"Ew, no. You're actually kinda hideous."
			};
			getResponse(options);
			chat++;
		}
		else if(chat == 2) {
			say("Hey! Wanna play fetch? Say yes! Say yes!");
			String[] options = {
					"Yes! I love fetch!",
					"No. I am a horrible person and don't like playing with puppies."
			};
			chat++;
			getResponse(options);
		}
		else if(chat > 2) {
			say("Yip!");
			chat++;
		}
	}
	
	@Override
	public void response(int option) {
		switch(option) {
		case 1:
			say("I am! I'm a good boy!");
			break;
		case 2:
			say("I am adorable! Why are you so mean?");
			Game.print("The puppy bites you. You deserve it.");
			break;
			
		case 3:
			say("Yay! Fetch!");
			Game.print("The puppy runs off and returns with a ball. The player receives the ball.");
			Item ball = new Item("Ball", "an ugly dog gave it to you");
			Game.addItemInv(ball);
			break;
		case 4:
			say("You're a bad person! I don't like you!");
			Game.print("The puppy runs away and doesn't come back.");
			break;
		}
	}
}