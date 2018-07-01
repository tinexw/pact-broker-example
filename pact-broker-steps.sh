#!/usr/bin/env bash

export PACT_BROKER_BASE_URL=https://test.pact.dius.com.au
export PACT_BROKER_USERNAME=pact-broker-username
export PACT_BROKER_PASSWORD=pact-broker-password
export PACT_BROKER_CLI_HOME=~/pact-broker-1.47.1

curl -XDELETE $PACT_BROKER_BASE_URL/pacticipants/de.tine.foo -v -u $PACT_BROKER_USERNAME:$PACT_BROKER_PASSWORD
curl -XDELETE $PACT_BROKER_BASE_URL/pacticipants/de.tine.bar -v -u $PACT_BROKER_USERNAME:$PACT_BROKER_PASSWORD
curl -XDELETE $PACT_BROKER_BASE_URL/pacticipants/de.tine.baz -v -u $PACT_BROKER_USERNAME:$PACT_BROKER_PASSWORD

cd consumer
../mvnw clean verify -Dtest=de.tine.consumer.BazContractTest,de.tine.consumer.Bar1ContractTest
../mvnw pact:publish -Dpact.project.version=1.0.0

cd ../provider-bar
../mvnw clean verify -Dpact.provider.version=10.0.0 -Dpact.verifier.publishResults=true

$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 1.0.0 --to prod
$PACT_BROKER_CLI_HOME/bin/pact-broker create-version-tag -a de.tine.bar -e 10.0.0 -t prod
$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 1.0.0 --to prod

cd ../provider-baz
../mvnw clean verify -Dpact.provider.version=20.0.0 -Dpact.verifier.publishResults=true
$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 1.0.0 --to prod
$PACT_BROKER_CLI_HOME/bin/pact-broker create-version-tag -a de.tine.baz -e 20.0.0 -t prod
$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 1.0.0 --to prod

cd ../consumer
../mvnw clean verify -Dtest=de.tine.consumer.BazContractTest,de.tine.consumer.Bar2ContractTest
../mvnw pact:publish -Dpact.project.version=2.0.0

cd ../provider-bar
../mvnw clean verify -Dpact.provider.version=11.0.0 -Dpact.verifier.publishResults=true

$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 2.0.0 --to prod
$PACT_BROKER_CLI_HOME/bin/pact-broker create-version-tag -a de.tine.bar -e 11.0.0 -t prod
$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 2.0.0 --to prod

cd ../consumer
../mvnw clean verify -Dtest=de.tine.consumer.BazContractTest,de.tine.consumer.Bar3ContractTest
../mvnw pact:publish -Dpact.project.version=3.0.0

cd ../provider-bar
../mvnw clean verify -Dpact.provider.version=10.0.0 -Dpact.verifier.publishResults=true

$PACT_BROKER_CLI_HOME/bin/pact-broker can-i-deploy -a de.tine.foo -e 3.0.0 --to prod

