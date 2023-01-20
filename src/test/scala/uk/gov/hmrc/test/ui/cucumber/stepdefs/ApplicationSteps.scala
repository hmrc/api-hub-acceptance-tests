package uk.gov.hmrc.test.ui.cucumber.stepdefs

class ApplicationSteps(homepageSteps: HomepageSteps) extends BaseStepDef {
  Then("the inputted application name should match the registered application name") { () =>
    homepageSteps.expectedAppName should be(homepageSteps.actualAppName)
  }
}
