package org.modelica.uml.sysml.diagram2.util;

/*
 * example snippet: convert between SWT Image and AWT BufferedImage
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.WritableRaster;

import org.eclipse.swt.graphics.*;

public class ImageConvertUtil {
	
static BufferedImage convertToAWT(ImageData data) {
	ColorModel colorModel = null;
	PaletteData palette = data.palette;
	if (palette.isDirect) {
		colorModel = new DirectColorModel(data.depth, palette.redMask, palette.greenMask, palette.blueMask);
		BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[3];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				int pixel = data.getPixel(x, y);
				RGB rgb = palette.getRGB(pixel);
				pixelArray[0] = rgb.red;
				pixelArray[1] = rgb.green;
				pixelArray[2] = rgb.blue;
				raster.setPixels(x, y, 1, 1, pixelArray);
			}
		}
		return bufferedImage;
	} else {
		RGB[] rgbs = palette.getRGBs();
		byte[] red = new byte[rgbs.length];
		byte[] green = new byte[rgbs.length];
		byte[] blue = new byte[rgbs.length];
		for (int i = 0; i < rgbs.length; i++) {
			RGB rgb = rgbs[i];
			red[i] = (byte)rgb.red;
			green[i] = (byte)rgb.green;
			blue[i] = (byte)rgb.blue;
		}
		if (data.transparentPixel != -1) {
			colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
		} else {
			colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
		}		
		BufferedImage bufferedImage = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[1];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				int pixel = data.getPixel(x, y);
				pixelArray[0] = pixel;
				raster.setPixel(x, y, pixelArray);
			}
		}
		return bufferedImage;
	}
}

static ImageData convertToSWT(BufferedImage bufferedImage) {
	if (bufferedImage.getColorModel() instanceof DirectColorModel) {
		DirectColorModel colorModel = (DirectColorModel)bufferedImage.getColorModel();
		PaletteData palette = new PaletteData(colorModel.getRedMask(), colorModel.getGreenMask(), colorModel.getBlueMask());
		
		ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[4];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				raster.getPixel(x, y, pixelArray);
				int pixel = palette.getPixel(new RGB(pixelArray[0], pixelArray[1], pixelArray[2]));
				data.setPixel(x, y, pixel);
				data.setAlpha(x, y, pixelArray[3]);
			}
		}		
		return data;		
	} else if (bufferedImage.getColorModel() instanceof IndexColorModel) {
		IndexColorModel colorModel = (IndexColorModel)bufferedImage.getColorModel();
		int size = colorModel.getMapSize();
		byte[] reds = new byte[size];
		byte[] greens = new byte[size];
		byte[] blues = new byte[size];
		colorModel.getReds(reds);
		colorModel.getGreens(greens);
		colorModel.getBlues(blues);
		RGB[] rgbs = new RGB[size];
		for (int i = 0; i < rgbs.length; i++) {
			rgbs[i] = new RGB(reds[i] & 0xFF, greens[i] & 0xFF, blues[i] & 0xFF);
		}
		PaletteData palette = new PaletteData(rgbs);
		ImageData data = new ImageData(bufferedImage.getWidth(), bufferedImage.getHeight(), colorModel.getPixelSize(), palette);
		data.transparentPixel = colorModel.getTransparentPixel();
		WritableRaster raster = bufferedImage.getRaster();
		int[] pixelArray = new int[1];
		for (int y = 0; y < data.height; y++) {
			for (int x = 0; x < data.width; x++) {
				raster.getPixel(x, y, pixelArray);
				data.setPixel(x, y, pixelArray[0]);
			}
		}
		return data;
	}
	return null;
}
}
