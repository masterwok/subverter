package exceptions;

/**
 * This exception is thrown when an input file extension type
 * is not supported for conversion.
 */
public class ConversionNotSupportedException extends Exception {

    /**
     * Create a new ConversionNotSupportedException instance.
     *
     * @param filePath The file path of the file with an unsupported extension.
     */
    public ConversionNotSupportedException(String filePath) {
        super(filePath);
    }

}
