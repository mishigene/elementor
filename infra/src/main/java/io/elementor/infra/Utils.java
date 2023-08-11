package io.elementor.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Utils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);
    public static final String YYYYMMDD = "yyyy-MM-dd";
    public static final String DDMMMYYYY = "dd MMM, yyyy";

    // region Random String Generators

    /**
     * @param length To specify the number of characters in the randomly generated String.
     * @return A randomly generated String consisting of Letters Upper case and Lower case, and digits.
     */
    public static String randomAlphanumericString(int length) {

        if (length < 1) throw new IllegalArgumentException();

        // a string containing [a-z] & [A-Z] & [0-9]
        final String DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String randomString = "";

        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randomCharAt = random.nextInt(DATA.length());
            char randomChar = DATA.charAt(randomCharAt);
            randomString += randomChar;
        }
        return randomString;
    }

    /**
     * @param length To specify the number of letters in the randomly generated String.
     * @return A randomly generated String consisting of Letters Upper case and Lower case only.
     */
    public static String randomAlphabeticString(int length) {

        if (length < 1) throw new IllegalArgumentException();

        // a string containing [a-z] & [A-Z] & [0-9]
        final String DATA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        String randomString = "";

        for (int i = 0; i < length; i++) {
            Random random = new Random();
            int randomCharAt = random.nextInt(DATA.length());
            char randomChar = DATA.charAt(randomCharAt);
            randomString += randomChar;
        }
        return randomString;
    }

    /**
     * @param length To specify the number of digits in the randomly generated String.
     * @return A randomly generated String consisting of digits only.
     */
    public static String randomNumericString(int length) {
        if (length < 1) throw new IllegalArgumentException();
        String randomString = "";
        for (int i = 0; i < length; i++) {
            randomString += String.valueOf(getRandomNumber(9));
        }
        return randomString;
    }

    public static Integer getRandomIndexFromList(int number) {
        return (int) (number * Math.random());
    }

    // endregion

    // region json

    /**
     * Maps a json to a class/entity
     *
     * @param json the json as a string that will be mapped
     * @param <T>  the class type than will be returned
     * @return an object of the requested type
     */
    public static <T> T fromJson(Class<T> type, String json) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return t;
    }

    /**
     * ???
     *
     * @param type
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T fromJson(TypeReference<T> type, String json) {
        ObjectMapper mapper = new ObjectMapper();
        T t = null;
        try {
            t = mapper.readValue(json, type);
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return t;
    }

    /**
     * Converts an object/Entity/class to a json.  This is used often to send data via REST API
     *
     * @param object The object to be converted
     * @return a string with json format
     */
    public static String toJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        String s = "";
        try {
            s = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return s;
    }
    //endregion

    // region Files

    /**
     * Receives an object of type File and reads the entire content of the file
     *
     * @param file an object of type File the in pointing to an existing file
     * @return the content of the file is returned as a string in UTF-8 format
     */
    public static String readFromFile(File file) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            fileInputStream.read(data);
            fileInputStream.close();
            return new String(data, StandardCharsets.UTF_8.toString());
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    /**
     * Receives a path to a file and creates a File object pointing to that file
     *
     * @param fileName Full path to a file to be read
     * @return a string with the file's contents
     */
    public static String readFromFile(String fileName) {
        File file = new File(fileName);
        return readFromFile(file);
    }

    public static List<String> readLinesFromFile(File file) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
            return lines;
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    public static void writeStringToFile(String content, File file) {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(content);
            bufferedWriter.close();
        } catch (IOException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public static void writeObjectToFile(Object object, File file) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String content = objectMapper.writeValueAsString(object);
            writeStringToFile(content, file);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }
    }

    public static String getSubStringWithRegex(Pattern pattern, String string) {
        Matcher m = pattern.matcher(string);
        String result = "";
        while (m.find()) {
            result += m.group();
        }
        return result;
    }

    public static String removeTrailingZeros(double d) {
        return String.valueOf(d).replaceAll("[0]*$", "").replaceAll(".$", "");
    }

    public static String generateMd5(String str) {
        String result = normalizeString(str).trim();
        return MD5(result);
    }

    public static String MD5(String strToHash) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(strToHash.getBytes());
            StringBuffer sb = new StringBuffer();

            for (byte anArray : array) {
                sb.append(Integer.toHexString((anArray & 0xFF) | 0x100).substring(1, 3));
            }

            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
        }

        return null;
    }

    // Remove accents and non-alphanumeric characters from a string, trim it and lower-case it
    public static String normalizeString(String text) {
        return Normalizer.normalize(text, Normalizer.Form.NFD).replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[ '\\\"!@#$%^&*\\(\\)\\[\\]{}<>\\/\\,\\.\\-\\_\\=\\\\+]", "").trim().toLowerCase();
    }




    private static DateTimeFormatter getDateFormat(String pattern) {
        return DateTimeFormatter.ofPattern(pattern);
    }

    public static boolean isOneYearPeriod(String startDate, String endDate) {
        DateTimeFormatter format = getDateFormat(DDMMMYYYY);
        LocalDate initialDate = LocalDate.parse(startDate, format);
        LocalDate targetDate = LocalDate.parse(endDate, format).minusDays(1);
        LocalDate calculatedDate = initialDate.minusYears(1);
        return calculatedDate.equals(targetDate);
    }

    public static void sleep(int milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            logger.error(e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            e.printStackTrace();
        }
    }



    public static StringBuilder inputStreamToString(InputStream is) throws IOException {
        String line = "";
        StringBuilder total = new StringBuilder();

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            total.append(line);
        }
        // Return full string
        return total;
    }

    public static Integer getRandomNumber(int number) {
        return (int) (number * Math.random());
    }


}
