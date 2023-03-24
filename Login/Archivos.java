import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Archivos {
    private String filePath;

    public Archivos() {
        filePath = System.getProperty("user.dir");
    }

    public void writeDataToFile(String data) {
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath + "\\File.vge"));
            if(readDataFromFile()== null)
            outputStream.writeObject(data);
            else
            outputStream.writeObject(readDataFromFile()+"@"+data);
            outputStream.close();
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public String readDataFromFile() {
        String data = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath + "\\File.vge"));
            if (inputStream == null) {
                data = "";
            } else {
                data = (String) inputStream.readObject();
                inputStream.close();
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }
        return data;
    }

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al crear el hash de la contraseña.");
            return null;
        }
    }
}