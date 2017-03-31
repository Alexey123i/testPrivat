package client;

import com.google.gson.Gson;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

class ClientThread extends Thread {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private final int PORT = 8080;
    private Scanner input = new Scanner(System.in);


    public ClientThread(InetAddress addr) {
        try {
            socket = new Socket(addr, PORT);
        }
        catch (IOException e) {
            System.err.println("Socket failed");
        }
        try {
            in = new BufferedReader(new InputStreamReader(socket
                    .getInputStream()));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                    socket.getOutputStream())), true);
            start();
        }
        catch (IOException e) {
            try {
                socket.close();
            }
            catch (IOException e2) {
                System.err.println("Socket not closed");
            }
        }
    }

    public void run() {
        while (true) {
            String str = input.nextLine();

            out.println(str);
            switch (str) {
                case "END": return;
                case "list":
                    try {
                        String strIn = in.readLine();
                        if(Objects.equals(strIn, "0")) {
                            System.out.println("No deposits");
                            break;
                        }
                        while (!Objects.equals(strIn, "0")) {
                            System.out.println(strIn);
                            strIn = in.readLine();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "add":
                    Deposit newDeposit;

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    System.out.print("Country: ");
                    String country = input.nextLine();

                    System.out.print("Type (demand/urgent/settlement/accumulative/savings/metal): ");

                    String type = input.nextLine();

                    System.out.print("Depositor: ");
                    String depositor = input.nextLine();

                    System.out.print("Account id: ");
                    String accontID = input.nextLine();

                    System.out.print("Amount on deposit: ");
                    double amount = input.nextDouble();

                    System.out.print("Profitability: ");
                    double profitability = input.nextDouble();

                    System.out.print("Time constraints: ");
                    int contraints = input.nextInt();

                    newDeposit = new Deposit(name,country,type,depositor,accontID,amount,profitability,contraints);

                    Gson gson = new Gson();
                    String json = gson.toJson(newDeposit);

                    out.println(json);
                    try {
                        System.out.println(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "sum":
                    try {
                        System.out.print("Amount of deposits: "+in.readLine());
                        System.out.println();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "info account":
                    System.out.print("Account ID: ");
                    accontID = input.nextLine();
                    out.println(accontID);
                    try {
                        System.out.println(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "count":
                    try {
                        System.out.println(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "delete":
                    System.out.print("Account ID: ");
                    accontID = input.nextLine();
                    out.println(accontID);
                    try {
                        System.out.println(in.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                case "info depositor":
                    System.out.print("Depositor: ");
                    depositor = input.nextLine();
                    out.println(depositor);
                    printingObject();
                    break;

                case "show type":
                    System.out.print("Type (demand/urgent/settlement/accumulative/savings/metal): ");
                    type = input.nextLine();
                    out.println(type);
                    printingObject();
                    break;

                case "show bank":
                    System.out.print("Name: ");
                    name = input.nextLine();
                    out.println(name);
                    printingObject();
                    break;
            }
        }
    }

    private void printingObject() {
        String strIn;
        try {
            strIn = in.readLine();
            if (Objects.equals(strIn, "0")) {
                System.out.println(in.readLine());
            }
            while (!Objects.equals(strIn, "0")) {
                System.out.println(strIn);
                strIn = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
