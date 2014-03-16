
package org.modelica.mdt.ui.util;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.ui.IEditorInput;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaClass.Restriction;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.CompilerInstantiationException;
import org.modelica.mdt.core.compiler.ConnectException;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.InvocationError;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.CompilerProxy;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaComponent;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.modelica.mdt.ui.text.ModelicaKeywords;

public class AnalyzeRegion {

	private static final boolean DEBUG = false;

	public String getWord() {
		return word;
	}

	public boolean identifierFound() {
		return identifierFound;
	}

	public String performAnalysis(IRegion region, IDocument document, ModelicaEditor fEditor) {
		this.word = null;
		this.identifierFound = false;
		String info = null;

		if (region != null) {
			int regionLength = region.getLength();
			int regionOffset = region.getOffset();

			try {
				if (regionLength > -1) {
					word = document.get(regionOffset, regionLength);
					int offs = 1;
					int position = regionOffset - offs;
					char ch = position >= 0 ? document.getChar(position) : '\0';
					while (position >= 0 && (Character.isJavaIdentifierPart(ch) || ch == '.')) {
						word = String.valueOf(ch) + word;
						++offs;
						position = regionOffset - offs;
						ch = position >= 0 ? document.getChar(position) : '\0';
					}
				}
			}
			catch (BadLocationException x) {
				x.printStackTrace();
			}

			if (word != null) {
				if (DEBUG) System.out.println("Handling word \"" + word + "\".");
				info = handleWord(word, regionOffset, fEditor);
			}
		}

		if (DEBUG) System.out.println("Finally returning \"" + info + "\" from performAnalysis.");

		return info;
	}

	private String handleWord(String word, int offset, ModelicaEditor fEditor) {
		if (DEBUG) System.out.println("handleWord called with word = \"" + word + "\".");
		String info = null;

		// if is a keyword don't bother!
		if (ModelicaKeywords.isKeyword(word)) {
			info = "keyword: " + word;
		}
		else if (Character.isDigit(word.charAt(0))) {
			info = "number: " + word;
		}
		else if (fEditor != null) {
			// else try to go for the full class name
			info = handleFile(word, offset, fEditor);

			if (info != null && !info.equals(word)) {
				identifierFound = true;
			}
		}

		return info;
	}

	private String handleFile(String word, int offset, ModelicaEditor fEditor) {
		if (DEBUG) System.out.println("handleFile called with word = \"" + word + "\".");
		String info = null;

		IModelicaSourceFile file = getFile(fEditor);

		if (file != null) {
			IModelicaElement c = fEditor.getElementAt(offset);

			while (c != null) {
				try {
					info = handleKids(word, c);

					if (info != null) {
						return info;
					}
				}
				catch (CoreException e) {
					ErrorManager.showCoreError(e);
				}
				catch (CompilerException e) {
					ErrorManager.showCompilerError(e);
				}
				catch (Exception e) {
					ErrorManager.logError(e);
				}

				// see if the word is the same as the class
				if (word.equals(c.getElementName())) {
					if (DEBUG) System.out.println("Lookup: " + c.getFullName());
					info = getInformation(c.getFullName());
					if (info != null) {
						if (DEBUG) System.out.println("(see if the word is the same as the class) Returning \"" + info + "\".");
						return info;
					}
				}

				// try to put the word inside the class
				if (DEBUG) System.out.println("Lookup: " + c.getElementName() + "." + word);
				info = getInformation(c.getElementName() + "." + word);

				if (info != null) {
					if (DEBUG) System.out.println("(try to put the word inside the class) Returning \"" + info + "\".");
					return info;
				}

				// try to go from the class to the TOP level and search for children in
				// any of the classes until then.
				c = c.getParent();
			}
		}

		if (info == null) {
			// lastly try to see if we can solve the name directly
			if (DEBUG) System.out.println("Lookup: " + word);
			info = getInformation(word);
			if (info == null) {
				info = word;
			}
		}

		return info;
	}

	private IModelicaSourceFile getFile(ModelicaEditor fEditor) {
		IModelicaSourceFile file = null;

		if (fEditor instanceof ModelicaEditor) {
			IModelicaElement me = fEditor.getEditorInputModelicaElement();

			if (me != null) {
				file = me.getSourceFile();
			}
		}

		if (file == null) {
			IEditorInput input = fEditor.getEditorInput();

			if (input instanceof ModelicaElementEditorInput) {
				file = ((ModelicaElementEditorInput)input).getSourceFile();
			}
		}

		return file;
	}

	private String handleKids(String word, IModelicaElement c)
			throws ConnectException, UnexpectedReplyException, InvocationError, CompilerInstantiationException, CoreException {
		String info = null;
		Collection<? extends IModelicaElement> kids = null;

		if (c instanceof IModelicaClass) {
			IModelicaClass modelicaClass = (IModelicaClass)c;
			kids = modelicaClass.getChildren();
		}
		else if (c instanceof IModelicaSourceFile) {
			IModelicaSourceFile modelicaSourceFile = (IModelicaSourceFile)c;
			kids = modelicaSourceFile.getChildren();
		}

		// try to see the components of the class:
		if (kids != null) {
			for (final IModelicaElement comp : kids) {
				if (comp == null) {
					continue;
				}

				String elementName = comp.getElementName();

				if (DEBUG) System.out.println("Searching childen: " + elementName);
				if (elementName.equals(word)) {
					if (comp instanceof IModelicaComponent) {
						if (DEBUG) System.out.println("Found component child: " + comp.getFullName() + "." + word);
						info = getInformation(c.getFullName() + "." + word);
						if (info != null) {
							if (DEBUG) System.out.println("Returning \"" + info + "\".");
							return info;
						}

						ModelicaComponent cmp = (ModelicaComponent)comp;
						info = cmp.getVisibility().name().toLowerCase() + " " + cmp.getTypeName();
						return info;
					}
					else if (comp instanceof IModelicaClass) {
						if (DEBUG) System.out.println("Found child: " + comp.getFullName() + "." + word);
						info = getInformation(c.getFullName() + "." + word);
						if (info != null) {
							if (DEBUG) System.out.println("Returning \"" + info + "\".");
							return info;
						}
					}
				}
				// not equal, try directly
				if (comp instanceof IModelicaClass) {
					if (DEBUG) System.out.println("Trying inner class: " + comp.getFullName() + "." + word);
					info = getInformation(comp.getFullName() + "." + word);
					if (info != null) {
						if (DEBUG) System.out.println("(not equal, try directly) Returning \"" + info + "\".");
						return info;
					}
				}
			}
		}

		return info;
	}

	private synchronized String getInformation(String className) {
		if (DEBUG) System.out.println("getInformation called with className = \"" + className + "\".");

		String info = null;
		// if the name starts with something fancy, just ignore it!
		char firstChar = className.charAt(0);
		if (Character.isJavaIdentifierStart(firstChar)) {
			try {
				ICompilerResult res = CompilerProxy.getClassString(className);
				info = res.getFirstResult().trim();

				if (info.startsWith("\"")) {
					info = info.substring(1);
				}

				if (info.endsWith("\"")) {
					info = info.substring(0, info.length() - 1);
				}

				info = info.trim();

				// un-escape the Modelica string
				info = info.replace("\\\"", "\"");
				info = info.replace("\\\\", "\\");

				if (!info.equals("")) {
					info += "\n";
				}
				else {
					IClassInfo classAttributes = CompilerProxy.getClassInfo(className);
					Restriction restriction = classAttributes.getRestriction();
					info = restriction.name();
				}
			}
			// The following exceptions do happen, at least UnexpectedReplyException, and
			// when they do occur it's important that we set info to null (it might be
			// an empty string), so the caller of this function can continue its lookup
			// work.
			catch (UnexpectedReplyException e) {
				info = null;
				//e.printStackTrace();
			}
			catch (Exception e) {
				info = null;
				//e.printStackTrace();
			}
		}

		return info;
	}

	private String word = null;
	private boolean identifierFound = false;
}
