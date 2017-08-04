package com.ybveg.dubbox.config;

import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.spring.AnnotationBean;
import java.util.List;
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
    registerProtocols(properties.getProtocols(), ctx.getBeanFactory());
  }

  /**
   * 多协议支持 分别注入
   */
  private void registerProtocols(List<ProtocolConfig> protocols,
      ConfigurableListableBeanFactory factory) {
    if (protocols != null) {
      for (ProtocolConfig protocol : protocols) {
        String name = "DubboxProtocol-" + protocol.getId();
        factory.registerSingleton(name, protocol);
      }
    }
  }
}