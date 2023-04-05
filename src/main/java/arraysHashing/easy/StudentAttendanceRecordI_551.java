package arraysHashing.easy;

// https://leetcode.com/problems/student-attendance-record-i/description/
public class StudentAttendanceRecordI_551 {
    public boolean checkRecord(String s) {
        // time O(n)
        // space O(1)

        // do 2 passes : first time check first cond, second second
        // count('A') < 2 -> true;
        // count max length of conseq L  if it < 3 -> true;

        int countA = 0;
        int lengthL = 0;
        int currLengthL = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if(ch == 'L') {
                currLengthL++;
                continue;
            }

            lengthL = Math.max(lengthL, currLengthL);
            currLengthL = 0;

            if(ch == 'A')
                countA++;
        }

        lengthL = Math.max(lengthL, currLengthL);

        return countA < 2 && lengthL < 3;
    }
}
