package com.ybveg.dubbox.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @auther zbb
 * @create 2017/8/3
 */
@ConfigurationProperties(prefix = "spring.dubbox")
public class DubboProperties {


  /**
   * 扫描包地址
   */
  private String scan;
  /**
   * 应用信息
   */
  private ApplicationConfig application;
  /**
   * 应用注册中心
   */
  private RegistryConfig registry;

  /**
   * 监控中心
   */
  private MonitorConfig monitor;

  /**
   * 应用支持协议
   */
  private List<ProtocolConfig> protocols;
  /**
   * 服务提供者
   */
  private List<ServiceConfig<?>> services;
  /**
   * 服务消费者
   */
  private List<ReferenceConfig<?>> references;

  public String getScan() {
    return scan;
  }

  public void setScan(String scan) {
    this.scan = scan;
  }

  public ApplicationConfig getApplication() {
    return application;
  }

  public void setApplication(ApplicationConfig application) {
    this.application = application;
  }

  public RegistryConfig getRegistry() {
    return registry;
  }

  public void setRegistry(RegistryConfig registry) {
    this.registry = registry;
  }

  public MonitorConfig getMonitor() {
    return monitor;
  }

  public void setMonitor(MonitorConfig monitor) {
    this.monitor = monitor;
  }

  public List<ProtocolConfig> getProtocols() {
    return protocols;
  }

  public void setProtocols(List<ProtocolConfig> protocols) {
    this.protocols = protocols;
  }

  public List<ServiceConfig<?>> getServices() {
    return services;
  }

  public void setServices(List<ServiceConfig<?>> services) {
    this.services = services;
  }

  public List<ReferenceConfig<?>> getReferences() {
    return references;
  }

  public void setReferences(List<ReferenceConfig<?>> references) {
    this.references = references;
  }
}
