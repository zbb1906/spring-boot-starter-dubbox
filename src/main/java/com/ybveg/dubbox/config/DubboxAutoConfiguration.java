package com.ybveg.dubbox.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ConsumerConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(DubboxProperties.class)
public class DubboxAutoConfiguration {

  @Autowired
  private DubboxProperties dubboProperties;

  @Bean
  public ApplicationConfig requestApplicationConfig() {
    ApplicationConfig applicationConfig = dubboProperties.getApplication();
    if (applicationConfig == null) {
      applicationConfig = new ApplicationConfig();
    }
    return applicationConfig;
  }

  @Bean
  public RegistryConfig requestRegistryConfig() {
    RegistryConfig registryConfig = dubboProperties.getRegistry();
    if (registryConfig == null) {
      registryConfig = new RegistryConfig();
    }
    return registryConfig;
  }

  @Bean
  public MonitorConfig requestMonitorConfig() {
    MonitorConfig monitorConfig = dubboProperties.getMonitor();
    if (monitorConfig == null) {
      monitorConfig = new MonitorConfig();
    }
    return monitorConfig;
  }

  @Bean
  public ProviderConfig requestProviderConfig() {
    ProviderConfig providerConfig = dubboProperties.getProvider();
    if (providerConfig == null) {
      providerConfig = new ProviderConfig();
    }
    return providerConfig;
  }

  @Bean
  public ConsumerConfig requestConsumerConfig() {
    ConsumerConfig consumerConfig = dubboProperties.getConsumer();
    if (consumerConfig == null) {
      consumerConfig = new ConsumerConfig();
    }
    return consumerConfig;
  }
}