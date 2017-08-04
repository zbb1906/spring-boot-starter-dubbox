package com.ybveg.dubbox.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;

/**
 * @author xiaofei.wxf(teaey)
 * @since 0.0.0
 */
@SuppressWarnings("rawtypes")
public class DubboxHolderListener implements ApplicationListener {

  private static final Logger LOGGER = LoggerFactory.getLogger(DubboxHolderListener.class);

  private static Thread holdThread;
  private static Boolean running = Boolean.FALSE;

  @Override
  public void onApplicationEvent(ApplicationEvent event) {
    if (event instanceof ApplicationPreparedEvent) {
      if (running == Boolean.FALSE) {
        running = Boolean.TRUE;
      }
      if (holdThread == null) {
        holdThread = new Thread(new Runnable() {
          @Override
          public void run() {
            if (LOGGER.isTraceEnabled()) {
              LOGGER.trace(Thread.currentThread().getName());
            }
            while (running && !Thread.currentThread().isInterrupted()) {
              try {
                Thread.sleep(2000);
              } catch (InterruptedException e) {
              }
            }
          }
        }, "spring-boot-dubbox-starter Holder");
        holdThread.setDaemon(false);
        holdThread.start();
      }
    }
    if (event instanceof ContextClosedEvent) {
      running = Boolean.FALSE;
      if (null != holdThread) {
        holdThread.interrupt();
        holdThread = null;
      }
    }
  }

  public static void stopApplicationContext(Boolean stop) {
    running = stop.booleanValue();
    if (null != holdThread) {
      holdThread.interrupt();
      holdThread = null;
    }
  }
}