package ukma.groupproject.shop.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Button;
import ukma.groupproject.shop.model.Employee;
import ukma.groupproject.shop.model.Order;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.PurchaseItem;
import ukma.groupproject.shop.model.Supply;
import ukma.groupproject.shop.model.SupplyItem;
import ukma.groupproject.shop.service.EmployeeService;
import ukma.groupproject.shop.service.OrderService;
import ukma.groupproject.shop.service.PurchaseService;
import ukma.groupproject.shop.service.SupplyService;

@Component
@Scope("prototype")
public class EmployeeStatsController extends Controller {

	@FXML private BarChart<String, Integer> chart;

	@FXML private Button reloadButton;
	
    @Autowired private PurchaseService purchaseService;
    @Autowired private OrderService orderService;
    @Autowired private EmployeeService employeeService;
    
    private ObservableList<Purchase> purchases;
    private ObservableList<Order> orders;

    class EmployeeSummary
    {
    	public Integer reputation;
    	public EmployeeSummary(int rep)
    	{
    		this.reputation = rep;
    	}
    }
    
    private Map<String, EmployeeSummary> employees;
    
    @Override
    public void initialize() 
    {
    	reloadButton.setOnAction(e -> loadData());
    	loadData();
    }
    
    private void loadData()
    {
    	chart.getData().clear();
    	    	
    	purchases = FXCollections.observableList(purchaseService.getAll());
    	orders = FXCollections.observableList(orderService.getAll());
    	
    	employees = new TreeMap<String, EmployeeSummary>();
    	
    	for (Purchase p : purchases)
    	{
    		String name = p.getEmployee().getName();
    		if (employees.containsKey(name)) employees.get(name).reputation ++;
    		else employees.put(name, new EmployeeSummary(1));
    	}

    	for (Order o : orders)
    	{
    		String name = o.getEmployee().getName();
    		if (employees.containsKey(name)) employees.get(name).reputation ++;
    		else employees.put(name, new EmployeeSummary(1));
    	}
    	
        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
    	
    	for (String name : employees.keySet())
    		series.getData().add(new XYChart.Data<String, Integer>(name, employees.get(name).reputation));
    	
    	chart.getData().add(series);
    }
}
