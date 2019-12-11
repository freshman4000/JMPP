package com.freshman4000;

import com.freshman4000.config.SpringConfig;
import com.freshman4000.model.Cat;
import com.freshman4000.model.HelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        HelloWorld hw1 = context.getBean("HelloWorld", HelloWorld.class);
        HelloWorld hw2 = context.getBean("HelloWorld", HelloWorld.class);
        Cat cat1 = context.getBean("cat", Cat.class);
        Cat cat2 = context.getBean("cat", Cat.class);
        System.out.println(hw1 == hw2);
        System.out.println(cat1 == cat2);
    }
}
