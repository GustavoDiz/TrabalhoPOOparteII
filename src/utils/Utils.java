package utils;

import javax.swing.*;
import java.util.Arrays;

public class Utils {
    public static <T> int  findEmptyPosition(T[] array){
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null){
                return i;
            }
        }
        return -1;
    }

    public static  <T> T[] rearrangeArray(T[] array){
        int qtNotNull = 0;

        for(T element : array){
            if (element != null){
                qtNotNull ++;
            }
        }

        T[] newArray = Arrays.copyOf(array, qtNotNull);
        int indexNewArray = 0;

        for (T element : array){
            if (element != null){
                newArray[indexNewArray++] = element;
            }
        }

        return  newArray;
    }

    public  static void jError(String msg){
        JOptionPane.showMessageDialog(null,msg,"Alerta", JOptionPane.ERROR_MESSAGE);
    }

    public  static void jConfirmation(String msg){
        JOptionPane.showMessageDialog(null,msg,"Sucesso",JOptionPane.INFORMATION_MESSAGE);
    }


}
