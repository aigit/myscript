package com.taike.cms;

import io.xjar.XCryptos;
import io.xjar.XKit;
import io.xjar.boot.XBoot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;

@Slf4j
public class MakeEncryJar {

    public static void main(String[] args) throws Exception{
        log.info("args 0 ：{}",args[0]);
        encode(args[0]);
        //printbs();
    }

    private static void encodeUse() throws Exception{
        ApplicationHome h = new ApplicationHome(TaikeCmsApplication.class);
        String src = h.getSource().getParentFile().getParentFile().getParentFile().getPath()
                +"/libs/tikr-statistics-1.0-SNAPSHOT.jar";
        log.info("resrouce path:{}",src);

        XCryptos.encryption()
                .from(src)
                .use("DES",56,128,"1234567890")
                .to(src.replace("tikr-statistics-1.0-SNAPSHOT","encryed"));
    }

    private static void encode(String sourceJarName) throws Exception{
        ApplicationHome h = new ApplicationHome(TaikeCmsApplication.class);
        String src = h.getSource().getParentFile().getParentFile().getParentFile().getPath()
                +"/libs/"+sourceJarName+".jar";
        log.info("resrouce path:{}",src);

        XBoot.encrypt(src,
                src.replace(sourceJarName,"encrypted"),
                XKit.key("tikr2021"));
    }

    private static void decode() throws Exception{
        ApplicationHome h = new ApplicationHome(TaikeCmsApplication.class);
        String src = h.getSource().getParentFile().getParentFile().getParentFile().getPath()
                +"/libs/encryed.jar";
        log.info("resrouce path:{}",src);

        XBoot.decrypt(src,src.replace("encryed","tikr-statistics-1.0-SNAPSHOT"),
                "tokoy");
    }

    private static void printbs(){
        byte[] sb = new byte[]{13, 10};
        log.info("{}",new String(sb));

    }

}
