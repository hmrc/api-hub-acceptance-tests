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

package uk.gov.hmrc.test.ui.pages2

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages2.SignInPage._
import uk.gov.hmrc.test.ui.pages2.SignInPage.elements._

class SignInPage extends ApiHubBasePage(pageReadyTest) {

  def signInViaLdap(): LdapSignInPage = {
    click(signInViaLdapButton)
    LdapSignInPage()
  }

  def signInViaStride(): StrideSignInPage = {
    click(signInViaStrideButton)
    StrideSignInPage()
  }

}

object SignInPage {

  val pageReadyTest: PageReadyTest = UrlPageReadyTest("sign-in")

  object elements {
    val signInViaLdapButton: By = By.id("signInViaLdapButton")
    val signInViaStrideButton: By = By.id("signInViaStrideButton")
  }

  def apply(): SignInPage = {
    new SignInPage ()
  }

}