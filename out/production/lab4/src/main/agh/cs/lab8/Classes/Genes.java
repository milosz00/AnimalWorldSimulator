package agh.cs.lab8.Classes;

import java.util.Arrays;
import java.util.Random;

public class Genes {
    private int[] genes = new int[32];
    private int [] countGenes = new int[8];
    Random generator = new Random();

    public Genes(){
        fillRandom();
        validate();
    }

    public Genes(Genes parent1,Genes parent2){
        fillFromParents(parent1.getGenes(),parent2.getGenes());
        validate();
    }

    private void fillRandom(){
        for(int i=0;i<32;i++){
            int gen = generator.nextInt(8);
            genes[i] = gen;countGenes[gen]+=1;
        }
    }

    private void fillFromParents(int[] parent1,int[] parent2){
        int firstInterval = generator.nextInt(16);
        int secondInterval = generator.nextInt(16)+16;

        for(int i=0;i<=firstInterval;i++){
            genes[i] = parent1[i];
            countGenes[parent1[i]]+=1;
        }
        for(int i = firstInterval+1;i<=secondInterval;i++){
            genes[i] = parent2[i];
            countGenes[parent2[i]]+=1;
        }
        for(int i = secondInterval+1;i<32;i++){
            genes[i] = parent1[i];
            countGenes[parent1[i]]+=1;
        }
    }



    private void validate(){
        for(int i=0;i<8;i++){
            if(countGenes[i]==0){
                int index = generator.nextInt(32);
                while(countGenes[genes[index]]<2)
                    index = generator.nextInt(32);

                countGenes[genes[index]]-=1;
                genes[index] = i;
                countGenes[i]+=1;
            }
        }
        Arrays.sort(genes);
    }

    public int[] getGenes(){
        return this.genes;
    }

    public int getMaxGen(){
        int max = 0;int gen = 0;
        for(int i=0;i<8;i++){
            if(countGenes[i]>max){
                max = countGenes[i];
                gen = i;
            }
        }
        return gen;
    }

    public int getRandomGen(){
        int gen = generator.nextInt(32);
        return this.genes[gen];
    }

    @Override
    public String toString(){
        String genesFull = "[";
        for (int gene : genes) {
            genesFull+=gene + " ";
        }
        genesFull+=" ]";
        return genesFull;
    }
}
