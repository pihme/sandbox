package git.sandbox.java.dozer;

import java.util.List;

public class SampleClass {
	
	private String stringField;
	
	private List<SampleItem> items;	
	
	private SampleItem sampleItem;
	
	private SingletonClass singleton;

	public String getStringField() {
		return stringField;
	}

	public void setStringField(String stringField) {
		this.stringField = stringField;
	}

	public List<SampleItem> getItems() {
		return items;
	}

	public void setItems(List<SampleItem> items) {
		this.items = items;
	}

	public SampleItem getSampleItem() {
		return sampleItem;
	}

	public void setSampleItem(SampleItem sampleItem) {
		this.sampleItem = sampleItem;
	}

	public SingletonClass getSingleton() {
		return singleton;
	}

	public void setSingleton(SingletonClass singleton) {
		this.singleton = singleton;
	}
	
	
	
}
