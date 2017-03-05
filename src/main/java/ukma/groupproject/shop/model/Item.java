package ukma.groupproject.shop.model;

public class Item {

    private Long id;

    private String name;

    private Long price;

    private Long amount;

    private Long minAmount;

    private Department department;

	public Item() {}

    public Item(String name, Long price, Long amount, Long minAmount, Department department) {
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

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(Long minAmount) {
		this.minAmount = minAmount;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
