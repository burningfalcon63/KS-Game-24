package game;

public class Prince extends NPC{
	public Prince() {
		super("Prince of Shadow", "The destroyer of the Fairy Queen's garden");
	}
	
	@Override
	public void talk() {
		say("I am Aatrox the Prince of Shadow.");
		String[] options = {
			"What happened to this place?",
			"How did you destroy the garden?",
			"Will you let me fix it?",
			"I will tell her I defeated you"
			
		};
		getResponse(options);
		}
	
	@Override
	public void response(int option) {
		switch(option) {
		case 1:
			say("The Fairy King found out about the history I shared with the Fairy Queen and destroyed the garden, framing me");
			break;
		case 2:
			say("He spread a poison through the water in the pipes corrupting the entire garden and mistakenly sealing his Queen's power");
			break;
		case 3:
			say("I will allow you to anything to restore my lost love's glory, just tell her you defeated me");
			break;
		case 4:
			say("Thank you, good luck on your mission.");
		}
	}
}
