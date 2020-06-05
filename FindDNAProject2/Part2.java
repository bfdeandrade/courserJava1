
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
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
}
