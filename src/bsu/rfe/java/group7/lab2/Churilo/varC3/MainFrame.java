package bsu.rfe.java.group7.lab2.Churilo.varC3;

import net.sf.image4j.codec.bmp.BMPDecoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;

    private double mem1;
    private double mem2;
    private double mem3;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JLabel labelForMem1;
    private JLabel labelForMem2;
    private JLabel labelForMem3;

    private JTextField textFieldResult;

    private JLabel labelImg;
    private Icon icon;

    private ButtonGroup radioButtonsFormulas = new ButtonGroup();
    private ButtonGroup radioButtonsVariable = new ButtonGroup();

    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariableType = Box.createHorizontalBox();

    private int formulaId = 1;
    private int variableId = 1;

    public Double calculate1 (Double x, Double y, Double z){
        return (Math.sin(Math.PI * y * y) + Math.log(y * y)) / (Math.sin(Math.PI * z * z) + Math.sin(x) + Math.log(z * z) + x * x + Math.exp(Math.cos(z * x)));
    }

    public Double calculate2 (Double x, Double y, Double z){
        return Math.sqrt(Math.sqrt(Math.cos(Math.exp(y)) + Math.exp(y * y) + Math.sqrt(1 / x))) / Math.pow(Math.cos(Math.PI * z * z * z) + Math.log(Math.pow(1 + z, 2)), Math.sin(y));
    }

    private void addFormulaRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.formulaId = formulaId;
                reloadFormulaImage();
            }
        });
        radioButtonsFormulas.add(button);
        hboxFormulaType.add(button);
    }

    private void addVariableRadioButton(String buttonName, final int variableId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.variableId = variableId;
            }
        });
        radioButtonsVariable.add(button);
        hboxVariableType.add(button);
    }

    private void reloadMemoryPanel(){
        labelForMem1.setText("" + mem1);
        labelForMem2.setText("" + mem2);
        labelForMem3.setText("" + mem3);
    }

    private void reloadFormulaImage(){
        icon = new ImageIcon("Formula" + formulaId + ".png");
        labelImg.setIcon(icon);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        Image img = kit.getImage("icon.png");
        setIconImage(img);

        //Тип формулы
        hboxFormulaType.add(Box.createHorizontalGlue());
        addFormulaRadioButton("Формула 1", 1);
        addFormulaRadioButton("Формула 2", 2);
        radioButtonsFormulas.setSelected(radioButtonsFormulas.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        //Картинка формулы
        labelImg = new JLabel();
        icon = new ImageIcon("Formula1.png");
        labelImg.setIcon(icon);

        Box hboxFormulaImg = Box.createHorizontalBox();
        hboxFormulaImg.add(labelImg);


        //Поля аргументов
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z: ");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxArgumets = Box.createHorizontalBox();
        hboxArgumets.add(Box.createHorizontalGlue());
        hboxArgumets.add(labelForX);
        hboxArgumets.add(Box.createHorizontalStrut(10));
        hboxArgumets.add(textFieldX);
        hboxArgumets.add(Box.createHorizontalStrut(30));
        hboxArgumets.add(labelForY);
        hboxArgumets.add(Box.createHorizontalStrut(10));
        hboxArgumets.add(textFieldY);
        hboxArgumets.add(Box.createHorizontalStrut(30));
        hboxArgumets.add(labelForZ);
        hboxArgumets.add(Box.createHorizontalStrut(10));
        hboxArgumets.add(textFieldZ);
        hboxArgumets.add(Box.createHorizontalGlue());

        //Вывод результата
        JLabel labelForResult = new JLabel("Результат: ");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());

        //Кнопки "Вычислить" и "Очистить"
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());
                    Double result;
                    if (formulaId == 1) {
                        if (y == 0)
                            throw new ArithmeticException("Y не должен быть равен 0");
                        if (z == 0)
                            throw new ArithmeticException("Z не должен быть равен 0");
                        result = calculate1(x, y, z);
                        mem1 = x;
                        mem2 = y;
                        mem3 = z;
                        reloadMemoryPanel();
                    }
                    else {
                        if (x <= 0)
                            throw new ArithmeticException("X должен быть больше 0");
                        if (z == -1)
                            throw new ArithmeticException("Z не должен быть равен -1");
                        result = calculate2(x, y, z);
                        mem1 = x;
                        mem2 = y;
                        mem3 = z;
                        reloadMemoryPanel();
                    }
                    textFieldResult.setText(result.toString());
                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(MainFrame.this,"Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа", JOptionPane.WARNING_MESSAGE);
                }
                catch (ArithmeticException ex){
                    JOptionPane.showMessageDialog(MainFrame.this, ex.toString(), "Выход из ОДЗ", JOptionPane.WARNING_MESSAGE);
                }
            }


        });
        JButton buttonReset = new JButton("Очистить");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtons1 = Box.createHorizontalBox();
        hboxButtons1.add(Box.createHorizontalGlue());
        hboxButtons1.add(buttonCalc);
        hboxButtons1.add(Box.createHorizontalStrut(100));
        hboxButtons1.add(buttonReset);
        hboxButtons1.add(Box.createHorizontalGlue());

        //Выбор переменной
        hboxVariableType.add(Box.createHorizontalGlue());
        addVariableRadioButton("Переменная 1", 1);
        hboxVariableType.add(Box.createHorizontalStrut(10));
        addVariableRadioButton("Переменная 2", 2);
        hboxVariableType.add(Box.createHorizontalStrut(10));
        addVariableRadioButton("Переменная 3", 3);
        radioButtonsVariable.setSelected(radioButtonsVariable.getElements().nextElement().getModel(), true);
        hboxVariableType.add(Box.createHorizontalGlue());

        //Кнопки МС и М+
        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (variableId == 1) {
                    mem1 = 0;
                    textFieldX.setText("0");
                }
                else if (variableId == 2) {
                    mem2 = 0;
                    textFieldY.setText("0");
                }
                else {
                    mem3 = 0;
                    textFieldZ.setText("0");
                }
                reloadMemoryPanel();
            }
        });
        JButton buttonMP = new JButton("M+");
        buttonMP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (variableId == 1)
                    textFieldResult.setText("" + (Double.parseDouble(textFieldResult.getText()) + mem1));
                else if (variableId == 2)
                    textFieldResult.setText("" + (Double.parseDouble(textFieldResult.getText()) + mem2));
                else
                    textFieldResult.setText("" + (Double.parseDouble(textFieldResult.getText()) + mem3));
            }
        });
        Box hboxVariablesButtons = Box.createHorizontalBox();
        hboxVariablesButtons.add(Box.createHorizontalGlue());
        hboxVariablesButtons.add(buttonMC);
        hboxVariablesButtons.add(Box.createHorizontalStrut(100));
        hboxVariablesButtons.add(buttonMP);
        hboxVariablesButtons.add(Box.createHorizontalGlue());

        //Панел памяти
        Box hboxMemory = Box.createHorizontalBox();
        labelForMem1 = new JLabel("" + mem1);
        labelForMem2 = new JLabel("" + mem2);
        labelForMem3 = new JLabel("" + mem3);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.add(new JLabel("Память 1:"));
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(labelForMem1);
        hboxMemory.add(Box.createHorizontalStrut(30));
        hboxMemory.add(new JLabel("Память 2:"));
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(labelForMem2);
        hboxMemory.add(Box.createHorizontalStrut(30));
        hboxMemory.add(new JLabel("Память 3:"));
        hboxMemory.add(Box.createHorizontalStrut(10));
        hboxMemory.add(labelForMem3);
        hboxMemory.add(Box.createHorizontalGlue());
        hboxMemory.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Совмещение
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxFormulaImg);
        contentBox.add(hboxArgumets);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons1);
        contentBox.add(hboxVariableType);
        contentBox.add(hboxVariablesButtons);
        contentBox.add(hboxMemory);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
