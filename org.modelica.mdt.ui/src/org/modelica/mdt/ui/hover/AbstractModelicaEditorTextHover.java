
package org.modelica.mdt.ui.hover;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHoverExtension;
import org.eclipse.jface.text.ITextViewer;

import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.keys.IBindingService;
import org.eclipse.ui.part.FileEditorInput;

import org.modelica.mdt.core.CompilerProxy;
import org.modelica.mdt.core.ICompilerResult;
import org.modelica.mdt.core.IModelicaClass;
import org.modelica.mdt.core.IModelicaComponent;
import org.modelica.mdt.core.IModelicaElement;
import org.modelica.mdt.core.IModelicaSourceFile;
import org.modelica.mdt.core.compiler.CompilerException;
import org.modelica.mdt.core.compiler.IClassInfo;
import org.modelica.mdt.core.compiler.UnexpectedReplyException;
import org.modelica.mdt.internal.core.ErrorManager;
import org.modelica.mdt.internal.core.ModelicaComponent;
import org.modelica.mdt.internal.core.ModelicaElement;
import org.modelica.mdt.ui.UIPlugin;
import org.modelica.mdt.ui.actions.IModelicaEditorActionDefinitionIds;
import org.modelica.mdt.ui.editor.ModelicaEditor;
import org.modelica.mdt.ui.editor.ModelicaElementEditorInput;
import org.modelica.mdt.ui.editor.SystemFileEditorInput;
import org.modelica.mdt.ui.text.ModelicaKeywords;
import org.modelica.mdt.ui.text.ModelicaWordFinder;
import org.osgi.framework.Bundle;

/**
 * Abstract class for providing hover information for Modelica elements.
 *
 * @since 2.1
 */
public abstract class AbstractModelicaEditorTextHover implements IModelicaEditorTextHover, ITextHoverExtension {

	/**
	 * The style sheet (css).
	 * @since 3.2
	 */
	private static String fgStyleSheet;
	private ModelicaEditor fEditor;
	
	private boolean DEBUG = false;	
	
	private IBindingService fBindingService;
	{
		fBindingService= (IBindingService)PlatformUI.getWorkbench().getAdapter(IBindingService.class);
	}

	/*
	 * @see IModelicaEditorTextHover#setEditor(IEditorPart)
	 */
	public void setEditor(IEditorPart editor) {
		fEditor= (ModelicaEditor)editor;
	}

	protected IEditorPart getEditor() {
		return fEditor;
	}

	/*
	 * @see ITextHover#getHoverRegion(ITextViewer, int)
	 */
	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		return ModelicaWordFinder.findWord(textViewer.getDocument(), offset);
	}

	/**
	 * @see ITextHover#getHoverInfo(ITextViewer, IRegion)
	 */
		public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) 
		{
			if (hoverRegion != null) 
			{
				String word = null;
				try 
				{
					if (hoverRegion.getLength() > -1)
					{ 
						word = textViewer.getDocument().get(hoverRegion.getOffset(), hoverRegion.getLength());
						int offs = +1;
						while (Character.isJavaIdentifierPart(textViewer.getDocument().getChar(hoverRegion.getOffset()-offs)) ||
								textViewer.getDocument().getChar(hoverRegion.getOffset()-offs) == '.')
						{
							word = String.valueOf(textViewer.getDocument().getChar(hoverRegion.getOffset()-offs)) + word;
							offs = offs + 1;
						}
					}
				} 
				catch (BadLocationException x) 
				{
					x.printStackTrace();
				}
				if (word != null)
				{
					// if is a keyword don't bother!
					if (ModelicaKeywords.isKeyword(word)) return "keyword: " + word;
					if (Character.isDigit(word.charAt(0))) return "number: " + word;
					
					String info = null;
					
					// else try to go for the full class name
					if (fEditor == null) return null;  
					IModelicaSourceFile file = null;				
					if (fEditor instanceof ModelicaEditor)
					{
						IModelicaElement me = ((ModelicaEditor)fEditor).getEditorInputModelicaElement();
						if (me != null)
							file = me.getSourceFile();
					}
					if (file == null)
					{
						IEditorInput input = fEditor.getEditorInput();
						if (input instanceof SystemFileEditorInput ||
						 	input instanceof FileEditorInput)
						{
							file = null;
							//IPath path = ((SystemFileEditorInput)input).getStorage().getFullPath();
							//IFile f = path.toFile();
							//file = new ModelicaSourceFile((FolderPackage)null, (IFile)path);
						}
						if (input instanceof ModelicaElementEditorInput)
						{
							file = ((ModelicaElementEditorInput)input).getSourceFile();
						}
					}
					if (file != null)
					{
						try
						{
							IModelicaElement c = fEditor.getElementAt(hoverRegion.getOffset());
							// (ModelicaElement)file.getClassAt(hoverRegion.getOffset());
							while (c != null)
							{
								if (c != null) 
								{
									Collection<? extends IModelicaElement> kids = null;								
									if (c instanceof IModelicaClass)
									{
										kids = ((IModelicaClass)c).getChildren();
									}
									if (c instanceof IModelicaSourceFile)
									{
										kids = ((IModelicaSourceFile)c).getChildren();
									}
									
									// try to see the components of the class:
									if (kids != null)
									for (IModelicaElement comp : kids)
									{
										if (comp == null) continue;
										
										if (DEBUG) System.err.println("Searching childen:" + comp.getElementName());								
										if (comp.getElementName().equals(word))
										{
											if (comp instanceof IModelicaComponent)
											{
												if (DEBUG) System.err.println("Found component child:" + comp.getFullName() + "." + word);
												info = getInformation(c.getFullName() + "." + word);						
												if (info != null) return info;
												ModelicaComponent cmp = (ModelicaComponent)comp;
												return cmp.getVisibility().name().toLowerCase() + " " + cmp.getTypeName();
											}
											if (comp instanceof IModelicaClass)
											{
												if (DEBUG) System.err.println("Found child:" + comp.getFullName() + "." + word);
												info = getInformation(c.getFullName() + "." + word);						
												if (info != null) return info;
											}
										}
										// not equal, try directly:
										if (comp instanceof IModelicaClass)
										{
											if (DEBUG) System.err.println("Trying inner class:" + comp.getFullName() + "." + word);
											info = getInformation(comp.getFullName() + "." + word);						
											if (info != null) return info;
										}									
									}
									// see if the word is the same as the class:
									if (word.equals(c.getElementName()))
									{
										if (DEBUG) System.err.println("Lookup:" + c.getFullName());						
										info = getInformation(c.getFullName());						
										if (info != null) return info;							
									}
									
									// try to put the word inside the class
									if (DEBUG) System.err.println("Lookup:" + c.getElementName() + "." + word);						
									info = getInformation(c.getElementName() + "." + word);
									if (info != null) return info;
									// try to go from the class to the TOP level and search for children in
									// any of the classes until then.
									c = (ModelicaElement)c.getParent();							
								}						
								if (info != null) return info;
							}
						}
						catch (CoreException e)
						{
							ErrorManager.showCoreError(e);
						} 
						catch (CompilerException e)
						{
							ErrorManager.showCompilerError(e);
						}	
						catch (Exception e)
						{
							ErrorManager.logError(e);
						}
					}
					// lastly try to see if we can solve the name directly
					if (DEBUG) System.err.println("Lookup:" + word);				
					info = getInformation(word);
					if (info != null) return info;
					
					return word;
				}
			}
			return null; 
		}

		public synchronized String getInformation(String className)
		{
			// if the name starts with something fancy, just ignore it!
			if (!Character.isJavaIdentifierStart(className.charAt(0))) 
				return null;
			
			try
			{
				 ICompilerResult res = CompilerProxy.getClassString(className);
				 String info = res.getFirstResult().trim();
				if (info.startsWith("\"")) info = info.substring(1);
				if (info.endsWith("\"")) info = info.substring(0, info.length()-1);
				info = info.trim();
				
				info = info.replace("\\\"", "\"");
				info = info.replace("\\\\", "\\");
				
				if (!info.equals("")) return info + "\n";

				IClassInfo classAttributes = CompilerProxy.getClassInfo(className);
				return classAttributes.getRestriction().name();			
			}
			catch(UnexpectedReplyException e)
			{
				//e.printStackTrace();
			}
			catch(Exception e)
			{
				//e.printStackTrace();
			}
			return null;
		}

	/**
	 * Provides hover information for the given Modelica elements.
	 *
	 * @param javaElements the Modelica elements for which to provide hover information
	 * @return the hover information string
	 * @since 2.1
	 */
	protected String getHoverInfo(IModelicaElement[] javaElements) {
		return null;
	}

	/*
	 * @see ITextHoverExtension#getHoverControlCreator()
	 * @since 3.0
	 */
	public IInformationControlCreator getHoverControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell parent) {
				return new DefaultInformationControl(parent, SWT.NONE, new HTMLTextPresenter(true), getTooltipAffordanceString());
			}
		};
	}

	/**
	 * Returns the tool tip affordance string.
	 *
	 * @return the affordance string or <code>null</code> if disabled or no key binding is defined
	 * @since 3.0
	 */
	protected String getTooltipAffordanceString() {
		if (fBindingService == null)
			return null;

		String keySequence= fBindingService.getBestActiveBindingFormattedFor(IModelicaEditorActionDefinitionIds.SHOW_MODELICADOC);
		if (keySequence == null)
			return null;
		
		return Messages.format(ModelicaHoverMessages.ModelicaTextHover_makeStickyHint, keySequence == null ? "" : keySequence); //$NON-NLS-1$
	}

	/**
	 * Returns the style sheet.
	 *
	 * @since 3.2
	 */
	protected static String getStyleSheet() {
		if (fgStyleSheet == null) {
			Bundle bundle= Platform.getBundle(UIPlugin.PLUGIN_ID);
			URL styleSheetURL= bundle.getEntry("/ModelicaDocumentationHoverStyleSheet.css"); //$NON-NLS-1$
			if (styleSheetURL != null) {
				try {
					styleSheetURL= FileLocator.toFileURL(styleSheetURL);
					BufferedReader reader= new BufferedReader(new InputStreamReader(styleSheetURL.openStream()));
					StringBuffer buffer= new StringBuffer(200);
					String line= reader.readLine();
					while (line != null) {
						buffer.append(line);
						buffer.append('\n');
						line= reader.readLine();
					}
					fgStyleSheet= buffer.toString();
				} catch (IOException ex) {
					ex.printStackTrace();
					fgStyleSheet= ""; //$NON-NLS-1$
				}
			}
		}
		return fgStyleSheet;
	}

	/*
	 * @see org.eclipse.jface.text.ITextHoverExtension2#getInformationPresenterControlCreator()
	 * @since 3.4
	 */
	public IInformationControlCreator getInformationPresenterControlCreator() {
		return new IInformationControlCreator() {
			public IInformationControl createInformationControl(Shell shell) {
				return new DefaultInformationControl(shell, true);
			}
		};
	}
	
	
}
