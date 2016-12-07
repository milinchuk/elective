package i18n;

import utils.constants.AttributesHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by click on 11/25/2016.
 */
public class LocaleHolder {
    public static final Locale[] SUPPORTED = { new Locale(AttributesHolder.EN, AttributesHolder.EN.toUpperCase()),
            new Locale(AttributesHolder.RU, AttributesHolder.RU.toUpperCase()),
            new Locale(AttributesHolder.UA, AttributesHolder.UA.toUpperCase()) };

    private Locale currentLocale;
    public static final Locale DEFAULT = new Locale("en", "EN");

    public LocaleHolder(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }
}
