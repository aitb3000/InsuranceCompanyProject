package app.connection;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class loggerAPI {

    private static loggerAPI ourInstance = new loggerAPI();
    private String loggerFileName = "Insurance.log";
    private Date date = new Date();
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public static loggerAPI getInstance() {
        return ourInstance;
    }


    private loggerAPI()
    {
        OpenStream();
    }

    private void OpenStream()
    {
        try
        {
            fileWriter = new FileWriter(loggerFileName,true);
            bufferedWriter = new BufferedWriter(fileWriter);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }


    /**
     * @param className
     * @param User
     * @param msg
     */
    public void WriteLog(String className, String User, String msg)
    {
        try
        {

            StringBuilder newLog = new StringBuilder();
            newLog.append(GetDate());
            newLog.append(className + "\t");
            newLog.append(User + " ");
            newLog.append(msg);
            newLog.append("\n");
            bufferedWriter.write(newLog.toString());
        }
        catch(IOException ex)
        {
            ex.printStackTrace();
        }

    }

    private String GetDate()
    {
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy - hh:mm:ss -- ");
        return ft.format(date);
    }


    /**
     * @apiNote Closing the logger file.
     * @return true is succeed otherwise false.
     */
    public boolean CloseLogger() {
        try {
            bufferedWriter.close();
        } catch (IOException ex)
        {
            return false;
        }
        return true;
    }
}
