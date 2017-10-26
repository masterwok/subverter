import java.io.IOException;

import contracts.Converter;
import converters.SrtConverter;
import converters.VttConverter;
import exceptions.ConversionNotSupportedException;
import helpers.SubTypeHelper;


public class Subverter implements contracts.Subverter {

    @Override
    public void convert(
            String inputFilePath,
            String outputFilePath
    ) throws ConversionNotSupportedException, IOException {
        Converter converter = getConverter(inputFilePath);

        switch (SubTypeHelper.getType(outputFilePath)) {
            case SRT:
                converter.toSrt(inputFilePath, outputFilePath);
                break;
            case VTT:
                converter.toVtt(inputFilePath, outputFilePath);
                break;
        }
    }

    @Override
    public Converter getConverter(String filePath) throws ConversionNotSupportedException {
        switch (SubTypeHelper.getType(filePath)) {
            case VTT:
                return VttConverter.getInstance();
            case SRT:
                return SrtConverter.getInstance();
        }

        throw new ConversionNotSupportedException(filePath);
    }

}
