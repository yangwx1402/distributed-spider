package com.young.spider.core.injector

/**
  * Created by yangyong3 on 2017/7/7.
  */
trait Injector {
  @throws[InjectorException]
   def injectSeed()
}
