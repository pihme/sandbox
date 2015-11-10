package git.sandbox.java.codemodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

import com.sun.codemodel.JClassAlreadyExistsException;
import com.sun.codemodel.JCodeModel;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JExpr;
import com.sun.codemodel.JInvocation;
import com.sun.codemodel.JMethod;
import com.sun.codemodel.JMod;
import com.sun.codemodel.JPackage;
import com.sun.codemodel.writer.SingleStreamCodeWriter;

public class WrapperGenerator {

	public static void main(String[] args) throws JClassAlreadyExistsException, IOException {
		JCodeModel codeModel = new JCodeModel();

		Class template = AbstractBaseWrapper.class;

		String packageName = template.getPackage().getName();

		String className = template.getSimpleName().replace("Abstract", "") + "Impl";
		JPackage generatedPackage = codeModel._package(packageName);

		JDefinedClass generatedClass = generatedPackage._class(JMod.FINAL, className); // Creates
																						// a
																						// new
																						// class
		generatedClass._extends(template);

		for (Method method : template.getMethods()) {

			if (Modifier.isAbstract(method.getModifiers())) {
				System.out.println(
						"Found abstract method " + Modifier.toString(method.getModifiers()) + " " + method.getName());

				JMethod generatedMethod = generatedClass.method(JMod.PUBLIC, method.getReturnType(), method.getName());

				for (Parameter parameter : method.getParameters()) {
					generatedMethod.param(parameter.getModifiers(), parameter.getType(), parameter.getName());
				}

				if (method.getReturnType().equals(Void.TYPE)) {
					generatedMethod.body().add(generateInvocation(method));
				} else {

					generatedMethod.body()._return(generateInvocation(method));
				}
			}
		}

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		codeModel.build(new SingleStreamCodeWriter(out));

		System.out.println(out);

	}

	private static JInvocation generateInvocation(Method method) {
		JInvocation generatedInvocation = JExpr.invoke(JExpr.invoke("getWrapped"), method.getName());

		for (Parameter parameter : method.getParameters()) {
			generatedInvocation.arg(JExpr.ref(parameter.getName()));
		}

		return generatedInvocation;
	}

}
