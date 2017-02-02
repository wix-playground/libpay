/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay

import com.wix.pay.creditcard.CreditCard
import com.wix.pay.model.{CurrencyAmount, Customer, Deal, Payment}

import scala.util.Try


/** The direct payment gateway trait, which defines all activities:
  *  - Sale
  *  - Authorize
  *  - Capture
  *  - VoidAuthorization
  *  - Refund
  *
  * Each specific gateway should implement this trait.
  */
trait PaymentGateway {
  /** A ''Sale'' combines the ''Authorization'' and ''Capture'' process in one transaction.
    * Credit card associations require a merchant to submit a ''Sale'' transaction request only when the merchant
    * fulfills an order immediately. Transactions that include physical shipments are not fulfilled until shipment,
    * usually sometime after the customer "purchases" the product, and so would not qualify as a sales transaction.
    *
    * @param merchantKey
    *                    Specific gateway may verify merchants in various means. Some, by login ID and transaction
    *                    key; some, by username/password; others, by private and public key, etc.
    *                    This parameter is a string holding a merchant credentials, for the specific gateway.
    * @param creditCard
    *                   The credit card to be charged.
    * @param currencyAmount
    *                       The amount and currency of the transaction.
    * @param customer
    *                 Some gateways require verbose details about the paying customer. For these gateways, this
    *                 optional parameter must be provided.
    * @param deal
    *             Some gateways require verbose details about the deal. For these gateways, this optional parameter
    *             must be provided.
    * @return
    *         The gateway generated transaction ID, a unique identifier of the transaction.
    */
  def sale(merchantKey: String,
           creditCard: CreditCard,
           currencyAmount: CurrencyAmount,
           customer: Option[Customer] = None,
           deal: Option[Deal] = None): Try[String] = {
    throw new UnsupportedOperationException()
  }


  /** A ''Sale'' combines the ''Authorization'' and ''Capture'' process in one transaction.
    * Credit card associations require a merchant to submit a ''Sale'' transaction request only when the merchant
    * fulfills an order immediately. Transactions that include physical shipments are not fulfilled until shipment,
    * usually sometime after the customer "purchases" the product, and so would not qualify as a sales transaction.
    *
    * @param merchantKey
    *                    Specific gateway may verify merchants in various means. Some, by login ID and transaction
    *                    key; some, by username/password; others, by private and public key, etc.
    *                    This parameter is a string holding a merchant credentials, for the specific gateway.
    * @param creditCard
    *                   The credit card to be charged.
    * @param payment
    *                       The amount, currency and installments number of the transaction.
    * @param customer
    *                 Some gateways require verbose details about the paying customer. For these gateways, this
    *                 optional parameter must be provided.
    * @param deal
    *             Some gateways require verbose details about the deal. For these gateways, this optional parameter
    *             must be provided.
    * @return
    *         The gateway generated transaction ID, a unique identifier of the transaction.
    */
  @deprecated("Please use sale2 instead", "2/2/2017")
  def sale2(merchantKey: String,
           creditCard: CreditCard,
           payment: Payment,
           customer: Option[Customer] = None,
           deal: Option[Deal] = None): Try[String] = {
    throw new UnsupportedOperationException()
  }

  /** The ''Authorize'' operation.
    *
    * An ''Authorization'' is the practice within the banking industry of authorizing electronic transactions done
    * with a debit card or credit card and holding this balance as unavailable either until the merchant clears the
    * transaction (also called settlement), or the hold "falls off".
    * In other words, an ''Authorization'' is an authorization for a specific amount of funds from a credit card
    * holder. When a merchant process a transaction on behalf of the customer, an initial credit card authorization is
    * sent to check if the customer’s credit card is valid and that the customer has sufficient funds to complete the
    * online transaction.
    * Assuming the customer has sufficient funds, the total amount of the online transaction is then held and deducted
    * from the customer’s credit limit.
    *
    * It is important to note that even though the funds are held and deducted from the customer’s credit limit, the
    * funds are not automatically transferred to the merchant’s settlement account; for this, the ''Capture'' operation
    * is required.
    *
    * For example, if an individual has a credit limit of USD 100 and uses a credit card to make a purchase at a retail
    * store for USD 30, then his available credit will immediately decrease to USD 70. This is because the merchant has
    * obtained an authorization from the individual's bank. However, the actual charges would still be USD 0. The actual
    * charge is not put through until the merchant submits a ''Capture'' (settlement) operation and the banking system
    * transfers the funds.
    *
    * @param merchantKey
    *                    Specific gateway may verify merchants in various means. Some, by login ID and transaction
    *                    key; some, by username/password; others, by private and public key, etc.
    *                    This parameter is a string holding a merchant credentials, for the specific gateway.
    * @param creditCard
    *                   The credit card to be charged.
    * @param currencyAmount
    *                       The amount and currency of the transaction.
    * @param customer
    *                 Some gateways require verbose details about the paying customer. For these gateways, this
    *                 optional parameter must be provided.
    * @param deal
    *             Some gateways require verbose details about the deal. For these gateways, this optional parameter
    *             must be provided.
    * @return
    *         A gateway-specific authorization key that can later be used to capture the payment.
    */
  def authorize(merchantKey: String,
                creditCard: com.wix.pay.creditcard.CreditCard,
                currencyAmount: CurrencyAmount,
                customer: Option[Customer] = None,
                deal: Option[Deal] = None): Try[String]

  /** A ''Capture'' is the second step in the paying processing cycle. It is the actual beginning of the money
    * transfer from the customer’s account to the merchant’s account (prior to that, the funds are simply reserved
    * from the customer’s account by means of an ''Authorization'', which is the payment validation).
    * In order to proceed with the payment process after an ''Authorization'' has taken place, the bank first needs to
    * get a ''Capture'' order. A ''Capture'' order is the instruction from the merchant to the financial institution to
    * deduct the funds from the debtor’s account.
    * In other words, the ''Capture'' allows a merchant to have the funds that are owed to him transferred from the
    * customer’s account to the merchant’s account. The online transaction amount does not reach the merchant’s
    * settlement account until the funds are captured.
    *
    * @param merchantKey
    *                    Specific gateway may verify merchants in various means. Some, by login ID and transaction
    *                    key; some, by username/password; others, by private and public key, etc.
    *                    This parameter is a string holding a merchant credentials, for the specific gateway.
    * @param authorizationKey
    *                          The gateway-specific authorization key, as returned by ''Authorize''.
    * @param amount
    *               The amount to capture.
    * @return
    *         The gateway generated transaction ID, a unique identifier of the transaction.
    */
  def capture(merchantKey: String,
              authorizationKey: String,
              amount: Double): Try[String]

  /** A ''VoidAuthroization'' transaction is used to nullify a non-captured authorization, releasing the payer's funds.
    *
    * @param merchantKey
    *                    Specific gateway may verify merchants in various means. Some, by login ID and transaction
    *                    key; some, by username/password; others, by private and public key, etc.
    *                    This parameter is a string holding a merchant credentials, for the specific gateway.
    * @param authorizationKey
    *                          An authorization key as returned by ''authorize''
    *
    * @return
    *         The gateway generated transaction ID, a unique identifier of the transaction.
    */
  def voidAuthorization(merchantKey: String, authorizationKey: String): Try[String]
}
