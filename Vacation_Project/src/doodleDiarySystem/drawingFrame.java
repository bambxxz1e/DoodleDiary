package doodleDiarySystem;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class drawingFrame extends JPanel {
    boolean isUsePen = false;
    boolean isUseEraser = false;
    boolean isUseStemp = false;
    Point lastPoint = null;

    public drawingFrame() {
        setSize(700, 500);
        setVisible(true);

        JPanel topPanel = new JPanel();
        add(topPanel, BorderLayout.NORTH);

        JPanel toolPanel = new JPanel();
        toolPanel.setLayout(new BoxLayout(toolPanel, BoxLayout.Y_AXIS));
        toolPanel.setBackground(Color.decode("#ffffa8"));

        DrawingPanel drawingPanel = new DrawingPanel();
        drawingPanel.setBackground(Color.WHITE);
        add(drawingPanel, BorderLayout.CENTER);

        String[] tools = new String[] { "pen", "eraser", "stemp", "color", "Del" };
        for (int i = 0; i < tools.length; i++) {
            JButton toolButton = new JButton(tools[i]);
            switch (i) {
            case 0:
                toolButton.setText("✐");
                toolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isUsePen = true;
                        isUseEraser = false;
                        isUseStemp = false;
                        drawingPanel.setFocusable(true);
                    }
                });
                break;
            case 1:
                toolButton.setText("▧");
                toolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isUsePen = false;
                        isUseEraser = true;
                        isUseStemp = false;
                        drawingPanel.setFocusable(true);
                    }
                });
                break;
            case 2:
                toolButton.setText("😊");
                toolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isUsePen = false;
                        isUseEraser = false;
                        isUseStemp = true;
                        drawingPanel.setFocusable(true);
                    }
                });
                break;
            case 3:
                toolButton.setText("");
                toolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        toolButton.setBackground(drawingPanel.setColor());
                    }
                });
                break;
            case 4:
                toolButton.setText("⦸");
                toolButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        drawingPanel.DelDrawing();
                    }
                });
                break;
            }
            if (toolButton.getText() == "") {
                toolButton.setBackground(Color.BLACK);
            } else {
                toolButton.setBackground(Color.decode("#ffffa8"));
                toolButton.setForeground(Color.decode("#d63600"));
            }
            toolButton.setMaximumSize(new Dimension(100, 60));
            toolButton.setFont(new Font("맑은고딕", Font.PLAIN, 40));
            toolPanel.add(toolButton);
        }

        add(drawingPanel, BorderLayout.CENTER);
        add(toolPanel, BorderLayout.EAST);
        
    }

    class DrawingPanel extends JPanel {
        BufferedImage currentImage;
        Graphics2D g2d;
        boolean isDrawing = false;
        Color currentColor;
        int currentColorNum = 7;

        public DrawingPanel() {
            setPreferredSize(new Dimension(500, 500));

            setFocusable(true); // 키 이벤트를 받기 위해 필요
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (isUseStemp) {
                        Graphics g = getGraphics();
                        g.setFont(new Font("맑은고딕", Font.PLAIN, 50));
                        g.setColor(currentColor);  // 스템프 색상 현재 색으로 설정
                        g.drawString(RandomStemp(), e.getX(), e.getY());
                        g2d.setColor(currentColor);  // 색상 설정
                    } else if (isUsePen) {
                        lastPoint = e.getPoint();
                    }
                }
            });

            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    Graphics g = getGraphics();
                    Graphics2D g2d = (Graphics2D) g;
                    if (isUsePen) {
                        g.setColor(currentColor);
                        g2d.setStroke(new BasicStroke(5)); // 펜 두께
                    } else if (isUseEraser) {
                        g.setColor(Color.white);
                        g2d.setStroke(new BasicStroke(20)); // 지우개 크기 고정 20
                    }

                    if (lastPoint != null && !isUseStemp) {
                        g.drawLine(lastPoint.x, lastPoint.y, e.getX(), e.getY());
                    }
                    lastPoint = e.getPoint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (currentImage == null) {
                currentImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                g2d = currentImage.createGraphics();
                g2d.setColor(currentColor);
                g2d.fillRect(0, 0, getWidth(), getHeight());
                g2d.dispose();
            }
            g.drawImage(currentImage, 0, 0, this);
        }

        public void DelDrawing() {
            repaint();
        }

        Color setColor() {
            Color[] colors = new Color[] { Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE,
                    Color.MAGENTA, Color.BLACK };
            if (currentColorNum != 7) {
                currentColorNum++;
            } else {
                currentColorNum = 0;
            }
            currentColor = colors[currentColorNum]; 
            return currentColor;
        }

        String RandomStemp() {
            String[] arr = new String[] { "😊", "❤️", "⛦", "✨", "🌸" };
            return arr[(int) (Math.random() * 5)];
        }
    }
}