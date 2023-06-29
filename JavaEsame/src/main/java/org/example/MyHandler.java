package org.example;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;


public class MyHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        InputStream is = exchange.getRequestBody();

        URI uri = exchange.getRequestURI();
        System.out.println(uri);

        String method = exchange.getRequestMethod();
        System.out.println(method);

        String s = read(is);
        System.out.println(s);

        String myAnswer = method.equalsIgnoreCase("post") ? response(s) : response(uri.toString());

        String response = "<!doctype html>\n" +
                "<html lang=en>\n" +
                "<head>\n" +
                "<meta charset=utf-8>\n" +
                "<title>Lista Scarpe</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "</br>query:" +
                "<table>\n" +
                "<tr>\n" +
                "<th>" + "Id" + "</th>" +
                "<th>" + "Name" + "</th>" +
                "<th>" + "Value" + "</th>" +
                "<th>" + "Genre" + "</th>" +
                "</tr>\n" +
                myAnswer +
                "</table>\n" +
                "</body>\n" +
                "</html>\n";

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
    private String read(InputStream is) {
        BufferedReader br = new BufferedReader( new InputStreamReader(is) );
        System.out.println("\n");
        String received = "";
        while (true) {
            String s = "";
            try {
                if ((s = br.readLine()) == null) break;
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s);
            received += s;
        }
        return received;
    }

    public String response(String uri) {
        String[] res = uri.split("[/?&]");
        ArrayList<String> ls = new ArrayList<>();
        if (res.length == 0) {
            return "No data";
        }

        for (String r : res) {
            if (r.contains("cmd")) {
                ls.add(r);
            }
        }

        String answer = "";
        if (ls.size() != 0) {
            String[] s;
            if (ls.get(0).contains("cmd")) {
                s = ls.get(0).split("=");
                answer = CmdHandler.getInstance().getAction(s[1]);
            }
        }
        if (answer.equalsIgnoreCase("Command not found!")) {
            return answer;
        }

        return dataFormat(answer);
    }

    public String dataFormat(String answer) {
        Gson gson = new Gson();

        Scarpe[] listaScarpe = gson.fromJson(answer, Scarpe[].class);

        String res = "<tr>";
        for (Scarpe s: listaScarpe) {
            res += (
                    "<td>" + s.getId() + "</td>" +
                            "<td>" + s.getName() + "</td>" +
                            "<td>" + s.getPrice() + "</td>" +
                            "<td>" + s.getGenere() + "</td>" +
                            "</tr>\n"
            );
        }

        return res;
    }
}