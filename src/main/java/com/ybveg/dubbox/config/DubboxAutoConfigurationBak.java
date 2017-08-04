//package com.ybveg.dubbox.config;
//
//import com.alibaba.dubbo.config.ApplicationConfig;
//import com.alibaba.dubbo.config.MonitorConfig;
//import com.alibaba.dubbo.config.ProtocolConfig;
//import com.alibaba.dubbo.config.ReferenceConfig;
//import com.alibaba.dubbo.config.RegistryConfig;
//import com.alibaba.dubbo.config.ServiceConfig;
//import com.alibaba.dubbo.config.spring.AnnotationBean;
//import com.alibaba.dubbo.remoting.http.servlet.BootstrapListener;
//import java.util.List;
//import javax.servlet.ServletContextListener;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.bind.PropertiesConfigurationFactory;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
//import org.springframework.context.EnvironmentAware;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.env.ConfigurableEnvironment;
//import org.springframework.core.env.Environment;
//import org.springframework.util.StringUtils;
//
///**
// * @auther zbb
// * @create 2017/8/3
// */
//@Configuration
//@EnableConfigurationProperties(DubboProperties.class)
//public class DubboxAutoConfigurationBak extends AnnotationBean implements EnvironmentAware {
//
//  private final static Logger logger = LoggerFactory.getLogger(DubboxAutoConfiguration.class);
//
//  private DubboProperties properties;
//
//  @Override
//  public void setEnvironment(Environment environment) {
//    ConfigurableEnvironment env = (ConfigurableEnvironment) environment;
//    PropertiesConfigurationFactory<DubboProperties> factory = new PropertiesConfigurationFactory<DubboProperties>(
//        DubboProperties.class);
//    factory.setPropertySources(env.getPropertySources());
//    factory.setConversionService(env.getConversionService());
//    factory.setIgnoreInvalidFields(false);
//    factory.setIgnoreUnknownFields(true);
//    factory.setIgnoreNestedProperties(false);
//    factory.setTargetName(DubboProperties.PREFIX);
//    try {
//      factory.bindPropertiesToTarget();
//      properties = factory.getObject();
//    } catch (Exception e) {
//      logger.error("set DubboProperties err", e);
//      throw new RuntimeException(e);
//    }
//  }
//
//  @Override
//  public void postProcessBeanFactory(ConfigurableListableBeanFactory factory)
//      throws BeansException {
//    this.register(factory);
//    this.registerProtocols(properties.getProtocols(), factory);
//    this.registerReferences(properties.getReferences(), factory);
//    this.registerServices(properties.getServices(), factory);
//    this.registerApplication(properties.getApplication(), factory);
//    this.registerRegistry(properties.getRegistry(), factory);
//    this.registerMonitor(properties.getMonitor(), factory);
//  }
//
//  /**
//   * 扫描注解 注入服务
//   */
//  private void register(ConfigurableListableBeanFactory beanFactory) {
//    if (StringUtils.isEmpty(properties.getScan())) {
//      logger.warn("spring.dubbox.scan is not configured ！！！");
//    }
//    super.setId("DubboxAutoConfiguration");
//    super.setPackage(properties.getScan());
//    super.postProcessBeanFactory(beanFactory);
//  }
//
//  /**
//   * 多协议支持 分别注入
//   */
//  private void registerProtocols(List<ProtocolConfig> protocols,
//      ConfigurableListableBeanFactory factory) {
//    if (protocols != null) {
//      for (ProtocolConfig protocol : protocols) {
//        String name = "DubboxProtocol-" + protocol.getId();
//        factory.registerSingleton(name, protocol);
//      }
//    }
//  }
//
//  /**
//   * 注入配置文件的Reference
//   */
//  private void registerReferences(List<ReferenceConfig<?>> references,
//      ConfigurableListableBeanFactory factory) {
//    if (references != null) {
//      for (ReferenceConfig<?> reference : references) {
//        String name = "DubboxReference-" + reference.getId();
//        factory.registerSingleton(name, reference);
//      }
//    }
//  }
//
//  /**
//   * 注入配置文件的Service
//   */
//  private void registerServices(List<ServiceConfig<?>> services,
//      ConfigurableListableBeanFactory factory) {
//    if (services != null) {
//      for (ServiceConfig<?> service : services) {
//        String name = "DubboxService-" + service.getId();
//        factory.registerSingleton(name, service);
//      }
//    }
//  }
//
//  public void registerApplication(ApplicationConfig application,
//      ConfigurableListableBeanFactory factory) {
//    if (application == null) {
//      application = new ApplicationConfig();
//    }
//    factory.registerSingleton("DubboxApplication", application);
//  }
//
//  public void registerRegistry(RegistryConfig registry,
//      ConfigurableListableBeanFactory factory) {
//    if (registry == null) {
//      registry = new RegistryConfig();
//    }
//    factory.registerSingleton("DubboxRegistry", registry);
//  }
//
//  public void registerMonitor(MonitorConfig monitor,
//      ConfigurableListableBeanFactory factory) {
//    if (monitor == null) {
//      monitor = new MonitorConfig();
//    }
//    factory.registerSingleton("DubboxMonitor", monitor);
//  }
//
//
//  @Bean
//  @ConditionalOnClass(ServletContextListener.class)
//  public ServletListenerRegistrationBean<BootstrapListener> initBootstrapListener() {
//    BootstrapListener bootstrapListener = new BootstrapListener();
//    ServletListenerRegistrationBean<BootstrapListener> registrationBean = new ServletListenerRegistrationBean<BootstrapListener>(
//        bootstrapListener);
//    registrationBean.setName("DubboxBootstrapListener");
//    return registrationBean;
//  }
//}
