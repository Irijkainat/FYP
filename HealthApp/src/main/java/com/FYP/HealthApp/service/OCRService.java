package com.FYP.HealthApp.service;

import com.FYP.HealthApp.model.Field;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OCRService {

    private final Tesseract tesseract;

    public OCRService() {
        tesseract = new Tesseract();


        tesseract.setDatapath("C:/Program Files/Tesseract-OCR/tessdata");


        System.setProperty("TESSDATA_PREFIX", "C:/Program Files/Tesseract-OCR/tessdata");

        tesseract.setLanguage("eng");

        // Optional: Debug log
        System.out.println("Tesseract setup complete");
    }

    public String extractText(File file, List<Field> fields) {

      Map<String,String>  extractedData=new HashMap<>();

        try {
            System.out.println("Running OCR on: " + file.getAbsolutePath());
            String result = tesseract.doOCR(file);
            System.out.println("OCR Result:\n" + result);
        extractedData  =  extractRelevantTests(result,fields);
            System.out.println("Map data"+extractedData);
            return result;
        } catch (TesseractException e) {
            e.printStackTrace();
            return "OCR Error: " + e.getMessage();
        }
    }



    public Map<String, String> extractRelevantTests(String ocrText, List<Field> fields) {
        Map<String, String> extracted = new LinkedHashMap<>();

        String[] lines = ocrText.split("\\r?\\n");
        for (String line : lines) {
            String cleanedLine = line.replaceAll("\\s+", " ").trim();

            for (Field test : fields) {
                String testName = test.getFieldName().trim();
                if (cleanedLine.toUpperCase().contains(testName.toUpperCase())) {
                    // Regex to match testName followed by number and unit
                    Pattern pattern = Pattern.compile(
                            "(?i)" + Pattern.quote(testName) + "\\s+(\\d+(\\.\\d+)?)\\s*([a-zA-Zµ/]+)?"
                    );
                    Matcher matcher = pattern.matcher(cleanedLine);
                    if (matcher.find()) {
                        String value = matcher.group(1); // e.g., "5.51"
                        String unit = matcher.group(3) != null ? matcher.group(3) : "";
                        extracted.put(testName, value + " " + unit);
                    } else {
                        extracted.put(testName, "Matched line but no value found");
                    }
                    break; // move to next line
                }
            }
        }

        return extracted;
    }



}
