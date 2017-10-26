package helpers;

import constants.SubType;
import exceptions.ConversionNotSupportedException;

/**
 * A simple helper class that provides convenience methods for SubType.
 */
public final class SubTypeHelper {

    private SubTypeHelper() {
    }

    /**
     * Get the subtitle type from the provided file path.
     *
     * @param filePath The file path.
     * @return The SubType value.
     * @throws ConversionNotSupportedException
     */
    public static SubType getType(String filePath) throws ConversionNotSupportedException {
        if (filePath == null) {
            return null;
        }

        try {
            return SubType.valueOf(filePath.substring(
                    filePath.lastIndexOf(".") + 1,
                    filePath.length()
            ).toUpperCase());
        } catch (Exception ignored) {
            throw new ConversionNotSupportedException(filePath);
        }
    }

}
