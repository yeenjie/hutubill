package service;

import dao.ConfigDAO;
import entity.Config;

public class ConfigService {
    public static final String budget = "budget";
    public static final String mysqlPath ="mysqlPath";
    public static final String default_budge = "1500";
    static ConfigDAO dao = new ConfigDAO();
    static{
        init();
    }
    public static void init(){
        init(budget,default_budge);
        init(mysqlPath,"");
    }

    private static void init (String key, String value){
        Config config = dao.getByKey(key);
        if(config == null){
            Config c = new Config();
            c.setKey(key);
            c.setValue(value);
            dao.add(c);
        }
    }
    public String get(String key){
        return dao.getByKey(key).getValue();
    }

    public void update (String key,String value){
        Config c = dao.getByKey(key);
        c.setValue(value);
        dao.update(c);
    }

    public int getIntBudget(){
        return Integer.parseInt(get(budget));
    }
}
