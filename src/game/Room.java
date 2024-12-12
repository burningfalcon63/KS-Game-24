package game;

import java.io.Serializable;
import java.util.HashMap;

public class Room implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Room east;
    private Room west;
    private Room north;
    private Room south;
    private Room up;
    private Room down;
    private Boolean locked = false;
    private String name;
    private HashMap<String, NPC> npcs = new HashMap<String, NPC>();
    private HashMap<String, Item>items = new HashMap<String, Item>();
    private HashMap<Character, Room> exits;

    public Room(String name) {
        this.name = name;
        this.exits = new HashMap<>();
    }

    public Room getExit(char x) {
        Room nextRoom = null;
        switch (x) {
            case 'e': nextRoom = east; break;
            case 'w': nextRoom = west; break;
            case 'n': nextRoom = north; break;
            case 's': nextRoom = south; break;
            case 'u': nextRoom = up; break;
            case 'd': nextRoom = down; break;
        }
        if (nextRoom != null && nextRoom.isLocked()) {
            System.out.println("The room is locked.");
            return null; // Prevent access
        }
        return nextRoom;
    }

    public void addExit(Room room, char direction){
        if(direction == 'e')
            east = room;

        else if(direction == 'w')
            west = room;
        
        else if(direction == 'n')
            north = room;
        
        else if(direction == 's')
            south = room;
        
        else if(direction == 'u')
            up = room;
        
        else if(direction == 'd')
            down = room;
        exits.put(direction, room);
    }
    
    public Item getItem(String name) {
    	for (String key : items.keySet()) {
            if (key.equalsIgnoreCase(name)) { // Case-insensitive match
                return items.get(key);
            }
        }
        return null; // Return null if no match is found
	}

	public void addItem(Item item) {
		items.put(item.getName(), item);
	}

	public Item removeItem(String name) {
		for (String key : items.keySet()) {
	        if (key.equalsIgnoreCase(name)) { // Case-insensitive match
	            return items.remove(key);
	        }
	    }
	    return null; // Return null if no match is found
	}
	
	public void addNPC(NPC npc) {
		npcs.put(npc.getName(), npc);
		
	}
	
	public NPC getNPC(String name) {
    	for (String key : npcs.keySet()) {
            if (key.equalsIgnoreCase(name)) { // Case-insensitive match
                return npcs.get(key);
            }
        }
        return null; // Return null if no match is found
	}
	
	public void printExits() {
        Game.print("Exits from " + name + ":");
        for (HashMap.Entry<Character, Room> entry : exits.entrySet()) {
            Game.print(entry.getKey() + " - " + entry.getValue().getName());
        }
    }

	@Override
    public String toString() {
        StringBuilder roomDescription = new StringBuilder(getDesc() + "\nItems in this room:");
        if (items.isEmpty() && npcs.isEmpty()) {
            roomDescription.append(" None.");
        } else {
            for (String itemName : items.keySet()) {
                roomDescription.append("\n- ").append(itemName);
            }
            for(String npcName : npcs.keySet()) {
            	roomDescription.append("\n* ").append(npcName);
            }
            printExits();
        }
        return roomDescription.toString();
    }


	public Boolean isLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDesc() {
        // Fetch description from Game's HashMap
        String description = Game.rooms.get(name);
        return description != null ? description : "No description available.";
    }
}


