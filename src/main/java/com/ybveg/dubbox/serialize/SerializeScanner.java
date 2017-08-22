package com.ybveg.dubbox.serialize;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.ybveg.dubbox.config.SerializeConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

/**
 * @auther zbb
 * @create 2017/8/17
 */
public class SerializeScanner {

  private static final Logger logger = LoggerFactory.getLogger(SerializeScanner.class);

  private static final String POSTFIX = "/**/*.class";
  private static final String PREFIX = "classpath*:";

  private ResourcePatternResolver resourcePatternResolver;

  private List<String> scans;

  private Set<Class<?>> classes;

  public SerializeScanner(SerializeConfig serialize) {

    this.classes = new HashSet<>();
    if (serialize.getClasses() != null) {
      serialize.getClasses().forEach((className) -> {
        try {
          Class<?> clazz = Class.forName(className);
          this.classes.add(clazz);
          logger.debug("Dubbox SerializeScanner Class > " + clazz.getName());
        } catch (ClassNotFoundException e) {
          logger.info("dubbox SerializeScanner {} not found", className);
        }
      });
    }

    this.resourcePatternResolver = new PathMatchingResourcePatternResolver();

    this.scans = new ArrayList<>();
    if (StringUtils.isNotEmpty(serialize.getScan())) {
      String[] sc = serialize.getScan().replaceAll("\\.", "/").split(",");
      for (String s : sc) {
        scans.add(PREFIX + s + POSTFIX);
      }
    }
  }

  public Set<Class<?>> scan() {
    Set<Class<?>> result = new HashSet<>();
    List<Resource> resources = getResource();
    if (!resources.isEmpty()) {
      MetadataReaderFactory readerFactory = new CachingMetadataReaderFactory(
          this.resourcePatternResolver);
      for (Resource resource : resources) {
        if (resource.isReadable()) {
          try {
            MetadataReader reader = readerFactory.getMetadataReader(resource);
            String className = reader.getClassMetadata().getClassName();
            Class<?> clazz = Class.forName(className);
            result.add(clazz);
            logger.debug("Dubbox SerializeScanner Class > " + clazz.getName());
          } catch (ClassNotFoundException | IOException e) {
            logger
                .info("dubbox SerializeScanner className not found or MetadataReader IOException");
          }
        }
      }
    }
    return result;
  }

  public Set<Class<?>> getSerializableClasses() {
    Set<Class<?>> result = new HashSet<>();
    result.addAll(classes);
    result.addAll(scan());
    return result;
  }

  private List<Resource> getResource() {
    List<Resource> list = new ArrayList<>();
    for (String s : scans) {
      try {
        list.addAll(Arrays.asList(resourcePatternResolver.getResources(s)));
      } catch (IOException e) {
        logger.info("SerializeScanner getResource() error {}", s);
      }
    }
    return list;
  }

}
