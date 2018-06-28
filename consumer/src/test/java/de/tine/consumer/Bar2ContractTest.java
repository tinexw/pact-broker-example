package de.tine.consumer;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class Bar2ContractTest {

    @Rule
    public PactProviderRuleMk2 provider = new PactProviderRuleMk2("de.tine.bar", this);

    @Pact(consumer = "de.tine.foo")
    public RequestResponsePact bar2(PactDslWithProvider builder) {
        return builder.given(
                "Default state")
                .uponReceiving("A request to /bar2")
                .path("/bar2")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("bar2").toPact();
    }

    @PactVerification(value = "de.tine.bar", fragment = "bar2")
    @Test
    public void bar2() {
        final String result = new RestTemplateBuilder().build().getForObject(provider.getUrl() + "/bar2",
                String.class);
        assertEquals("bar2", result);
    }

}
