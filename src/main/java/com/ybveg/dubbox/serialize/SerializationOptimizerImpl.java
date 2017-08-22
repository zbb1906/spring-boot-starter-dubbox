package com.ybveg.dubbox.serialize;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class must be accessible from both the provider and consumer
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {

  private static final Logger logger = LoggerFactory.getLogger(SerializationOptimizerImpl.class);

  private static SerializeScanner scanner;

  public static void setScanner(SerializeScanner scanner) {
    SerializationOptimizerImpl.scanner = scanner;
  }

  // 因为dubbox 序列化失效了不是Spring 初始化 所以无法注入对象
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Collection getSerializableClasses() {
    //DubboxContextInitializer 内生成 SerializeScanner对象

    List<Class> classes = new LinkedList<>();
    if (scanner != null) {
      classes.addAll(scanner.getSerializableClasses());
    }

    logger.info("Spring Boot Starter Dubbox Serializable Class Size:" + classes.size());
//    classes.add(UserDto.class);
    return classes;
  }


}
