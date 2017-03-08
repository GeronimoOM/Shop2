package ukma.groupproject.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sh_suppliers")
public class Supplier implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "supplier")
    private List<Order> ordersTo = new ArrayList<>();

    @OneToMany(mappedBy = "supplier")
    private List<Supply> suppliesBy = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "sh_suppliers_items")
    private List<Item> items = new ArrayList<>();

    public Supplier() {}

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

    public List<Order> getOrdersTo() {
        return ordersTo;
    }

    public void setOrdersTo(List<Order> ordersTo) {
        this.ordersTo = ordersTo;
    }

    public List<Supply> getSuppliesBy() {
        return suppliesBy;
    }

    public void setSuppliesBy(List<Supply> suppliesBy) {
        this.suppliesBy = suppliesBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Supplier supplier = (Supplier) o;

        return id != null ? id.equals(supplier.id) : supplier.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
