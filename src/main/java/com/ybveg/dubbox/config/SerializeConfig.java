package com.ybveg.dubbox.config;

import java.util.List;

/**
 * @auther zbb
 * @create 2017/8/17
 */
public class SerializeConfig {


  /**
   * 单独注入的对象
   */
  List<String> classes;

  /**
   * 需要扫描的包
   */
  String scan;

  public List<String> getClasses() {
    return classes;
  }

  public void setClasses(List<String> classes) {
    this.classes = classes;
  }

  public String getScan() {
    return scan;
  }

  public void setScan(String scan) {
    this.scan = scan;
  }
}
