package repository;


import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.MySQLContainer;

public class MySQLExtension implements BeforeAllCallback, AfterAllCallback {
    private MySQLContainer<?> mySQLContainer;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        mySQLContainer = new MySQLContainer<>("mysql:5.7.34")
                .withDatabaseName("my_db")
                .withUsername("bestuser")
                .withPassword("bestuser")
                .withInitScript("db.sql");

        mySQLContainer.start();

        System.setProperty("jdbcUrl", mySQLContainer.getJdbcUrl());
        System.setProperty("driverClass", mySQLContainer.getDriverClassName());
        System.setProperty("user", mySQLContainer.getUsername());
        System.setProperty("password", mySQLContainer.getPassword());
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {

    }
}
