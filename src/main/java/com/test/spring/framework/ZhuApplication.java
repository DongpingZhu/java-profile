package com.test.spring.framework;


import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 模拟实现IOC容器
public class ZhuApplication {
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();// 存储bean
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();//存储beanDefinition
    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public ZhuApplication(Class configClass){
        // 1. 解析传入的配置类：扫描bean，并将其beanDefinition保存到一个map中，还没有创建bean
        scan(configClass);
        // 2. 开始创建bean, 并将其放入map中
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if(beanDefinition.getScope().equals("singleton")){ // bean的创建时机：容器创建时只有单例bean才会被创建
                Object bean = createBean(beanName,beanDefinition); // bean的生命周期
                singletonObjects.put(beanName,bean);
            }
        }
    }

    private void scan(Class configClass) {
        ComponentScan declaredAnnotation = (ComponentScan) configClass.getDeclaredAnnotation(ComponentScan.class);
        String path = declaredAnnotation.value();
        ClassLoader classLoader = ZhuApplication.class.getClassLoader();
        URL resources = classLoader.getResource("com/test/spring/test/service");
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
                    if (BeanPostProcessor.class.isAssignableFrom(aClass)) { // 是否继承了BeanPostProcessor接口
                        try {
                            BeanPostProcessor o = (BeanPostProcessor) aClass.getDeclaredConstructor().newInstance();
                            beanPostProcessorList.add(o);
                        } catch (Exception e) {
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
                        beanDefinition.setScope("singleton"); // 默认单例bean
                    }
                    beanDefinitionMap.put(beanName,beanDefinition); // 将所有beanDefinition保存到map中
                }
            }
        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition){
        Class clazz = beanDefinition.getClazz();
        Object instance = null;
        try {
            // 1. 实例化：
            instance = clazz.getDeclaredConstructor().newInstance();//反射创建对象
            // 2.属性赋值：
            for (Field declaredField : clazz.getDeclaredFields()) {
                if(declaredField.isAnnotationPresent(Autowired.class)){//该bean依赖的bean
                    Object bean = getBean(declaredField.getName());
                    declaredField.setAccessible(true);
                    declaredField.set(instance, bean);
                }
            }
            // 3. 初始化：前面两步已经得到了对象，可以用了。后面的初始化是为了扩展
            // 3.1  Aware接口
            if(instance instanceof  BeanNameAware){
                ((BeanNameAware) instance).setBeanName(beanName);
            }
            // 3.2 BeanPostProcessor接口
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }
            // 3.3 InitializingBean接口
            if(instance instanceof InitializingBean){
                ((InitializingBean) instance).afterPropertiesSet();
            }
            // 3.4 自定义init-method方法
            // 3.5 BeanPostProcessor接口
            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance,beanName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
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
