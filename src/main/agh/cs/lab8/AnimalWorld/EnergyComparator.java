package agh.cs.lab8.AnimalWorld;

import agh.cs.lab8.Classes.Animal;

import java.util.Comparator;

public class EnergyComparator implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {

        if(o1 == o2) return 0;

        int energy1 = ((Animal) o1).getEnergy();
        int energy2 = ((Animal) o2).getEnergy();

        if(energy1 < energy2) return 1;
        else if(energy1 > energy2) return -1;
        else return 0;
    }
}
