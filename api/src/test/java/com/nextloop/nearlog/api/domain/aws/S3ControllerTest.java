package com.nextloop.nearlog.api.domain.aws;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class S3ControllerTest {

    @Test
    void extract_ext() {
        String type = "image/jpeg";
        String ext = type.substring(type.indexOf('/') + 1);
        assertThat(ext).isEqualTo("jpeg");
    }
}