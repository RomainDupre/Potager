package blokus.Save;

import blokus.Case;

public class Save {
    public static void sauvegarder(Object object, String fileName){
        try(java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(fileName);
            java.io.ObjectOutputStream objectOutputStream = new java.io.ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static void sauvegarderStockage(Object object, String fileName){
        try(java.io.FileOutputStream fileOutputStream = new java.io.FileOutputStream(fileName);
            java.io.ObjectOutputStream objectOutputStream = new java.io.ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(object);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
