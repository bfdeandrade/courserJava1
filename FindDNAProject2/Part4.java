import edu.duke.*;
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
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
    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while(true) {
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                break;
            }
            sr.add(currGene);
            
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return sr;
    }
    public void testOn(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        printAllGenes(dna);
    }
    public void test() {
        testOn("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOn("");
        testOn("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOn("AATGCTAACTAGCTGACTAAT");
    }
    public void testOnGuard(String dna) {
        System.out.println("Testing printAllGenes on " + dna);
        StorageResource genes = getAllGenes(dna);
        for(String gene : genes.data()){
            System.out.println(gene);
        }
    }
    public void testGet() {
        testOnGuard("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        testOnGuard("");
        testOnGuard("ATGATCATAAGAAGATAATAGAGGGCCATGTAA");
        testOnGuard("AATGCTAACTAGCTGACTAAT");
    }
    public int howMany(String stringa, String stringb){
        if (stringb == null || stringb.length() == 0) {
            return 0;
        } else {
            int index = 0, count = 0;
            while (true) {
                index = stringb.indexOf(stringa, index);
                if (index != -1) {
                    count++;
                    index += stringa.length();
                } else {
                    break;
                }
            }
            return count;
        }
        
    }
    public void testHowMany() {
        System.out.println(howMany("GAA", "ATGAACGAATTGAATC"));
        System.out.println(howMany("AA", "ATAAAA"));
    }
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true) {
            String currGene = findGene(dna,startIndex);
            if(currGene.isEmpty()){
                break;
            }
            count++;
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }  
       
        return count;
    }
    public void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
    }
    public double cgRatio(String dna){
        StorageResource sr = getAllGenes(dna);
        char g = 'G';
        char c = 'C';
        int cRatio = 0;
        int gRatio = 0;
        for(String genes : sr.data()){
            char[] toChar = genes.toCharArray();
            for(int i = 0; i < toChar.length;i++){
                if(toChar[i] == g){
                    gRatio++;
                }
                if(toChar[i] == c){
                    cRatio++;
                }
        }
        }
        return (double) (cRatio + gRatio)/dna.length();
    }
    public void testcgRatio(){
        System.out.println("Testing cgRatio");
        System.out.println(cgRatio("ATGCCATAG"));
    }
    public int countCTG(String dna){
        int count = howMany("CTG",dna);

    return count;
}
    public void testCountCTG(){
        System.out.println("Testing testCountCTG");
                                   //0123456789012345678901234567890
        System.out.println(countCTG("ATGCCACTGCCACTGCCCAAACTGTAG"));
    }
    public void processGenes(StorageResource sr){
       int countGreaterNine = 0;
       int countCgRatio = 0;
       int lengthMax = 0;
       for(String genes : sr.data()){
           if(genes.length() > 9){
               System.out.println("O tamanho do gene e " + genes.length());
               System.out.println("");
               System.out.println(genes);
               countGreaterNine++;
           }
           if(cgRatio(genes) > 0.35){
               System.out.println("CG Ratio > 0.35");
               countCgRatio++;
           }
           lengthMax = Math.max(genes.length(),lengthMax);
        }
        
       System.out.println("O Numero de genes com tamanho maior que 9 e: " + countGreaterNine);
       System.out.println("A quantidade de genes com CG ratio > 0.35 é: " + countCgRatio);
       System.out.println("O tamanho da maior sequencia de genes e: " + lengthMax);
    }
    public void testProcessGenes() {
        
        //String a = "GCCTCACTCATAGATCAAGAAAATGTTGTGAGGTTAGGGGCGTCTCATCGATGTTATAACCAAGCCGCCTTTAGGCCCAGCTTCCAGGGGCGGGACATGCGCAATGCGGACAAAGGGCAAGCATTGCCCGGAGGAGTTTACTCATAGAGATAGATATCCGAAACCCCCGACAGTTAAGAGATGCTAGACCGCTTTTGTGCCAGTCAAGCTTTCTGACTCCTGTCGGTGCATCTTCTCTCCCCCGCGAATGCCAGTAGTGGAGACACGCGGGCAAACTGAGTGGTAGCTGTGGCGAACACTTACAAGAAAAGAGGCACTGAACGTACACATGGCTCTCCAACCTAGCACTCGGCAAGCCCGGAGCCTGCCTAACAGCAGAGCGATCCGTTCATGCCCAGGTGGTACTCATTCTATCCTTCCCATTTTTTAGTTGAGACGCAAATGCCTCAAGTGTCAGACCGGCCTTATCAAAGATGAGGTAAGCGTTAAATAATACGGACCACACCATAGGAGGCTTTCGCCGGTCCACCTAGGGTGGGCTACAGAACACCCACTGGCGGCAACGAGAACGCTCGGGGAGATACATCTACGAAGTCTTTATTGGAGGAATGAGAAGGTTGTGGCCCGCGGTGCTTATAACGCTTGCTCCTCCACCGGTATATGGCTATGCAACATGAAGTCTAGTAACCATAACGTCTCATAGCTAACGGAAAGTTCAATTGTGCCTACGATTACCGTTAGCAGCCATGGGATGCACGCGACGTTGGGATGACTACTATTTGTATTTGTCGCGAAAACGGCTTACGGCGCATTAACGGACGACTTGGGACGCACCCTTATGTGCCGGCAATCCAACTCCTGCACTTGCCATGTACTTTAGCTTTCAATACTAAAGACTTATCGTTGCCCTTGCATGCGCCGGCCATAAAGACTGATAAGATTGGGCGATTGGTTCTTTATCCGCCAGCAGTGAAGAAGCTAACAAGGCTCAGATAGACG";
        String a = "oneAtGMyGeneOneAATGGGGTAATGATAGAACCCGGYGGGGTAGGGCTGCCCATGendOneTAAnonCodingDnaTAGTGAZZZtaaTwoATGMyGeneTwoCATGGGGTAATGATAGCCatgCCCFalseStartTAATGATGendTwoTAGnonCodingDNATAACCCThreeATGMyGeneThreeATGGGGTAATGATAGATGccendThreeTAAnonCodingDNAccTAAfalsecccFourATGMyGeneFourATGGGGTAATGATAGCendFourTAGnonCodingdnaFiveAtgMyGeneFiveATGGGGTAATGATAGCendFiveTGAnonCodingdnaSixATGmyGeneSixATATGGGGTAATGATAGAendSixTAAnoncodingdnaSevenATGMyGeneSevenCcATGGGGTAATGATAGendSeventaAnoncodingdnaEightATGmyGeneEightATGGGGTAATGATAGGGendEighttaAnoncodingdnaCcccWrongtgaCtaaCtagCCcgNineATgmyGeneNineATGGGGTAATGATAGTaaAendNineTAAnonCodingDnaCcccTenATGmyGeneTenGATGGGGTAATGATAGCCHasFakeATGFAKEatgcendTentaanonCodingDnaCtagCtganonCodingDnaxxxElevenATGmyGeneElevenCATGGGGTAATGATAGTAAxxGeneATGTAACATGTAAATGCendElevenTAAnonCodingDnaxTAGxtgaTwelveATGmyGeneTwelveCATGGGGTAATGATAGCTheLastGeneIsATGTAGendTwelvetgaATGTAG";
        a = a.toUpperCase();
        int count = countGenes(a);
        System.out.println("Existe um total de " + count + " genes");
        
        StorageResource sr = getAllGenes(a);
        processGenes(sr);
    }
    public void ProcessGenesFile(StorageResource sr){
       int countGreaterNine = 0;
       int countCgRatio = 0;
       int lengthMax = 0;
       for(String genes : sr.data()){
           if(genes.length() > 60){
               System.out.println("");
               System.out.println(genes);
               countGreaterNine++;
           }
           if(cgRatio(genes) > 0.35){
               countCgRatio++;
           }
           lengthMax = Math.max(genes.length(),lengthMax);
        }
       System.out.println("O Numero de genes com tamanho maior que 60 e: " + countGreaterNine);
       System.out.println("O numero de genes com cg ratio > 0.35: " + countCgRatio);
       System.out.println("O maior gene tem o tamanho : " + lengthMax);
    }
    public void testRealDna(){
        //FileResource fr = new FileResource("brca1line.fa");
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String str = fr.asString();
        str = str.toUpperCase();
        int count = countGenes(str);
        int countCTG = countCTG(str);
        
        System.out.println("Existe um total de " + count + " genes");
        StorageResource sr = getAllGenes(str);
        ProcessGenesFile(sr);
        System.out.println("O numero de CTG e: " + countCTG);
        
    }
    public String mystery(String dna) {
    int pos = dna.indexOf("T");
    int count = 0;
    int startPos = 0;
    String newDna = "";
    if (pos == -1) {
    return dna;
    }
    while (count < 3) {
    count += 1;
    newDna = newDna + dna.substring(startPos,pos);
    startPos = pos+1;
    pos = dna.indexOf("T", startPos);
    if (pos == -1) {
      break;
    }
    }
    newDna = newDna + dna.substring(startPos);
    return newDna;
}
   public void testMistery(){
       System.out.println(mystery("ATGCCACTGCCACTGCCCAAACTGTAG"));
   }
}