package validators;

import i18n.messages.error.ErrorsMessages;
import model.entity.Course;
import utils.constants.AttributesHolder;
import validators.entity.Errors;

import java.util.regex.Pattern;

/**
 * Created by click on 12/1/2016.
 */
public class CourseValidator implements Validator<Course> {
    private Pattern textPattern;
    private ParameterValidator parameterValidator;

    public CourseValidator(String textRegex, ParameterValidator parameterValidator) {
        this.textPattern = Pattern.compile(textRegex);
        this.parameterValidator = parameterValidator;
    }

    @Override
    public boolean validate(Course course, Errors errors) {
        // name validate
        if (!textPattern.matcher(course.getName()).matches()) {
            setError(errors, AttributesHolder.NAME, ErrorsMessages.COURSE_NAME_INVALID);
        }

        if(!textPattern.matcher(course.getAbout()).matches()) {
            setError(errors, AttributesHolder.ABOUT, ErrorsMessages.COURSE_NAME_INVALID);
        }

        // dates validate
        if (course.getStartDate().compareTo(course.getEndDate()) == 1) {
            setError(errors, AttributesHolder.START_DATE, ErrorsMessages.DATE_INVALID);
        }

        return errors.getResult();
    }

    @Override
    public boolean validate(Course course) {
        if ( (!textPattern.matcher(course.getName()).matches()) ||
                (!textPattern.matcher(course.getAbout()).matches()) ||
                (course.getStartDate().compareTo(course.getEndDate()) == 1) ) {
            return false;
        }
        return true;
    }

    private void setError(Errors errors, String attribute, String errorMessage) {
        errors.setResult(false);
        errors.addMessage(attribute, errorMessage);
    }

}
