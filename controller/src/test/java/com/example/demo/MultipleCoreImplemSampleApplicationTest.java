package com.example.demo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MultipleCoreImplemSampleApplication.class })
public class MultipleCoreImplemSampleApplicationTest {

    @Autowired
    private MultipleCoreImplemSampleApplication multipleCoreImplemSampleApplication;

    @Test
    public void initTest () {
        Assertions.assertThat(multipleCoreImplemSampleApplication).isNotNull();
    }

}
