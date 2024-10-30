package game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Room currentRoom = World.buildWorld();
        Scanner scanner = new Scanner(System.in);
        ArrayList<Item> inventory = new ArrayList<Item>();
        String command = " ";
        
        do {
        		System.out.println(currentRoom);

        		// Get user input for direction (e.g., 'n' for north)
        		System.out.println("\nEnter a direction (n)orth, (s)outh, (e)ast, (w)est, (u)p, or (d)own, or 'q' to quit: ");
        		System.out.println("\nNEW: say 'take' to grab an item, 'look' to examine an item, or 'inv' to check your inventory");
        		command = scanner.nextLine().trim().toLowerCase();
        		String[] words = command.split(" ");
        		Room nextRoom = null;
        		
        		switch(words[0]){
                	case "n":
                		System.out.println("Moving north...");
                		nextRoom = currentRoom.getExit('n');
                		break;
                		
                	case "s":
                		System.out.println("Moving south...");
                		nextRoom = currentRoom.getExit('s');
                		break;
                		
                	case "e":
                		System.out.println("Moving east...");
                    	nextRoom = currentRoom.getExit('e');
                    	break;
                    	
                	case "w":
                		System.out.println("Moving west...");
                    	nextRoom = currentRoom.getExit('w');
                    	break;
                    	
                	case "u":
                		System.out.println("Moving upstairs...");
                		nextRoom = currentRoom.getExit('u');
                		break;
                		
                	case "d":
                		System.out.println("Moving downstairs...");
                		nextRoom = currentRoom.getExit('d');
                    	break; 
                    
                	case "take":
                		if (words.length < 2) {
                	        System.out.println("Specify an item to take.");
                	    } else {
                	        String itemName = command.substring(command.indexOf(" ") + 1).toLowerCase(); // Get full item name after "take"
                	        
                	        Item item = currentRoom.getItem(itemName);
                	        if (item == null) {
                	            System.out.println("No item found with that name.");
                	        } else {
                	            inventory.add(item);
                	            currentRoom.removeItem(itemName);
                	            System.out.println("You picked up the " + item.getName() + ".");
                	        }
                	    }
                	    break;
                		
                	case "inv":
                		if(inventory.isEmpty()) {
            				System.out.println("Your backpack is empty");
            			}
            			else {
            				System.out.println("You have: ");
            				for(Item item: inventory) {
            					System.out.println("a " + item);
            				}
            			}
                		break;
                		
                	case "look":
                		if (words.length < 2) {
                	        System.out.println("Specify an item to look at.");
                	    } else {
                	        String itemName = String.join(" ", java.util.Arrays.copyOfRange(words, 1, words.length));
                	        Item item = currentRoom.getItem(itemName);
                	        
                	        // Check if the item is in the current room
                	        if (item != null) {
                	            System.out.println("You look at the " + item.getName() + ": " + item.getDesc());
                	        } else {
                	            // Check if the item is in the inventory
                	            boolean found = false;
                	            for (Item c : inventory) {
                	                if (c.getName().equalsIgnoreCase(itemName)) {
                	                    System.out.println("You look at the " + c.getName() + ": " + c.getDesc());
                	                    found = true;
                	                    break; // Exit loop if found
                	                }
                	            }
                	            // If item is not found in the inventory
                	            if (!found) {
                	                System.out.println("There is no such item.");
                	            }
                	        }
                	    }
                	    break;
                        
                	case "q":
                		System.out.println("Thank you for playing, goodbye!");
                		break;
                		
                	default:
                		System.out.println("You can't go there, you'll fall into the void! Try again.");
        			}
        		
        			if (nextRoom != null) {
                    	currentRoom = nextRoom; // Move to the next room
                	} 
        } while (!command.equals("q"));
        scanner.close(); 
    } 
}
