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
			"How can I fix the garden?",
			"I restored your crown what do I do next?",
			"The fountain doesn't have water though",
			"I've found the wrench",
			"Woah I fixed the pipes and defeated the Prince of Shadow"
			
		};
		getResponse(options);
		}
	
	@Override
	public void response(int option) {
		switch(option) {
		case 1:
			say("The Prince of Shadow destroyed it and imprisoned me here");
			break;
		case 2:
			say("You must find and restore my crown so I can figure out what to do next");
			break;
		case 3:
			say("You must find my seed and water it with water from the fountain in order to restore the garden");
			break;
		case 4:
			say("The pipes must have been damaged and interfering with the flow of water you must fix them");
			break;
		case 5:
			say("The pipes for the fountain are downstairs in the maintenance room");
			break;
		case 6:
			say("Thank you for defeating him. All you have left to do is place the miracle seed in the fountain");
			break;
		}
	}
	
	public void give(Item item){
        if(Game.getFromInv(item.getName()) == null){
            Game.print("You dont have such item");
        }
        else if(item.getName() == "Fairy Crown"){
            Game.print("You gave the " + item.getName() + " to the Fairy Queen");
            Game.removeItemInv(item);
        }
        else{
            Game.print("You can't give the " + item.getName() + " to the Queen!");
        }
    }
}
