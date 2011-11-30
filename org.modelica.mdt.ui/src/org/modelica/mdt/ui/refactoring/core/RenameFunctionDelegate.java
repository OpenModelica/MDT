// Copyright (c) 2005 by Leif Frenzel. All rights reserved.
// See http://leiffrenzel.de
package org.modelica.mdt.ui.refactoring.core;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.CompositeChange;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.TextFileChange;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.IConditionChecker;
import org.eclipse.ltk.core.refactoring.participants.ValidateEditChecker;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.modelica.mdt.ui.UIPlugin;

/** <p>delegate object that contains the logic used by the processor.</p>
 *
 * @author Leif Frenzel
 */
class RenameFunctionDelegate {

	private static final String EXT_MO = "mo"; //$NON-NLS-1$

	private final RenameFunctionInfo info;
	// properties file with the key to rename -> offset of the key
	private final Map<IFile, Integer> propertiesFiles = new HashMap<IFile, Integer>();

	RenameFunctionDelegate(final RenameFunctionInfo info) {
		this.info = info;
	}

	RefactoringStatus checkInitialConditions() {
		RefactoringStatus result = new RefactoringStatus();
		IFile sourceFile = info.getSourceFile();
		if( sourceFile == null || !sourceFile.exists() ) {
			result.addFatalError( CoreTexts.renameFunctionDelegate_noSourceFile );
		} else if( info.getSourceFile().isReadOnly() ) {
			result.addFatalError( CoreTexts.renameFunctionDelegate_roFile );
		}
		return result;
	}

	RefactoringStatus checkFinalConditions( final IProgressMonitor pm, 
			final CheckConditionsContext ctxt ) {
		RefactoringStatus result = new RefactoringStatus();
		pm.beginTask( CoreTexts.renameFunctionDelegate_checking, 100 );
		// do something long-running here: traverse the entire project (or even
		// workspace) to look for all *.properties files with the same bundle
		// base name
		IContainer rootContainer;
		if( info.isAllProjects() ) {
			rootContainer = ResourcesPlugin.getWorkspace().getRoot();
		} else {
			rootContainer = info.getSourceFile().getProject();
		}
		search( rootContainer, result );
		pm.worked( 50 );

		if( ctxt != null ) {
			IFile[] files = new IFile[ propertiesFiles.size() ];
			propertiesFiles.keySet().toArray( files );
			IConditionChecker checker = ctxt.getChecker( ValidateEditChecker.class );
			ValidateEditChecker editChecker = ( ValidateEditChecker )checker;
			editChecker.addFiles( files );
		}
		pm.done();
		return result;
	}

	void createChange( final IProgressMonitor pm, 
			final CompositeChange rootChange ) {
		try {
			pm.beginTask( CoreTexts.renameFunctionDelegate_collectingChanges, 100 );
			// the property which was directly selected by the user
			rootChange.add( createRenameChange() );
			pm.worked( 10 );
			// all files in the same bundle
			pm.worked( 90 );
		} finally {
			pm.done();
		}
	}


	public static String convertStreamToString(InputStream is) throws Exception {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			sb.append(line + "\n");
		}
		is.close();
		return sb.toString();
	}  

	// helping methods
	//////////////////

	private Change createRenameChange() {
		// create a change object for the file that contains the property the 
		// user has selected to rename
		IFile file = info.getSourceFile();
		TextFileChange result = new TextFileChange( file.getName(), file );
		// a file change contains a tree of edits, first add the root of them
		MultiTextEdit fileChangeRootEdit = new MultiTextEdit();
		result.setEdit( fileChangeRootEdit );    

		String refactorString = "";
		file.getLocation().toString();
		/*
    try{
      res = CompilerProxy.sendExpression("loadFile(\"" + fullFileName +"\");", true);
      res = CompilerProxy.sendExpression("refactor_renFunc(TestFunction.MyFunction, " + info.getNewName() +");", true);
      res = CompilerProxy.sendExpression("refactor_list(TestFunction)", true);
      refactorString = res.getFirstResult().trim();
      if (refactorString.startsWith("\"")) refactorString = refactorString.substring(1);
      if (refactorString.endsWith("\"")) refactorString = refactorString.substring(0, refactorString.length()-1);
    }
    catch(Exception e)
    {
    }
		 */
		InputStream is = null;
		try
		{    
			is = file.getContents();
			refactorString = convertStreamToString(is);
		}
		catch(Exception e)
		{
		}

		int fromIndex = refactorString.indexOf(info.getOldName()) + 2;

		// edit object for the text replacement in the file, this is the only child
		ReplaceEdit edit = new ReplaceEdit( fromIndex, 
				info.getOldName().length(),
				info.getNewName());
		fileChangeRootEdit.addChild( edit );

		fromIndex = refactorString.indexOf(info.getOldName(), fromIndex) + 7;

		// edit object for the text replacement in the file, this is the only child
		edit = new ReplaceEdit( fromIndex, 
				info.getOldName().length(),
				info.getNewName());
		fileChangeRootEdit.addChild( edit );

		fromIndex = refactorString.indexOf(info.getOldName(), fromIndex) + 16;

		// edit object for the text replacement in the file, this is the only child
		edit = new ReplaceEdit( fromIndex, 
				info.getOldName().length(),
				info.getNewName());    
		fileChangeRootEdit.addChild( edit );

		return result;
	}

//	private boolean isEmpty( final String candidate ) {
//		return candidate == null || candidate.trim().length() == 0;
//	}

//	private boolean isPropertyKey( final IFile file, final String candidate ) {
//		boolean result = false;
//		try {
//			Properties props = new Properties();
//			props.load( file.getContents() );
//			result = props.containsKey( candidate );
//		} catch( Exception ex ) {
//			// ignore this, we just assume this is not a favourable situation
//			ex.printStackTrace();
//		}
//		return result;
//	}

	// whether the file is a .properties file with the same base name as the 
	// one we refactor and contains the key that interests us
	private boolean isToRefactor( final IFile file ) {
		return    EXT_MO.equals( file.getFileExtension() ) 
				&& file.getName().startsWith( getBundleBaseName() )
				&& !file.equals( info.getSourceFile() )
				/*&& isPropertyKey( file, info.getOldName() ) */;
	}

	private String getBundleBaseName() {
		String result = info.getSourceFile().getName();
		int underscoreIndex = result.indexOf( '_' );
		if( underscoreIndex !=  -1 ) {
			result = result.substring( 0, underscoreIndex );
		} else {
			int index = result.indexOf( EXT_MO ) - 1;
			result = result.substring( 0, index );
		}
		return result;
	}

	private void search( final IContainer rootContainer,
			final RefactoringStatus status ) {
		try {
			IResource[] members = rootContainer.members();
			for( int i = 0; i < members.length; i++ ) {
				if( members[ i ] instanceof IContainer ) {
					search( ( IContainer )members[ i ], status );
				} else {
					IFile file = ( IFile )members[ i ];
					handleFile( file, status );
				}
			}
		} catch( final CoreException cex ) {
			status.addFatalError( cex.getMessage() );
		}
	}

	private void handleFile( final IFile file, final RefactoringStatus status ) {
		if( isToRefactor( file ) ) {
			int keyOffset = determineKeyOffset( file, status );
			if( keyOffset != -1 ) {
				Integer offset = new Integer( keyOffset );
				propertiesFiles.put( file, offset );
			}
		}
	}

//	private int getKeyOffset( final IFile file ) {
//		return ( ( Integer )propertiesFiles.get( file ) ).intValue();
//	}

	// finds the offset of the property key to rename
	// usually, this would be the job of a proper parser;
	// using a primitive brute-force approach here
	private int determineKeyOffset( final IFile file, 
			final RefactoringStatus status ) {
		String content = readFileContent( file, status );

		int result = -1;
		int candidateIndex = content.indexOf( info.getOldName() );
		while( result == -1 && candidateIndex != -1 ) {
			if( isKeyOccurrence( content, candidateIndex ) ) {
				result = candidateIndex;
			}
		} 
		if( result == -1 ) {
			// still nothing found, we add a warning to the status
			// (we have checked the file contains the property, so that we can't
			// determine it's offset is probably because of the rough way employed
			// here to find it)
			String msg =   CoreTexts.renameFunctionDelegate_propNotFound 
					+ file.getLocation().toOSString();
			status.addWarning( msg );
		}
		return result;
	}

	private String readFileContent( final IFile file,
			final RefactoringStatus refStatus ) {
		String result = null;
		try {
			InputStream is = file.getContents();
			byte[] buf = new byte[ 1024 ];
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = is.read( buf );
			while( len > 0 ) {
				bos.write( buf, 0, len );
				len = is.read( buf );
			}
			is.close();
			result = new String( bos.toByteArray() );
		} catch( Exception ex ) {
			String msg = ex.toString();
			refStatus.addFatalError( msg );
			String pluginId = UIPlugin.PLUGIN_ID;
			IStatus status = new Status( IStatus.ERROR, pluginId, 0, msg, ex );
			UIPlugin.getDefault().getLog().log( status );
		}
		return result;
	}

	// we check only that there is a separator before the next line break (this
	// is not sufficient, the whole thing may be in a comment etc. ...)
	private boolean isKeyOccurrence( final String content, 
			final int candidateIndex ) {
		int index = candidateIndex + info.getOldName().length();
		// skip whitespace 
		while( content.charAt( index ) == ' ' || content.charAt( index ) == '\t' ) {
			index++;
		}
		return content.charAt( index ) == '=' || content.charAt( index ) == ':';
	}
}
