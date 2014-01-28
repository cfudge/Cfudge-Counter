package ca.ualberta.cs.cfudgecounter;


public class CounterModel
{
	private int value;
	private String name;
	
	public CounterModel(int value, String name)
	{

		super();
		this.name = name;
		this.value = value;
	}
	
	public int getValue()
	{
	
		return value;
	}
	
	public void setValue(int value)
	{
	
		this.value = value;
	}
	
	public String getName()
	{
	
		return name;
	}
	
	public void setName(String name)
	{
	
		this.name = name;
	}

}
