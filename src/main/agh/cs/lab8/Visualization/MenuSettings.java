package agh.cs.lab8.Visualization;

import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Simulation.MapSimulation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuSettings extends JFrame implements ActionListener {


    JLabel mapWidth;
    JLabel mapHeight;
    JLabel jungleWidth;
    JLabel jungleHeight;
    JLabel grassProfit;
    JLabel dayCost;
    JLabel startAnimalEnergy;
    JLabel numJungleAnimals;
    JLabel numStepAnimal;
    JLabel delay;

    JTextField textMapWidth;
    JTextField textMapHeight;
    JTextField textJungleWidth;
    JTextField textJungleHeight;
    JTextField textGrassProfit;
    JTextField textDayCost;
    JTextField textStartAnimalEnergy;
    JTextField textNumJungleAnimals;
    JTextField textNumStepAnimals;
    JTextField textDelay;

    JButton startButton;


    public MenuSettings(Integer[] parameters){
        setSize(500,500);
        setLocationRelativeTo(null);

        mapWidth = new JLabel("Map Width: ");
        mapWidth.setBounds(90,50,80,30);
        mapHeight = new JLabel("Map Height: ");
        mapHeight.setBounds(90,80,80,30);
        jungleWidth = new JLabel("Jungle Width: ");
        jungleWidth.setBounds(90,110,80,30);
        jungleHeight = new JLabel("Jungle Height: ");
        jungleHeight.setBounds(90,140,100,30);
        grassProfit = new JLabel("Profit after eat grass: ");
        grassProfit.setBounds(90,170,140,30);
        dayCost = new JLabel("Day cost: ");
        dayCost.setBounds(90,200,80,30);
        startAnimalEnergy = new JLabel("Animal energy on start: ");
        startAnimalEnergy.setBounds(90,230,140,30);
        numJungleAnimals = new JLabel("Animal at start in Jungle: ");
        numJungleAnimals.setBounds(90,260,150,30);
        numStepAnimal = new JLabel("Animal at start in Step: ");
        numStepAnimal.setBounds(90,290,140,30);
        delay = new JLabel("Delay (ms): ");
        delay.setBounds(90,320,80,30);

        textMapWidth = new JTextField();
        textMapWidth.setBounds(250,50,100,30);
        textMapHeight = new JTextField();
        textMapHeight.setBounds(250,80,100,30);
        textJungleWidth = new JTextField();
        textJungleWidth.setBounds(250,110,100,30);
        textJungleHeight = new JTextField();
        textJungleHeight.setBounds(250,140,100,30);
        textGrassProfit = new JTextField();
        textGrassProfit.setBounds(250,170,100,30);
        textDayCost = new JTextField();
        textDayCost.setBounds(250,200,100,30);
        textStartAnimalEnergy = new JTextField();
        textStartAnimalEnergy.setBounds(250,230,100,30);
        textNumJungleAnimals = new JTextField();
        textNumJungleAnimals.setBounds(250,260,100,30);
        textNumStepAnimals = new JTextField();
        textNumStepAnimals.setBounds(250,290,100,30);
        textDelay = new JTextField();
        textDelay.setBounds(250,320,100,30);


        textMapWidth.setText(parameters[0].toString());
        textMapHeight.setText(parameters[1].toString());
        textJungleWidth.setText(parameters[2].toString());
        textJungleHeight.setText(parameters[3].toString());
        textGrassProfit.setText(parameters[4].toString());
        textDayCost.setText(parameters[5].toString());
        textStartAnimalEnergy.setText(parameters[6].toString());
        textNumJungleAnimals.setText(parameters[7].toString());
        textNumStepAnimals.setText(parameters[8].toString());
        textDelay.setText(parameters[9].toString());

        startButton = new JButton("Start Simulation");
        startButton.setBounds(150,370,140,30);
        startButton.addActionListener(this);


        add(mapWidth);add(mapHeight);add(jungleWidth);add(jungleHeight);add(dayCost);add(startAnimalEnergy);add(grassProfit);add(delay);add(numJungleAnimals);add(numStepAnimal);
        add(textMapWidth);add(textMapHeight);add(textJungleHeight);add(textJungleWidth);add(textDayCost);add(textStartAnimalEnergy);add(textGrassProfit);
        add(textDelay);add(textNumJungleAnimals);add(textNumStepAnimals);
        add(startButton);
        setLayout(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GrassField map1 = new GrassField(Integer.parseInt(textMapWidth.getText()),
                                        Integer.parseInt(textMapHeight.getText()),
                                        Integer.parseInt(textJungleWidth.getText()),
                                        Integer.parseInt(textJungleHeight.getText()),
                                        Integer.parseInt(textGrassProfit.getText()),
                                        Integer.parseInt(textStartAnimalEnergy.getText()),
                                        Integer.parseInt(textDayCost.getText()),1);

        GrassField map2 = new GrassField(Integer.parseInt(textMapWidth.getText()),
                Integer.parseInt(textMapHeight.getText()),
                Integer.parseInt(textJungleWidth.getText()),
                Integer.parseInt(textJungleHeight.getText()),
                Integer.parseInt(textGrassProfit.getText()),
                Integer.parseInt(textStartAnimalEnergy.getText()),
                Integer.parseInt(textDayCost.getText()),2);

        MapSimulation simulation = new MapSimulation(map1,map2,Integer.parseInt(textNumJungleAnimals.getText())
                                                    ,Integer.parseInt(textNumStepAnimals.getText())
                                                    ,Integer.parseInt(textDelay.getText()));

        simulation.startSimulation();

    }


}
