package com.ybveg.dubbox.serialize;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class DubboxSpringFactory implements BeanFactoryAware {

  private static BeanFactory beanFactory;

  // private static ApplicationContext context;

  public void setBeanFactory(BeanFactory factory) throws BeansException {
    this.beanFactory = factory;
  }

  public static <T> T getBean(String name) {
    if (null != beanFactory) {
      return (T) beanFactory.getBean(name);
    }
    return null;
  }

  public static <T> T getBean(Class<T> clazz) {
    if (null != beanFactory) {
      return beanFactory.getBean(clazz);
    }
    return null;
  }
}