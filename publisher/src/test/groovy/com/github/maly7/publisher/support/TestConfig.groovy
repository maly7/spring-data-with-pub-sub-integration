package com.github.maly7.publisher.support

import org.springframework.context.annotation.Bean

class TestConfig {

    @Bean
    MessageRecorder messageRecorder() {
        new MessageRecorder()
    }
}
