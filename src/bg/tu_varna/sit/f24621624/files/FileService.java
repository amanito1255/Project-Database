package bg.tu_varna.sit.f24621624.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileService
{
    private static FileService instance;
    private String currentFilePath;

    private FileService() {}

    public static FileService getInstance()
    {
        if (instance == null)
        {
            instance = new FileService();
        }
        return instance;
    }

    public void writeToFile(String fileName, List<String> lines)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName)))
        {
            for (String line : lines)
            {
                writer.println(line);
            }
        } catch (IOException e)
        {
            System.err.println("Error while trying to write into the file: " + e.getMessage());
        }
    }

    public List<String> readFromFile(String fileName)
    {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
        } catch (IOException e)
        {
            System.err.println("Error while trying to read the file: " + e.getMessage());
        }
        return lines;
    }

    public String getCurrentFilePath()
    {
        return currentFilePath;
    }
    public void setCurrentFilePath(String path)
    {
        this.currentFilePath = path;
    }
    public boolean hasOpenedFile()
    {
        return currentFilePath != null;
    }
}
