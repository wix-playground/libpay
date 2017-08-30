/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay


/**
  * An exception raised upon failure processing a payment transaction.
  *
  * @param gatewayInternalCode   an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                              Do not rely on this for business logic: values and format are treated as implementation
  *                              detail and may change without notice.
  */
class PaymentException(message: String, cause: Throwable, gatewayInternalCode: Option[String] = None) extends RuntimeException(message, cause)

/** The Companion Object of the [[PaymentException]] class, which introduces means for instantiating an exception
  * object.
  */
object PaymentException {
  def apply(gatewayInternalCode: Option[String] = None): PaymentException = new PaymentException(null, null, gatewayInternalCode)
  def apply(message: String, gatewayInternalCode: Option[String] = None): PaymentException = new PaymentException(message, null, gatewayInternalCode)
  def apply(cause: Throwable, gatewayInternalCode: Option[String] = None): PaymentException = new PaymentException(Option(cause).map(_.toString).orNull, cause, gatewayInternalCode)
}



/**
  * An exception raised upon rejecting a payment transaction.
  *
  * @param gatewayInternalCode   an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                              Do not rely on this for business logic: values and format are treated as implementation
  *                              detail and may change without notice.
  */
case class PaymentRejectedException(message: String, cause: Throwable, gatewayInternalCode: Option[String] = None) extends PaymentException(message, cause, gatewayInternalCode)

/** The Companion Object of the [[PaymentRejectedException]] class, which introduces means for instantiating an
  * exception object.
  */
object PaymentRejectedException {
  def apply(gatewayInternalCode: Option[String] = None): PaymentRejectedException = this(null, null, gatewayInternalCode)
  def apply(message: String, gatewayInternalCode: Option[String] = None): PaymentRejectedException = this(message, null, gatewayInternalCode)
  def apply(cause: Throwable, gatewayInternalCode: Option[String] = None): PaymentRejectedException = this(Option(cause).map(_.toString).orNull, cause, gatewayInternalCode)
}



/**
  * An exception raised upon encountering an error by the payment gateway (e.g., in the case of an invalid credentials).
  *
  * @param gatewayInternalCode   an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                              Do not rely on this for business logic: values and format are treated as implementation
  *                              detail and may change without notice.
  */
case class PaymentErrorException(message: String, cause: Throwable, gatewayInternalCode: Option[String] = None) extends PaymentException(message, cause, gatewayInternalCode)


/** The Companion Object of the [[PaymentErrorException]] class, which introduces means for instantiating an exception
  * object.
  */
object PaymentErrorException {
  def apply(gatewayInternalCode: Option[String] = None): PaymentErrorException = this(null, null, gatewayInternalCode)
  def apply(message: String, gatewayInternalCode: Option[String] = None): PaymentErrorException = this(message, null, gatewayInternalCode)
  def apply(cause: Throwable, gatewayInternalCode: Option[String] = None): PaymentErrorException = this(Option(cause).map(_.toString).orNull, cause, gatewayInternalCode)
}
