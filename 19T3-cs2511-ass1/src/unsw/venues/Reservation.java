package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A reservation
 * @author SamAcharya z5161678
 *
 */
public class Reservation {
	private String id;
	private ArrayList<Room> roomsFulfilled;	//List of rooms that the reservaion is under
	private int smallRooms;
	private int mediumRooms;
	private int largeRooms;
	private LocalDate startDate;
	private LocalDate endDate;
	private Venue venue;
	private VenueHireSystem venueHireSystem;	
	
	/**
	 * Constructor for reservation
	 * @param venueHireSystem Venue Hiring System that the reservation belongs to
	 * @param id Name of the reservation
	 * @param startDate Starting date of reservation
	 * @param endDate Ending date of reservation
	 * @param small Small no. of rooms
	 * @param medium Medium no. of rooms
	 * @param large Large no. of rooms
	 */
	public Reservation(VenueHireSystem venueHireSystem, String id, LocalDate startDate, LocalDate endDate, int small, int medium, int large) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.smallRooms = small;
		this.mediumRooms = medium;
		this.largeRooms = large;
		this.venueHireSystem = venueHireSystem;
		this.venueHireSystem.addReservation(this);
		roomsFulfilled = new ArrayList<Room>();
	}
	
	/**
	 * Gets ID of reservation
	 * @return Returns ID of reservation
	 */
	public String getID() {
		return id;
	}
	
	//Debugging
	/**
	 * For debugging. Prints all rooms that the reservation is booked under 
	 */
	public void printRooms() {
		for (Room room: roomsFulfilled) {
			System.out.println(room.getName());
		}
	}

	/**
	 * Gets the start date of reservation
	 * @return Start date of reservation
	 */
	public LocalDate getStartDate() {	
		return startDate;
	}

	/**
	 * Gets the end date of reservation
	 * @return end date of reservation
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Gets no. of small rooms
	 * @return Number of small rooms under reservation
	 */
	public int getSmall() {
		return smallRooms;
	}

	/**
	 * Gets no. of medium rooms
	 * @return Number of medium rooms under reservation
	 */
	public int getMedium() {
		return mediumRooms;
	}

	/**
	 * Gets no. of large rooms
	 * @return Number of large rooms under reservation
	 */
	public int getLarge() {
		return largeRooms;
	}
	
	/**
	 * Adds rooms to the list of rooms the reservation fulfills
	 */
	public void addRoom(Room room) {
		roomsFulfilled.add(room);
	}
	
	/**
	 * Removes a room from the list of rooms the reservation fulfills
	 * @param room Room to remove
	 */
	public void removeRoom(Room room) {
		if(roomsFulfilled.contains(room)) {
			roomsFulfilled.remove(room);
		}
	}
	
	/**
	 * Sets venue for reservation
	 * @param venue Venue to set
	 */
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	public void removeVenue(Venue argVenue) {
		if (this.venue != null) {
			this.venue = null;
		}
	}

	/**
	 * Gets venue for reservation
	 * @return Venue to get
	 */
	public Venue getVenue() {
		return venue;
	}

	/**
	 * Remove all rooms from the reservation (used when cancelling)
	 * and vice versa
	 */
	public void removeRooms() {
		for (Room room: roomsFulfilled) {
			room.removeReservation(this);
		}
		roomsFulfilled.clear();
	}

	/**
	 * Set date for reservation
	 * @param start Starting date of reservation
	 * @param end Ending date of reservation
	 */
	public void setDate(LocalDate start, LocalDate end) {
		this.startDate = start;
		this.endDate = end;
	}

	/**
	 * Set the room sizes requested
	 * @param small Np. of small rooms
	 * @param medium Np. of small rooms
	 * @param large Np. of small rooms
	 */
	public void setSizes(int small, int medium, int large) {
		this.smallRooms = small;
		this.mediumRooms = medium;
		this.largeRooms = large;
	}

	
	
}
