package ukma.groupproject.shop.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sh_supplies")
public class Supply {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date date;

    @ManyToOne
    private Supplier supplier;

    @OneToMany(mappedBy = "supply")
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "key.supply", cascade = CascadeType.ALL)
    private List<SupplyItem> items = new ArrayList<>();

    public Supply() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<SupplyItem> getItems() {
        return items;
    }

    public void setItems(List<SupplyItem> items) {
        this.items = items;
    }
}
