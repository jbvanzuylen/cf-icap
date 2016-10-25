#cf-icap

# How to install

### Lucee

Download the latest version [here](https://github.com/jbvanzuylen/cf-icap/releases/download/v0.0.6/cf-icap-ext.zip)

__Install as an extension__

* Connect to the Web Administration
* Go to `Extension > Application`
* Scroll down to the bottom of the page
* Click on `Browse` button in the `Upload new extension` section
* Select the ZIP file you have downloaded above
* Hit the `Upload` button and follow the instructions

__Manual installation__

* Unzip the file you have downloaded above
* Copy the `.jar` files from the `lib` folder to the `WEB-INF\lucee\lib` directory in your web root
* Copy the `.cfm` files from the `functions` folder to the `WEB-INF\lucee\library\function` directory in your web root
* Copy the `.cfc` file from the `components` folder to the `WEB-INF\lucee\components\icap` directory in your web root
