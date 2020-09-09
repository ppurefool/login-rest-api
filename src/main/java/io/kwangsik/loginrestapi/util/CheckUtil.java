package io.kwangsik.loginrestapi.util;

public class CheckUtil {
    // org.apache.commons.lang3.StringUtils.isBlank() 참조
    public static boolean isBlank(final CharSequence cs) {
        final int length = length(cs);

        if (0 == length) return true;
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    // org.apache.commons.lang3.StringUtils.isNotBlank() 참조
    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    // private ----------
    // org.apache.commons.lang3.StringUtils.length() 참조
    private static int length(final CharSequence cs) {
        return (null == cs ? 0 : cs.length());
    }
}