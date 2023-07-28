mvn clean install -P dashboard -Dtags=DataConfig1Runner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-1

mvn clean install -P dashboard -Dtags=Regression1Runner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-1 -Dsubmodule.name=regression-suite-1 -Drp.enable=false

mvn clean install -P dashboard -Dtags=OCRunner -Dtest.env=doha -Drp.launch=doha-sales-flutter-app-regression-2 -Dsubmodule.name=oc-regression -Drp.enable=false

mvn clean install -P dashboard -Dtags=ProdSanityRunner -Dtest.env=Production -Drp.launch=sales-prod-sanity -Dsubmodule.name=production-sanity -Drp.enable=false
