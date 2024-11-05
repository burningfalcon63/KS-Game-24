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
                		print("Moving north...");
                		nextRoom = currentRoom.getExit('n');
                		break;
                		
                	case "s":
                		print("Moving south...");
                		nextRoom = currentRoom.getExit('s');
                		break;
                		
                	case "e":
                		print("Moving east...");
                    	nextRoom = currentRoom.getExit('e');
                    	break;
                    	
                	case "w":
                		print("Moving west...");
                    	nextRoom = currentRoom.getExit('w');
                    	break;
                    	
                	case "u":
                		print("Moving upstairs...");
                		nextRoom = currentRoom.getExit('u');
                		break;
                		
                	case "d":
                		print("Moving downstairs...");
                		nextRoom = currentRoom.getExit('d');
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
                	        print("Specify an item to use.");
                	    } else {
                	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase(); // Get full item name after "take"
                	        
                	        Item item = currentRoom.getItem(itemName);
                	        if (item == null) {
                	            print("No item found with that name.");
                	        } else {
                	            item.use();
                	            print("You used the " + item.getName() + ".");
                	        }
                	    }
                	    break;
                	    
                	case "open":
                		if (words.length < 2) {
                	        print("Specify an item to take.");
                	    } else {
                	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase(); // Get full item name after "take"
                	        
                	        Item item = currentRoom.getItem(itemName);
                	        if (item == null) {
                	            print("No item found with that name.");
                	        } else {
                	            item.open();
                	            print("You opened up the " + item.getName() + ".");
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
    
    public static void print(Object obj) {
    	System.out.println(obj.toString());
    }
    
    public static Room getCurrentRoom() {
    	return currentRoom;
    }
    
    public static void addItemInv(Item item) {
    	inventory.add(item);
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
