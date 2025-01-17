package doodleDiarySystem;

import java.sql.*;

public class DatabaseManager {
	Connection conn = null;
	Statement stmt = null;
	String url = "jdbc:mysql://localhost/dbstudy?serverTimezone = Asia/Seoul";
	String user = "root";
	String passwd = ""; // 개인 비밀번호 입력!!
	
	public DatabaseManager() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // 이 이름의 디비를 연결하겟다ㄴ
			conn = DriverManager.getConnection(url, user, passwd);
			stmt = conn.createStatement();
			System.out.println("MySQL 서버 연동 성공");
		} catch(Exception e) {
			System.out.println("MySQL 서버 연동 실패 > " + e.toString());
		}
	}

    // 데이터 삽입 메소드
    public void insertDiary(String date, String weather, String textContent, String imagePath) throws SQLException {
        String sql = "INSERT INTO diary (date, weather, text_content, image_path) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, date);
            stmt.setString(2, weather);
            stmt.setString(3, textContent);
            stmt.setString(4, imagePath);
            stmt.executeUpdate();
        }
    }

    // DB 연결 닫기
    public void close() throws SQLException {
        if (conn != null) conn.close();
    }
}
