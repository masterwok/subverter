package converters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;

import contracts.Converter;

/**
 * This converter implementation converts VTT subtitle files into
 * various other subtitle formats.
 * <p>
 * Supported Types:
 * - SRT
 * - VTT (copies input file to output file path)
 */
public class VttConverter implements Converter {

    private static VttConverter instance;
    private static final Object lock = new Object();

    private static final String vttTimestampRegex = "^(\\d{2}:\\d{2}:\\d{2})(.)(\\d+\\s+-->\\s+\\d+:\\d+:\\d+)(.)(\\d+)";
    private static final String srtTimestampRegexReplace = "$1,$3,$5";
    private static final Pattern webVttHeaderPattern = Pattern.compile("^WEBVTT\\s*");

    private VttConverter() {
    }

    /**
     * Get a singleton instance of the SrtConverter.
     *
     * @return An SrtConvert instance.
     */
    public static VttConverter getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new VttConverter();
            }

            return instance;
        }
    }


    @Override
    public void toVtt(
            String inputFilePath,
            String outputFilePath
    ) throws IOException {
        Files.copy(
                Paths.get(inputFilePath),
                Paths.get(outputFilePath)
        );
    }

    @Override
    public void toSrt(
            String inputFilePath,
            String outputFilePath
    ) throws IOException {
        FileReader fileReader = new FileReader(new File(inputFilePath));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        PrintWriter printWriter = new PrintWriter(new FileWriter(outputFilePath, false));
        String inputLine;

        while ((inputLine = bufferedReader.readLine()) != null) {
            if (webVttHeaderPattern.matcher(inputLine).matches()) {
                continue;
            }

            printWriter.println(inputLine.replaceAll(
                    vttTimestampRegex,
                    srtTimestampRegexReplace)
            );
        }

        printWriter.close();
        fileReader.close();

    }

}
