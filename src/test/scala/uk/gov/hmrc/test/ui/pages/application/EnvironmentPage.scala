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

package uk.gov.hmrc.test.ui.pages.application

import org.openqa.selenium.By
import uk.gov.hmrc.test.ui.pages.application.EnvironmentPage._
import uk.gov.hmrc.test.ui.pages.application.EnvironmentPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class EnvironmentPage(id: String, environment: String) extends BasePage[EnvironmentPage](pageReadyTest(id, environment)) {

  def getCredentialCount: Int = {
    findElements(credentials).size
  }

  def createNewCredential(): EnvironmentPage = {
    click(credentialTab)
    click(createNewCredentialButton)
    EnvironmentPage(id, environment)
  }

  def createNewProductionCredential(): GenerateProductionCredentialsPage = {
    click(credentialTab)
    click(createNewCredentialButton)
    GenerateProductionCredentialsPage(id)
  }

  def hasCredential(clientId: String): Boolean = {
    findElements(credentialForClientId(clientId)).nonEmpty
  }

  def lastCredentialClientId: String = {
    findElements(credentials).last.getAttribute(credentialAttribute)
  }

  def revokeFirstCredential(): EnvironmentPage = {
    click(credentialTab)
    click(revokeFirstCredentialLink)
    EnvironmentPage(id, environment)
  }

}

object EnvironmentPage {

  def pageReadyTest(id: String, environment: String): PageReadyTest = {
    PageReadyTests.allOf(
      PageReadyTests.apiHubPage.urlContaining(s"application/environments/$id/$environment"),
    )
  }

  object elements {
    val credentialAttribute: String = "data-credential-client-id"
    val credentials: By = By.cssSelector(s"p[$credentialAttribute]")
    val createNewCredentialButton: By = By.id("addCredentialButton")
    val credentialTab: By = By.id("tab_credentials")
    def credentialForClientId(clientId: String): By = By.cssSelector(s"p[$credentialAttribute='$clientId']")
    val revokeFirstCredentialLink: By = By.className("hip-revoke-credential")
  }

  def apply(id: String, environment: String): EnvironmentPage = {
    new EnvironmentPage(id, environment)
  }

}
