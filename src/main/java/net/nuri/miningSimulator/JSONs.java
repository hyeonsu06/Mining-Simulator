package net.nuri.miningSimulator;

import org.bukkit.Bukkit;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static net.nuri.miningSimulator.things.*;

public class JSONs {
    private static JSONObject temp1 = new JSONObject();
    private static JSONObject temp2 = new JSONObject();
    private static JSONObject temp3 = new JSONObject();
    static {
        try {
            temp1 = (JSONObject) new JSONParser().parse(oreJSON);
            temp2 = (JSONObject) new JSONParser().parse(pickaxeJSON);
            temp3 = (JSONObject) new JSONParser().parse(materialJSON);
        } catch (ParseException e) {
            Bukkit.getLogger().warning(e.getMessage());
        }
    }
    public static JSONObject ores = temp1;
    public static JSONObject pickaxes = temp2;
    public static JSONObject materials = temp3;
}
