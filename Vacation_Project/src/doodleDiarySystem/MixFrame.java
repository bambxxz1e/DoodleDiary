package doodleDiarySystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MixFrame extends JFrame {
	JPanel basePane = new JPanel(new BorderLayout()); // 가장 큰 메인 화면
    JPanel topPane = new JPanel(); // 날짜, 날씨 선택, exit 버튼 위치
    JPanel centerPane = new JPanel(new BorderLayout()); // 그림판, 일기 쓰는 화면 위치
    JPanel bottomPane = new JPanel(); // 저장 버튼
    JPanel northPane = new JPanel(); // 그림판
    JPanel southPane = new JPanel(); // 일기장
    
    JLabel date; // 오늘 날짜
    
    JButton exitButton = new JButton("EXIT");
    
	public MixFrame(String choiceDate) {
		setTitle("DoodleDiary");
		setSize(650, 850);
		setResizable(false); // 사이즈 고정
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
        requestFocusInWindow();
        
        date = new JLabel(choiceDate + "일");
        
        // 패널 크기
        basePane.setPreferredSize(new Dimension(650, 850));
        centerPane.setPreferredSize(new Dimension(650, 25));
        topPane.setPreferredSize(new Dimension(650, 800));
        bottomPane.setPreferredSize(new Dimension(650, 25));
        
        // 베이스 패널에 집어 넣기
        basePane.add(topPane, BorderLayout.NORTH);
        basePane.add(centerPane, BorderLayout.CENTER);
        basePane.add(bottomPane, BorderLayout.SOUTH);
        
        setContentPane(basePane); 
        
        // 센터 패널에 집어 넣기
        centerPane.add(northPane, BorderLayout.NORTH);
        centerPane.add(southPane, BorderLayout.SOUTH);
        
        // 날짜, 날씨, exit 버튼 (topPane) 그 정렬을 못햇다..
        topPane.setLayout(new FlowLayout());
        topPane.add(date);
        topPane.add(exitButton);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        date.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        add(topPane);
        
        northPane.setPreferredSize(new Dimension(650, 400));
        southPane.setPreferredSize(new Dimension(650, 400));
        
        // 지금 여기 실행 안됨ㅠㅠ
        textFrame tf = new textFrame();
        southPane.add(tf);
        add(southPane);
        
        // exit 버튼 누르면 mixframe 창만 나가짐
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
	}
}