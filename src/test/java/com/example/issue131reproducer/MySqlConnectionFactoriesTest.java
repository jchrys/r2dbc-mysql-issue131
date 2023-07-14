package com.example.issue131reproducer;

import io.asyncer.r2dbc.mysql.MySqlConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.r2dbc.core.DatabaseClient;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
class MySqlConnectionFactoriesTest {

    @Autowired
    private DatabaseClient databaseClient;

    @Test
    public void test() {
        assertInstanceOf(MySqlConnectionFactory.class, databaseClient.getConnectionFactory());
    }

}