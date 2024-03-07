**This is a template README.md.  Be sure to update this with project specific content that describes your ui test project.**

# api-hub-acceptance-tests
UI test suite for the `<digital service name>` using WebDriver and `<scalatest/cucumber>`.  

## Running the tests

Prior to executing the tests ensure you have:
 - Docker - to run mongo and browser (Chrome, Firefox or Edge) inside a container 
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).
 - Installed/configured [service manager config](https://github.com/hmrc/service-manager-config)
 - Cloned the repo [selenium grid](https://github.com/hmrc/docker-selenium-grid) - for testing against a maintained browser in selenium grid locally.

Run the following command to start services locally:

    docker run --restart unless-stopped -d -p 27017-27019:27017-27019 --name mongodb mongo:4.2
    sm2 --start API_HUB_ALL -wait 100

Using the `-wait 100` -wait int used with --start, waits a specified number of seconds for the services to become 
available before exiting (use with --start)

Then execute the `run_tests.sh` script:

    ./run_tests.sh <browser-driver> <environment> 

The `run_tests.sh` script defaults to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
 - `src/test/resources/application.conf` for **environment** 

## Running tests against a containerised browser - on a developer machine

Given you have cloned and obtained the latest code from the [selenium grid repo](https://github.com/hmrc/docker-selenium-grid) you should launch the script from the root of this project

```bash
./start.sh
```

Which will get the selenium-hub, chrome, firefox and edge docker images locally and start the hub on port 4444.
With this enabled all tests can now run on these images against the local environment.

Be sure to double-check the images are running through docker dashboard or from the terminal before continuing alternatively,
you can check in the browser what is running on localhost:4444, where you should see all the browser instances running.

```bash
docker container list
```

For example, to run the tests against a containerised Chrome browser:

```bash
./run_tests.sh chrome local
```

#### Running the tests against a test environment

To run the tests against an environment set the corresponding `host` environment property as specified under
 `<env>.host.services` in the [application.conf](/src/test/resources/application.conf). 

For example, to execute the `run_tests.sh` script using Chrome remote-webdriver against QA environment 

    ./run_tests.sh chrome qa

*Note* that while the above statement is technically correct, only the local environment is configured correctly for testing purposes
other environments are not integrated properly for end-to-end testing, and as such cannot be run reliably against any environment other than the local one.

## Running ZAP tests

ZAP tests can be automated using the HMRC Dynamic Application Security Testing approach. Running 
automated ZAP tests should not be considered a substitute for manual exploratory testing using OWASP ZAP.

#### Tagging tests for ZAP

It is not required to proxy every journey test via ZAP. The intention of proxying a test through ZAP is to expose all the
 relevant pages of an application to ZAP. So tagging a subset of the journey tests or creating a 
 single ZAP focused journey test is sufficient.

#### Executing a ZAP test

The shell script `run_zap_tests.sh` is available to execute ZAP tests. The script proxies a set of journey tests, 
tagged as `ZapTests`, via ZAP.  

For example, to execute ZAP tests locally using a Chrome browser

```
./run_zap_test.sh chrome local
```

### Running tests using BrowserStack
If you would like to run your tests via BrowserStack from your local development environment please refer to the [webdriver-factory](https://github.com/hmrc/webdriver-factory/blob/main/README.md/#user-content-running-tests-using-browser-stack) project.

## Installing local driver binaries

This project supports UI test execution using Firefox (Geckodriver) and Chrome (Chromedriver) browsers. 

See the `drivers/` directory for some helpful scripts to do the installation work for you.  They should work on both Mac and Linux by running the following command:

    ./installGeckodriver.sh <operating-system> <driver-version>
    or
    ./installChromedriver <operating-system> <driver-version>

- *<operating-system>* defaults to **linux64**, however it also supports **macos**
- *<driver-version>* defaults to **0.21.0** for Gecko/Firefox, and the latest release for Chrome.  You can, however, however pass any version available at the [Geckodriver](https://github.com/mozilla/geckodriver/tags) or [Chromedriver](http://chromedriver.storage.googleapis.com/) repositories.

**Note 1:** *You will need to ensure that you have a recent version of Chrome and/or Firefox installed for the later versions of the drivers to work reliably.*

**Note 2** *These scripts use sudo to set the right permissions on the drivers so you will likely be prompted to enter your password.*

## Scalafmt

Check all project files are formatted as expected as follows:

```bash
sbt scalafmtCheckAll scalafmtCheck
```

Format `*.sbt` and `project/*.scala` files as follows:

```bash
sbt scalafmtSbt
```

Format all project files as follows:

```bash
sbt scalafmtAll
```

## License

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").