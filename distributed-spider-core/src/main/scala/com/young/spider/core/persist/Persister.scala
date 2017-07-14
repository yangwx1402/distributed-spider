package com.young.spider.core.persist

import com.young.spider.core.message.{PersisterConfig, ParserEntity}
import com.young.spider.core.utils.ClassUtils

/**
  * Created by yangyong3 on 2017/7/14.
  */
trait Persister[Meta, Result] {
  def persist(meta: Meta, parserEntity: ParserEntity[Meta, Result],config:PersisterConfig)
}

object PersisterFactory {
  def getPersist[Meta, Result](persiterClass: String) = ClassUtils.getInstance(persiterClass)
}
