package ca.ualberta.cs.cfudgecounter;


public interface CounterControllerInterface
{
	public void addCounter(int value, String name);
	public int	 incCounter(CounterModel counter);

}
