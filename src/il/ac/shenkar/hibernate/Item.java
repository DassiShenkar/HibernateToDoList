package il.ac.shenkar.hibernate;

import javax.persistence.Id;

public class Item {
	@Id
	private int id;
	private String itemName;
	private String itemStatus;
	
	public Item(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}

	public Item(int id, String itemName, String itemStatus) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemStatus = itemStatus;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", itemName=" + itemName + ", itemStatus=" + itemStatus + "]";
	}
	
}
