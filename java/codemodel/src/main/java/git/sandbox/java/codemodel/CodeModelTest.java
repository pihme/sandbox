package git.sandbox.java.codemodel;

import java.util.ArrayList;
import java.util.List;

import com.rits.cloning.Cloner;

import git.sandbox.java.dozer.SampleClass;
import git.sandbox.java.dozer.SampleItem;
import git.sandbox.java.dozer.SingletonClass;
import git.sandbox.java.dozer.SpecialSampleItem;
import git.sandbox.java.dozer.SpecialSingletonClass;

public class CodeModelTest {

	public static void main(String[] args) {		
		SampleClass sourceObject = new SampleClass();
		sourceObject.setStringField("testValue");

		SampleItem itemA = new SampleItem();
		itemA.setName("A");

		SampleItem itemB = new SampleItem();
		itemB.setName("B");
		
		SpecialSampleItem itemC = new SpecialSampleItem();
		itemC.setName("C");
		itemC.setSpecialName("Special");

		List<SampleItem> items = new ArrayList<>();
		items.add(itemA);
		items.add(itemB);
		items.add(itemC);
		
		sourceObject.setItems(items);
		sourceObject.setSampleItem(itemC);
		
		SingletonClass singleton = new SpecialSingletonClass();
		singleton.setField("testField");
		
		sourceObject.setSingleton(singleton);

		Cloner cloner = new Cloner();

		cloner.dontCloneInstanceOf(SingletonClass.class);
		
		SampleClass destObject = cloner.deepClone(sourceObject);
		
		System.out.println(destObject);

	}

}
