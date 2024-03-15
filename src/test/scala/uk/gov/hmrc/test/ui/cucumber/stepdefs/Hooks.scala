/*
 * Copyright 2023 HM Revenue & Customs
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

package uk.gov.hmrc.test.ui.cucumber.stepdefs

import com.typesafe.scalalogging.LazyLogging
import io.cucumber.scala.{EN, ScalaDsl, Scenario}
import org.apache.commons.io.FileUtils
import org.openqa.selenium.{OutputType, TakesScreenshot}
import uk.gov.hmrc.selenium.webdriver.{Browser, Driver}

import java.io.File

class Hooks extends LazyLogging with ScalaDsl with EN with Browser {

  private def takeScreenShot(testMethodName: String, result: Scenario): Unit = {
    val wd: TakesScreenshot   = Driver.instance.asInstanceOf[TakesScreenshot]
    val srcFile: File         = wd.getScreenshotAs(OutputType.FILE)
    val byteFile: Array[Byte] = wd.getScreenshotAs(OutputType.BYTES)
    val screenShot: String    = "./target/screenshots/" + testMethodName + ".png"
    FileUtils.copyFile(srcFile, new File(screenShot))
    result.attach(byteFile, "image/png", testMethodName + ".png")
  }

  Before { scenario: Scenario =>
    logger.info(s"Before scenario -> ${scenario.getName}")
    startBrowser()

    Driver.instance.manage().deleteAllCookies()
  }

  After { scenario: Scenario =>
    logger.info(s"After scenario -> ${scenario.getName}")
    if (scenario.isFailed) {
      takeScreenShot(scenario.getName, scenario)
    }
    Driver.instance.manage().deleteAllCookies()

    quitBrowser()
  }
}
