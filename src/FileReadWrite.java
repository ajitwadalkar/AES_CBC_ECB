/*
* This class is implemented to read and write files to
* */

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReadWrite {

    static String folderPath;



    static void setFolderPath(String path){

        folderPath = path;
    }

    static String ReadFile(String fileName)
    {
        File f = new File("./data/key.txt");
        if(f.exists() && !f.isDirectory()) {
            folderPath = "./data/";
        }
        else
        {
            folderPath = "../data/";
        }

        String filePath = folderPath+fileName;
        StringBuilder ReadData = new StringBuilder();

        try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
        {
                stream.forEach(s -> ReadData.append(s));
        }
        catch (IOException e)
        {
            System.out.println("File does not exist.");
        }
        if(ReadData.toString().length()==0)
        {
            return fileName + " is Empty or Does not exist.";
        }
        else {
            return ReadData.toString();
        }

    }
    static Boolean WriteFile(String fileName, String DataToWrite)
    {
        String filePath = folderPath+fileName;
        try
        {
            Path file = Paths.get(filePath);
            Files.write(file, DataToWrite.getBytes());
            return true;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }

    }
}
