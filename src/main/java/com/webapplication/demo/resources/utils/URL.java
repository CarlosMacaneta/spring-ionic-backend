package com.webapplication.demo.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author CarlosMacaneta
 */
public class URL {
    
    public static String decodeParam(String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "";
        }
    }
    
    public static List<Integer> decodeIntList(String n) {
        return Arrays.asList(n.split(",")).stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }
}
