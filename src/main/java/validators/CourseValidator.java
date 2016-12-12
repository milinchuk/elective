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

    public CourseValidator(String textRegex) {
        this.textPattern = Pattern.compile(textRegex);
    }

    @Override
    public boolean validate(Course course, Errors errors) {
        if (course != null) {
            // name validate
            if (!textPattern.matcher(course.getName()).matches()) {
                setError(errors, AttributesHolder.NAME, ErrorsMessages.COURSE_NAME_INVALID);
            }

            if (!textPattern.matcher(course.getAbout()).matches()) {
                setError(errors, AttributesHolder.ABOUT, ErrorsMessages.ABOUT_INVALID);
            }

            // dates validate
            if (course.getStartDate() == null || (course.getStartDate().compareTo(course.getEndDate()) == 1)) {
                setError(errors, AttributesHolder.START_DATE, ErrorsMessages.DATE_INVALID);
            }

            if (course.getEndDate() == null) {
                setError(errors, AttributesHolder.END_DATE, ErrorsMessages.DATE_INVALID);
            }
        } else {
            setError(errors, AttributesHolder.COURSE, ErrorsMessages.INVALID);
        }

        return errors.getResult();
    }

    @Override
    public boolean validate(Course course) {
        // name validate
        if ( (course == null) || (!textPattern.matcher(course.getName()).matches()) ||
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
