package game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {
	
	 private static Room currentRoom;
	 public static Scanner scanner = new Scanner(System.in);
	 private static ArrayList<Item> inventory = new ArrayList<>();
	 public static HashMap <String, String> rooms = new HashMap<>();
	 private static GUI gui; // use the name of your gui class
     
    public static void main(String[] args) {
        currentRoom = World.buildWorld(); // If you don't already do this
        gui = new GUI(); // Use the name of your gui class
        gui.print("This Game is unfortunately very simple"); 
        gui.print("I simply ran out of ideas but feel free to look around");
        gui.print("For instructions the Fairy Queen will tell you what to do\n");    
        gui.print(currentRoom);
    }
    
    private static boolean canEnter(Room nextRoom) {
        if (nextRoom == null) {
            print("There is no exit in that direction.");
            return false;
        } else if (nextRoom.isLocked()) {
            print("The room is locked. You need a key or another way to unlock it.");
            return false;
        }
        return true;
    }
    
    public static void print(Object obj) {
    	gui.print(obj.toString());
    }
    
    public static Room getCurrentRoom() {
    	return currentRoom;
    }
    
    public static void addItemInv(Item item) {
    	inventory.add(item);
    }
    
    public static void removeItemInv(Item item) {
    	inventory.remove(item);
    }
    
    public static Item getFromInv(String itemName) {
        for (Item item : inventory) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;  // Return null if item is not found in inventory
    }
    
    public static void saveGame(String save) {
		File f = new File(save);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream stream = new ObjectOutputStream(fos);
			stream.writeObject(currentRoom);
			stream.writeObject(inventory);
			stream.writeObject(rooms);
			stream.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File " + save + "not found");
			System.exit(0);
		}
		catch (IOException ex) {
			System.out.println("Bummer man");
		}
	}
    @SuppressWarnings("unchecked")
	public static void loadGame(String fileName) {
    	File f = new File(fileName);
    	try {
    		FileInputStream fos = new FileInputStream(f);
    		ObjectInputStream stream = new ObjectInputStream(fos);
    		currentRoom = (Room) stream.readObject();
    		inventory =  (ArrayList<Item>) stream.readObject();
    		rooms = (HashMap<String, String>) stream.readObject();
    		stream.close();
    	} 
    	catch (FileNotFoundException e) {
    		System.out.println("File "+fileName+" not found.");
    		System.exit(0);
    	} 
    	catch (IOException ex) {
    		System.out.println("Error saving game.");
    	} 
    	catch (ClassNotFoundException ex) {
    		System.out.println("Something went horribly wrong.");
    	}
    }
    
    public static void processCommand(String command) {
		if (command == null || command.isEmpty()) {
			print("Please enter a valid command.");
			return;
		}
		
		String[] words = command.trim().toLowerCase().split(" ");
		
		Room nextRoom = null;
    	switch(words[0]){
    	case "n":
    	case "s":
    	case "e":
    	case "w":
    	case "u":	
    	case "d":
    		nextRoom = currentRoom.getExit(command.charAt(0));
            if (canEnter(nextRoom)) {
                print("Moving to the next room...");
                currentRoom = nextRoom;
                Game.print(currentRoom);
                print("Say 'take' to grab an item, 'look' to examine an item, or 'inv' to check your inventory");
        		print("Say 'use' to use an item, 'open' to open an item, talk to speak to an NPC");
            }
            break;
        
    	case "take":
    		if (words.length < 2) {
    	        print("Specify an item to take.");
    	    } else {
    	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase(); // Get full item name after "take"
    	        
    	        Item item = currentRoom.getItem(itemName);
    	        if (item == null) {
    	            print("No item found with that name.");
    	        } else {
    	            inventory.add(item);
    	            currentRoom.removeItem(itemName);
    	            print("You picked up the " + item.getName() + ".");
    	        }
    	    }
    	    break;
    		
    	case "inv":
    		if(inventory.isEmpty()) {
				print("Your backpack is empty");
			}
			else {
				print("You have: ");
				for(Item item: inventory) {
					print("- " + item);
				}
			}
    		break;
    		
    	case "look":
    		if (words.length < 2) {
    	        print("Specify an item to look at.");
    	    } else {
    	        String itemName = String.join(" ", java.util.Arrays.copyOfRange(words, 1, words.length));
    	        Item item = currentRoom.getItem(itemName);
    	        
    	        // Check if the item is in the current room
    	        if (item != null) {
    	            print("You look at the " + item.getName() + ": " + item.getDesc());
    	        } else {
    	            // Check if the item is in the inventory
    	            boolean found = false;
    	            for (Item c : inventory) {
    	                if (c.getName().equalsIgnoreCase(itemName)) {
    	                    print("You look at the " + c.getName() + ": " + c.getDesc());
    	                    found = true;
    	                    break; // Exit loop if found
    	                }
    	            }
    	            // If item is not found in the inventory
    	            if (!found) {
    	                print("There is no such item.");
    	            }
    	        }
    	    }
    	    break;
    	    
    	case "use":
    	    if (words.length < 2) {
    	        Game.print("Specify an item to use.");
    	    } else {
    	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase();
    	        Item item = getFromInv(itemName); // Look in inventory
    	        if (item != null) {
    	            item.use(); // Calls the overridden use() method
    	        } else {
    	            Game.print("You don't have that item.");
    	        }
    	    }
    	    break;
    	    
    	case "open":
    		if (words.length < 2) {
    	        Game.print("Specify an item to open.");
    	    } else {
    	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase();
    	        Item item = currentRoom.getItem(itemName); // Look in room
    	        if (item != null) {
    	            item.open(); 
    	        } else {
    	            Game.print("You don't have that item.");
    	        }
    	    }
    	    break;
    	   
    	case "talk":
    		if (words.length < 2) {
    	        Game.print("Specify someone to talk to.");
    	    } else {
    	        String npcName = command.substring(command.indexOf(" ") + 1).toLowerCase();
    	        NPC npc = currentRoom.getNPC(npcName); // Look in room
    	        if (npc != null) {
    	            npc.talk(); 
    	        } else {
    	            Game.print("You can't talk to puppy");
    	        }
    	    }
    	    break;
    	    
    	case "save":
    			Game.saveGame("new game");
    		break;
    		
    	case "load":
    			Game.loadGame("new game");
    		break;
    	case "q":
    		print("Thank you for playing, goodbye!");
    		break;
    		
    	default:
    		print("You can't go there, you'll fall into the void! Try again.");
		}
	
		if (nextRoom != null) {
        	currentRoom = nextRoom; // Move to the next room
    	} 
    }
}
