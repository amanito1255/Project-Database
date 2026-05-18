package bg.tu_varna.sit.f24621624.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Услуга за управление на файлови операции (четене и запис), реализирана чрез Singleton pattern.
 */
public class FileService
{
    // Единствената инстанция на класа в цялото приложение
    private static FileService instance;
    // Пази пътя до текущо отворения или импортиран файл
    private String currentFilePath;

    /**
     * Частен (private) конструктор, за да се предотврати създаването на външни обекти.
     */
    private FileService() {}

    /**
     * Глобална точка за достъп до единствената инстанция на FileService.
     * @return обекта на файловата услуга
     */
    public static FileService getInstance()
    {
        if (instance == null)
        {
            instance = new FileService();
        }
        return instance;
    }


    /**
     * Записва подадения списък от текстови редове в указания файл.
     * @param fileName името или пътят до файла за запис
     * @param lines списък от редове с данни за записване
     */
    public void writeToFile(String fileName, List<String> lines)
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName)))
        {
            // Обхождаме редовете и ги записваме един по един
            for (String line : lines)
            {
                writer.println(line);
            }
        } catch (IOException e)
        {
            System.out.println("Error: while saving file -> " + e.getMessage());
        }
    }

    /**
     * Прочита съдържанието на текстов файл ред по ред и го връща като списък.
     * @param fileName името или пътят до файла за четене
     * @return списък от прочетените текстови редове
     */
    public List<String> readFromFile(String fileName) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Четем файла до достигане на края му
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error: while reading file -> " + e.getMessage());
        }
        return lines;
    }

    /**
     * Връща пътя до текущо заредения файл.
     * @return текущият път като текст
     */
    public String getCurrentFilePath()
    {
        return currentFilePath;
    }
    /**
     * Задава пътя до текущо обработвания файл.
     * @param path новият път до файла
     */
    public void setCurrentFilePath(String path)
    {
        this.currentFilePath = path;
    }
    /**
     * Проверява дали в програмата в момента има успешно зареден файл.
     * @return true, ако има зареден файл, иначе false
     */
    public boolean hasOpenedFile()
    {
        return currentFilePath != null;
    }
}
