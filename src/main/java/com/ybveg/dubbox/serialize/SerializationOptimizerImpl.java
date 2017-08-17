package com.ybveg.dubbox.serialize;

import com.alibaba.dubbo.common.serialize.support.SerializationOptimizer;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * This class must be accessible from both the provider and consumer
 */
public class SerializationOptimizerImpl implements SerializationOptimizer {


  // 因为dubbox 序列化失效了不是Spring 初始化 所以无法注入对象
  @SuppressWarnings({"rawtypes", "unchecked"})
  public Collection getSerializableClasses() {
    //DubboxContextInitializer 内生成 SerializeScanner对象
    SerializeScanner scanner = DubboxSpringFactory.getBean(SerializeScanner.class.getName());

    List<Class> classes = new LinkedList<>();
    if (scanner != null) {
      classes.addAll(scanner.getSerializableClasses());
    }
//    classes.add(UserDto.class);
    return classes;
  }


}
