package nextstep.jdbc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDatabasePopulatorUtils {

    private static final Logger log = LoggerFactory.getLogger(TestDatabasePopulatorUtils.class);

    public static void execute(final DataSource dataSource) {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
        ) {
            final var url = TestDatabasePopulatorUtils.class.getClassLoader().getResource("schema.sql");
            final var file = new File(url.getFile());
            final var sql = Files.readString(file.toPath());
            statement.execute(sql);
        } catch (NullPointerException | IOException | SQLException e) {
            log.error(e.getMessage(), e);
        }
    }

    private TestDatabasePopulatorUtils() {
    }
}
