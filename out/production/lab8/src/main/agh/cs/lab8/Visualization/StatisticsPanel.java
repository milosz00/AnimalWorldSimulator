package agh.cs.lab8.Visualization;

import agh.cs.lab8.AnimalWorld.GrassField;
import agh.cs.lab8.Classes.Animal;
import agh.cs.lab8.Simulation.MapSimulation;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


public class StatisticsPanel extends JPanel {



    MapSimulation simulation;
    GrassField map;

    JLabel avgEnergy;
    JLabel avgLifeLength;
    JLabel avgNumOfChildren;
    JLabel countLifeAnimals;
    JLabel countGrass;
    JLabel dominantGen;
    JLabel chosenAnimalInformation;
    JLabel chosenAnimalInformationTitle;

    JButton writeToFile;

    Animal chosenAnimal;
    String informationToWrite="";



    public StatisticsPanel(GrassField map, MapSimulation simulation){
        this.simulation = simulation;
        this.map = map;

        countLifeAnimals = new JLabel("Liczba żyjących zwierząt: " + map.getAnimalList().size());
        countLifeAnimals.setBounds(20,20,200,30);

        countGrass = new JLabel("Liczba trawy na mapie: " + map.getGrassList().size());
        countGrass.setBounds(20,60,200,30);

        avgEnergy = new JLabel("Srednia energia zwierząt: " + map.getAvgEnergy());
        avgEnergy.setBounds(20,100,200,30);

        avgLifeLength = new JLabel("Srednia długość życia: " + map.getAvgLifeLength());
        avgLifeLength.setBounds(20,140,200,30);

        avgNumOfChildren = new JLabel("Srednia ilość dzieci zwierząt: " + map.getAvgNumOfChildren());
        avgNumOfChildren.setBounds(20,180,200,30);

        dominantGen = new JLabel("Dominujący gen: " + map.getDominantGen());
        dominantGen.setBounds(20,220,200,30);

        writeToFile = new JButton("Write to File");
        writeToFile.setBounds(20,250,200,30);
        writeToFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    informationToWrite+=countLifeAnimals.getText() + '\n';
                    informationToWrite+=countGrass.getText() + '\n';
                    informationToWrite+="--------------------------\nWartości średnie z n-epok:\n---------------------------\n";
                    informationToWrite+=avgEnergy.getText() + '\n';
                    informationToWrite+=avgLifeLength.getText() + '\n';
                    informationToWrite+=avgNumOfChildren.getText() + '\n';
                    informationToWrite+=dominantGen.getText() + '\n';


                    new WriteToFile(informationToWrite,map.getId());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });

        chosenAnimalInformationTitle = new JLabel("--------------STATYSTYKI WYBRANEGO ZWIERZECIA--------------");
        chosenAnimalInformationTitle.setBounds(160,310,400,30);
        chosenAnimalInformation = new JLabel("Nie wybrano");
        chosenAnimalInformation.setBounds(200,350,350,30);
        Border blackLine = BorderFactory.createLineBorder(Color.black);
        this.setBorder(blackLine);

        setLayout(null);
        add(avgEnergy);add(avgLifeLength);add(avgNumOfChildren);add(countLifeAnimals);add(countGrass);
        add(dominantGen);add(chosenAnimalInformation);add(chosenAnimalInformationTitle);add(writeToFile);
    }


    public void changeChosenAnimalInformation() {

        if(chosenAnimal!=null) {
            if (!chosenAnimal.isDead()) {
                this.chosenAnimalInformation.setText("Pozycja: " + this.chosenAnimal.getPosition() + ",\n" +
                        "Energia: " + this.chosenAnimal.getEnergy() + ",\n" +
                        "Wiek: " + this.chosenAnimal.getAge() + ",\n" +
                        "Ilość dzieci: " + this.chosenAnimal.getHowManyChildren());

            }
            else{
                this.chosenAnimalInformation.setText(
                        "Wiek: " + this.chosenAnimal.getAge() + ", " +
                        "Ilość dzieci: " + this.chosenAnimal.getHowManyChildren() +
                        ", ZMARL");
            }
        }
    }

    public void setChosenAnimal(Animal chosenAnimal) {
        this.chosenAnimal = chosenAnimal;
    }
}
