package ukma.groupproject.shop.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
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

	@FXML private Button reloadButton;
	
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
    	reloadButton.setOnAction(e -> loadData());
    	loadData();
    }
    
    private void loadData()
    {
    	chart.getData().clear();
    	
    	purchases = FXCollections.observableList(purchaseService.getAllWithItems());
    	for (int i = 0; i < purchases.size(); i ++)
    		// very half assed way to see if some Date() is today
    		if (Math.abs(purchases.get(i).getDate().getTime() - (new Date()).getTime()) > TimeUnit.DAYS.toMillis(1))
    			purchases.remove(i --);

    	supplies = FXCollections.observableList(supplyService.getAll());
    	for (int i = 0; i < supplies.size(); i ++)
        	if (Math.abs(supplies.get(i).getDate().getTime() - (new Date()).getTime()) > TimeUnit.DAYS.toMillis(1))
    			supplies.remove(i --);
    	
    	items = new HashMap<String, ItemSummary>();
    	
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
    	
    	
        XYChart.Series<String, Integer> purchased_series = new XYChart.Series<String, Integer>();
        purchased_series.setName("purchased");       

        XYChart.Series<String, Integer> supplied_series = new XYChart.Series<String, Integer>();
        supplied_series.setName("supplied");       
    	
    	for (String name : items.keySet())
    	{
    		purchased_series.getData().add(new XYChart.Data<String, Integer>(name, items.get(name).purchased));
    		supplied_series.getData().add(new XYChart.Data<String, Integer>(name, items.get(name).supplied));
        }
    	
    	chart.getData().add(purchased_series);
    	chart.getData().add(supplied_series);
    }
}
