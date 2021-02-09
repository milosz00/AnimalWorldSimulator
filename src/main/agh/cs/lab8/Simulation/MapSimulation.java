package agh.cs.lab8.Simulation;

import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Visualization.GraphicMap;
import agh.cs.lab8.Visualization.StatisticsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapSimulation extends JFrame  {

    private GrassField map1;
    private GrassField map2;
    private int numJungleAnimal;
    private int numStepAnimal;

    private GraphicMap graphicMap1;
    private GraphicMap graphicMap2;

    private StatisticsPanel statisticsPanel1;
    private StatisticsPanel statisticsPanel2;

    private boolean isStop1 = false;
    private Timer timer1;
    JButton startStopButton1;

    private Timer timer2;
    private boolean isStop2 = false;
    JButton startStopButton2;

    private int delay;


    public MapSimulation(GrassField map1,GrassField map2,int numJungleAnimal,int numStepAnimal,int delay){
        this.map1 = map1;
        this.map2 = map2;
        this.numJungleAnimal = numJungleAnimal;
        this.numStepAnimal = numStepAnimal;
        this.delay = delay;



        setLayout(null);
        setSize(1400,1000);
        setLocationRelativeTo(null);



        setName("Map Simulation 2020");
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setVisible(true);

        statisticsPanel1 = new StatisticsPanel(map1,this);
        statisticsPanel1.setBounds(0,520,this.getWidth()/2-20,this.getHeight()/2-60);
        statisticsPanel2 = new StatisticsPanel(map2,this);
        statisticsPanel2.setBounds(685,520,this.getWidth()/2-20,this.getHeight()/2-60);


        graphicMap1 = new GraphicMap(this,map1,statisticsPanel1,new Engine(map1));
        graphicMap1.setBounds(0,0,this.getWidth()/2-20,this.getHeight()/2-20);
        graphicMap2 = new GraphicMap(this,map2,statisticsPanel2,new Engine(map2));
        graphicMap2.setBounds(685,0,this.getWidth()/2-20,this.getHeight()/2-20);


        timer1 = new Timer(delay,graphicMap1);
        timer2= new Timer(delay,graphicMap2);

        startStopButton1 = new JButton("Start/Stop Animation1");
        startStopButton1.setBounds(250,490,200,20);
        startStopButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStop1){
                    timer1.start();
                }
                else
                    timer1.stop();
                isStop1 = !isStop1;
            }
        });

        startStopButton2 = new JButton("Start/Stop Animation2");
        startStopButton2.setBounds(950,490,200,20);
        startStopButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isStop2){
                    timer2.start();
                }
                else
                    timer2.stop();
                isStop2 = !isStop2;
            }
        });


        add(graphicMap1);
        add(graphicMap2);
        add(statisticsPanel1);
        add(statisticsPanel2);
        add(startStopButton1);
        add(startStopButton2);

    }


    public void startSimulation(){
        for(int i=0;i<numJungleAnimal;i++){
            map1.placeAnimalInJungle();
        }
        for(int i=0;i<numStepAnimal;i++){
            map1.placeAnimalInStep();
        }

        for(int i=0;i<numJungleAnimal;i++){
            map2.placeAnimalInJungle();
        }
        for(int i=0;i<numStepAnimal;i++){
            map2.placeAnimalInStep();
        }

        timer1.start();
        timer2.start();
    }

    public boolean isStop1() {
        return isStop1;
    }

    public boolean isStop2() {
        return isStop2;
    }
}
