package com.example.issue131reproducer;

import io.asyncer.r2dbc.mysql.constant.SslMode;
import io.asyncer.r2dbc.mysql.constant.TlsVersions;
import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class MySqlConnectionFactories extends AbstractR2dbcConfiguration {

    @Value("${spring.datasource.host}")
    public String host;

    @Value("${spring.datasource.db}")
    public String db;

    @Value("${spring.datasource.username}")
    public String userName;

    @Value("${spring.datasource.password}")
    public String password;

    @Override
    @Bean
    public ConnectionFactory connectionFactory() {
        ConnectionFactoryOptions options = ConnectionFactoryOptions.builder()
                                                                   .option(ConnectionFactoryOptions.DRIVER, "mysql")
                                                                   .option(ConnectionFactoryOptions.HOST, host)
                                                                   .option(ConnectionFactoryOptions.USER, userName)
                                                                   .option(ConnectionFactoryOptions.PORT, 3306)
                                                                   .option(ConnectionFactoryOptions.PASSWORD, password)
                                                                   .option(ConnectionFactoryOptions.DATABASE, db)
                                                                   .option(Option.valueOf("sslMode"), SslMode.VERIFY_IDENTITY)
                                                                   .option(Option.valueOf("tlsVersion"), TlsVersions.TLS1_2)
                                                                   .option(Option.valueOf("tcpKeepAlive"), true) // optional, default false
                                                                   .option(Option.valueOf("tcpNoDelay"), true) // optional, default false
                                                                   .build();
        return ConnectionFactories.get(options);
    }
}
