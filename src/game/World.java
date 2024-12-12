package game;

public class World {
	
	private static Room createRoom(String name, String description) {
	    Room room = new Room(name);
	    Game.rooms.put(name, description);
	    //addRoomToFile("rooms.txt", name, description);
	    return room;
	}
	
	private static void connectRooms(Room from, char direction, Room to) {
	    from.addExit(to, direction);
	    // Optionally, add a reverse connection
	    if (direction == 'n') to.addExit(from, 's');
	    if (direction == 's') to.addExit(from, 'n');
	    if (direction == 'e') to.addExit(from, 'w');
	    if (direction == 'w') to.addExit(from, 'e');
	    if (direction == 'u') to.addExit(from, 'd');
	    if (direction == 'd') to.addExit(from, 'u');
	}
	
	public static Room buildWorld() {

    	Puppy puppy = new Puppy();
    	Queen queen = new Queen();
    	Fountain fountain = new Fountain("Grand Fountain", "The center of room");
        Room grandFountain = createRoom("Grand Fountain", "You are next to the Grand Fountain, it's the biggest you've seen.");
        grandFountain.addNPC(puppy);
        grandFountain.addNPC(queen);
        grandFountain.addItem(fountain);
        
        Item seed = new Fountain("Miracle Seed", "it has a strange warmth to it.");
        Room greenHouse = createRoom("Green House", "You are at the Greenhouse, plants from all over grow here.");
        greenHouse.addItem(seed);
        
        Item rose = new Item("Crystal Rose", "it sparkles in the light.");
        Room roseGarden = createRoom("Rose Garden", "You are in the Rose Garden, the roses smell nice.");
        Room flowerFields = createRoom("Flower Fields", "You are in the Flower Fields, the fields go off into the distance."); 
        Room parkingLot = createRoom("Parking Lot", "You are in the Parking lot, your mom dropped you off here.");
        roseGarden.addItem(rose);

        Room wishingWell = createRoom("Wishing Well", "You are at the Wishing Well, maybe wishes can come true.");
        Item coin = new Well("Gold Coin", "the fairy Queen is printed on it.");
        Item well = new Well("Wishing Well", "maybe you should throw a coin in");
        grandFountain.addItem(coin);
        wishingWell.addItem(well);

        Room fairyShrine = createRoom("Fairy Shrine", "You are at the Fairy Shrine, the statue of the Fairy King glows faintly.");
        Item crown = new Item("Fairy Crown", "it looks like it's fallen into disrepair.");
        fairyShrine.addItem(crown);
        
        Room observatoryM = createRoom("Observatory", "You are in the Observatory, the stars look beautiful here."); 
        Room observatoryB = createRoom("Observatory Bathroom", "You are in the Observatory Bathroom, it smells like flowers.");

        Room repairRoomM = createRoom("Repair Room", "You are in the Repair Room, the fountain is maintained here."); 
        Prince prince = new Prince();
        Room repairRoomC = createRoom("Repair Closet", "You are in the Repair Room Closet, there are many tools here."); 
        Room repairRoomB = createRoom("Repair Bathroom", "You are in the Repair Room Bathroom, it smells pretty bad here."); 
        Item wrench = new Pipe("Magic Wrench", "it's warm to the touch, maybe it can fix something.");
        Item pipe = new Item("Broken Pipe", "it looks like this pipe pumps something very important");
        repairRoomC.addItem(wrench);
        repairRoomM.addItem(pipe);
        repairRoomM.addNPC(prince);
        
        Room ruinedGarden = createRoom("Ruined Garden", "You are in a Ruined Garden it looks like it was once very beautiful here");
        Item stone = new Key("Shiny Stone", "i feel like this fits somewhere", ruinedGarden);
        Item glasses = new Crown("Rose Tinted Glasses", "The world looks different now");
        ruinedGarden.setLocked(true);
        ruinedGarden.addItem(glasses);
        grandFountain.addItem(stone);

        connectRooms(grandFountain, 's', roseGarden); // Grand Fountain
        connectRooms(grandFountain, 'w', wishingWell);
        connectRooms(grandFountain, 'e', greenHouse);
        connectRooms(grandFountain, 'n', fairyShrine);
        connectRooms(grandFountain, 'u', observatoryM);
        connectRooms(grandFountain, 'd', repairRoomM);

        connectRooms(roseGarden, 'e', flowerFields); // Rose Garden
        connectRooms(roseGarden, 'w', parkingLot);

        connectRooms(flowerFields, 'w', roseGarden);

        connectRooms(wishingWell, 'e', grandFountain); // Wishing Well

        connectRooms(greenHouse, 'w', grandFountain); // Green House

        connectRooms(fairyShrine, 's', grandFountain); // Fairy Shrine
        connectRooms(fairyShrine, 'u', ruinedGarden);

        connectRooms(ruinedGarden, 'd', fairyShrine);

        connectRooms(observatoryM, 'd', grandFountain); // Observatory
        connectRooms(observatoryM, 'e', observatoryB);
        connectRooms(observatoryB, 'w', observatoryM);

        connectRooms(repairRoomM, 'u', grandFountain); // Repair Room
        connectRooms(repairRoomM, 'e', repairRoomC);
        connectRooms(repairRoomC, 'w', repairRoomM);
        connectRooms(repairRoomM, 'w', repairRoomB);
        connectRooms(repairRoomB, 'e', repairRoomM);
        
        return grandFountain;
    }
}