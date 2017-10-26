package contracts;

import java.io.IOException;

import exceptions.ConversionNotSupportedException;

/**
 * This contract provides methods for subtitle conversion.
 */
public interface Subverter {

    /**
     * Convert from one subtitle file type to another.
     *
     * @param inputFilePath  The input file path.
     * @param outputFilePath The output file path.
     * @throws ConversionNotSupportedException Thrown when conversion is not supported between
     *                                the two file paths.
     * @throws IOException            Thrown when an error occurs while accessing the supplied
     *                                file paths.
     */
    void convert(
            String inputFilePath,
            String outputFilePath
    ) throws ConversionNotSupportedException, IOException;

    /**
     * Get the convert associated with the provided file path.
     *
     * @param filePath The file path.
     * @return The Converter associated with the file path type.
     * @throws ConversionNotSupportedException Thrown when there is no supported converter
     *                                for the provided file path.
     */
    Converter getConverter(String filePath) throws ConversionNotSupportedException;

}
