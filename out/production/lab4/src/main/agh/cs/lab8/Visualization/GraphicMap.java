package agh.cs.lab8.Visualization;

import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Classes.Grass;
import agh.cs.lab8.Classes.Vector2d;
import agh.cs.lab8.Simulation.Engine;
import agh.cs.lab8.Simulation.MapSimulation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.*;
import java.util.List;

public class GraphicMap extends JPanel implements ActionListener {

    MapSimulation simulation;
    GrassField map;
    Engine engine;
    StatisticsPanel statisticsPanel;

    List<Ellipse2D> ellipseList = new ArrayList<Ellipse2D>();
    Map<Vector2d, LinkedList<Animal>> animals = new LinkedHashMap<>();


    public GraphicMap(MapSimulation simulation,GrassField map,StatisticsPanel statisticsPanel,Engine engine){
        this.simulation = simulation;
        this.map = map;
        this.statisticsPanel = statisticsPanel;
        this.engine = engine;
        addMouseListener(new MyMouseListener(animals,simulation,ellipseList,statisticsPanel));
    }

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2 = (Graphics2D) g;
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        super.paintComponent(g);
        this.setBorder(blackLine);

        int width = this.getWidth();
        int height = this.getHeight();

        int scaleWidth = Math.round(width/map.getWidth());
        int scaleHeight = (height/map.getHeight());

        g2.setColor(new Color(153,255,153));
        g2.fillRect(0,0,width,height);


        g2.setColor(new Color(0,102,0));
        g2.fillRect(map.getLowerLeftJungle().x*scaleWidth
                ,map.getLowerLeftJungle().y*scaleHeight
                ,map.getWidthJungle()*scaleWidth,
                map.getHeightJungle()*scaleHeight);

        //rysujemy trawe
        for(Grass grass: map.getGrassList()){
            g2.setColor(grass.toColor());
            int x = map.toRoundPosition(grass.getPosition()).x*scaleWidth;
            int y = map.toRoundPosition(grass.getPosition()).y*scaleHeight;
            //g2.fillRect(x,y,scaleWidth,scaleHeight);
            Rectangle2D rectangle = new Rectangle2D.Double(x,y,scaleWidth,scaleHeight);
            g2.fill(rectangle);
        }

        //rysujemy zwierzątka

        for (Animal animal : map.getAnimalList()) {
            g2.setColor(animal.toColor());
            int x = map.toRoundPosition(animal.getPosition()).x * scaleWidth;
            int y = map.toRoundPosition(animal.getPosition()).y * scaleHeight;
            Ellipse2D ellipse = new Ellipse2D.Double(x, y, scaleWidth, scaleHeight);
            g2.fill(ellipse);
            switch (map.getId()) {
                case 1:
                    if(!simulation.isStartStop()) {
                        addAnimal(animal, new Vector2d(x,y));
                        ellipseList.add(ellipse);
                    }
                    break;
                case 2:
                    if(!simulation.isStartStop1()){
                        addAnimal(animal, new Vector2d(x,y));
                        ellipseList.add(ellipse);
                    }
                    break;
            }
        }



    }


    @Override
    public void actionPerformed(ActionEvent e) {

        this.repaint();

        updateStatistic();
        engine.daySimulation();

        animals.clear();
        ellipseList.clear();

    }

    public void addAnimal(Animal animal , Vector2d position){
        LinkedList<Animal> list = animals.get(position);
        if(list == null){
            LinkedList<Animal> tmp = new LinkedList<>();
            tmp.add(animal);
            animals.put(position,tmp);
        }
        else{
            list.add(animal);
        }
    }

    private void updateStatistic(){

        statisticsPanel.avgEnergy.setText("Srednia energia zwierząt: " + map.getAvgEnergy());
        statisticsPanel.avgLifeLength.setText("Srednia długość życia: " + map.getAvgLifeLength());
        statisticsPanel.avgNumOfChildren.setText("Srednia ilość dzieci zwierząt: " + map.getAvgNumOfChildren());
        statisticsPanel.countLifeAnimals.setText("Liczba żyjących zwierząt: " + map.getAnimalList().size());
        statisticsPanel.countGrass.setText("Liczba trawy na mapie: " + map.getGrassList().size());
        statisticsPanel.dominantGen.setText("Dominujący gen: " + map.getDominantGen());
        statisticsPanel.changeChosenAnimalInformation();

    }

}

