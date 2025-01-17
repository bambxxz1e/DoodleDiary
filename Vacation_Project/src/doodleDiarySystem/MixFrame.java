package doodleDiarySystem;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.*;
import javax.swing.event.*;

public class MixFrame extends JFrame {
    JPanel topPanel = new JPanel(); // 날짜, 날씨 선택, exit 버튼 위치
    JPanel centerPanel = new JPanel(new BorderLayout()); // 그림판, 일기 쓰는 화면 위치
    JPanel bottomPanel = new JPanel(); // 저장 버튼
    JPanel northPanel = new JPanel(); // 그림판
    JPanel southPanel = new JPanel(); // 일기장
    
    JLabel date; // 오늘 날짜
    JButton exitButton = new JButton("EXIT");
    JButton saveButton = new JButton("SAVE");
    
    JMenuBar menuBar = new JMenuBar();
	JMenu menu = new JMenu("날씨");

	JMenuItem sunny = new JMenuItem("☀️");
	JMenuItem cloudysun = new JMenuItem("⛅");
	JMenuItem cloudy = new JMenuItem("☁️");
	JMenuItem rainy = new JMenuItem("🌧️");
	JMenuItem windy = new JMenuItem("💨");
	JMenuItem snowy = new JMenuItem("❄️");
	
    
	public MixFrame(String choiceDate) {
		setTitle("DoodleDiary");
		setSize(650, 850);
		setLayout(new BorderLayout()); // 전체 레이아웃
		setResizable(false); // 사이즈 고정
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        date = new JLabel(choiceDate + "일");
        
        // 패널 크기
        topPanel.setPreferredSize(new Dimension(650, 45));
        centerPanel.setPreferredSize(new Dimension(650, 780));
        bottomPanel.setPreferredSize(new Dimension(650, 25));
        
        // 베이스 패널에 집어 넣기
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        
        // 센터 패널에 집어 넣기
        centerPanel.add(northPanel, BorderLayout.NORTH);
        centerPanel.add(southPanel, BorderLayout.SOUTH);
        
        // 날짜, 날씨, save, exit 버튼 정렬 위해 borderlayout 사용
        topPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        menu.add(sunny); // 날씨 메뉴 집어넣기
		menu.add(cloudysun);
		menu.add(cloudy);
		menu.add(rainy);
		menu.add(windy);
		menu.add(snowy);
		
		Weather weatherListener = new Weather(menu); // 메뉴 참조 전달
        sunny.addActionListener(weatherListener);
        cloudysun.addActionListener(weatherListener);
        cloudy.addActionListener(weatherListener);
        rainy.addActionListener(weatherListener);
        windy.addActionListener(weatherListener);
        snowy.addActionListener(weatherListener);
		
		menuBar.add(menu);

        leftPanel.add(date);
        leftPanel.add(menuBar);
        rightPanel.add(saveButton);
        rightPanel.add(exitButton);
        
        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
        
        saveButton.setBackground(Color.DARK_GRAY);
        saveButton.setForeground(Color.WHITE);
        exitButton.setBackground(Color.RED);
        exitButton.setForeground(Color.WHITE);
        date.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        
        northPanel.setPreferredSize(new Dimension(650, 550));
        southPanel.setPreferredSize(new Dimension(650, 230));
        
        // 텍스트 입력창 설정
        southPanel.setLayout(new BorderLayout());
        textFrame tf = new textFrame();
        southPanel.setLayout(new BorderLayout());
        southPanel.add(tf, BorderLayout.CENTER);
        
        // 갱신
        southPanel.revalidate();
        southPanel.repaint();
        
        // exit 버튼 누르면 mixframe 창만 나가짐
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        setVisible(true);
	}
	
	private class Weather implements ActionListener {
		private JMenu menu; // 메뉴 참조 변수

	    // 생성자 : 변경할 JMenu를 받아옴
	    public Weather(JMenu menu) {
	        this.menu = menu;
	    }

	    @Override
	    public void actionPerformed(ActionEvent e) {
	        JMenuItem clickedItem = (JMenuItem) e.getSource(); // 클릭된 아이템
	        menu.setText(clickedItem.getText()); // 메뉴 이름을 클릭된 아이템의 텍스트로 변경
	    }
	}
}