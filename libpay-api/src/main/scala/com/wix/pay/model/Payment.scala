package com.wix.pay.model

/** Container for currency, amount and installments number, used to encapsulate the monetary details of a transaction. */
case class Payment(currency: String, amount: Double, installments: Option[Int] = None)
