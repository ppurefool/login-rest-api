package io.kwangsik.loginrestapi.config.jpa;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy;

import java.util.Locale;

// application*.yaml 에서 이용한다.
public class CustomSpringPhysicalNamingStrategy extends SpringPhysicalNamingStrategy {
    @Override
    protected Identifier getIdentifier(String name, boolean quoted, JdbcEnvironment jdbcEnvironment) {
        if (isCaseInsensitive(jdbcEnvironment)) {
            name = name.toUpperCase(Locale.ROOT);
        }
        return new Identifier(name, quoted);
    }
}