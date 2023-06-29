package org.example;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class CmdHandler {
    private static CmdHandler INSTANCE;
    private List<String> cmdList = new ArrayList<>();

    private CmdHandler() {
        cmdList.add("for_man");
        cmdList.add("for_woman");
        cmdList.add("sorted_by_name");
        cmdList.add("sorted_by_price");
        cmdList.add("help");
    }

    public static CmdHandler getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CmdHandler();
        }

        return INSTANCE;
    }

    public String getCmd() {
        Gson gson = new Gson();
        String res = gson.toJson(cmdList);

        return res;
    }

    public String getAction(String inCmd) {
        String output = "";

        switch (inCmd) {
            case "for_man":
                output = ArmadioScarpe.getInstance().getMan();
                break;

            case "for_woman":
                output = ArmadioScarpe.getInstance().getWoman();
                break;

            case "sorted_by_name":
                output = ArmadioScarpe.getInstance().getSortedName();
                break;

            case "sorted_by_price":
                output = ArmadioScarpe.getInstance().getSortedPrice();
                break;

            case "help":
                output = getCmd();
                break;

            default:
                output = "Command not found!";
                break;
        }

        return output;
    }
}