/*
 * 
 */
package org.openmodelica.simulation.environment.filehandling;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// TODO: Auto-generated Javadoc
/**
 * Simple File Copy Class.
 * Copies byte to byte
 * @author EADS Innovation Works, Parham Vasaiely, Parham.Vasaiely@gmx.de
 * 
 */
public class cp {
	
	/**
	 * Copy.
	 *
	 * @param in the in
	 * @param out the out
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static void copy(InputStream in, OutputStream out)
			throws IOException {
		byte[] buffer = new byte[0xFFFF];
		for (int len; (len = in.read(buffer)) != -1;)
			out.write(buffer, 0, len);

		// TODO [20100907] org.apache.commons.io.IOUtils.copy(in, out);
	}

	/**
	 * Copies a source file to a specified destination file. If a file with the
	 * specified destination file name exists, it will be overwritten. This copy
	 * method is not efficient because it copies byte to byte from the source
	 * file to the destination file.
	 * 
	 * @param src
	 *            path to source file ("C:\src.ext")
	 * @param dest
	 *            path to destination file ("C:\dest.ext")
	 */
	public static void copyFile(String src, String dest) {
		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dest);

			copy(fis, fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
				}
			if (fos != null)
				try {
					fos.close();
				} catch (IOException e) {
				}
		}
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		copyFile("d:\\src.exe", "d:\\dest3.exe");
	}
}
