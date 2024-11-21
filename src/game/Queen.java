package game;

public class Queen extends NPC{
	public Queen() {
		super("Fairy Queen", "The once radiant Queen of the fairy realm");
	}
	
	@Override
	public void talk() {
		say("I am Orianna the Fairy Queen.");
		String[] options = {
			"What happened to this place?",
			"How can I fix the garden?"
		};
		getResponse(options);
		}
	/*
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
		*/

	
	@Override
	public void response(int option) {
		switch(option) {
		case 1:
			say("The Prince of Shadow destroyed it and imprisoned me here");
			break;
		case 2:
			say("You must find my seed and water it with water from the fountain in order to restore the garden");
			break;
		}
	}
}
