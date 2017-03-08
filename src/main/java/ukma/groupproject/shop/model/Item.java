package ukma.groupproject.shop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sh_items")
public class Item implements Serializable {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float price;

    private int amount;

	@Column(name = "min_amount")
    private int minAmount;

	@ManyToOne
    private Department department;

	public Item() {}

	public Item(String name, float price, int amount, int minAmount, Department department) {
		this.name = name;
		this.price = price;
		this.amount = amount;
		this.minAmount = minAmount;
		this.department = department;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Item item = (Item) o;

		return id != null ? id.equals(item.id) : item.id == null;

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}
