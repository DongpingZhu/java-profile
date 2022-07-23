package com.test.annotation;

import java.lang.annotation.Annotation;

@Anno1(name="ddd",age=20)
@Anno2("nn")
public class Anno1Test {
    public static void main(String[] args) {
        Class<Anno1Test> anno1TestClass = Anno1Test.class;

        Annotation[] annotations = anno1TestClass.getAnnotations();
        for (Annotation annotation1 : annotations) {
            System.out.println(annotation1);
        }

        if (anno1TestClass.isAnnotationPresent(Anno1.class)) {
            Anno1 annotation = anno1TestClass.getAnnotation(Anno1.class);
            System.out.println(annotation.name());
            System.out.println(annotation.age());

        }
    }

}
