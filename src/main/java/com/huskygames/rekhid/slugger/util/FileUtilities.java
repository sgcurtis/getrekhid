package com.huskygames.rekhid.slugger.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtilities {
    public static void copyFileOutOfJar(String pathInJar, String outputFile) throws IOException {
        InputStream ddlStream = FileUtilities.class.getClassLoader().getResourceAsStream(pathInJar);

        try (FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] buf = new byte[2048];
            int r;
            while(-1 != (r = ddlStream.read(buf))) {
                fos.write(buf, 0, r);
            }
        }
    }
}
