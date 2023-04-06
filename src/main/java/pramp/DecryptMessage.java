package pramp;

public class DecryptMessage {
/*
    Convert every letter to its ASCII value. Add 1 to the first letter,
    and then for every letter from the second one to the last one, add the value of the previous letter.
    Subtract 26 from every letter until it is in the range of lowercase letters a-z in ASCII. Convert the values back to letters.

    For instance, to encrypt the word “crime”

    Decrypted message:	c	  r	  i	  m	  e
           Step 1:  	99	114	105	109	101
           Step 2:	   100	214	319	428	529
           Step 3:	   100	110	111	116	113
    Encrypted message:	d	n	o	t	q
*/
    static String decrypt(String word) {
        // write decrypt function
        // enc[i] = dec[i] + secondStepVal - 26*m
        // dec[i] = enc[i] - secondStepVal + 26*m

        StringBuilder res = new StringBuilder();
        int secondStep = 1;
        for (int i = 0; i < word.length(); i++) {
            int newLetterAscii = (int) word.charAt(i);
            newLetterAscii = newLetterAscii - secondStep;

            while (newLetterAscii < (int) 'a')
                newLetterAscii += 26;

            res.append((char) newLetterAscii);
            secondStep += newLetterAscii;
        }

        return res.toString();
    }

    static String encrypt(String word) {
        int prevAsciVal = 0;
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < word.length(); i++) {
            int asciVal = (int) word.charAt(i);
            if (i == 0) {
                asciVal += 1;
                prevAsciVal = asciVal;
            } else {
                asciVal += prevAsciVal;
                prevAsciVal = asciVal;
            }

            while (asciVal > 122)
                asciVal -= 26;

            res.append((char) asciVal);
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(encrypt("crime"));
        System.out.println(encrypt("encyclopedia"));
        System.out.println(encrypt("cat"));
    }

    /*
   Input: "rajsb"
   Expected: "qqqqq"
   Actual: qqqqq
* */
}
