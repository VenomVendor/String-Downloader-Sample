/**
 * Author		:	VenomVendor
 * Dated		:	7 Nov, 2013 - 2:45:48 AM
 * Project		:	String-Downloader
 * Contact		:	info@VenomVendor.com
 * URL			:	https://www.google.com/search?q=VenomVendor
 * Copyright(c)	:	2013-Present, VenomVendor.
 * License		:	This work is licensed under Attribution-NonCommercial 3.0 Unported (CC BY-NC 3.0).
 *					License info at http://creativecommons.org/licenses/by-nc/3.0/deed.en_US
 *					Read More at http://creativecommons.org/licenses/by-nc/3.0/legalcode
 **/

package vee.vee.string.downloader.sample.utils;

import java.util.Locale;

public class TagGen {
    public static String getTag(Class<?> cls) {
        return cls.getSimpleName().toString().trim().toUpperCase(Locale.UK).toString();
    }
}
