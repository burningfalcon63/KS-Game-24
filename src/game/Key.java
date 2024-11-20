package game;

public class Key extends Item {
    private static final long serialVersionUID = 1L;
	private Room lockRoom;
    
    public Key(String name, String description, Room lockRoom) {
        super(name, description);
        this.lockRoom = lockRoom;
    }

    @Override
    public void use() {
        if (lockRoom.isLocked()) {
            lockRoom.setLocked(false);
            Game.print("You used the " + getName() + " to unlock the " + lockRoom.getName() + ".");
        } else {
            Game.print("The " + lockRoom.getName() + " is already unlocked.");
        }
    }
}
