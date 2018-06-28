package util;

import sun.security.pkcs11.Secmod;

import java.io.*;

public class MysqlUtil {
    public static void backup(String mysqlPath ,String backupFile) {
        //mysqldump -h localhost -u root -p123456 www > d:\www2008-2-26.sql
        String commandFormat = "\"%s/bin/mysqldump.exe\" -u%s -p%s   -hlocalhost   -P%d %s -r \"%s\"";
        String command = String.format(commandFormat,mysqlPath,DBUtil.loginName,DBUtil.password,DBUtil.port,DBUtil.database,backupFile);
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void recover(String mysqlPath ,String recoverFile) throws IOException {
        String commandFormat = "\"%s/bin/mysql.exe\" -u%s -p%s   %s ";
        String command = String.format(commandFormat,mysqlPath,DBUtil.loginName,DBUtil.password,DBUtil.database);
        Process p = Runtime.getRuntime().exec(command);
        OutputStream out = p.getOutputStream();
        String inStr;
        StringBuffer stringBuffer = new StringBuffer("");
        String outStr;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(recoverFile)));
        while((inStr = br.readLine())!=null){
            stringBuffer.append(inStr +"\r\n");
        }
        outStr = stringBuffer.toString();

        OutputStreamWriter writer = new OutputStreamWriter(out,"utf8");
        writer.write(outStr);
        writer.flush();
        out.close();
        br.close();
        writer.close();


    }
}
