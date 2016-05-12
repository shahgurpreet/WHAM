package wham.model;

import java.io.Serializable;

import javax.persistence.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.Principal;
import java.util.List;


/**
 * The persistent class for the user database table.
 */
@Entity
@Table(name="user")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Principal, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int uId;

	private String address;

	private String city;

	private String emailId;

	private String fName;

	private String lName;

	private String password;

	private String phone;

	private String status;

	private String zipCode;

	//bi-directional many-to-one association to Booking
	@OneToMany(mappedBy="user")
	private List<Booking> bookings;

	//bi-directional many-to-one association to Userpreference
	@OneToMany(mappedBy="user")
	private List<Userpreference> userpreferences;

	public User() {
	}

	public int getUId() {
		return this.uId;
	}

	public void setUId(int uId) {
		this.uId = uId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getEmailId() {
		return this.emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getFName() {
		return this.fName;
	}

	public void setFName(String fName) {
		this.fName = fName;
	}

	public String getLName() {
		return this.lName;
	}

	public void setLName(String lName) {
		this.lName = lName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public List<Booking> getBookings() {
		return this.bookings;
	}

	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
	}

	public Booking addBooking(Booking booking) {
		getBookings().add(booking);
		booking.setUser(this);

		return booking;
	}

	public Booking removeBooking(Booking booking) {
		getBookings().remove(booking);
		booking.setUser(null);

		return booking;
	}

	public List<Userpreference> getUserpreferences() {
		return this.userpreferences;
	}

	public void setUserpreferences(List<Userpreference> userpreferences) {
		this.userpreferences = userpreferences;
	}

	public Userpreference addUserpreference(Userpreference userpreference) {
		getUserpreferences().add(userpreference);
		userpreference.setUser(this);

		return userpreference;
	}

	public Userpreference removeUserpreference(Userpreference userpreference) {
		getUserpreferences().remove(userpreference);
		userpreference.setUser(null);

		return userpreference;
	}

	@Override
	public String getName() {
		return fName + " " + lName;
	}
	
    public JSONObject serialize() {
        JSONObject e = new JSONObject();
        e.put("uId", uId);
        e.put("fName", fName);
        e.put("lName", lName);
        e.put("address", address);
        e.put("city", city);
        e.put("emailId", emailId);
        e.put("zipCode", zipCode);
        e.put("phone", phone);
        e.put("status", status);
        
        JSONArray bookingArray = new JSONArray();
        for(Booking b : bookings){
        	bookingArray.put(b.serialize());
        }
        e.put("bookings", bookingArray);
        
        JSONArray prefArray = new JSONArray();
        for(Userpreference up : userpreferences){
        	prefArray.put(up.serialize());
        }
        e.put("userpreferences", prefArray);
        return e;
    }

}