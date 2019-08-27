package edu.sabanciuniv.it592.mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import edu.sabanciuniv.it592.domain.Orders;
import edu.sabanciuniv.it592.services.OrderService;
import edu.sabanciuniv.it592.services.ShopService;

@ManagedBean
public class MonthlyIncomeBean {
	
	@EJB
	private OrderService orderService;
	
	@EJB
	private ShopService shopService;
	
	@Inject private LoginBean loginBean;
	
	private List<Orders>sales=new ArrayList<>();
	private List<Orders>monthlySales=new ArrayList<>();
	private BarChartModel barModel;
	private BarChartModel barModel2;
	private int month;
	private double totalBillAmount;
	private double dailyTotal;
	private double yearlyTotal;
	
    @PostConstruct
    public void init() {
    	sales=orderService.getOrdersByShopname(loginBean.getShopname());
        createBarModels(); 
        for(Orders sale:sales) {
        	yearlyTotal+=sale.getTotalbill();
        }
    }
    
    public double getCalculatedValue() {
    	double d = dailyTotal ;
    	dailyTotal = 0;
        return d;
    }
    
    public void calculateValue(double value){
    	dailyTotal += value;
    }
    
    private void createBarModels() {
        createBarModel();   
        createBarModel2();
    }
    
	 private void createBarModel() {
        barModel = initBarModel();
 
        barModel.setTitle("Monthly Sales");
        barModel.setLegendPosition("ne");
 
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Months");
 
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Sale count");
        yAxis.setMin(0);
        yAxis.setMax(25);
    }
	
	private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
        
        int jan=0,feb=0,mar=0,apr=0,may=0,jun=0,jul=0,aug=0,sep=0,oct=0,nov=0,dec=0;
        for(Orders sale:sales) {
        	Date date=sale.getOrderdate();
        	int month=date.getMonth()+1;
        	switch (month) {
			case 1:
				jan++;
				break;
			case 2:
				feb++;
				break;
			case 3:
				mar++;
				break;
			case 4:
				apr++;
				break;
			case 5:
				may++;
				break;
			case 6:
				jun++;
				break;
			case 7:
				jul++;
				break;
			case 8:
				aug++;
				break;
			case 9:
				sep++;
				break;
			case 10:
				oct++;
				break;
			case 11:
				nov++;
				break;
			case 12:
				dec++;
				break;
          }
        }	
        ChartSeries sales = new ChartSeries();
        sales.setLabel("Sale count");
        sales.set("January", jan);
        sales.set("February", feb);
        sales.set("March", mar);
        sales.set("April", apr);
        sales.set("May", may);
        sales.set("June", jun);
        sales.set("July", jul);
        sales.set("August", aug);
        sales.set("September", sep);
        sales.set("October", oct);
        sales.set("November", nov);
        sales.set("December", dec);
 
        model.addSeries(sales);
 
        return model;
	}	
	
	public void loadData() {
		for(Orders order:sales) {
			int m=Integer.valueOf(order.getOrderdate().getMonth())+1;
			if(m==month) {
				monthlySales.add(order);
			}
		}
		
		for(Orders order:monthlySales) {
			totalBillAmount+=order.getTotalbill();
		}						
	}
	
	 private void createBarModel2() {
        barModel2 = initBarModel2();
 
        barModel2.setTitle("Monthly Income");
        barModel2.setLegendPosition("ne");
 
        Axis xAxis = barModel2.getAxis(AxisType.X);
        xAxis.setLabel("Months");
 
        Axis yAxis = barModel2.getAxis(AxisType.Y);
        yAxis.setLabel("Income");
        yAxis.setMin(0);
        yAxis.setMax(15000);
    }
	 
	 private BarChartModel initBarModel2() {
        BarChartModel model = new BarChartModel();
        
        double jan=0,feb=0,mar=0,apr=0,may=0,jun=0,jul=0,aug=0,sep=0,oct=0,nov=0,dec=0;
        for(Orders sale:sales) {
        	Date date=sale.getOrderdate();
        	int month=date.getMonth()+1;
        	switch (month) {
			case 1:
				jan+=sale.getTotalbill();
				break;
			case 2:
				feb+=sale.getTotalbill();
				break;
			case 3:
				mar+=sale.getTotalbill();
				break;
			case 4:
				apr+=sale.getTotalbill();
				break;
			case 5:
				may+=sale.getTotalbill();
				break;
			case 6:
				jun+=sale.getTotalbill();
				break;
			case 7:
				jul+=sale.getTotalbill();
				break;
			case 8:
				aug+=sale.getTotalbill();
				break;
			case 9:
				sep+=sale.getTotalbill();
				break;
			case 10:
				oct+=sale.getTotalbill();
				break;
			case 11:
				nov+=sale.getTotalbill();
				break;
			case 12:
				dec+=sale.getTotalbill();
				break;
          }
        }	
        ChartSeries sales = new ChartSeries();
        sales.setLabel("Monthly Income");
        sales.set("January", jan);
        sales.set("February", feb);
        sales.set("March", mar);
        sales.set("April", apr);
        sales.set("May", may);
        sales.set("June", jun);
        sales.set("July", jul);
        sales.set("August", aug);
        sales.set("September", sep);
        sales.set("October", oct);
        sales.set("November", nov);
        sales.set("December", dec);
 
        model.addSeries(sales);
 
        return model;
	}
	 
	public double dailyTotal() {
		double daily=0;
		for(Orders sale:monthlySales) {
			daily+=sale.getTotalbill();
		}
		return daily;
	}
  
	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public List<Orders> getMonthlySales() {
		return monthlySales;
	}

	public void setMonthlySales(List<Orders> monthlySales) {
		this.monthlySales = monthlySales;
	}

	public double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public BarChartModel getBarModel2() {
		return barModel2;
	}

	public void setBarModel2(BarChartModel barModel2) {
		this.barModel2 = barModel2;
	}

	public double getYearlyTotal() {
		return yearlyTotal;
	}

	public void setYearlyTotal(double yearlyTotal) {
		this.yearlyTotal = yearlyTotal;
	}
	
}
