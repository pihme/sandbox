package git.sandbox.java.dozer;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingOptions;

public class DozerTest {

	public static void main(String[] args) {
		BeanMappingBuilder builder = new BeanMappingBuilder() {
		      protected void configure() {
		        mapping(SingletonClass.class, SingletonClass.class,
		                TypeMappingOptions.beanFactory(SingletonClassBeanFactory.class.getName())
		        );
		      }
		};
		
		BeanMappingBuilder builder2 = new BeanMappingBuilder() {
		      protected void configure() {
		        mapping(SpecialSingletonClass.class, SingletonClass.class,
		                TypeMappingOptions.beanFactory(SingletonClassBeanFactory.class.getName())
		        );
		      }
		};
		
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

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.addMapping(builder);
		mapper.addMapping(builder2);
		
		SampleClass destObject = mapper.map(sourceObject, SampleClass.class);
		
		System.out.println(destObject);
	}
	
	
}
