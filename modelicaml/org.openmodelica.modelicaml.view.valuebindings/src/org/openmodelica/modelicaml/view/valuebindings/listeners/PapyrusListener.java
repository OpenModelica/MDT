package org.openmodelica.modelicaml.view.valuebindings.listeners;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.papyrus.core.listenerservice.IPapyrusListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.uml2.uml.NamedElement;
import org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView;

public class PapyrusListener implements IPapyrusListener {
	
//	public PapyrusListener() {	
//	}

	
	@Override
	public void notifyChanged(Notification notification) {

		if ( notification.getEventType() == Notification.REMOVE && notification.getNewValue() == null ) {
//			System.err.println("Event type: " + notification.getEventType());
//			System.err.println("Notifier: " + ((NamedElement)notification.getNotifier()).getName() );
//			System.err.println("OldValue: " + notification.getOldValue());
//			System.err.println("NewValue: " + notification.getNewValue());
			
			if (notification.getOldValue() instanceof NamedElement) {
				IViewPart view = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().findView("org.openmodelica.modelicaml.view.valuebindings.views.ValueBindingsView");
				
				ValueBindingsView myView = null;
				if (view instanceof ValueBindingsView) {
				    myView = (ValueBindingsView)view;			
				}
				
				if (myView != null) {
					myView.removeTreeItem(((NamedElement)notification.getOldValue()));
					myView.getViewer().refresh(); // refresh the entire viewer
					
//					System.err.println("REMOVED: " + ((NamedElement)notification.getOldValue()).getName());
				}
			}
		}
	}
}
