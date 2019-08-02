/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Herramientas;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author SerGenis
 */
public class PropertiesIO {

    public static Properties read(java.net.URL url)
            throws IOException {
        Properties props;
        try (InputStream is = url.openStream()) {
            props = new Properties();
            props.load(is);
        }
        return props;
    }

    public static Properties read(File file)
            throws IOException {
        return read(file.toURI().toURL());
    }

    public static Properties readXML(java.net.URL url)
            throws IOException {
        Properties props;
        try (InputStream is = url.openStream()) {
            props = new Properties();
            props.loadFromXML(is);
        }
        return props;
    }

    public static Properties readXML(File file)
            throws IOException {
        return readXML(file.toURI().toURL());
    }

    public static void write(Properties props, File file)
            throws FileNotFoundException, IOException {
        write(props, file, null);
    }

    public static void write(Properties props, File file, String comments)
            throws IOException {
        try (FileOutputStream ops = new FileOutputStream(file, false)) {
            props.store(ops, comments);
        }
    }

    public static void writeXML(Properties props, File file, String comments)
            throws IOException {
        try (OutputStream os = new FileOutputStream(file, false)) {
            props.storeToXML(os, comments);
        }
    }

    public static void writeXML(Properties props, File file)
            throws IOException {
        writeXML(props, file, null);
    }
}
