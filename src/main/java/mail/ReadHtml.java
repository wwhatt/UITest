package mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ReadHtml {
    public static String readString(String FILE_IN)

    {

        String str="";

        File file=new File(FILE_IN);

        try {

            FileInputStream in=new FileInputStream(file);

            int size=in.available();

            byte[] buffer=new byte[size];

            in.read(buffer);

            in.close();

            str=new String(buffer,"UTF-8");

        } catch (IOException e) {


            System.out.println("error");
            return null;
        }

        return str;

    }
}
