package edu.wit.dcsn.comp2000.queueapp.config;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**@author Zachary Shelton, Taken from a previous project*/
public class ConfigHandler {

    private static final List<Parser> parserList = new ArrayList<>();

    public static Config loadConfig(final File file, final Config config) throws IOException, IllegalAccessException {
        final Map<String, String> configMap = readConfigAsMap(file);
        final Field[] fields = config.getClass().getDeclaredFields();
        for(final Field f : fields) {
            final ConfigTarget target = f.getAnnotation(ConfigTarget.class);
            if(target == null) {
                continue;
            }
            final String strValue = configMap.get(target.name());
//            System.out.println(f.getType());
            for(final Parser p : parserList) {
                if(!p.getClazz().equals(f.getType())) {
                    continue;
                }
                final Object value = p.parse(strValue);
                if(value == null) {
                    continue;
                }
                final boolean oldAccess = f.isAccessible();
                f.setAccessible(true);
                f.set(config, value);
                f.setAccessible(oldAccess);
//                System.out.println(String.format("Value for %s=%s", target.name(), value));
            }
        }
        return config;
    }

    private static Map<String, String> readConfigAsMap(final File file) throws IOException {
        final FileReader fileReader = new FileReader(file);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = null;
        final Map<String, String> map = new HashMap<>();
        while ((line = bufferedReader.readLine()) != null) {
            String trimmed = line.trim();
            if (trimmed == null || trimmed.length() <= 0 || trimmed.startsWith("#") || !trimmed.contains("=")) {
                continue;
            }
//            out.println(String.format("\"%s\"", trimmed));
            if(trimmed.contains("#")) {
                trimmed = trimmed.split("#")[0].trim();
            }
            final String[] split = trimmed.split("=");
            map.put(split[0], split[1]);
        }
        bufferedReader.close();
        fileReader.close();
        return map;
    }

    static {
        parserList.add(new Parser<Integer>(int.class) {
            @Override
            public Integer parse(String input) {
                return Integer.parseInt(input);
            }
        });
        parserList.add(new Parser<Double>(double.class) {
            @Override
            public Double parse(String input) {
                return Double.parseDouble(input);
            }
        });
        parserList.add(new Parser<Float>(float.class) {
            @Override
            public Float parse(String input) {
                return Float.parseFloat(input);
            }
        });
        parserList.add(new Parser<String[]>(String[].class){

            @Override
            public String[] parse(String input) {
                final String[] tempArray = input.split(",");
                final String[] newArray = new String[tempArray.length];
                for(int i = 0; i < tempArray.length; i++) {
                    newArray[i] = tempArray[i].trim();
                }
                return newArray;
            }
        });
        parserList.add(new Parser<Long>(long.class){

            @Override
            public Long parse(String input) {
                return Long.parseLong(input);
            }
        });
        parserList.add(new Parser<int[]>(int[].class) {
            @Override
            public int[] parse(String input) {
                final String[] tempArray = input.split(",");
                final int[] newArray = new int[tempArray.length];
                for(int i = 0; i < tempArray.length; i++) {
                    newArray[i] = Integer.parseInt(tempArray[i].trim());
                }
                return newArray;
            }
        });
    }
}
