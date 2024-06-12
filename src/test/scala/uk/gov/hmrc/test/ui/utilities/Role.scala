/*
 * Copyright 2024 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.test.ui.utilities

sealed trait Role {

  def name: String
  def srsRole: String
  def ldapResourceLocation: Option[String]

}

object Roles {

  private val roles = Seq(UserRole, ApproverRole, SupportRole, PrivilegedUserRole)

  def forName(name: String): Role = {
    roles
      .find(_.name.equalsIgnoreCase(name))
      .getOrElse(throw new IllegalArgumentException(s"Unknown role: $name"))
  }

}

object UserRole extends Role {

  override def name = "user"
  override def srsRole: String = "api_hub_user"
  override def ldapResourceLocation: Option[String] = None

}

object ApproverRole extends Role {

  override def name = "approver"
  override def srsRole: String = "api_hub_approver"
  override def ldapResourceLocation: Option[String] = Some("approvals")

}

object SupportRole extends Role {

  override def name = "support"
  override def srsRole: String = "api_hub_support"
  override def ldapResourceLocation: Option[String] = Some("support")

}

object PrivilegedUserRole extends Role {

  override def name = "privileged-user"
  override def srsRole: String = "api_hub_privileged_user"
  override def ldapResourceLocation: Option[String] = Some("privileged-usage")

}
