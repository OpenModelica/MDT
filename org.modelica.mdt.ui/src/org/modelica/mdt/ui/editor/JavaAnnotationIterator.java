/*******************************************************************************
 * Copyright (c) 2000, 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.modelica.mdt.ui.editor;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.ui.texteditor.MarkerAnnotation;


/**
 * Filters problems based on their types.
 */
public class JavaAnnotationIterator implements Iterator {

	private Iterator fIterator;
	private Annotation fNext;
	private boolean fReturnAllAnnotations;


	/**
	 * Returns a new JavaAnnotationIterator.
	 * @param parent the parent iterator to iterate over annotations
	 * @param returnAllAnnotations whether to return all annotations or just problem annotations
	 */
	public JavaAnnotationIterator(Iterator parent, boolean returnAllAnnotations) {
		fReturnAllAnnotations= returnAllAnnotations;
		fIterator= parent;
		skip();
	}

	private void skip() {
		while (fIterator.hasNext()) {
			Annotation next= (Annotation) fIterator.next();

			if (next.isMarkedDeleted())
				continue;

			if (fReturnAllAnnotations || isProblemMarkerAnnotation(next)) {
				fNext= next;
				return;
			}
		}
		fNext= null;
	}

	private static boolean isProblemMarkerAnnotation(Annotation annotation) {
		if (!(annotation instanceof MarkerAnnotation))
			return false;
		try {
			return(((MarkerAnnotation)annotation).getMarker().isSubtypeOf(IMarker.PROBLEM));
		} catch (CoreException e) {
			return false;
		}
	}

	/*
	 * @see Iterator#hasNext()
	 */
	public boolean hasNext() {
		return fNext != null;
	}

	/*
	 * @see Iterator#next()
	 */
	public Object next() {
		try {
			return fNext;
		} finally {
			skip();
		}
	}

	/*
	 * @see Iterator#remove()
	 */
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
