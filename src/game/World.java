package game;
public class World {
    // Builds the game world.
    // Returns the room the player starts in.
    public static Room buildWorld() {

        Room grandFountain = new Room("You are next to the Grand Fountain, it's the biggest you've seen.");
        
        Item seed = new Item("Miracle Seed", "it has a strange warmth to it.");
        Room greenHouse = new Room("You are at the Greenhouse, plants from all over grow here.");
        grandFountain.addItem(seed);
        
        Item rose = new Item("Crystal Rose", "it sparkles in the light.");
        Room roseGarden = new Room("You are in the Rose Garden, the roses smell nice.");
        grandFountain.addItem(rose);
        Room flowerFields = new Room("You are in the Flower Fields, the fields go off into the distance."); 
        Room parkingLot = new Room("You are in the Parking lot, your mom dropped you off here.");

        Item coin = new Item("Gold Coin", "the fairy Queen is printed on it.");
        Room wishingWell = new Room("You are at the Wishing Well, maybe wishes can come true.");
        grandFountain.addItem(coin);

        Item crown = new Item("Fairy Crown", "it looks like it's fallen into disrepair.");
        Room fairyShrine = new Room("You are at the Fairy Shrine, the statue of the Fairy King glows faintly.");
        grandFountain.addItem(crown);
        
        Room observatoryM = new Room("You are in the Observatory, the stars look beautiful here."); 
        Room observatoryB = new Room("You are in the Observatory Bathroom, it smells like flowers.");

        Item wrench = new Item("Magic Wrench", "it's warm to the touch, maybe it can fix something.");
        Room repairRoomM = new Room("You are in the Repair Room, the fountain is maintained here."); 
        Room repairRoomC = new Room("You are in the Repair Room Closet, there are many tools here."); 
        Room repairRoomB = new Room("You are in the Repair Room Bathroom, it smells pretty bad here."); 
        grandFountain.addItem(wrench);

        grandFountain.addExit(roseGarden, 's'); //Grand Fountain
        grandFountain.addExit(wishingWell, 'w');
        grandFountain.addExit(greenHouse, 'e');
        grandFountain.addExit(fairyShrine, 'n');
        grandFountain.addExit(observatoryM, 'u');	
        grandFountain.addExit(repairRoomM, 'd');
     

        roseGarden.addExit(grandFountain, 'n'); //Rose Garden(exit)
        roseGarden.addExit(flowerFields, 'e');
        
        flowerFields.addExit(roseGarden, 'w');
        roseGarden.addExit(parkingLot, 'w');
        parkingLot.addExit(roseGarden, 'e');
        
        wishingWell.addExit(grandFountain, 'e');
       //Wishing Well

        greenHouse.addExit(grandFountain, 'w'); //GreenHouse
        

        fairyShrine.addExit(grandFountain, 's'); //Fairy Shrine
        
        
        observatoryM.addExit(grandFountain, 'd'); //Observatory
        observatoryM.addExit(observatoryB, 'e'); //Observatory bathroom
        observatoryB.addExit(observatoryM, 'w'); //Observatory 
        
        repairRoomM.addExit(grandFountain, 'u'); //repairroom
        repairRoomM.addExit(repairRoomC, 'e'); //repairroom closet
        repairRoomC.addExit(repairRoomM, 'w'); 
        
        repairRoomM.addExit(repairRoomB, 'w'); //repairroom bathroom
        repairRoomB.addExit(repairRoomM, 'e');
        
        return grandFountain;
    }
}