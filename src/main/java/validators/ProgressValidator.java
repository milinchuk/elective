package validators;

import i18n.messages.error.ErrorsMessages;
import model.entity.Progress;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * Created by click on 12/1/2016.
 */
public class ProgressValidator implements Validator<Progress> {
    private Pattern notePattern;
    private Pattern markPattern;

    public ProgressValidator(String noteRegex, String markRegex) {
        notePattern = Pattern.compile(noteRegex);
        markPattern = Pattern.compile(markRegex);
    }

    @Override
    public boolean validate(Progress progress, Errors errors) {
        if (!notePattern.matcher(progress.getNote()).matches()) {
            setError(errors, AttributesHolder.NOTE, ErrorsMessages.INVALID_NOTE);
        }

        if (!markPattern.matcher(progress.getMark()).matches()) {
            setError(errors, AttributesHolder.MARK, ErrorsMessages.INVALID_MARK);
        }

        return errors.getResult();
    }

    @Override
    public boolean validate(Progress progress) {
        if ( (!notePattern.matcher(progress.getNote()).matches()) ||
                (!markPattern.matcher(progress.getMark()).matches())) {
            return false;
        }
        return true;
    }

    private void setError(Errors errors, String attribute, String errorMessage) {
        errors.setResult(false);
        errors.addMessage(attribute, errorMessage);
    }
}
