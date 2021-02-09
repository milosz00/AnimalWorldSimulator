package agh.cs.lab8.Visualization;

import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Classes.Vector2d;
import agh.cs.lab8.Visualization.StatisticsPanel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyMouseListener extends MouseAdapter {

    Map<Vector2d,LinkedList<Animal>> animals;
    List<Ellipse2D> ellipses;
    JFrame frame;
    StatisticsPanel statisticsPanel;

    public MyMouseListener(Map<Vector2d, LinkedList<Animal>> animals, JFrame frame , List<Ellipse2D> ellipses,StatisticsPanel statisticsPanel){
        this.animals = animals;
        this.frame = frame;
        this.ellipses = ellipses;
        this.statisticsPanel = statisticsPanel;
    }

    @Override
    public void mousePressed(MouseEvent e){

        for (Ellipse2D ellipse : ellipses) {
            if(ellipse.contains(e.getPoint())){
                LinkedList<Animal> list = animals.get(new Vector2d((int)ellipse.getX(),(int)ellipse.getY()));

                    String message = createMessageAboutChosenAnimal(list);

                    JOptionPane.showMessageDialog(frame,message);

                    statisticsPanel.setChosenAnimal(list.get(0));
                    statisticsPanel.changeChosenAnimalInformation();
                    break;

            }
        }
    }

    private String createMessageAboutChosenAnimal(LinkedList<Animal> list){

        String message = "Pozycja: " + list.get(0).getPosition() +'\n' +
                "Energia: " + list.get(0).getEnergy() + '\n' +
                "Geny: " + list.get(0).getGenes() +'\n' +
                "Wiek: " + list.get(0).getAge() + '\n' +
                "Ilość dzieci: " + list.get(0).getHowManyChildren() + '\n';


        return message;
    }
}
