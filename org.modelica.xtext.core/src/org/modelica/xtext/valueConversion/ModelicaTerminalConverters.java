package org.modelica.xtext.valueConversion;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.parsetree.AbstractNode;
import org.eclipse.xtext.util.Strings;

public class ModelicaTerminalConverters extends DefaultTerminalConverters {
	@ValueConverter(rule = "UNSIGNED_INTEGER")
	public IValueConverter<Integer> UNSIGNED_INTEGER() {
		return new IValueConverter<Integer>() {
			
			public Integer toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Couldn't convert empty string to int", node, null);
				try {
					return Integer.valueOf(string);
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Couldn't convert '"+string+"' to int", node, e);
				}
			}

			public String toString(Integer value) {
				return value.toString();
			}

		};
	}

	@ValueConverter(rule = "UNSIGNED_REAL")
	public IValueConverter<Double> UNSIGNED_REAL() {
		return new IValueConverter<Double>() {
			
			public Double toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Couldn't convert empty string to int", node, null);
				try {
					return Double.valueOf(string);
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Couldn't convert '"+string+"' to int", node, e);
				}
			}

			public String toString(Double value) {
				return value.toString();
			}

		};
	}

	@ValueConverter(rule = "BOOL_VAL")
	public IValueConverter<Boolean> BOOL_VAL() {
		return new IValueConverter<Boolean>() {
			
			public Boolean toValue(String string, AbstractNode node) {
				if (Strings.isEmpty(string))
					throw new ValueConverterException("Couldn't convert empty string to int", node, null);
				try {
					return Boolean.valueOf(string);
				} catch (NumberFormatException e) {
					throw new ValueConverterException("Couldn't convert '"+string+"' to int", node, e);
				}
			}

			public String toString(Boolean value) {
				return value.toString();
			}

		};
	}

}
