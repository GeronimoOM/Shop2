package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Supplier;
import ukma.groupproject.shop.model.view.SupplierView;

@Component
public class SupplierViewMapper extends AbstractMapper<Supplier, SupplierView> {

    public SupplierViewMapper() {
        super(Supplier.class, SupplierView.class);
    }

    @Override
    public void mapTo(Supplier supplier, SupplierView supplierView) {
        supplierView.setId(supplier.getId());
        supplierView.setName(supplier.getName());
    }

    @Override
    public void mapFrom(SupplierView supplierView, Supplier supplier) {
        supplier.setId(supplierView.getId());
        supplier.setName(supplierView.getName());
    }
}
