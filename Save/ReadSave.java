package blokus.Save;

import blokus.Case;
import blokus.RecolteLegume.Stock;

public class ReadSave {
    public static Case[][] loadObject(String fileName){
        Case[][] object = null;
        try(java.io.FileInputStream fileInputStream = new java.io.FileInputStream(fileName);
            java.io.ObjectInputStream objectInputStream = new java.io.ObjectInputStream(fileInputStream)) {
            object = (Case[][]) objectInputStream.readObject();
            return object;
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Stock loadStockage(String fileName){
        Stock object = null;
        try(java.io.FileInputStream fileInputStream = new java.io.FileInputStream(fileName);
            java.io.ObjectInputStream objectInputStream = new java.io.ObjectInputStream(fileInputStream)) {
            object = (Stock) objectInputStream.readObject();
            return object;
        } catch (java.io.IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
