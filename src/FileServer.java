import java.io.*;
import java.rmi.*;

public class FileServer {
   public static void main(String argv[]) {
      try {
            FileInterface fi = new FileImplementer();
            Naming.rebind("//127.0.0.1/RMI", fi);
      }
      catch(Exception e) {
            System.out.println("FileServer: "+e.getMessage());
      }
   }
}