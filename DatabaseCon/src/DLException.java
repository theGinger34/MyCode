/**
 * -- ISTE 330 Group Project
 * -- Authors: Lowell Pence, Campbell Schweickhardt, Vincent Cheng
 */

import java.io.*;

public class DLException extends Exception {

    public DLException(Exception e, String userMessage) {
        super(userMessage);
        writeLog(e, userMessage);
    }

    public DLException(Exception e, String userMessage, String... values) {
        super(userMessage);
        writeLog(e, userMessage, values);
    }

    private void writeLog(Exception e, String userMessage, String... data) {

        // create or append to log file
        try {
            File logFile = new File("mySQL_server_log.txt");

            if (!logFile.exists()) {
                logFile.createNewFile();
            }

            FileWriter fw = new FileWriter(logFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println("");
            pw.println(userMessage + "\n");

            StackTraceElement[] es = e.getStackTrace();
            for (StackTraceElement elmt : es) {
                pw.println(elmt.toString());
            }

            for (String datum : data) {
                pw.println(datum);
            }

            pw.flush();
            pw.close();

        } catch(IOException ioe) {
            ioe.printStackTrace();
        }

    }
}
