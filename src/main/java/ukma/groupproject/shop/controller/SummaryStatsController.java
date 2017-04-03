package ukma.groupproject.shop.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import ukma.groupproject.shop.model.Item;
import ukma.groupproject.shop.model.Purchase;
import ukma.groupproject.shop.model.PurchaseItem;
import ukma.groupproject.shop.model.Supply;
import ukma.groupproject.shop.model.SupplyItem;
import ukma.groupproject.shop.service.PurchaseService;
import ukma.groupproject.shop.service.SupplyService;

@Component
@Scope("prototype")
public class SummaryStatsController extends Controller {

	@FXML private BarChart<String, Integer> chart;
	
    @Autowired private PurchaseService purchaseService;
    @Autowired private SupplyService supplyService;
    
    private ObservableList<Purchase> purchases;
    private ObservableList<Supply> supplies;

    class ItemSummary
    {
    	public Integer supplied;
    	public Integer purchased;
    	public ItemSummary(int supplied, int purchased)
    	{
    		this.supplied = supplied;
    		this.purchased = purchased;
    	}
    }
    
    private Map<String, ItemSummary> items;
    
    @Override
    public void initialize() 
    {
    	purchases = FXCollections.observableList(purchaseService.getAll());
    	for (int i = 0; i < purchases.size(); i ++)
    		if (purchases.get(i).getDate().compareTo(new Date()) >= 0)
    			purchases.remove(i --);

    	supplies = FXCollections.observableList(supplyService.getAll());
    	for (int i = 0; i < supplies.size(); i ++)
    		if (supplies.get(i).getDate().compareTo(new Date()) >= 0)
    			supplies.remove(i --);
    	
    	items = new HashMap<String, ItemSummary>();
    	
    	/*
    	for (Purchase p : purchases)
        	for (PurchaseItem i : p.getItems())
        	{
        		String name = i.getItem().getName();
        		if (items.containsKey(name))
        			items.get(name).purchased += i.getAmount();
        		else
        			items.put(name, new ItemSummary(0, i.getAmount()));
        	}
    	
    	for (Supply s : supplies)
        	for (SupplyItem i : s.getItems())
        	{
        		String name = i.getItem().getName();
        		if (items.containsKey(name))
        			items.get(name).purchased += i.getAmount();
        		else
        			items.put(name, new ItemSummary(0, i.getAmount()));
        	}
		*/
    	
    	items.put("lol", new ItemSummary(5, 10));
    	items.put("lolss", new ItemSummary(9, 2));
    	
        XYChart.Series purchased_series = new XYChart.Series();
        purchased_series.setName("purchased");       

        XYChart.Series supplied_series = new XYChart.Series();
        supplied_series.setName("supplied");       
    	
    	for (String name : items.keySet())
    	{
    		purchased_series.getData().add(new XYChart.Data(name, items.get(name).purchased));
    		supplied_series.getData().add(new XYChart.Data(name, items.get(name).supplied));
        }
    	
    	chart.getData().add(purchased_series);
    	chart.getData().add(supplied_series);
    }
}
