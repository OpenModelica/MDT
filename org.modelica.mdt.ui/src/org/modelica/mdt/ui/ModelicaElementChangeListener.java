
package org.modelica.mdt.ui;

import java.util.Collection;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.modelica.mdt.core.IModelicaElementChange;
import org.modelica.mdt.core.IModelicaElementChangeListener;

/* 
 * @author Adrian Pop [adrpo@ida.liu.se, http://www.ida.liu.se]
 */

public class ModelicaElementChangeListener implements IModelicaElementChangeListener
{
	
	private TreeViewer fViewer;	
	private boolean bShouldExpand = false;
	
	public ModelicaElementChangeListener(boolean shouldExpand)
	{ 
		bShouldExpand = shouldExpand;
	}
	
	public void setViewer(Viewer viewer)
	{
		// we only know how to deal with tree viewers
		if (viewer instanceof TreeViewer)
		{
			fViewer = (TreeViewer)viewer;
		}
		else
		{
			fViewer = null;
		}
	}
	
	/*
	 * IModelicaElementChangeListener interface method
	 */
	public void elementsChanged(final Collection<IModelicaElementChange> changes)
	{
		if (fViewer == null) return;
		Control ctrl = fViewer.getControl();

		if (ctrl == null || ctrl.isDisposed())
		{
			return;
		}
				
		if (ctrl.getDisplay().getThread() == Thread.currentThread()) 
		{
			handleChanges(changes);
		}
		else
		{			
			ctrl.getDisplay().asyncExec(new Runnable()
			{
				public void run() 
				{
					/* Abort if this happens after disposes */
					Control ctrl = fViewer.getControl();
					if (ctrl == null || ctrl.isDisposed())
					{
						return;
					}
					handleChanges(changes);
				}
			});
		}
	}

	protected void handleChanges(Collection<IModelicaElementChange> changes)
	{
		// if we have no viewer then there are no changes
		if (fViewer == null) return;
		for (IModelicaElementChange change : changes)
		{			  
			Assert.isNotNull(change);			  
			if (change == null) continue;
			Object element = change.getElement();
			//Assert.isNotNull(element);			  
			//if (element == null) continue;
			switch (change.getChangeType())
			{
			case ADDED:
				fViewer.add(change.getParent(), element);
				break;
			case MODIFIED:
				fViewer.update(element, null);
				break;
			case REMOVED:
				fViewer.remove(element);
				break;
			case OPENED:
			case CLOSED:
				fViewer.refresh(element);
				break;
			}
		}
		if (bShouldExpand)
			fViewer.expandToLevel(2);
	}	
}



///**
// * The element change listener of the Modelica outline viewer.
// * @see IModelicaElementChangedListener
// */
//class ElementChangedListener implements IModelicaElementChangeListener {
//
//	/*
//	 * IModelicaElementChangeListener interface method
//	 */
//	public void elementsChanged(final Collection<IModelicaElementChange> changes)
//	{
//		Control ctrl = getControl();
//
//		if (ctrl == null || ctrl.isDisposed() || ctrl.getDisplay() == null)
//		{
//			return;
//		}
//				
//		if (ctrl.getDisplay().getThread() == Thread.currentThread()) 
//		{
//			handleChanges(changes);
//		}
//		else
//		{			
//			ctrl.getDisplay().asyncExec(new Runnable()
//			{
//				public void run() 
//				{
//					/* Abort if this happens after disposes */
//					Control ctrl = fOutlineViewer.getControl();
//					if (ctrl == null || ctrl.isDisposed())
//					{
//						return;
//					}
//					handleChanges(changes);
//				}
//			});
//		}
//	}
//
//	protected void handleChanges(Collection<IModelicaElementChange> changes)
//	{
//		  for (IModelicaElementChange change : changes)
//		  {			  
//			  Assert.isNotNull(change);			  
//			  if (change == null) continue;
//			  Object element = change.getElement();
//			  Assert.isNotNull(element);			  
//			  if (element == null) continue;
//			  switch (change.getChangeType())
//			  {
//			  case ADDED:
//				  fOutlineViewer.add(change.getParent(), element);
//				  break;
//			  case MODIFIED:
//				  fOutlineViewer.update(element, null);
//				  break;
//			  case REMOVED:
//				  fOutlineViewer.remove(element);
//				  break;
//			  case OPENED:
//			  case CLOSED:
//				  fOutlineViewer.refresh(element);
//				  break;
//			  }
//		  }
//	}
//}
