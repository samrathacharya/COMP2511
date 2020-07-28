package unsw.venues;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONObject;

/***
 * A Room object
 * @author Sam Acharya z5161678
 *
 */
public class Room {
	/**
	 * 
	 */
	private String name;
	private String size;	
	private ArrayList<Reservation> reservations;	//List of bookings
	private Venue venue;
	
	/**
	 * Constructor or room
	 * @param venue Venue room is assigned to
	 * @param size Size of room
	 * @param name Name of the room
	 */
	public Room(Venue venue, String size, String name) {
		this.size = size;
		this.venue = venue;
		this.name = name;
		this.venue.addRoom(this);
		reservations = new ArrayList<Reservation>();
	}
	
	/**
	 * //For debugging: Prints all booking
	 */
	public void printReservations() {
		System.out.println("For room "+this.getName());
		for (Reservation reservation: reservations) {
			System.out.println("From: "+reservation.getStartDate().toString()+"to "+reservation.getEndDate().toString());
		}
	}
	
	/**
	 * Adds reservation to list of reservations for the room
	 * @param reservation eservation to add to list of reservations for the room
	 */
	public void addReservation(Reservation reservation) {
		reservations.add(reservation);
	}
	
	/**
	 * 
	 * @return Returns name of room
	 */
	public String getName() {
		return name;
		
	}

	/**
	 * 
	 * @return Returns size of room
	 */
	public String getSize() {
		return size;
	}

	/**
	 * States whether a room can fulfill a reservation
	 * @param start Start date of booking
	 * @param end End date of booking
	 * @return Returns booleans stating whether a room can fulfill a reservation
	 */
	public boolean isAvailable(LocalDate start, LocalDate end) {
		for (Reservation reservation: reservations) {
			//If start date is between any booking date, or end date is between any booking date, then the booking
			//is not possible
			//start overlaps
			if(start.isAfter(reservation.getStartDate()) && start.isBefore(reservation.getEndDate())) {
				return false;
			}
			
			//end overlaps
			if(end.isAfter(reservation.getStartDate()) && end.isBefore(reservation.getEndDate())) {
				return false;
			}
			
			//overlaps on the same day
			if(start.isEqual(reservation.getStartDate()) || start.isEqual(reservation.getEndDate()) || end.isEqual(reservation.getStartDate()) 
					|| end.isEqual(reservation.getEndDate())) {
				return false;
			}
			
			//starts before
			if (start.isBefore(reservation.getStartDate()) && end.isAfter(reservation.getEndDate())) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Removes reservation from the room
	 * @param reservation Reservation to room
	 */
	public void removeReservation(Reservation reservation) {
		reservations.remove(reservation);
	}
	
	/**
	 * Comparator to order bookings on start date for the list command
	 */
	Comparator<Reservation> mapComparator = (Reservation m1, Reservation m2) -> m1.getStartDate().compareTo(m2.getStartDate());

	/**
	 * Lists reservations in date
	 * @return Returns a list of reservations according to the date
	 */
	public JSONObject listReservations() {
		JSONObject toReturn = new JSONObject();
		toReturn.put("room", this.name);
		JSONArray listOfReservations = new JSONArray();
		Collections.sort(reservations,mapComparator);
		for (Reservation reservations: reservations) {
			JSONObject reservationDetails = new JSONObject();
			reservationDetails.put("end", reservations.getEndDate().toString());
			reservationDetails.put("start", reservations.getStartDate().toString());
			reservationDetails.put("id", reservations.getID());
			listOfReservations.put(reservationDetails);
		}
		toReturn.put("reservations", listOfReservations);
		return toReturn;
	}

		

}
