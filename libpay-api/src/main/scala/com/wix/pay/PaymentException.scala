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
  * @param gatewayInternalCode an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                            Do not rely on this for business logic: values and format are treated as implementation
  *                            detail and may change without notice.
  * @param transactionId an optional unique identifier of a transaction in case if provider return one on exception.
  */
class PaymentException(message: String,
                       cause: Throwable,
                       gatewayInternalCode: Option[String] = None,
                       transactionId: Option[String] = None)
  extends RuntimeException(message, cause)

/** The Companion Object of the [[PaymentException]] class, which introduces means for instantiating an exception
  * object.
  */
object PaymentException {
  def apply(): PaymentException = new PaymentException(null, null, None, None)

  def apply(message: String): PaymentException = new PaymentException(message, null, None, None)

  def apply(cause: Throwable): PaymentException = new PaymentException(Option(cause).map(_.toString).orNull, cause, None, None)

  def apply(message: String, transactionId: Option[String]): PaymentException = new PaymentException(message, cause = null, None, transactionId)
}


/**
  * An exception raised upon rejecting a payment transaction.
  *
  * @param gatewayInternalCode an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                            Do not rely on this for business logic: values and format are treated as implementation
  *                            detail and may change without notice.
  * @param transactionId an optional unique identifier of a transaction in case if provider return one on exception.
  */
case class PaymentRejectedException(message: String,
                                    cause: Throwable,
                                    gatewayInternalCode: Option[String] = None,
                                    transactionId: Option[String] = None)
  extends PaymentException(message, cause, gatewayInternalCode, transactionId)

/** The Companion Object of the [[PaymentRejectedException]] class, which introduces means for instantiating an
  * exception object.
  */
object PaymentRejectedException {
  def apply(): PaymentRejectedException = this(null, null, None, None)

  def apply(message: String): PaymentRejectedException = this(message, null, None)

  def apply(cause: Throwable): PaymentRejectedException = this(Option(cause).map(_.toString).orNull, cause, None)

  def apply(message: String, transactionId: Option[String]): PaymentException = this(message, null, None, transactionId)
}


/**
  * An exception raised upon encountering an error by the payment gateway (e.g., in the case of an invalid credentials).
  *
  * @param gatewayInternalCode an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                            Do not rely on this for business logic: values and format are treated as implementation
  *                            detail and may change without notice.
  * @param transactionId an optional unique identifier of a transaction in case if provider return one on exception.
  */
case class PaymentErrorException(message: String,
                                 cause: Throwable,
                                 gatewayInternalCode: Option[String] = None,
                                 transactionId: Option[String] = None)
  extends PaymentException(message, cause, gatewayInternalCode, transactionId)


/** The Companion Object of the [[PaymentErrorException]] class, which introduces means for instantiating an exception
  * object.
  */
object PaymentErrorException {
  def apply(): PaymentErrorException = this(null, null, None, None)

  def apply(message: String): PaymentErrorException = this(message, null, None, None)

  def apply(cause: Throwable): PaymentErrorException = this(Option(cause).map(_.toString).orNull, cause, None, None)

  def apply(message: String, transactionId: Option[String]): PaymentException = this(message, null, None, transactionId)
}


/**
  * An exception raised upon encountering an error with user account (in the case when credentials were correct, but account is incorrectly set up).
  *
  * @param gatewayInternalCode an optional, opaque, gateway-specific error code for debugging, logging, and analytics.
  *                            Do not rely on this for business logic: values and format are treated as implementation
  *                            detail and may change without notice.
  * @param transactionId an optional unique identifier of a transaction in case if provider return one on exception.
  */
case class AccountException(message: String,
                            cause: Throwable,
                            gatewayInternalCode: Option[String] = None,
                            transactionId: Option[String] = None)
  extends PaymentException(message, cause, gatewayInternalCode, transactionId)

/** The Companion Object of the [[AccountException]] class, which introduces means for instantiating an
  * exception object.
  */
object AccountException {
  def apply(): AccountException = this (null, null, None, None)

  def apply(message: String): AccountException = this (message, null, None, None)

  def apply(cause: Throwable): AccountException = this (Option(cause).map(_.toString).orNull, cause, None, None)

  def apply(message: String, transactionId: Option[String]): PaymentException = this(message, null, None, transactionId)
}