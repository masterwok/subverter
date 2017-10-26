package contracts;

import java.io.IOException;


/**
 * This contract defines methods for converting a subtitle input file to
 * the specified output file subtitle type.
 */
public interface Converter {

    /**
     * Convert the input file into a VTT file.
     *
     * @param inputFilePath  The input file path.
     * @param outputFilePath The output file path.
     * @throws IOException Thrown when there is a failure accessing files.
     */
    void toVtt(String inputFilePath, String outputFilePath) throws IOException;

    /**
     * Convert the input file into a SRT file.
     *
     * @param inputFilePath  The input file path.
     * @param outputFilePath The output file path.
     * @throws IOException Thrown when there is a failure accessing files.
     */
    void toSrt(String inputFilePath, String outputFilePath) throws IOException;

}
