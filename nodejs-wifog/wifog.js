#!/usr/bin/env node

require('chromedriver');
var config = require('./myconfig.js');
var webdriver = require('selenium-webdriver');
var By = require("selenium-webdriver").By;
var until = require("selenium-webdriver").until;

var Capabilities = require('selenium-webdriver/lib/capabilities').Capabilities;
var driver = new webdriver.Builder().forBrowser('chrome').build();

driver.get("https://app.wifog.com/login-register/");
driver.wait(until.titleIs("Login and Registration"), 5000);

driver.sleep(2000);

driver.findElement(webdriver.By.xpath("//div[@id='loggain-popup']/div/div/form/div/input[@type='email']")).then(function(elem) {
	console.log("input: Email...");
	elem.click();
	elem.clear();
	elem.sendKeys(config.USERNAME);
});

driver.findElement(webdriver.By.xpath("//div[@id='loggain-popup']/div/div/form/div/input[@type='password']")).then(function(elem) {
	console.log("input: Password...");
	elem.click();
	elem.clear();
	elem.sendKeys(config.PASSWORD);
});

driver.findElement(webdriver.By.xpath("//div[@id='loggain-popup']/div/div/form/div/button[@type='button']")).then(function(elem) {
	console.log("click: Submit...");
	elem.click();
	driver.wait(until.titleIs("Wifog"), 5000);
	driver.sleep(2000);

	driver.findElement(webdriver.By.xpath("//div[@id='adverts-pop']")).then(function(elem) {
		console.log("click: Reklam...");
		elem.click();
		driver.sleep(2000);

		var handlePromise = driver.getAllWindowHandles();
		handlePromise.then(function (handles) {
			var popUpWindow = handles[1];
			driver.switchTo().window(popUpWindow);

			driver.sleep(2000);
			driver.navigate().refresh();

			driver.sleep(5000);

			driver.findElement(webdriver.By.tagName('video')).then(function(elem) {
				console.log("video: Get video (1)...");
				driver.findElement(webdriver.By.id('video-cont')).then(function(elem) {
					console.log("video: Get video (2)...");
					elem.click();
				});
			});
			driver.sleep(30000);
		});
	});
});

driver.quit(); 
console.log("Running...");

process.on('uncaughtException', (e) => {
	console.log("Exception");
	console.log(e);
	driver.quit();
});
