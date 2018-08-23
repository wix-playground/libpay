/*      __ __ _____  __                                              *\
**     / // // /_/ |/ /          Wix                                 **
**    / // // / /|   /           (c) 2006-2014, Wix LTD.             **
**   / // // / //   |            http://www.wix.com/                 **
**   \__/|__/_//_/| |                                                **
\*                |/                                                 */
package com.wix.pay.model


/**
  * Represents a customer.
  *
  * @param id         customer id, client specific, not provider specific
  * @param name       name
  * @param phone      phone
  * @param email      email
  * @param ipAddress  remote ip address
  * @param fax        fax
  * @param company    company
  * @param userAgent  user agent
  * @param referrer   referrer, it is page url from which a charge was initiated
  * @param deviceId   device fingerprint for the user's device or browser
  */
case class Customer(id: Option[String] = None,
                    name: Option[Name] = None,
                    phone: Option[String] = None,
                    email: Option[String] = None,
                    ipAddress: Option[String] = None,
                    fax: Option[String] = None,
                    company: Option[String] = None,
                    userAgent: Option[String] = None,
                    referrer: Option[String] = None,
                    deviceId: Option[String] = None) {
  def firstName: Option[String] = {
    name map (_.first)
  }

  def lastName: Option[String] = {
    name map (_.last)
  }
}

case class Name(first: String, last: String)
