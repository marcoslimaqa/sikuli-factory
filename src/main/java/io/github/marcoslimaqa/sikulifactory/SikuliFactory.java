package io.github.marcoslimaqa.sikulifactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import org.sikuli.script.Screen;

public class SikuliFactory { 

	public static void initElements(Screen sikuli, Object page) {
		Class<?> proxyIn = page.getClass();
		while (proxyIn != Object.class) {
			proxyFields(sikuli, page, proxyIn);
			proxyIn = proxyIn.getSuperclass();
		}
	}

	private static void proxyFields(Screen sikuli, Object page, Class<?> proxyIn) {
		Field[] fields = proxyIn.getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			if (field.getType().getTypeName().contains("SikuliElement")) {
				SikuliElement sikuliElement = createSikuliElement(sikuli, field);
				try {
					field.set(page, sikuliElement);
				} catch (IllegalArgumentException e) {
					throw new RuntimeException(e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}

		}
	}
	
	private static SikuliElement createSikuliElement(Screen sikuli, Field field) {
		Annotation[] annotations = field.getDeclaredAnnotations();
		String image = null;
		String[] images = { "" };
		float similarity = 70;
		int x = 0;
		int y = 0;
		for (Annotation annotation : annotations) {
			if (annotation instanceof FindBy) {
				FindBy myAnnotation = (FindBy) annotation;
				image = myAnnotation.image();
				images = myAnnotation.images();
				similarity = myAnnotation.similarity();
				x = myAnnotation.x();
				y = myAnnotation.y();
			} else if (annotation instanceof FindByImage) {
				FindByImage myAnnotation = (FindByImage) annotation;
				image = myAnnotation.value();
				similarity = myAnnotation.similarity();
				x = myAnnotation.x();
				y = myAnnotation.y();
			} else if (annotation instanceof FindByImages) {
				FindByImages myAnnotation = (FindByImages) annotation;
				images = myAnnotation.value();
				similarity = myAnnotation.similarity();
				x = myAnnotation.x();
				y = myAnnotation.y();
			}
		}
		return new SikuliElement(sikuli, image, images, similarity, x, y);
	}

}
