package com.test.spring.framework;


import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ZhuApplication {

    private Class configClass;

    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ZhuApplication(Class configClass){
        this.configClass = configClass;

        // 1. 解析传入的配置类
        scan(configClass);
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){
                Object bean = createBean(beanName,beanDefinition);
                singletonObjects.put(beanName,bean);

            }
        }


    }
    private Object createBean(String beanName, BeanDefinition beanDefinition){
        Class clazz = beanDefinition.getClazz();
        Object instance = null;
        try {



            instance = clazz.getDeclaredConstructor().newInstance();
            // 依赖注入：
            for (Field declaredField : clazz.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Autowired.class)){
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }
            // aware回调
            if(instance instanceof  BeanNameAware){
                ((BeanNameAware) instance).setBeanName(beanName);
            }
            // 初始化前
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            // 初始化后
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }

            // 初始化
            if(instance instanceof InitializingBean){
                ((InitializingBean) instance).afterPropertiesSet();
            }


        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return instance;
    }

    private void scan(Class configClass) {
        ComponentScan declaredAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = declaredAnnotation.value();
        ClassLoader classLoader = ZhuApplication.class.getClassLoader();
        URL resources = classLoader.getResource("com/test/spring/zhu/service");
        File file = new File(resources.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                String fileName = path+"."+f.getName().substring(0,f.getName().indexOf(".class"));
                Class<?> aClass = null;
                try {
                    aClass = classLoader.loadClass(fileName);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                assert aClass != null;
                if (aClass.isAnnotationPresent(Component.class)) { // 若有Component注解。说明是bean
                    Component declaredAnnotation1 = aClass.getDeclaredAnnotation(Component.class);

                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) {
                        try {
                            BeanPostProcessor o = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                            beanPostProcessorList.add(o);

                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                    }

                    BeanDefinition beanDefinition = new BeanDefinition();
                    beanDefinition.setClazz(aClass);

                    String beanName = declaredAnnotation1.value();
                    if (aClass.isAnnotationPresent(Scope.class)) {
                        Scope scopeAnno = aClass.getAnnotation(Scope.class);
                        beanDefinition.setScope(scopeAnno.value());
                    }else {
                        beanDefinition.setScope("singleton");
                    }
                    beanDefinitionMap.put(beanName,beanDefinition);
                }
            }
        }
    }

    public Object getBean(String beanName){
        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if(beanDefinition.getScope().equals("singleton")){
                Object o = singletonObjects.get(beanName);
                return o;
            }else {
                Object bean = createBean(beanName, beanDefinition);
                return bean;
            }
        }else {
            throw new NullPointerException();// 不存在此bean
        }
    }
}
