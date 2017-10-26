package converters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

import contracts.Converter;

/**
 * This converter implementation converts SRT subtitle files into
 * various other subtitle formats.
 * <p>
 * Supported Types:
 * - VTT
 * - SRT (copies input file)
 */
public class SrtConverter implements Converter {

    private static SrtConverter instance;
    private static final Object lock = new Object();

    private static final String vttTimestampRegex = "^(\\d{2}:\\d{2}:\\d{2})(,)(\\d+\\s+-->\\s+\\d+:\\d+:\\d+)(,)(\\d+)";
    private static final String vttTimestampRegexReplace = "$1.$3.$5";
    private static final String webVttHeader = "WEBVTT";

    private SrtConverter() {
    }

    /**
     * Get a singleton instance of the SrtConverter.
     *
     * @return An SrtConvert instance.
     */
    public static SrtConverter getInstance() {
        synchronized (lock) {
            if (instance == null) {
                instance = new SrtConverter();
            }

            return instance;
        }
    }


    @Override
    public void toVtt(
            String inputFilePath,
            String outputFilePath
    ) throws IOException {
        FileReader fileReader = new FileReader(new File(inputFilePath));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        PrintWriter printWriter = new PrintWriter(new FileWriter(outputFilePath, false));
        String inputLine;

        printWriter.println(webVttHeader);

        while ((inputLine = bufferedReader.readLine()) != null) {
            printWriter.println(inputLine.replaceAll(
                    vttTimestampRegex,
                    vttTimestampRegexReplace
            ));
        }

        printWriter.close();
        fileReader.close();
    }

    @Override
    public void toSrt(
            String inputFilePath,
            String outputFilePath
    ) throws IOException {
        Files.copy(
                Paths.get(inputFilePath),
                Paths.get(outputFilePath)
        );

    }

}
