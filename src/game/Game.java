package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
	
	 private static Room currentRoom;
	 private static Scanner scanner = new Scanner(System.in);
	 private static ArrayList<Item> inventory = new ArrayList<>();
     
    public static void main(String[] args) {
    	currentRoom = World.buildWorld();
        String command = " ";
        
        do {
        		print(currentRoom);

        		// Get user input for direction (e.g., 'n' for north)
        		print("\nEnter a direction (n)orth, (s)outh, (e)ast, (w)est, (u)p, or (d)own, or 'q' to quit: ");
        		print("\nNEW: say 'take' to grab an item, 'look' to examine an item, or 'inv' to check your inventory");
        		command = scanner.nextLine().trim().toLowerCase();
        		String[] words = command.split(" ");
        		Room nextRoom = null;
        		
        		switch(words[0]){
                	case "n":
                		nextRoom = currentRoom.getExit('n');
                        if (canEnter(nextRoom)) {
                            print("Moving north...");
                            currentRoom = nextRoom;
                        }
                        break;
                		
                	case "s":
                		nextRoom = currentRoom.getExit('s');
                        if (canEnter(nextRoom)) {
                            print("Moving south...");
                            currentRoom = nextRoom;
                        }
                        break;
                		
                	case "e":
                		nextRoom = currentRoom.getExit('e');
                        if (canEnter(nextRoom)) {
                            print("Moving east...");
                            currentRoom = nextRoom;
                        }
                        break;
                    	
                	case "w":
                		nextRoom = currentRoom.getExit('w');
                        if (canEnter(nextRoom)) {
                            print("Moving west...");
                            currentRoom = nextRoom;
                        }
                        break;
                    	
                	case "u":
                		nextRoom = currentRoom.getExit('u');
                        if (canEnter(nextRoom)) {
                            print("Moving up...");
                            currentRoom = nextRoom;
                        }
                        break;
                		
                	case "d":
                		nextRoom = currentRoom.getExit('d');
                        if (canEnter(nextRoom)) {
                            print("Moving down...");
                            currentRoom = nextRoom;
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
                	    
                	case "q":
                		print("Thank you for playing, goodbye!");
                		break;
                		
                	default:
                		print("You can't go there, you'll fall into the void! Try again.");
        			}
        		
        			if (nextRoom != null) {
                    	currentRoom = nextRoom; // Move to the next room
                	} 
        } while (!command.equals("q"));
        scanner.close(); 
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
    	System.out.println(obj.toString());
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
}
