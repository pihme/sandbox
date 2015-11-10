package git.sandbox.java.codemodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JType;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

public class CodeModelTest {

	public static void main(String[] args) throws JClassAlreadyExistsException, IOException {		
		JCodeModel codeModel = new JCodeModel();
		JDefinedClass foo = codeModel._class( "a.b.c.Foo" ); //Creates a new class
		
		JMethod method = foo.method( JMod.PUBLIC, Void.TYPE, "doFoo" ); //Adds a method to the class
		method.body()._return( JExpr.lit( 42 ) );

		generateProperty(foo, "test", String.class, true, true); 
		
		generateProperty(foo, "codeModel", CodeModelTest.class, true, true);
		
		generateProperty(foo, "codeModel2", codeModel.INT , true, true);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		codeModel.build( new SingleStreamCodeWriter( out ) );

		System.out.println(out);
	}
	
	private static void generateProperty(JDefinedClass type, String propertyName, JType propertyType, boolean generateGetter, boolean generateSetter) {
		type.field(JMod.PRIVATE, propertyType, propertyName);
		
		String upperCamelCase = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		
		JMethod getter = type.method(JMod.PUBLIC,  propertyType, "get" + upperCamelCase);
		getter.body()._return(JExpr.ref(propertyName));
		
		JMethod setter = type.method(JMod.PUBLIC, Void.TYPE, "set" + upperCamelCase);
		setter.param(propertyType, propertyName);
		setter.body().assign(JExpr.refthis(propertyName), JExpr.ref(propertyName));		
		
	}
	
	private static void generateProperty(JDefinedClass type, String propertyName, Class propertyType, boolean generateGetter, boolean generateSetter) {
		type.field(JMod.PRIVATE, propertyType, propertyName);
		
		String upperCamelCase = propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
		
		JMethod getter = type.method(JMod.PUBLIC,  propertyType, "get" + upperCamelCase);
		getter.body()._return(JExpr.ref(propertyName));
		
		JMethod setter = type.method(JMod.PUBLIC, Void.TYPE, "set" + upperCamelCase);
		setter.param(propertyType, propertyName);
		setter.body().assign(JExpr.refthis(propertyName), JExpr.ref(propertyName));		
		
	}

}
