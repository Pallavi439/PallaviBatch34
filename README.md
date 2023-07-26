mvn clean install -P dashboard -Dtags=DataConfig1Runner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-1 -Dremote.execution=true -Dparallel.test.count=1 -Dheadless.execution=false

mvn clean install -P dashboard -Dtags=Regression1Runner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-1 -Dremote.execution=true -Dparallel.test.count=1 -Dheadless.execution=false

mvn clean install -P dashboard -Dtags=OCRunner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-2 -Dremote.execution=true -Dparallel.test.count=1 -Dheadless.execution=false