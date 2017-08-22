package com.ybveg.dubbox.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import com.ybveg.dubbox.serialize.SerializationOptimizerImpl;
import com.ybveg.dubbox.serialize.SerializeScanner;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.bind.PropertiesConfigurationFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;

public class DubboxContextInitializer implements
    ApplicationContextInitializer<ConfigurableApplicationContext> {

  public DubboxProperties getProperties(Environment environment) {
    ConfigurableEnvironment env = (ConfigurableEnvironment) environment;
    PropertiesConfigurationFactory<DubboxProperties> factory = new PropertiesConfigurationFactory<DubboxProperties>(
        DubboxProperties.class);
    factory.setPropertySources(env.getPropertySources());
    factory.setConversionService(env.getConversionService());
    factory.setIgnoreInvalidFields(false);
    factory.setIgnoreUnknownFields(true);
    factory.setIgnoreNestedProperties(false);
    factory.setTargetName(DubboxProperties.PREFIX);
    try {
      factory.bindPropertiesToTarget();
      return factory.getObject();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void initialize(ConfigurableApplicationContext ctx) {
    DubboxProperties properties = getProperties(ctx.getEnvironment());

    String scan = properties.getScan();
    if (scan != null) {
      AnnotationBean scanner = BeanUtils.instantiate(AnnotationBean.class);
      scanner.setPackage(scan);
      scanner.setApplicationContext(ctx);
      ctx.addBeanFactoryPostProcessor(scanner);
      ctx.getBeanFactory().addBeanPostProcessor(scanner);
      ctx.getBeanFactory().registerSingleton("annotationBean", scanner);
    }

    registerProtocols(properties, ctx.getBeanFactory());
  }

  /**
   * 多协议支持 分别注入
   */
  private void registerProtocols(DubboxProperties properties,
      ConfigurableListableBeanFactory factory) {

    boolean needOptimizer = false;
    if (properties.getProtocols() != null) {
      for (ProtocolConfig protocol : properties.getProtocols()) {

        if ("dubbo".equalsIgnoreCase(protocol.getName()) && SerializationOptimizerImpl.class
            .getName()
            .equals(protocol.getOptimizer())) { // 如果dubbo 协议并且序列化是 SerializationOptimizerImpl
          needOptimizer = true;
        }

        String name = "DubboxProtocol-" + protocol.getId();
        factory.registerSingleton(name, protocol);
      }
    }

    if (properties.getSerialize() != null && needOptimizer) { // 注入序列化扫描器
//      SerializationOptimizerImpl.setBeanFactory(factory);
      SerializeScanner scanner = new SerializeScanner(properties.getSerialize());
      factory.registerSingleton(SerializeScanner.class.getName(), scanner);
      SerializationOptimizerImpl.setScanner(scanner);
    }
  }
}