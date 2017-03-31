package server;

import client.Deposit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.net.Socket;
import java.util.*;

class ServerThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Gson gson = new Gson();

    public ServerThread(Socket s) throws IOException {
        socket = s;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
        start();
    }

    public void run() {

        try {
            while (true) {
                String str = in.readLine();
                if (str.equals("END"))
                    break;
                switch (str) {

                    case "list":
                        List<Deposit> list = readFile();
                        for (Deposit aList1 : list) {
                            out.println(aList1.toString());
                        }
                        out.println("0");
                        break;

                    case "add":
                        String json = in.readLine();
                        Deposit dep = gson.fromJson(json, Deposit.class);
                        if (dep.getAmoundOnDeposit()<=0 || dep.getProfitability()<=0 || dep.getTimeContraints()<=0) {
                            out.println("Error. Invalid input");
                            break;
                        }
                        list = readFile();
                        boolean flag=true;
                        for (Deposit aList : list) {
                            if (Objects.equals(aList.getAccountID(), dep.getAccountID())) {
                                out.println("Error. This ID is already in use");
                                flag=false;
                                break;
                            }
                        }
                        if (flag) {
                            list.add(dep);
                            writeFile(list);
                            out.println("OK");
                        }
                        break;

                    case "sum":
                        list = readFile();
                        double sum=0;
                        for (Deposit aList : list) {
                            sum += aList.getAmoundOnDeposit();
                        }
                        out.println(sum);
                        break;

                    case "count":
                        list=readFile();
                        out.println(list.size());
                        break;

                    case "info account":
                        String accountId = in.readLine();
                        list=readFile();
                        flag = true;
                        for (Deposit aList1 : list) {
                            if (Objects.equals(aList1.getAccountID(), accountId)) {
                                out.println(aList1.toString());
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            out.println("No deposits with entered account id");
                        }
                        break;

                    case "delete":
                        accountId = in.readLine();
                        list=readFile();
                        flag = true;
                        for (int i = 0; i < list.size(); i++) {
                            if (Objects.equals(list.get(i).getAccountID(), accountId)) {
                                list.remove(list.get(i));
                                writeFile(list);
                                out.println("Deleted");
                                flag = false;
                                break;
                            }
                        }
                        if (flag) {
                            out.println("No deposits with entered account id");
                        }
                        break;

                    case "info depositor":
                        String depositor = in.readLine();
                        list=readFile();
                        flag = true;
                        for (Deposit aList : list) {
                            if (Objects.equals(aList.getDepositor(), depositor)) {
                                out.println(aList.toString());
                                flag = false;
                            }
                        }
                        out.println("0");
                        if (flag) {
                            out.println("No deposits with entered depositor");
                        }
                        break;

                    case "show type":
                        String type = in.readLine();
                        list=readFile();
                        flag = true;
                        for (Deposit aList : list) {
                            if (Objects.equals(aList.getType(), type)) {
                                out.println(aList.toString());
                                flag = false;
                            }
                        }
                        out.println("0");
                        if (flag) {
                            out.println("No deposits with entered type");
                        }
                        break;

                    case "show bank":
                        String name = in.readLine();
                        list=readFile();
                        flag = true;
                        for (Deposit aList : list) {
                            if (Objects.equals(aList.getName(), name)) {
                                out.println(aList.toString());
                                flag = false;
                            }
                        }
                        out.println("0");
                        if (flag) {
                            out.println("No deposits with entered name");
                        }
                        break;
                }
            }

            System.out.println("closing...");
        } catch (IOException e) {
            System.err.println("IO Exception");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Socket not closed");
            }
        }
    }

    private void writeFile(List<Deposit> list) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new File("deposits.json").getAbsoluteFile());
        Gson gson = new Gson();
        String json = gson.toJson(list);
        pw.write(json);
        pw.close();
    }

    private List<Deposit> readFile() throws FileNotFoundException {
        Gson gson = new Gson();
        String s = "";
        Scanner in = new Scanner(new File("deposits.json"));
        while (in.hasNext())
            s += in.nextLine() + "\r\n";
        in.close();
        Type type = new TypeToken<List<Deposit>>() {
        }.getType();
        List<Deposit> read = gson.fromJson(s, type);
        return read;
    }
}