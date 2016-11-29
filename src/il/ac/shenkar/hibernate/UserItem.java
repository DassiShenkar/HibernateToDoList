package il.ac.shenkar.hibernate;

import javax.persistence.Id;

public class UserItem {
	@Id
	private int id;
	private int itemID;
	private int userID;
	
	public UserItem(){}
	public UserItem(int id, int itemID, int userID) {
		super();
		this.id = id;
		this.itemID = itemID;
		this.userID = userID;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "UserItem [id=" + id + ", itemID=" + itemID + ", userID=" + userID + "]";
	}
	
	
}
