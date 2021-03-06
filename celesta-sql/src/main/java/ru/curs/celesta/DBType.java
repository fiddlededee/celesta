package ru.curs.celesta;

import java.util.Arrays;

public enum DBType {
    /**
     * Postgre.
     */
    POSTGRESQL {
        @Override
        public String getDriverClassName() {
            return "org.postgresql.Driver";
        }
    },
    /**
     * MS SQL.
     */
    MSSQL {
        @Override
        public String getDriverClassName() {
            return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        }
    },
    /**
     * ORACLE.
     */
    ORACLE {
        @Override
        public String getDriverClassName() {
            return "oracle.jdbc.driver.OracleDriver";
        }
    },
    /**
     * H2
     */
    H2 {
        @Override
        public String getDriverClassName() {
            return "org.h2.Driver";
        }
    },
    /**
     * Неизвестный тип.
     */
    UNKNOWN {
        @Override
        public String getDriverClassName() {
            return "";
        }
    };

    abstract public String getDriverClassName();

    public static DBType resolveByJdbcUrl(String url) {
        if (url.startsWith("jdbc:sqlserver")) {
            return DBType.MSSQL;
        } else if (url.startsWith("jdbc:postgresql")) {
            return DBType.POSTGRESQL;
        } else if (url.startsWith("jdbc:oracle")) {
            return DBType.ORACLE;
        } else if (url.startsWith("jdbc:h2")) {
            return DBType.H2;
        } else {
            return DBType.UNKNOWN;
        }
    }

    public static DBType getByNameIgnoreCase(String name) {
        return Arrays.stream(values())
                .filter(n -> n.name().equalsIgnoreCase(name))
                .findFirst()
                .get();
    }
}
