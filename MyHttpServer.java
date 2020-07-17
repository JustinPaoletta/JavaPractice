import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MyHttpServer implements Runnable{
    static final File ROOT = new File(".");
    static final String DEFAULT = "index.html";
    static final String NOT_FOUND = "404.html";
    private final Socket connect;

    public MyHttpServer(Socket con) {
        connect = con;
    }

    public static void main(String[] args) {
        System.out.println("Enter Your Port Number");
        Scanner input = new Scanner(System.in);
        final int PORT = Integer.parseInt(input.next());
        try {
            ServerSocket serverConnect = new ServerSocket(PORT);
            System.out.println("Server Says Hi From Port : " + PORT);
            System.out.println("Connection On");
            while (true) {
                MyHttpServer myServer = new MyHttpServer(serverConnect.accept());
                Thread thread = new Thread(myServer);
                thread.start();
            }
        } catch (IOException E){
            System.err.println("Server Error : " + E.getMessage());
        }
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        BufferedOutputStream outputData = null;
        String requestedFile = null;
        try {
            br = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            pw = new PrintWriter(connect.getOutputStream());
            outputData = new BufferedOutputStream(connect.getOutputStream());
            String input = br.readLine();
            StringTokenizer parse = new StringTokenizer(input);
            String method = parse.nextToken().toUpperCase();
            requestedFile = parse.nextToken().toLowerCase();
            if (!method.equals("GET") && !method.equals("HEAD")) {
                System.out.println("501 BAD REQUEST WE ONLY HANDLE THE GET METHOD");
            } else {
                if (requestedFile.endsWith("/")) {
                    requestedFile += DEFAULT;
                }
                File file = new File(ROOT, requestedFile);
                int fileLength = (int) file.length();
                String content = getContentType(requestedFile);
                byte[] fileData = readFileData(file, fileLength);
                pw.println("HTTP/1.1 200 OK");
                pw.println("Server: HTTP JAVA SERVER FOR 4C : 1.0");
                pw.println("Date " + new Date());
                pw.println("Content-type: " + content);
                pw.println("Content-length: " + fileLength);
                pw.println();
                pw.flush();
                outputData.write(fileData, 0, fileLength);
                outputData.flush();
                System.out.println("Returned " + requestedFile);
            }
        } catch (FileNotFoundException EX) {
            try {
                fileNotFound(pw, outputData, requestedFile);
            } catch (IOException E) {
                System.err.println("Error In File Not Found Exception: " + E.getMessage());
            }
        } catch (IOException E) {
            System.err.println("Server Error : " + E.getMessage());
        } finally {
            try {
                br.close();
                pw.close();
                outputData.close();
                connect.close();
            } catch (Exception E) {
                System.out.println("Error Closing Stream: " + E.getMessage());
            }
        }
    }

    private void fileNotFound(PrintWriter pw, BufferedOutputStream outputData, String requestedFile) throws IOException {
        File file = new File(ROOT, NOT_FOUND);
        int fileLength = (int) file.length();
        String content = "text/html";
        byte[] fileData = readFileData(file, fileLength);
        pw.println("HTTP/1.1 404 NOT FOUND");
        pw.println("Server: HTTP JAVA SERVER FOR 4C : 1.0");
        pw.println("Date " + new Date());
        pw.println("Content-type: " + content);
        pw.println("Content-length: " + fileLength);
        pw.println();
        pw.flush();
        outputData.write(fileData, 0, fileLength);
        outputData.flush();
        System.out.println("The File You Requested Could Not Be Found");
    }

    private String getContentType(String requestedFile) {
        if (requestedFile.endsWith(".html")) {
            return "text/html";
        } else {
            return "text/plain";
        }
    }

    private byte[] readFileData(File file, int fileLength) throws IOException {
        FileInputStream fileIn = null;
        byte[] fileData = new byte[fileLength];
        try {
            fileIn = new FileInputStream(file);
            fileIn.read(fileData);
        } finally {
            if (fileIn != null) {
                fileIn.close();
            }
        }
        return fileData;
    }
}
