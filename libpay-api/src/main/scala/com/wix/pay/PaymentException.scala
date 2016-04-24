/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay


/** An exception raised upon failure processing a payment transaction. */
class PaymentException(message: String = null, cause: Throwable = null) extends RuntimeException(message, cause)


/** An exception raised upon rejecting a payment transaction. */
case class PaymentRejectedException(message: String = null,
                                    cause: Throwable = null) extends PaymentException(message, cause)


/** An exception raised upon encountering an error by the payment gateway (e.g., in the case of an invalid credentials). */
case class PaymentErrorException(message: String = null, cause: Throwable = null) extends PaymentException(message, cause)
