package com.szs.restapi;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class H2Runner implements ApplicationRunner {

    private final DataSource dataSource;

    private final JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        try( Connection connection = dataSource.getConnection() ) {

            Statement statement = connection.createStatement();

            String ddlUserWhiteListTable = this.ddlUserWhiteListTable();
            String ddlUserTable = this.ddlUserTable();
            String ddlScrappingTable = this.ddlScrappingTable();
            String insertUserWhiteListTable = this.insertUserWhiteListTable();

            statement.executeUpdate(ddlUserWhiteListTable);
            statement.executeUpdate(ddlUserTable);
            statement.executeUpdate(ddlScrappingTable);
            statement.executeUpdate(insertUserWhiteListTable);

        } catch(Exception e) {

            e.printStackTrace();

        }

    }

    private List<Map<String, Object>> getUserWhiteList() {

        // 사용자 화이트리스트 테이블에 제시된 사용자들 추가
        return new ArrayList<>() {{

            add(Map.ofEntries(
                    Map.entry("name", "홍길동"),
                    Map.entry("regNo", "860824-1655068")
            ));
            add(Map.ofEntries(
                    Map.entry("name", "김둘리"),
                    Map.entry("regNo", "921108-1582816")
            ));
            add(Map.ofEntries(
                    Map.entry("name", "마징가"),
                    Map.entry("regNo", "880601-2455116")
            ));
            add(Map.ofEntries(
                    Map.entry("name", "베지터"),
                    Map.entry("regNo", "910411-1656116")
            ));
            add(Map.ofEntries(
                    Map.entry("name", "손오공"),
                    Map.entry("regNo", "820326-2715702")
            ));

        }};

    }

    private String ddlUserWhiteListTable() {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE IF NOT EXISTS SZS_USER_WHITELIST ( ");
        sb.append("  id integer, ");
        sb.append("  name VARCHAR(100), ");
        sb.append("  regNo VARCHAR(100), ");
        sb.append("  PRIMARY KEY (regNo) ");
        sb.append(" ); ");

        return sb.toString();

    }

    private String ddlUserTable() {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE IF NOT EXISTS SZS_USER ( ");
        sb.append("  userId VARCHAR(100), ");
        sb.append("  password VARCHAR(500), ");
        sb.append("  name VARCHAR(100), ");
        sb.append("  regNo VARCHAR(100), ");
        sb.append("  PRIMARY KEY (userId) ");
        sb.append(" ); ");

        return sb.toString();

    }

    private String ddlScrappingTable() {

        StringBuilder sb = new StringBuilder();

        sb.append("CREATE TABLE IF NOT EXISTS SZS_SCRAPPING ( ");
        sb.append("  이름 VARCHAR(100), ");
        sb.append("  주민등록번호 VARCHAR(100), ");
        sb.append("  총지급액 VARCHAR(200), ");
        sb.append("  산출세액 VARCHAR(200), ");
        sb.append("  보험료 VARCHAR(200), ");
        sb.append("  교육비 VARCHAR(200), ");
        sb.append("  기부금 VARCHAR(200), ");
        sb.append("  의료비 VARCHAR(200), ");
        sb.append("  퇴직연금 VARCHAR(200), ");
        sb.append("  PRIMARY KEY (주민등록번호) ");
        sb.append(" ); ");

        return sb.toString();

    }

    private String insertUserWhiteListTable() {

        List<Map<String, Object>> userWhiteList = this.getUserWhiteList();

        StringBuilder sb = new StringBuilder();

        sb.append(" INSERT INTO SZS_USER_WHITELIST(name, reg_no) VALUES ");

        for (Map<String, Object> userWhiteMap : userWhiteList) {
            String name = userWhiteMap.get("name").toString();
            String regNo = userWhiteMap.get("regNo").toString();
            sb.append(String.format("('%s', '%s'),", name, regNo));
        }

        String insertQuery = sb.toString();
        insertQuery = insertQuery.substring(0, insertQuery.length() - 1) + ";";

        System.out.println(insertQuery);

        return insertQuery;

    }

}
