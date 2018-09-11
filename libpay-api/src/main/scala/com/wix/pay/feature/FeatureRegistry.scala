package com.wix.pay.feature

import java.util.concurrent.ConcurrentHashMap

/**
  * Feature registry for using experiments within libpay projects.
  * Clients can register any features, by default all features are disabled
  */
object FeatureRegistry {

  private val registry = new ConcurrentHashMap[String, () => Boolean]()

  private val disabledFeature = () => false

  def register(featureId: String, feature: () => Boolean): Unit = {
    this.registry.put(featureId, feature)
  }

  def unregister(featureId: String): Unit = {
    this.registry.remove(featureId)
  }

  def isEnabled(featureId: String): Boolean = {
    this.registry.getOrDefault(featureId, disabledFeature)()
  }

}
