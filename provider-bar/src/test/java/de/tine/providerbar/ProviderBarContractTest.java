package de.tine.providerbar;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import au.com.dius.pact.provider.junit.Consumer;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactBroker;
import au.com.dius.pact.provider.junit.loader.PactBrokerAuth;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import au.com.dius.pact.provider.spring.SpringRestPactRunner;
import au.com.dius.pact.provider.spring.target.SpringBootHttpTarget;


@RunWith(SpringRestPactRunner.class)
@Provider("de.tine.bar")
@PactBroker(protocol = "https", host = "test.pact.dius.com.au", port = "443", authentication = @PactBrokerAuth(username = "pact-broker-username", password = "pact-broker-password"))
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Consumer("de.tine.foo")
public class ProviderBarContractTest {

    @TestTarget
    public final Target target = new SpringBootHttpTarget();

    @State("Default state")
    public void toDefaultState() {

    }
}
