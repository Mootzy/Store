package main;

import java.util.Scanner;

import javax.swing.JOptionPane;

public class Item
{
	private String name;
	private String color;

	private double cost;
	private int qty;
	private int sales;

	Item()
	{

	}

	Item(String name, String color, double cost, int qty, int sales)
	{
		this.name = name;
		this.color = color;
		this.cost = cost;
		this.qty = qty;
		this.sales = sales;
	}

	@Override
	public String toString( )
	{
		return name + "" + color + " " + cost + " " + qty + " " + sales;
	}

	/**
	 * @return the name
	 */
	public String getName( )
	{
		return name;
	}

	/**
	 * @param name
	 *           the name to set
	 */
	public void setName( String name )
	{
		this.name = name;
	}

	/**
	 * @return the color
	 */
	public String getColor( )
	{
		return color;
	}

	/**
	 * @param color
	 *           the color to set
	 */
	public void setColor( String color )
	{
		this.color = color;
	}

	/**
	 * @return the cost
	 */
	public double getCost( )
	{
		return cost;
	}

	/**
	 * @param cost
	 *           the cost to set
	 */
	public void setCost( double cost )
	{
		this.cost = cost;
	}

	/**
	 * @return the qty
	 */
	public int getQty( )
	{
		return qty;
	}

	/**
	 * @param qty
	 *           the qty to set
	 */
	public void setQty( int qty )
	{
		this.qty = qty;
	}

	/**
	 * @return the sales
	 */
	public int getSales( )
	{
		return sales;
	}

	/**
	 * @param sales
	 *           the sales to set
	 */
	public void setSales( int sales )
	{
		this.sales = sales;
	}

	public void setCost( String showInputDialog )
	{
		// TODO Auto-generated method stub

	}

	public void setQty( String showInputDialog )
	{
		// TODO Auto-generated method stub

	}

	public void setSales( String showInputDialog )
	{
		// TODO Auto-generated method stub

	}
}
