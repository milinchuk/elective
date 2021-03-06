package validators;

import controller.i18n.messages.error.ErrorsMessages;
import model.entity.Progress;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * @author Anastasia Milinchuk
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
        if (progress != null) {
            if (!notePattern.matcher(progress.getNote()).matches()) {
                setError(errors, AttributesHolder.NOTE, ErrorsMessages.INVALID_NOTE);
            }

            if (!markPattern.matcher(progress.getMark()).matches()) {
                setError(errors, AttributesHolder.MARK, ErrorsMessages.INVALID_MARK);
            }
        } else {
            setError(errors, AttributesHolder.PROGRESS, ErrorsMessages.INVALID);
        }
        return errors.getResult();
    }

    @Override
    public boolean validate(Progress progress) {
        if ( (progress == null) || (!notePattern.matcher(progress.getNote()).matches()) ||
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
