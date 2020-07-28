package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * A Venue 
 * @author Sam A z5161678
 *
 */
public class Venue {

	private VenueHireSystem venueHireSystem;	
	private ArrayList<Room> allRooms;	//All rooms the venue has
	private ArrayList<Reservation> reservations;	//List of reservations for the venue
	private String venueName;
	private ArrayList<Room> newRoomsBooked;			//New rooms booked under a new reservation
	
	/**
	 * Constructor for venue
	 * @param venueHireSystem The venue hiring system the venue belongs to
	 * @param venueName Name of the venue
	 */
	public Venue(VenueHireSystem venueHireSystem, String venueName) {
		this.venueName = venueName;
		this.venueHireSystem = venueHireSystem;
		allRooms = new ArrayList<Room>();
		reservations = new ArrayList<Reservation>();
		newRoomsBooked = new ArrayList<Room>();
		this.venueHireSystem.addVenue(this);
	}
	
	/**
	 * Adds a room to list of all rooms
	 * @param room Room to add
	 */
	public void addRoom(Room room) {
		allRooms.add(room);
	}
	
	/**
	 * Adds a reservation to list of all reservation
	 * @param reservation reservation to add
	 */
	public void addReservations(Reservation reservation) {
		reservations.add(reservation);
	}
	
	/**
	 * Adds a room to list of newly booked rooms
	 * @param room Room to add
	 */
	public void addBookedRoom(Room room) {
		newRoomsBooked.add(room);
	}
	
	/**
	 * Get name of venue
	 * @return Name of venue
	 */
	public String getName() {
		return venueName;
	}
	
	/**
	 * Debugging: Prints names of all rooms under venue to stdoutput
	 */
	public void getRooms() {
		System.out.println("----Venue: "+venueName+"----");
		for (Room room: allRooms) {
			System.out.println("Room: "+room.getName()+" Size:"+room.getSize());
		}
	}
	
	/**
	 * Removes the reservation from the list of reservations under venue
	 * @param reservation Reservation to remove
	 */
	public void removeReservation(Reservation reservation) {
		if(reservations.contains(reservation)) {
			reservations.remove(reservation);
		}
	}
	
	/**
	 * Returns a JSON object that lists of rooms that can fulfill the given reservation
	 * @param reservation Reservation to fulfill
	 * @return JSON object that lists of rooms that can fulfill the given reservation according to specs
	 */
	public JSONObject canFulfill(Reservation reservation) {
		LocalDate start = reservation.getStartDate();
		LocalDate end = reservation.getEndDate();
		int small = reservation.getSmall();
		int medium = reservation.getMedium();
		int large = reservation.getLarge();
		JSONObject toReturn = new JSONObject();
		JSONArray rooms = new JSONArray();
		
		//Iterate through all rooms and sees which ones can fulfill the reservation
		for (Room room: allRooms) {
			if (room.isAvailable(start, end)) {
				if(room.getSize().equals("small") && small > 0) {
					rooms.put(room.getName());
					addBookedRoom(room);
					small -= 1;
				}else if (room.getSize().equals("medium") && medium > 0) {
					rooms.put(room.getName());
					medium -= 1;
					addBookedRoom(room);
				}else if (room.getSize().equals("large") && large > 0) {
					rooms.put(room.getName());
					large -= 1;
					addBookedRoom(room);
				}
				
			}

		}
		//If all bookings can be fulfilled, then add the bookings to the given rooms
		if(small == 0 && medium == 0 && large == 0 ) {
			toReturn.put("status", "success");
			for (Room room: newRoomsBooked) {
				room.addReservation(reservation);
				reservation.addRoom(room);
			}
			addReservations(reservation);
			reservation.setVenue(this);
			//Clear the array for the next request
			newRoomsBooked.clear();
		}else {
			toReturn.put("status", "rejected");
		}
		
		toReturn.put("rooms", rooms);
        return toReturn;
	}

	/**
	 * Debugging: Lists reservations under the venue
	 */
	public void listReservations() {
		if (!reservations.isEmpty()) {
			for (Reservation reservation: reservations) {
				System.out.println("---Reservation: "+reservation.getID()+"----");
				System.out.println("Starts at: "+reservation.getStartDate()+" Ends: "+reservation.getEndDate());
				System.out.println("Small: "+reservation.getSmall()+" Medium: "+reservation.getMedium()+" Large: "+reservation.getLarge());
			}
		}else {
			System.out.println("List is empty");
		}
	}

	/**
	 * Returns list of rooms under venue for the list command
	 * @return Returns list of rooms under venue for the list command
	 */
	public JSONArray addListRooms() {
		JSONArray toReturn = new JSONArray();
		for (Room room: allRooms) {
			JSONObject roomObject = new JSONObject();
			roomObject = room.listReservations();
			toReturn.put(roomObject);
		}
		return toReturn;
	}
}
