package game_window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingWindow extends JFrame {
    private static final String BTN_START = "Start new game";
    private static final String LABEL_CHOICE_MODE = "Выберите режим игры";
    private static final String BTN_HUMAN_VERSUS_AI = "Человек против компьютера";
    private static final String BTN_HUMAN_VERSUS_HUMAN = "Человек против человека";
    private static final String SIZE_PREFIX = "Установленный размер поля: ";
    private static final String WIN_LENGTH_PREFIX = "Установленная длина: ";
    private static final String LABEL_CHOICE_SIZE = "Выберите размеры поля";
    private static final String LABEL_CHOICE_WIN_LENGTH = "Выберите длину для победы";
    private static final int MODE_HVA = 0;
    private static final int MODE_HVH = 1;

    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 10;

    private static final int WIDTH = 230;
    private static final int HEIGHT = 350;

    private GameWindow gameWindow;
    private JButton btnStart;
    private JRadioButton humanVHuman, humanVAI;
    private JLabel labelCurSize, labelWinLength;
    private JSlider sizeSlider, winSlider;

    SettingWindow(GameWindow gameWindow){
        this.gameWindow = gameWindow;

        int centerGameWindowX = gameWindow.getX() + gameWindow.getWidth()/2;
        int centerGameWindowY = gameWindow.getY() + gameWindow.getHeight()/2;
        setLocation(centerGameWindowX - WIDTH/2, centerGameWindowY - HEIGHT/2);
        setSize(WIDTH, HEIGHT);

        add(createMainPanel());
        add(createButtonStart(), BorderLayout.SOUTH);
    }

    private Component createMainPanel(){
        JPanel panel = new JPanel(new GridLayout(3, 1));

        panel.add(createChoiceModePanel());
        panel.add(createChoiceSizePanel());
        panel.add(createChoiceWinLengthPanel());
        return panel;
    }

    private Component createButtonStart(){
        btnStart = new JButton(BTN_START);
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                startGame();
            }
        });
        return btnStart;
    }

    private void startGame(){
        int mode;
        if (humanVAI.isSelected()){
            mode = MODE_HVA;
        } else if (humanVHuman.isSelected()){
            mode = MODE_HVH;
        } else {
            throw new RuntimeException("Unknown game mode");
        }
        int size = sizeSlider.getValue();
        int winLength = winSlider.getValue();
        gameWindow.startNewGame(mode, size, size, winLength);
    }

    private Component createChoiceModePanel(){
        JPanel panel = new JPanel(new GridLayout(3, 1));
        Label title = new Label(LABEL_CHOICE_MODE);
        ButtonGroup buttonGroup = new ButtonGroup();
        humanVAI = new JRadioButton(BTN_HUMAN_VERSUS_AI);
        humanVHuman = new JRadioButton(BTN_HUMAN_VERSUS_HUMAN);
        buttonGroup.add(humanVAI);
        buttonGroup.add(humanVHuman);
        humanVAI.setSelected(true);

        panel.add(title);
        panel.add(humanVAI);
        panel.add(humanVHuman);

        return panel;
    }

    private Component createChoiceSizePanel(){
        JPanel panel = new JPanel(new GridLayout(3, 1));
        Label title = new Label(LABEL_CHOICE_SIZE);
        labelCurSize = new JLabel(SIZE_PREFIX + MIN_SIZE);
        sizeSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        sizeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int curSize = sizeSlider.getValue();
                labelCurSize.setText(SIZE_PREFIX + curSize);
                winSlider.setMaximum(curSize);
            }
        });

        panel.add(title);
        panel.add(labelCurSize);
        panel.add(sizeSlider);
        return panel;
    }

    private Component createChoiceWinLengthPanel(){
        JPanel panel = new JPanel(new GridLayout(3, 1));
        Label title = new Label(LABEL_CHOICE_WIN_LENGTH);
        labelWinLength = new JLabel(WIN_LENGTH_PREFIX + MIN_SIZE);
        winSlider = new JSlider(MIN_SIZE, MAX_SIZE, MIN_SIZE);
        winSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWinLength.setText(SIZE_PREFIX + winSlider.getValue());
            }
        });

        panel.add(title);
        panel.add(labelWinLength);
        panel.add(winSlider);
        return panel;
    }
}
