package git.sandbox.java.dozer;

import org.dozer.BeanFactory;

public class SingletonClassBeanFactory implements BeanFactory {

	@Override
	public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
		return source;
	}

}
