package seedu.address.storage;

import java.io.FileNotFoundException;
import java.nio.file.Path;

import javax.xml.bind.JAXBException;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.util.XmlUtil;

/**
 * Stores stocklist data in an XML file
 */
public class XmlFileStorage {
    /**
     * Saves the given stocklist data to the specified file.
     */
    public static void saveDataToFile(Path file, XmlSerializableStockList stockList)
            throws FileNotFoundException {
        try {
            XmlUtil.saveDataToFile(file, stockList);
        } catch (JAXBException e) {
            throw new AssertionError("Unexpected exception " + e.getMessage(), e);
        }
    }

    /**
     * Returns stock list in the file or an empty stock list
     */
    public static XmlSerializableStockList loadDataFromSaveFile(Path file) throws DataConversionException,
                                                                            FileNotFoundException {
        try {
            return XmlUtil.getDataFromFile(file, XmlSerializableStockList.class);
        } catch (JAXBException e) {
            throw new DataConversionException(e);
        }
    }

}
