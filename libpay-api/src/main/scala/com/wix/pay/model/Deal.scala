/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay.model

import com.wix.pay.creditcard.AddressDetailed

/**
 * The deal for which a transaction applies.
 *
 * @param id   Uniquely identifies the deal with the merchant.
 * @param title   Human-readable deal title, e.g. "Online order"
 * @param description   Human-readable deal description, e.g. "Delivery to 123 main street"
 * @param invoiceId   Uniquely identifies the invoice for this deal. Each transaction requires a unique invoice id,
 *                    especially in the case of split payments for a single deal.
 */
case class Deal(id: String,
                title: Option[String] = None,
                description: Option[String] = None,
                invoiceId: Option[String] = None,
                shippingAddress : Option[ShippingAddress] = None,
                orderItems: Seq[OrderItem] = Seq.empty[OrderItem],
                includedCharges: Option[IncludedCharges] = None
                 ) extends Serializable

case class OrderItem(id: Option[String] = None,
                     name: Option[String] = None,
                     quantity: Option[Double] = None,
                     pricePerItem: Option[Double] = None,
                     description: Option[String] = None) extends Serializable


case class IncludedCharges(
                            tax  : Option[Double] = None,
                            shipping : Option[Double] = None
                            )

case class ShippingAddress (
                             firstName  : Option[String] = None,
                             lastName   : Option[String] = None,
                             company    : Option[String] = None,
                             fax        : Option[String] = None,
                             phone      : Option[String] = None,
                             email      : Option[String] = None,
                             address: Option[AddressDetailed] = None

                             ) extends Serializable {
  val street = address.flatMap(_.street)
  val city   = address.flatMap(_.city)
  val state  = address.flatMap(_.state)
  val postalCode  = address.flatMap(_.postalCode)
  val countryCode =  address.flatMap(_.countryCode)
}
