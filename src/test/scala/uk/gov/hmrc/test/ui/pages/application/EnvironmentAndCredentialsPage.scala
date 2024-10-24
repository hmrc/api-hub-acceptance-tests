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
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage._
import uk.gov.hmrc.test.ui.pages.application.EnvironmentAndCredentialsPage.elements._
import uk.gov.hmrc.test.ui.pages.{BasePage, PageReadyTest, PageReadyTests}

class EnvironmentAndCredentialsPage(id: String, environmentTab: Option[EnvironmentTab] = None) extends BasePage[EnvironmentAndCredentialsPage](pageReadyTest(id, environmentTab)) {

  def viewTestEnvironment(): EnvironmentAndCredentialsPage = {
    click(hipTestTab)
    EnvironmentAndCredentialsPage(id, Some(TestTab))
  }

  def getTestCredentialCount: Int = {
    findElements(testCredentials).size
  }

  def createNewTestCredential(): EnvironmentAndCredentialsPage = {
    click(createNewTestCredentialButton)
    EnvironmentAndCredentialsPage(id)
  }

  def revokeFirstTestCredential(): EnvironmentAndCredentialsPage = {
    click(revokeFirstTestCredentialLink)
    EnvironmentAndCredentialsPage(id, Some(TestTab))
  }

  def lastTestCredentialClientId: String = {
    findElements(testCredentials).last.getAttribute(testCredentialAttribute)
  }

  def hasTestCredential(clientId: String): Boolean = {
    findElements(testCredentialForClientId(clientId)).nonEmpty
  }

  def viewProductionEnvironment(): EnvironmentAndCredentialsPage = {
    click(hipProductionTab)
    EnvironmentAndCredentialsPage(id, Some(ProductionTab))
  }

  def getProductionCredentialCount: Int = {
    findElements(productionCredentials).size
  }

  def createNewProductionCredential(): GenerateProductionCredentialsPage = {
    click(createNewProductionCredentialButton)
    GenerateProductionCredentialsPage(id)
  }

}

object EnvironmentAndCredentialsPage {

  // We need an enumeration to represent which environment tab is selected. Scala 2 enumerations aren't very good
  // so we'll use an abstract trait and case objects. In Scala 3 we would do this differently.
  //  enum EnvironmentTab:
  //    case TestTab, ProductionTab
  sealed trait EnvironmentTab
  case object TestTab extends EnvironmentTab
  case object ProductionTab extends EnvironmentTab

  // We may have an environment tab selected in which case that is in the URL
  // If no tab is selected then we just want the basic page URL
  // In Scala we use Option to represent something that is not mandatory. The value of environmentTab is one of:
  //  Some(tab) - we can now use tab in code
  //  None - there's is no tab selected
  // We're defaulting environmentTab to None as that is the current behaviour of the tests. We don't need to change it
  // anywhere unless we specifically want to select a tab.
  def pageReadyTest(id: String, environmentTab: Option[EnvironmentTab] = None): PageReadyTest = {
    val fragment = environmentTab match {
      case Some(TestTab) => "#hip-development"
      case Some(ProductionTab) => "#hip-production"
      case None => ""
    }
    PageReadyTests.apiHubPage.url(s"application/environment-and-credentials/$id$fragment")
  }

  object elements {
    val hipTestTab: By = By.id("tab_hip-development")
    val testCredentialAttribute: String = "data-test-credential-client-id"
    val testCredentials: By = By.cssSelector(s"p[$testCredentialAttribute]")
    val createNewTestCredentialButton: By = By.id("addTestCredentialButton")
    def testCredentialForClientId(clientId: String): By = By.cssSelector(s"p[$testCredentialAttribute='$clientId']")
    val revokeFirstTestCredentialLink: By = By.cssSelector(s"a[$testCredentialAttribute]:first-child")
    val hipProductionTab: By = By.id("tab_hip-production")
    val productionCredentialAttribute: String = "data-production-credential-client-id"
    val productionCredentials: By = By.cssSelector(s"p[$productionCredentialAttribute]")
    val createNewProductionCredentialButton: By = By.id("addProductionCredentialButton")
  }

  def apply(id: String, environmentTab: Option[EnvironmentTab] = None): EnvironmentAndCredentialsPage = {
    new EnvironmentAndCredentialsPage(id, environmentTab)
  }

}
