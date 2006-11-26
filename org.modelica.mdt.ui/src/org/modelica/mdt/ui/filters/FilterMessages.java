/******************************************************************************
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se/~adrpo]
 * Copyright (c) 2006 Adrian Pop [adrpo@ida.liu.se]
 * All rights reserved. 
 * Permission to use, copy, modify, and distribute this software and
 * its documentation for NON-COMMERCIAL purposes and without fee is hereby 
 * granted, provided that this copyright notice appear in all copies and 
 * that both the copyright notice and this permission notice and warranty
 * disclaimer appear in supporting documentation, and that the name of
 * The Author not be used in advertising or publicity pertaining to
 * distribution of the software without specific, written prior permission.
 * 
 * COMMERCIAL use, copy, modification and distribution 
 * is NOT permitted without prior written agreement with 
 * Adrian Pop [adrpo@ida.liu.se].
 * 
 * THE AUTHOR DISCLAIMS ALL WARRANTIES WITH REGARD TO THIS SOFTWARE,
 * INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS.
 * IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, INDIRECT OR
 * CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM LOSS
 * OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE
 * OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
 *****************************************************************************/

package org.modelica.mdt.ui.filters;

import org.eclipse.osgi.util.NLS;

public final class FilterMessages extends NLS {

	private static final String BUNDLE_NAME= "org.modelica.mdt.ui.filters.FilterMessages";//$NON-NLS-1$

	private FilterMessages() {
		// Do not instantiate
	}

	public static String CustomFiltersDialog_title;
	public static String CustomFiltersDialog_patternInfo;
	public static String CustomFiltersDialog_enableUserDefinedPattern;
	public static String CustomFiltersDialog_filterList_label;
	public static String CustomFiltersDialog_description_label;
	public static String CustomFiltersDialog_SelectAllButton_label;
	public static String CustomFiltersDialog_DeselectAllButton_label;
	public static String OpenCustomFiltersDialogAction_text;
	public static String FilterDescriptor_filterDescriptionCreationError_message;
	public static String FilterDescriptor_filterCreationError_title;
	public static String FilterDescriptor_filterCreationError_message;

	static {
		NLS.initializeMessages(BUNDLE_NAME, FilterMessages.class);
	}
}