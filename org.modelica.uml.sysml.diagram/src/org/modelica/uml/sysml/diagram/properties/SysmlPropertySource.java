package org.modelica.uml.sysml.diagram.properties;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.gmf.runtime.emf.ui.properties.descriptors.EMFCompositePropertySource;
import org.eclipse.gmf.runtime.emf.ui.properties.descriptors.EMFCompositeSourcePropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import org.modelica.uml.sysml.ModelicaClass;
import org.modelica.uml.sysml.ModelicaProperty;


public class SysmlPropertySource extends EMFCompositePropertySource {

	public static int APPLED_TO_CLASS = 1;

	public static int APPLIED_TO_PROPERTY = 2;

	protected int appliedTo;

	protected String propertyKind;

	public SysmlPropertySource(Object object,
			IItemPropertySource itemPropertySource, String category) {
		super(object, itemPropertySource, category);

		if (object instanceof ModelicaClass) {
			appliedTo = APPLED_TO_CLASS;

		} else if (object instanceof ModelicaProperty) {
			appliedTo = APPLIED_TO_PROPERTY;

			propertyKind = ((ModelicaProperty) object).getPropertyKind()
					.toString();
		}
	}

	public SysmlPropertySource(Object object,
			IItemPropertySource itemPropertySource) {
		super(object, itemPropertySource);
	}

	public void readOnlyProperties(List propertyIdList) {

		IPropertyDescriptor descriptors[] = this.getPropertyDescriptors();
		for (int i = 0; i < descriptors.length; i++) {
			EMFCompositeSourcePropertyDescriptor propDescriptor = (EMFCompositeSourcePropertyDescriptor) descriptors[i];

			Object id = propDescriptor.getItemDescriptor().getId(null);
			if (SysmlPropertySource.isInList(propertyIdList,(String)id)) {
				propDescriptor.setReadOnly(true);
			}
		}
	}

	public void filterProperties(List propertyIdList) {

		List descriptors = this.itemPropertySource
				.getPropertyDescriptors(this.object);
		for (int i = 0; i < descriptors.size(); i++) {
			IItemPropertyDescriptor propDescriptor = (IItemPropertyDescriptor) descriptors
					.get(i);
			Object id = propDescriptor.getId(null);
			if (SysmlPropertySource.isInList(propertyIdList,(String)id)) {
				descriptors.remove(propDescriptor);
			}
		}
	}

	public int getAppliedTo() {
		return appliedTo;
	}

	public void setAppliedTo(int appliedTo) {
		this.appliedTo = appliedTo;
	}

	public String getPropertyKind() {
		return propertyKind;
	}

	public void setPropertyKind(String propertyKind) {
		this.propertyKind = propertyKind;
	}

	private static boolean isInList(List l,String string) {
		
		for (Iterator iter = l.iterator(); iter.hasNext();) {
			String element  = (String)iter.next();
			if (string.indexOf(element) > -1) {
				return true;
			}
		}
		return false;
	}
}
