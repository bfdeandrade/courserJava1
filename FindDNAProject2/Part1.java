
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public String findGeneSimple(String dna) {
        int startIndex = dna.indexOf("ATG");
        int currIndex = dna.indexOf("TAA", startIndex + 3);
        while (currIndex != -1) {
            if((currIndex - startIndex) % 3 == 0){
                return dna.substring(startIndex,currIndex + 3);
        } else {
            currIndex = dna.indexOf("TAA", currIndex + 1);
        }
    }
    return "";
}
    public void testFindGeneSimple() {
        String dna = "AATGCGTAATTAATCG";
        System.out.println("DNA Strand is: " +dna);
        String gene = findGeneSimple(dna);
        System.out.println("Gene is :"+ gene);
        
        dna = "CGATGGTTGATAAGCCTAAGCTATAA";
        System.out.println("DNA Strand is: " +dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is :"+ gene);
        dna = "CGATGGTTGATAAGCCTAAGCTAATCGTAGATAG";
        System.out.println("DNA Strand is: " +dna);
        gene = findGeneSimple(dna);
        System.out.println("Gene is :"+ gene);
        
    }
    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex+3);
        while (currIndex != -1){
            if((currIndex - startIndex) % 3 == 0){
                return currIndex;
            } else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex +1);
            }
            
    }
    return dnaStr.length();
    }
    public void testFindStop() {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
        int dex = findStopCodon(dna,0,"TAA");
        System.out.println(dex);
        if(dex != 9) System.out.println("Error on 9");
        dex = findStopCodon(dna,9,"TAA");
        System.out.println(dex);
        if(dex != 21) System.out.println("Error on 21");
        dex = findStopCodon(dna,1,"TAA");
        System.out.println(dex);
        if(dex != 26) System.out.println("Error on 26");
        dex = findStopCodon(dna,0,"TAG");
        if(dex != 26) System.out.println("ERROR ON 26 TAG");
        System.out.println("tests finished");
    }
    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int minIndex;
        minIndex = Math.min(taaIndex,Math.min(tgaIndex,tagIndex));
        if (minIndex == dna.length()){
            return "";
        }
        
        return dna.substring(startIndex,minIndex + 3);
    }
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true) {
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                break;
            }
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }   
    }
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
    }
}