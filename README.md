# Api-hub-acceptance-tests

UI test suite for the `api-hub-frontend` using WebDriver and `cucumber`.  

## Running the tests

Prior to executing the tests ensure you have:
 - Docker - to run mongo and browser (Chrome, Firefox or Edge) inside a container 
 - Installed/configured [service manager](https://github.com/hmrc/service-manager).
 - Installed/configured [service manager config](https://github.com/hmrc/service-manager-config)
 - Cloned the repo [selenium grid](https://github.com/hmrc/docker-selenium-grid) - for testing against a maintained browser in selenium grid locally.

## Running tests against a containerised browser - on a developer machine

Given you have cloned and obtained the latest code from the [selenium grid repo](https://github.com/hmrc/docker-selenium-grid) 
you can now execute the script for this project.

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

Run the following command to start services locally:

    docker run --restart unless-stopped -d -p 27017-27019:27017-27019 --name mongodb mongo:4.2
    sm2 --start API_HUB_ALL -wait 100

Using the `-wait 100` -wait int used with --start, waits a specified number of seconds for the services to become
available before exiting (use with --start)

Then execute the `run_tests.sh` script:

    ./run_tests.sh <browser-driver> <environment> 

The `run_tests.sh` script defaults to using `chrome` in the `local` environment.  For a complete list of supported param values, see:
- `src/test/resources/application.conf` for **environment**

It should be noted that when running the tests locally they will run in a headless mode (the browser is not launched).

You are now able to run the tests against the containers you have running locally.

For example, to run the tests against a containerised Chrome browser:

```bash
./run_tests.sh chrome local
```

As there are three browsers in the hub, the argument for what browser can be substituted for any of the browsers listed, for example

```bash
./run_tests.sh firefox local
```

Running the tests against the firefox container:

```bash
./run_tests.sh edge local
```

Will run the tests against edge.

*Note* A couple of points to gotchas here:

1) Anyone using a recent Mac (M1/M2/M3) the 'edge' browser won't start because selenium-grid will use the docker-compose.arm.yaml file which doesn't include this browser.
2) It has alo been reported that Firefox also doesn't work for recent Mac's (M1/M2/M3), but does for Intel based Macs. Please check Slack forums for more information.

The selenium hub will run as a daemon process and not interfere with your existing terminal or workflow. 
However, should you wish to stop the service for whatever reason then in the project root input the following on the terminal:

```bash
./stop.sh
```

Which will stop all 4 containers (selenium-hub, firefox, edge and chrome).

#### Running the tests against a test environment

To run the tests against an environment set the corresponding `host` environment property as specified under
 `<env>.host.services` in the [application.conf](/src/test/resources/application.conf). 

For example, to execute the `run_tests.sh` script using Chrome remote-webdriver against QA environment 

    ./run_tests.sh chrome qa

*Note* that while the above statement is technically correct, only the local environment is configured correctly for testing purposes
other environments are not integrated properly for end-to-end testing, and as such cannot be run reliably against any environment other than the local one.

## Screenshots on test failures

In the case that a test fails a screenshot is taken and placed in directory */target/screenshots/*. This is of course 
customizable (adjust this setting if you see fit, the current default is located in the hooks file).
Also note that given the headless nature of tests using the local selenium-hub this can be particularly useful when debugging
to see what is the source of the failure.

## Shell scripts in project root

Each shell script corresponds to a runner, for example, in the project root theres three shell scripts:

```text
run_regression.sh
run_tests.sh
run_zap_tests.sh
```

The shell scripts will execute tests with the appropriate tags as specified in the corresponding shell scripts themselves.

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