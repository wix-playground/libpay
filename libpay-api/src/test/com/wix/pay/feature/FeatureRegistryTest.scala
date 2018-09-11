package com.wix.pay.feature

import java.util.UUID.randomUUID

import org.specs2.mutable.SpecWithJUnit
import org.specs2.specification.Scope

class FeatureRegistryTest extends SpecWithJUnit {

  trait ctx extends Scope {
    val featureId = "featureId" + randomUUID()
  }

  "register feature and check" in new ctx {
    FeatureRegistry.register(featureId, () => true)
    FeatureRegistry.isEnabled(featureId) must_== true
  }

  "register and unregister feature" in new ctx {
    FeatureRegistry.register(featureId, () => true)
    FeatureRegistry.isEnabled(featureId) must_== true
    FeatureRegistry.unregister(featureId)
    FeatureRegistry.isEnabled(featureId) must_== false
  }

  "reregister feature" in new ctx {
    FeatureRegistry.register(featureId, () => true)
    FeatureRegistry.register(featureId, () => false)
    FeatureRegistry.isEnabled(featureId) must_== false
  }

}
