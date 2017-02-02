package com.wix.pay.model

case class Payment(currencyAmount: CurrencyAmount, installments: Int = 1)
