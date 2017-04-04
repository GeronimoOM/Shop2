package ukma.groupproject.shop.service.impl;

import org.springframework.stereotype.Component;
import ukma.groupproject.shop.model.Department;
import ukma.groupproject.shop.model.view.DepartmentView;

@Component
public class DepartmentViewMapper extends AbstractMapper<Department, DepartmentView> {

    public DepartmentViewMapper() {
        super(Department.class, DepartmentView.class);
    }

    @Override
    public void mapTo(Department department, DepartmentView view) {
        view.setId(department.getId());
        view.setName(department.getName());
    }

    @Override
    public void mapFrom(DepartmentView view, Department department) {
        department.setId(view.getId());
        department.setName(view.getName());
    }
}
