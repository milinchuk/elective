package validators;
import java.util.Date;

import i18n.messages.error.ErrorsMessages;
import org.apache.log4j.Logger;
import utils.constants.AttributesHolder;
import utils.constants.LoggingMessagesHolder;
import validators.entity.Errors;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Anastasia Milinchuk
 */
public class DateValidator implements Validator<String> {
    private static final Logger logger = Logger.getLogger(DateValidator.class);
    SimpleDateFormat formatter;

    public DateValidator(String dateFormat) {
        formatter = new SimpleDateFormat(dateFormat);
    }

    @Override
    public boolean validate(String dateInString, Errors errors) {
        if(!validate(dateInString)) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.START_DATE, ErrorsMessages.DATE_INVALID);
            return false;
        }
        return true;
    }

    @Override
    public boolean validate(String dateInString) {
        try {
            Date date = formatter.parse(dateInString);
            return true;
        } catch (ParseException e) {
            logger.info(LoggingMessagesHolder.ERROR_DATE);
            return false;
        }
    }
}
