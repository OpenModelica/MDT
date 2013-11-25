/*******************************************************************************
 * Copyright (c) 2002-2007
 * @author Adrian Pop [adrpo@ida.liu.se]
 * All rights reserved.
 *******************************************************************************/
package org.modelica.mdt.ui.hover;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.source.DefaultAnnotationHover;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.ui.texteditor.MarkerAnnotation;
import org.modelica.mdt.internal.core.ErrorManager;

/**
 * Determines all markers for the given line and collects, concatenates, and formats
 * returns their messages in HTML.
 *
 * @since 0.7.0
 */

public class ModelicaAnnotationHover extends DefaultAnnotationHover {

//	/*
//	 * Formats a message as HTML text.
//	 */
//	protected String formatSingleMessage(String message) {
//		StringBuffer buffer= new StringBuffer();
//		HTMLPrinter.addPageProlog(buffer);
//		HTMLPrinter.addParagraph(buffer, HTMLPrinter.convertToHTMLContent(message));
//		HTMLPrinter.addPageEpilog(buffer);
//		return buffer.toString();
//	}
//
//	/*
//	 * Formats several message as HTML text.
//	 */
//	protected String formatMultipleMessages(List messages) {
//		StringBuffer buffer= new StringBuffer();
//		HTMLPrinter.addPageProlog(buffer);
//		HTMLPrinter.addParagraph(buffer, HTMLPrinter.convertToHTMLContent("Multiple Markups at this line!"));
//
//		HTMLPrinter.startBulletList(buffer);
//		Iterator e= messages.iterator();
//		while (e.hasNext())
//			HTMLPrinter.addBullet(buffer, HTMLPrinter.convertToHTMLContent((String) e.next()));
//		HTMLPrinter.endBulletList(buffer);
//
//		HTMLPrinter.addPageEpilog(buffer);
//		return buffer.toString();
//	}

	/**
	 * Gets the hoverInfo attribute of the AnnotationHover object
	 *
	 * @param sourceViewer  Description of the Parameter
	 * @param lineNumber    Description of the Parameter
	 * @return              The hoverInfo value
	 */
	public String getHoverInfo(ISourceViewer sourceViewer, int lineNumber) {
		/* if we have something, just return it */
		String hoverInfo = super.getHoverInfo(sourceViewer, lineNumber);

		if (hoverInfo != null) {
			return hoverInfo;
		}

		List<IMarker> markers = this.getModelicaAnnotationsForLine(sourceViewer, lineNumber);

		if (markers != null) {
			if (markers.size() == 1) {
				IMarker marker = markers.get(0);
				String message = marker.getAttribute(IMarker.MESSAGE, (String) null);
				if (message != null && message.trim().length() > 0) {
					return formatSingleMessage(message);
				}
			}
			else {
				List<String> messages = new ArrayList<String>();

				for (IMarker marker : markers) {
					String message = marker.getAttribute(IMarker.MESSAGE, (String) null);
					if (message != null) {
						String trimmedMessage = message.trim();

						if (trimmedMessage.length() > 0) {
							messages.add(trimmedMessage);
						}
					}
					if (messages.size() == 1) {
						return this.formatSingleMessage(messages.get(0));
					}
					if (messages.size() > 1) {
						return this.formatMultipleMessages(messages);
					}
				}
			}
		}

		return null;
	}

	/**
	 * Gets the ModelicaAnnotationsForLine attribute of the AnnotationHover object
	 *
	 * @param viewer  Description of the Parameter
	 * @param line    Description of the Parameter
	 * @return        The jSPAnnotationsForLine value
	 */
	protected List<IMarker> getModelicaAnnotationsForLine(ISourceViewer viewer, int line) {
		IAnnotationModel model = viewer.getAnnotationModel();

		if (model == null) {
			return null;
		}

		List<IMarker> markers = new ArrayList<IMarker>();
		Iterator<?> e = model.getAnnotationIterator();

		while (e.hasNext()) {
			Object o = e.next();

			if (o instanceof MarkerAnnotation) {
				MarkerAnnotation a = (MarkerAnnotation)o;

				try {
					Integer ln = (Integer) a.getMarker().getAttribute(IMarker.LINE_NUMBER);
					if (ln.intValue() == line + 1) {
						markers.add(a.getMarker());
					}
				}
				catch (CoreException ex) {
					ErrorManager.logError(ex);
				}
			}
		}
		return markers;
	}
}
