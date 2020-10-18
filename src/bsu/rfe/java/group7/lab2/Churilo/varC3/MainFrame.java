package bsu.rfe.java.group7.lab2.Churilo.varC3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private JTextField textFieldResult;

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
        radioButtonsFormulas.add(button);
        hboxFormulaType.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);

        //Тип формулы
        hboxFormulaType.add(Box.createHorizontalGlue());
        addFormulaRadioButton("Формула 1", 1);
        addFormulaRadioButton("Формула 2", 2);
        radioButtonsFormulas.setSelected(radioButtonsFormulas.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());


        //Совмещение
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
