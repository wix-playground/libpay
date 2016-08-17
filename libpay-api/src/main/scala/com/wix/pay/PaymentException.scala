/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay


/** An exception raised upon failure processing a payment transaction. */
class PaymentException(message: String, cause: Throwable) extends RuntimeException(message, cause)

/** The Companion Object of the [[PaymentException]] class, which introduces means for instantiating an exception
  * object.
  *
  * @author <a href="mailto:ohadr@wix.com">Raz, Ohad</a>
  */
object PaymentException {
  def apply(): PaymentException = new PaymentException(null, null)
  def apply(message: String): PaymentException = new PaymentException(message, null)
  def apply(cause: Throwable): PaymentException = new PaymentException(Option(cause).map(_.toString).orNull, cause)
}



/** An exception raised upon rejecting a payment transaction. */
case class PaymentRejectedException(message: String, cause: Throwable) extends PaymentException(message, cause)

/** The Companion Object of the [[PaymentRejectedException]] class, which introduces means for instantiating an
  * exception object.
  *
  * @author <a href="mailto:ohadr@wix.com">Raz, Ohad</a>
  */
object PaymentRejectedException {
  def apply(): PaymentRejectedException = this(null, null)
  def apply(message: String): PaymentRejectedException = this(message, null)
  def apply(cause: Throwable): PaymentRejectedException = this(Option(cause).map(_.toString).orNull, cause)
}



/** An exception raised upon encountering an error by the payment gateway (e.g., in the case of an invalid
  * credentials).
  */
case class PaymentErrorException(message: String, cause: Throwable) extends PaymentException(message, cause)


/** The Companion Object of the [[PaymentErrorException]] class, which introduces means for instantiating an exception
  * object.
  *
  * @author <a href="mailto:ohadr@wix.com">Raz, Ohad</a>
  */
object PaymentErrorException {
  def apply(): PaymentErrorException = this(null, null)
  def apply(message: String): PaymentErrorException = this(message, null)
  def apply(cause: Throwable): PaymentErrorException = this(Option(cause).map(_.toString).orNull, cause)
}
