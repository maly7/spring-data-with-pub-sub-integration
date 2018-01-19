package com.github.maly7.subscriber.support

import com.github.maly7.subscriber.SubscriberApp
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [SubscriberApp])
class IntegrationSpec extends Specification {
}
