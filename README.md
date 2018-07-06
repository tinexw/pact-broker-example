This repo contains code to reproduce [this bug](https://github.com/pact-foundation/pact_broker-client/issues/33) in the pact broker CLI's `can-i-deploy` command.
Run `./pact-broker-steps.sh` to create the scenario in the https://test.pact.dius.com.au pact broker. 
Before running it you need to
- update the path to the pact broker CLI, search for `export PACT_BROKER_CLI_HOME=`
- replace all occurrences (in all files) of pact-broker-username and pact-broker-password with the actual pact broker credentials.  
