package com.ohgiraffers.test01;

import com.ohgiraffers.model.dto.EmployeeDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {
        //-- 모든 행 모든 컬럼 조회
        //-- EMPLOYEE테이블에서 모든 정보 조회


        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/employee-query.xml"));

            String query = prop.getProperty("selectEmployee");
            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            while (rset.next()) {
                System.out.println(rset.getString("EMP_ID")+" "+rset.getString("EMP_NAME")+" "+
                                rset.getString("EMP_NO") +" "+ rset.getString("EMAIL")+" "+
                                rset.getString("PHONE") +" "+ rset.getString("DEPT_CODE") +" "+
                                rset.getString("JOB_CODE") +" "+ rset.getString("SAL_LEVEL") +" "+
                                rset.getInt("SALARY") +" "+ rset.getDouble("BONUS") +" "+
                                rset.getString("MANAGER_ID") +" "+ rset.getDate("HIRE_DATE") +" "+
                                rset.getDate("ENT_DATE") +" "+ rset.getString("ENT_YN")
                        );
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(con);
            close(rset);
            close(pstmt);
        }

    }
}

