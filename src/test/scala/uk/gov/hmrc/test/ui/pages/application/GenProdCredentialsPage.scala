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

package scala.uk.gov.hmrc.test.ui.pages.application

import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage._
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class GenProdCredentialsPage(id: String) extends BasePage[GenProdCredentialsPage](pageReadyTest(id)) {


  def selectconfirmcheckbox(): GenProdCredentialsPage = {
    selectconfirmcheckbox()
    new GenProdCredentialsPage(id)
  }

  def clickconfirmandcontinue(): GenProdCredentialsPage = {
    clickconfirmandcontinue()
    new GenProdCredentialsPage(id)
  }

}

object GenProdCredentialsPage {

  def pageReadyTest(id: String): PageReadyTest = {
    PageReadyTests.apiHubPage.url(s"application/add-credential-checklist/$id")
  }

  object elements {
//    val secondaryCredentialClientId: By = By.cssSelector("[data-secondary-credential-client-id]")
//    val addCredentials: By = By.id("addTestCredentialButton")
//    val addProdCredentials: By = By.id("addProductionCredentialButton")
//    val addProdCredentialsLink: By = By.id("tab_hip-production")
    val confirmcheckbox: By = By.id("value_0")
    val confirmandcontinue: By = By.id("button.govuk-button")
  }

  def apply(id: String): GenProdCredentialsPage = {
    new GenProdCredentialsPage(id)
  }

}
