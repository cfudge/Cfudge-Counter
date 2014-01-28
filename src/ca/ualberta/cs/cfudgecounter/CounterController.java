package ca.ualberta.cs.cfudgecounter;

import java.util.ArrayList;

public class CounterController implements CounterControllerInterface
{
	private CounterListModel counterListModel;

	public void addCounter(int value, String name)
	{
		ArrayList<CounterModel> list = CounterListModel.getCounterList();
		list.add(new CounterModel(value, name));
	}

	public CounterController()
	{
		super();
		counterListModel = new CounterListModel();
	}

	
	public int incCounter(CounterModel Counter){
		
		return Counter.getValue();
	}

	
}
