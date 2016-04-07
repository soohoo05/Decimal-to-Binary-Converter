import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class SooHoo1 {
   public static void main(String[] args){
      while(true){
         String inputValue = JOptionPane.showInputDialog("input a value no larger then 10 characters");
         String simpleInput = inputValue.replaceAll("\\W", "");
         
         while (simpleInput.length()>10 ||isValidNUM(inputValue)==false){
            //while loop checks if length is under 10 not counting decimal point or if there are any invalid characters, keeps re-prompting
            JOptionPane.showMessageDialog(null,"Too Many characters have been inputted/invalid characters","ERROR",JOptionPane.PLAIN_MESSAGE);
            inputValue = JOptionPane.showInputDialog("input a value no larger then 10 characters");
            simpleInput = inputValue.replaceAll("\\W", "");
         }// while
         
         double bad = Double.parseDouble(inputValue);
         if (bad==0.0){
            break;
         }//if
         
         int[]arrayb=new int[8];
         int leftofdec = 0;
         double rightofdec=0;
         int count=0;
         int multiplier=1;
         String[] result = inputValue.split("\\.");
         
         //converts left of decimal in string into integer
         for (int i=0;i<inputValue.length();i++){
            //checks when you hit the decimal to stop
            if(Character.getNumericValue(inputValue.charAt(i))==-1){
               break;
            }//if
            else{
               leftofdec=(int) ((Character.getNumericValue(inputValue.charAt(i)))*(Math.pow(10,result[0].length()-multiplier))+leftofdec);
               multiplier++;
               count++;
            }//else
         } //for
         
         multiplier=changemult(result[1].length());	//resets multiplier for right side
         
         //converts right of decimal in string into integer
         for (int i=count+1;i<inputValue.length();i++){
            rightofdec=(double) ((Character.getNumericValue(inputValue.charAt(i)))/(Math.pow(10,(result[1].length()-multiplier)))+rightofdec);
            multiplier--;
         } //for
         System.out.println("your number in decimal is "+inputValue);
         System.out.print("your number in binary is ");
         //changing to binary,splitting into array,printing array
         String arraya[] = Integer.toBinaryString(leftofdec).split("");
         for(int i=0;i<arraya.length;i++){
            System.out.print(arraya[i]);
         }//for
         System.out.print(".");
         //changing into binary,putting into array, printing
         decimaltobin2(rightofdec,arrayb);
         for(int i=0;i<arrayb.length;i++){
            System.out.print(arrayb[i]);
         }//for
         System.out.println();
      }//while true
   }//main
   
   /*
   * method changmult
   * changes the multiplier as needed when comparing it to the length of the decimal fraction
   */
   private static int changemult(int length) {
      return length-1;
   }//changemult
   
   /*
   * method decimaltobin
   * changes decimal fraction to binary fraction
   */
   private static int[] decimaltobin2(double number, int[] arrayb) {
      for(int i=0;i<8;i++){
         number=number*2;
         if (number==1.0){
            arrayb[i]= 1;
            break;
         }//if
         if (number>1){
            number=number-1.0;
            arrayb[i]= 1;
         }//if
         else {
            arrayb[i]= 0;
         }//else
      }//for
      return arrayb;
   } //decimaltobin
   
   /*
   * method isValidNUM
   * this method checks to see if the inputed string of numbers is valid or not
   */
   private static boolean isValidNUM(String inputValue) {
      Pattern p;
      Matcher m;
      String NUM_PATTERN= "[0-9]+[.]?[0-9]+";
      //pattern:any digit (and any amount of digits) followed by decimal or not at all followed by any digit (and any amount of digits)
      p=Pattern.compile (NUM_PATTERN);
      m=p.matcher(inputValue);
      return m.matches();
   } //isValidNUM
}//class
