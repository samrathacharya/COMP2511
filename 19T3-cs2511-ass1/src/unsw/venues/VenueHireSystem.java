/**
 *
 */
package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Venue Hire System for COMP2511.
 *
 * A basic prototype to serve as the "back-end" of a venue hire system. Input
 * and output is in JSON format.
 * @author Sam Acharya with stub provided by Robert Clifton-Everest
 *
 */
public class VenueHireSystem {
	private ArrayList<Venue> venues;
	private ArrayList<Reservation> reservations;
	

    /**
     * Constructs a venue hire system. Initially, the system contains no venues or bookings.
     * The constructor creates a list of venues and reservations
     * @param venues A list of venues 
     * @param reservations A list of reservations
     */
    public VenueHireSystem() {

    	this.venues = new ArrayList<Venue>();
    	this.reservations = new ArrayList<Reservation>();
    }

    /**
     * Processes the command passed in by standard input/file to the program
     * Outputs the relevant JSON output as given by the specs
     * @param json The json passed by the parsing function
     * @return Returns either a JSON object or JSON array for the relevant command
     * @param id The id of the reservation
     * @param start The starting date for the reservation
     * @param end The starting date for the reservation
     * @param small The number of small rooms
     * @param medium The number of medium rooms
     * @param large The number of large rooms
     * @param result The JSON result object to return
     * @param resultArray
     */
    private void processCommand(JSONObject json) {
        String id;
        LocalDate start;
        LocalDate end;
        int small;
        int medium;
        int large;
        JSONObject result;
        JSONArray resultArray;
        String venueString;
        switch (json.getString("command")) {
       
        
        case "room":
            String venue = json.getString("venue");
            String room = json.getString("room");
            String size = json.getString("size");
            addRoom(venue, room, size);
            break;

        case "request":
			id = json.getString("id");
			start = LocalDate.parse(json.getString("start"));
			end = LocalDate.parse(json.getString("end"));
			small = json.getInt("small");
			medium = json.getInt("medium");
			large = json.getInt("large");
			result = request(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;
            
        case "change":
        	id = json.getString("id");
			start = LocalDate.parse(json.getString("start"));
			end = LocalDate.parse(json.getString("end"));
			small = json.getInt("small");
			medium = json.getInt("medium");
			large = json.getInt("large");
			result = change(id, start, end, small, medium, large);

            System.out.println(result.toString(2));
            break;
        	

        case "cancel":
        	id = json.getString("id");
        	cancel(id);
        	break;
        
        case "list":
        		venueString = json.getString("venue");
        		resultArray = list(venueString);
        		
        		System.out.println(resultArray.toString(2));
        	break;
        }
    }
    
    /**
     * Adds new venue to the list of venue
     * @param newVenue New venue to add to the hiring system
     */
	public void addVenue(Venue newVenue) {
    	venues.add(newVenue);
    }
    
	/**
	 * Adds new reservation to the list of venue
	 * @param newReservation New reservation to add to the hiring system
	 */
    public void addReservation(Reservation newReservation) {
    	reservations.add(newReservation);
    }
   
    /**
     * Processes the room command and adds the room to the venue
     * @param venue The venue the room is assigned to
     * @param room Name of the room
     * @param size The size of the room
     */
	private void addRoom(String venue, String room, String size) {
		//Check if the venue exists and retract the object
		boolean venueExists = false;
		Venue existingVenue = null;
		for (Venue checkVenue: venues) {
			if (checkVenue.getName().equals(venue)) {
				venueExists = true;
				existingVenue = checkVenue;
			}
		}		
		
		//Check to see if venue exists and add the room to the appropriate venue
		//Else make a new venue and add to it
		if(venueExists) {
			Room newRoom = new Room(existingVenue, size, room);
		}else {
			Venue newVenue = new Venue(this, venue);
			Room newRoom = new Room(newVenue, size, room);
		}
		
    }
	
	/**
	 * For debugging: Lists all reservations in the reservations list
	 */
	private void listReservations() {
		for (Reservation reservation: reservations) {
			System.out.println("---Reservation: "+reservation.getID()+"----");
			System.out.println("Starts at: "+reservation.getStartDate()+" Ends: "+reservation.getEndDate());
			System.out.println("Small: "+reservation.getSmall()+" Medium: "+reservation.getMedium()+" Large: "+reservation.getLarge());
		}
	}
	
	/**
	 * Changes the specs for the reservation. Does this by removing all the rooms and venues attached to the reservation
	 *  and vice versa, and then reassigning the room and venues using the findRoom()
	 * @param id The id of the reservation
	 * @param start The start Date of the reservation
	 * @param end The end date of the reservation
	 * @param small The number of small rooms
	 * @param medium The numebr of medium rooms
	 * @param large The number of large rooms
	 * @return Returns a JSON object with the appropriate result (success/failure) of the reservation and the rooms
	 */
    private JSONObject change(String id, LocalDate start, LocalDate end, int small, int medium, int large) {
		Reservation changeReservation = getReservation(id);
		changeReservation = cancelReservation(changeReservation);
		changeReservation.setDate(start, end);
		changeReservation.setSizes(small, medium, large);
		JSONObject toReturn = findRoom(changeReservation);
		return toReturn;
	}
    
    /**
     * Cancels the rooms and venues with the reservation and vice versa.
     * @param changeReservation The reservation to change
     * @return Returns the changed reservation object
     */
    private Reservation cancelReservation(Reservation changeReservation) {
    	Venue toDeleteVenue = changeReservation.getVenue();
    	//If the venue was booked, then make some changes
    	if(toDeleteVenue!=null) {
    		changeReservation.removeVenue(toDeleteVenue);
    		toDeleteVenue.removeReservation(changeReservation);
    		changeReservation.removeRooms();
    	}
		return changeReservation;
    }
    
    /**
     * Deals with the cancel command. Functionality done by cancelReservaton()
     * @param id The id of the reservation to cancel
     */
	private void cancel(String id) {
		Reservation changeReservation = getReservation(id);
		changeReservation = cancelReservation(changeReservation);
	}
    
	/**
	 * Gets the reservation object
	 * @param id ID of reservation
	 * @return Returns the reservation object
	 */
    public Reservation getReservation(String id) {
		for (Reservation reservation: reservations) {
			if(reservation.getID().equals(id)) {
				return reservation;
			}
		}
		return null;
	}
    
    /**
	 * Gets the venue object
	 * @param id ID of venue
	 * @return Returns the venue object
	 */
    public Venue getVenue(String id) {
  		for (Venue venue: venues) {
  			if(venue.getName().equals(id)) {
  				return venue;
  			}
  		}
  		return null;
  	}
    
    /**
     * Finds the rooms that can fulfill the reservation
     * @param reservation The reservation to fill
     * @return A JSON object with the result containing the status and rooms that can fulfill the reservation
     */
    public JSONObject findRoom(Reservation reservation) {
    	JSONObject test = new JSONObject();
    	test.put("status", "rejected");
        boolean reservationFilled = false;
        for (Venue venue: venues) {
        	test = venue.canFulfill(reservation);
        	String status = (String) test.get("status");
        	if(status.equals("success")) {
        		reservationFilled = true;
        		test.put("venue", venue.getName());
        		break;
        	}
        }
        //If request cannot be fulfilled, then remove the rooms attribute
        if(!reservationFilled) {
        	test.remove("rooms");
        }  
        return test;
    }
   
    /**
     * Deals with the list command
     * @param venueString Name of venue to list rooms and reservations
     * @return JSON Array with the list of rooms and their reservations
     */
	private JSONArray list(String venueString) {
		Venue venue = getVenue(venueString);
		JSONArray toReturn = venue.addListRooms();
		return toReturn;
	}

	/**
	 * Deals with the request command. Abstract functonality to findRoom()
	 * @param id ID of reservation
	 * @param start start date of reservation
	 * @param end end date of reservation
	 * @param small number of small rooms
	 * @param medium number of med. rooms
	 * @param large no. of large rooms
	 * @return JSON Object with the result of the request according to specs
	 */
    public JSONObject request(String id, LocalDate start, LocalDate end,
            int small, int medium, int large) {
        Reservation newReservation = new Reservation(this, id, start, end, small, medium, large);
        JSONObject toReturn = findRoom(newReservation);
        return toReturn;
    }

    /**
     * Main function
     * @param args Args given to main commands
     */
    public static void main(String[] args) {
        VenueHireSystem system = new VenueHireSystem();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (!line.trim().equals("")) {
                JSONObject command = new JSONObject(line);
                system.processCommand(command);
            }
        }
        sc.close();
    }

}
