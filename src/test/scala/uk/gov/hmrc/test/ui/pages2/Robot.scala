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

import com.typesafe.scalalogging.LazyLogging
import org.openqa.selenium.{By, WebDriver, WebElement}
import org.openqa.selenium.support.ui.{ExpectedCondition, ExpectedConditions, FluentWait}
import uk.gov.hmrc.selenium.webdriver.Driver
import uk.gov.hmrc.test.ui.conf.TestConfiguration

import java.time.Duration
import scala.jdk.CollectionConverters._

trait Robot extends LazyLogging {

  def waitForTitle(title: String): Unit = {
    fluentWait(ExpectedConditions.titleIs(title))
  }

  def waitForTitleOneOf(titles: Seq[String]): Unit = {
    fluentWait(ExpectedConditions.or(
      titles.map(
        title =>
          ExpectedConditions.titleIs(title)
      ): _*
    ))
  }

  def waitForUrl(relativeUrl: String): Unit = {
    fluentWait(ExpectedConditions.urlToBe(buildFullUrl(relativeUrl)))
  }

  def waitForUrlMatching(pattern: String): Unit = {
    fluentWait(ExpectedConditions.urlMatches(pattern))
  }

  def waitForElement(by: By): Unit = {
    fluentWait(ExpectedConditions.visibilityOfElementLocated(by))
  }

  def navigateTo(relativeUrl: String): Unit = {
    val url = buildFullUrl(relativeUrl)

    logger.info(s"Navigating to $url")

    Driver.instance.navigate().to(url)
  }

  def clear(by: By): Unit = {
    findElement(by).clear()
  }

  def click(by: By): Unit = {
    findElement(by).click()
  }

  def sendKeys(by: By, value: String): Unit = {
    clear(by)
    findElement(by).sendKeys(value)
  }

  def getTitle: String = {
    Driver.instance.getTitle
  }

  def getCurrentUrl: String = {
    Driver.instance.getCurrentUrl
  }

  def getText(by: By): String = {
    findElement(by).getText
  }

  // TODO: option? getAttribute can be null
  def getAttribute(by: By, attributeName: String): String = {
    Driver.instance.findElement(by).getAttribute(attributeName)
  }

  def findElement(by: By): WebElement = {
    Driver.instance.findElement(by)
  }

  def findElementWithAttributeValue(name: String, value: String): WebElement = {
    Driver.instance.findElement(By.cssSelector(s"[$name='$value']"))
  }

  def findElements(by: By): Seq[WebElement] = {
    Driver.instance.findElements(by).asScala.toSeq
  }

  def findElementsWithAttribute(name: String): Seq[WebElement] = {
    findElements(By.cssSelector(s"[$name]"))
  }

  def exists(by: By): Boolean = {
    findElements(by).nonEmpty
  }

  private def fluentWait(expectedCondition: ExpectedCondition[_]): Unit = {
    new FluentWait[WebDriver](Driver.instance)
      .withTimeout(Duration.ofSeconds(3))
      .pollingEvery(Duration.ofMillis(250))
      .until(expectedCondition)
  }

  private def buildFullUrl(relativeUrl: String): String = {
    val baseUrl = s"${TestConfiguration.url("api-hub")}"

    if (relativeUrl.nonEmpty) {
      s"$baseUrl/$relativeUrl"
    }
    else {
      baseUrl
    }
  }

}
