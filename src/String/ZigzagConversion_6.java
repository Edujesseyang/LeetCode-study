package src.String;

import java.util.ArrayList;
import java.util.List;

public class ZigzagConversion_6 {

    public static String convert(String s, int numRows) {
        if(numRows == 1) return s; // return special case
        List<StringBuilder> res = new ArrayList<>(numRows);  // multi-stringBuilder catch chars

        for (int i = 0; i < numRows; i++) {
            res.add(new StringBuilder()); // init catchers
        }

        char[] charArr = s.toCharArray(); // charArray for faster
        int size = charArr.length;

        int position = 0; // from catcher 0
        int direction = 1;  // go down first

        for(int i = 0; i < size; i++){
            res.get(position).append(charArr[i]);  // catcher_i catch the char
            position += direction; // move one step

            if(position == numRows - 1) direction = -1;  // change direction to up
            else if(position == 0) direction = 1;  // change direction to down
        }

        for(int i = 1; i <numRows; i++){
            res.get(0).append(res.get(i)); // merge all catchers to one
        }
        return res.get(0).toString();
    }


    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        System.out.println(convert(s, 4));  // Output: "PINALSIGYAHRPI"
    }
}
