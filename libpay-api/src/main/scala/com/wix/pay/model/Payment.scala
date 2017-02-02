package com.wix.pay.model

/** Container for currency, amount and installments number, used to encapsulate the monetary details of a transaction. */
case class Payment(currencyAmount: CurrencyAmount, installments: Int = 1)
