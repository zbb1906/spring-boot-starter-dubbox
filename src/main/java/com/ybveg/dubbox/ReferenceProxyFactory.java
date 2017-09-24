package com.ybveg.dubbox;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;

/**
 * 获取dubbo 代理
 */
public class ReferenceProxyFactory {

  private ApplicationConfig application = null;
  private RegistryConfig registry = null;
  private ConsumerConfig consumer = null;

  public ReferenceProxyFactory() {
  }

  public <T> T getProxy(Class<T> clazz) {
    ReferenceConfig<T> rc = new ReferenceConfig<>();
    rc.setRegistry(registry);
    rc.setConsumer(consumer);
    rc.setApplication(application);
    rc.setInterface(clazz);
    return rc.get();
  }

  public <T> T getProxy(String className) throws ClassNotFoundException {
    if (application != null && registry != null) {
      Class<T> clazz = (Class<T>) Class.forName(className);
      return getProxy(clazz);
    }
    return null;
  }


  public void setApplication(ApplicationConfig application) {
    this.application = application;
  }

  public void setRegistry(RegistryConfig registry) {
    this.registry = registry;
  }

  public void setConsumer(ConsumerConfig consumer) {
    this.consumer = consumer;
  }
}