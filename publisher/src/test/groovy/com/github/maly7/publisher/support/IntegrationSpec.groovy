package com.github.maly7.publisher.support

import com.github.maly7.publisher.Publisher
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@ActiveProfiles(profiles = 'test')
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = [Publisher])
class IntegrationSpec extends Specification {
}
