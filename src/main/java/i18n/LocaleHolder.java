package i18n;

import utils.constants.AttributesHolder;

import java.util.Locale;

/**
 * This class holder current, default locale. And array with existing locales in application.
 *
 * @author Anastasia Milinchuk
 * @see java.util.Locale
 */
public class LocaleHolder {
    /**
     * This object contain current locale in application
     */
    private Locale currentLocale;

    /**
     * Array of locales that support application
     */
    public static final Locale[] SUPPORTED = { new Locale(AttributesHolder.EN, AttributesHolder.EN.toUpperCase()),
            new Locale(AttributesHolder.RU, AttributesHolder.RU.toUpperCase()),
            new Locale(AttributesHolder.UA, AttributesHolder.UA.toUpperCase()) };

    /**
     * Default locale
     */
    public static final Locale DEFAULT = new Locale(AttributesHolder.EN, AttributesHolder.EN.toUpperCase());

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
