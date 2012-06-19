package org.openmodelica.modelicaml.simulation.simresults;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Arrays;

public class ReadMatlab4 implements IResultsReader {
	private MHeader hdr = new MHeader();
	private BigEndianRandomAccessFile beraf;
	private String matrixNames[] = { "Aclass", "name", "description", "dataInfo", "data_1", "data_2" };
	private int matrixTypes[] = { 51, 51, 51, 20, 0, 0 };
	private byte binTrans = 1;
	private int doublepresision = 0;
	// private Map allInfo= new HashMap<String, Map<Double, Double>>();
	private ModelicaMatVariable allInfo[];

	private final String binTrans_char = "binTrans";
	private final String binNormal_char = "binNormal";

	private int nall;
	private int nparam;
	private double[] params;
	private int nrows;
	private int nvar;
	private long var_offset;
//	private double[][] vars;
	private String path;

	public ReadMatlab4(String path) throws Exception {
		this.path=path;
		
		beraf = new BigEndianRandomAccessFile(this.path, "r");
		int i = 0;

		for (i = 0; i < matrixNames.length; i++) {
			int nr = hdr.read(beraf); // fread(&hdr,sizeof(MHeader_t),1,reader->file);
			int matrix_length, element_length;
			// String name;
			doublepresision = 1;
			if (nr != 1)
				throw new Exception("Corrupt header (1)");
			/*
			 * fprintf(stderr,
			 * "Found matrix type=%04d mrows=%d ncols=%d imagf=%d namelen=%d\n",
			 * hdr.type, hdr.mrows, hdr.ncols, hdr.imagf, hdr.namelen);
			 */
			if (hdr.type != matrixTypes[i]) {
				if ((i > 3) && (hdr.type == 10))
					doublepresision = 0;
				else
					throw new Exception("Matrix type mismatch");
			}
			if (hdr.imagf > 1)
				throw new Exception("Matrix uses imaginary numbers");
			if ((element_length = mat_element_length(hdr.type)) == -1)
				throw new Exception(
						"Could not determine size of matrix elements");
			byte name[] = new byte[hdr.namelen];
			// nr = fread(name,hdr.namelen,1,reader->file);
			nr = beraf.read(name);
			if (nr != name.length)
				throw new Exception("Corrupt header (2)");
			if (name[hdr.namelen - 1] != 0)
				throw new Exception("Corrupt header (3)");
			/* fprintf(stderr, "  Name of matrix: %s\n", name); */
			matrix_length = hdr.mrows * hdr.ncols * (1 + hdr.imagf) * element_length;
			if ((new String(name)).equalsIgnoreCase(matrixNames[i]))
				throw new Exception("Matrix name mismatch");
			name = null;
			switch (i) {
			case 0: {
				int k;
				int j;
				if (hdr.mrows != 4)
					throw new Exception("Aclass matrix does not have 4 rows");
				if (hdr.ncols != 11)
					throw new Exception("Aclass matrix does not have 11 cols");
				
				byte tmp[] = new byte[hdr.ncols * hdr.mrows];
				
				if (beraf.read(tmp) != tmp.length) {
					throw new Exception("Corrupt header: Aclass matrix");
				}
				for (k = 0; k < hdr.mrows; k++) {
					char row[] = new char[12];
					for (j = 0; j < hdr.ncols; j++) {
						row[j] = (char) tmp[j * hdr.mrows + k];
					}
					if (k == 3) {
						/* binTrans */
						if (binTrans_char.equalsIgnoreCase(new String(row).trim())) {
							binTrans = 1;
						} else if (binNormal_char.equalsIgnoreCase(new String(row).trim())) {
							/* binNormal */
							binTrans = 0;
						} else
							throw new Exception(
									"Aclass matrix does not match binTrans or binNormal format");
					}
				}
				break;
			}
			case 1: { /* "names" */
				int k;
				if (binTrans == 0)
					nall = hdr.mrows;
				else
					nall = hdr.ncols;
				allInfo = new ModelicaMatVariable[nall];
				if (binTrans == 1) {
					for (k = 0; k < hdr.ncols; k++) {
						byte nameString[] = new byte[hdr.mrows];

						if (beraf.read(nameString) != nameString.length)
							throw new Exception("Corrupt header: names matrix");
						allInfo[k]= new ModelicaMatVariable();
						allInfo[k].name=new String(nameString).trim();
						allInfo[k].isParam = false;
						allInfo[k].index = -1;
					}
				}
				if (binTrans == 0) {
					long j;

					byte nameString[] = new byte[hdr.ncols];
					for (k = 0; k < hdr.mrows; k++) {
						if (beraf.read(nameString) != nameString.length) {
							throw new Exception("Corrupt header: names matrix");
						}
						for (j = 0; j < hdr.ncols; j++) {
							allInfo[k]= new ModelicaMatVariable();
							allInfo[k].name=new String(nameString).trim();
						}
						allInfo[k].isParam = false;
						allInfo[k].index = -1;
					}
				}
				break;
			}
			 case 2: { /* description */
				int k;
				if (binTrans==1) {
					 for (k=0; k<hdr.ncols; k++) {
						 byte descString[] = new byte[hdr.mrows];
						 if (beraf.read(descString) != descString.length) 
							 throw new Exception( "Corrupt header: names matrix");
						 allInfo[k].descr=new String(descString).trim();
					 }
				 } else if (binTrans==0) {
					 int j;
						byte descString[] = new byte[hdr.ncols];
						for (k = 0; k < hdr.mrows; k++) {
							if (beraf.read(descString) != descString.length) {
								throw new Exception("Corrupt header: names matrix");
							}
							for (j = 0; j < hdr.ncols; j++) {
								allInfo[k].descr=new String(descString).trim();
							}
							allInfo[k].isParam = false;
							allInfo[k].index = -1;
						}
				 }
				 break;
				 }
			 case 3: { /* "dataInfo" */
				 int k,j;
				 if (binTrans==1) {
					 int dataInfo[][] = new int[hdr.ncols][hdr.mrows];
					 for (k=0; k<hdr.ncols; k++) {
						 for (j=0;j<hdr.mrows;j++){
							 dataInfo[k][j]=beraf.readBEInt();
						 }
				         if (j != dataInfo[k].length)
							throw new Exception("Corrupt header: data_2 matrix");
						 if(dataInfo[k][0] == 1)
							 allInfo[k].isParam = true;
						 else
							 allInfo[k].isParam = false;
						 allInfo[k].index = dataInfo[k][1];
//System.out.println(allInfo[k].index+"\t" +allInfo[k].name);
					 }
			         if (k != dataInfo.length)
						throw new Exception("Corrupt header: data_2 matrix");
				 }
				 if (binTrans==0) {
					 int dataInfo[][] = new int[hdr.mrows][hdr.ncols];
				 	for (k=0; k<hdr.mrows; k++) {
						 for (j=0;j<hdr.ncols;j++){
							 dataInfo[k][j]=beraf.readBEInt();
						 }
				         if (j != dataInfo.length)
							throw new Exception("Corrupt header: data_2 matrix");
						 if(dataInfo[k][0] == 1)
							 allInfo[k].isParam = true;
						 else
							 allInfo[k].isParam = false;
						 allInfo[k].index = dataInfo[k][hdr.mrows];
						 
					 }
				 }
				 /* Sort the variables so we can do faster lookup */
				 Arrays.sort(this.allInfo);
				 break;
			 }
			 case 4: { /* "data_1" */
				 if (binTrans==1) {
					 int k,j;
					 if (hdr.mrows == 0) throw new Exception("data_1 matrix does not contain at least 1 variable");
					 if (hdr.ncols != 2) throw new Exception("data_1 matrix does not have 2 rows");
					 this.nparam = hdr.mrows;
					 this.params = new double[hdr.mrows*hdr.ncols];
					 if (doublepresision==1)
					 {
						 for(j =0;j<params.length;j++){
							 this.params[j]=beraf.readBEDouble();
						 }
					 }
					 else
					 {
						 for(j =0;j<params.length;j++){
							 this.params[j]=(double)beraf.readBEFloat();
						 }
					 }
					 if (j != params.length) 
						 throw new Exception("Corrupt header: data_2 matrix");
					 for (k=1; k<this.nparam; k++) {
						 if (this.params[k] != this.params[k+this.nparam]) 
							 throw new Exception( "data_1 matrix contained parameter that changed between start and stop-time");
					 }
				 }
				 if (binTrans==0) {
					 int k,j;
					 if (hdr.ncols == 0) 
						 throw new Exception("data_1 matrix does not contain at least 1 variable");
					 if (hdr.mrows != 2) 
						 throw new Exception("data_1 matrix does not have 2 rows");
					 this.nparam = hdr.ncols;
					 double tmp[]=new double[hdr.mrows*hdr.ncols];
					 this.params = new double[hdr.mrows*hdr.ncols];
					 for(j = 0;j<tmp.length;j++){
						 if (this.doublepresision==1){
							 tmp[j]=beraf.readBEDouble();
						 }else{
							 tmp[j]=beraf.readBEFloat();
						 }
					 }
			         if (j != tmp.length) 
							 throw new Exception("Corrupt header: data_2 matrix");
					 for (k=0; k<hdr.mrows; k++) {
						 for (j=0; j<hdr.ncols; j++) {
							 this.params[k*hdr.ncols+j] = tmp[k +j*hdr.mrows];
						 }
					 }
					 for (k=1; k<this.nparam; k++) {
						 if (this.params[k] != this.params[k+this.nparam]) 
							 throw new Exception("data_1 matrix contained parameter that changed between start and stop-time");
					 }
				 }
				 break;
			 }
			 case 5: { /* "data_2" */
				 if (binTrans==1) {
					 this.nrows = hdr.ncols;
					 this.nvar = hdr.mrows;
					 if (this.nrows < 2) 
						 throw new Exception( "Too few rows in data_2 matrix");
					 this.var_offset = beraf.getFilePointer();
//					 this.vars = new double[this.nvar*2][hdr.mrows];
					 beraf.seek(matrix_length+beraf.getFilePointer());
				 }
				 if (binTrans==0) {
					 int k,j;
					 this.nrows = hdr.mrows;
					 this.nvar = hdr.ncols;
					 if (this.nrows < 2) 
						 throw new Exception("Too few rows in data_2 matrix");
					 this.var_offset = beraf.getFilePointer();
//					 this.vars = new double[this.nvar*2][hdr.mrows];
					 beraf.seek(matrix_length+beraf.getFilePointer());
				 }
				 break;
			}
			default:
				throw new Exception("Implementation error: Unknown case");
			}
		};
	}

	private int mat_element_length(int type) {
		int m = (type / 1000);
		int o = (type % 1000) / 100;
		int p = (type % 100) / 10;
		int t = (type % 10);
		if (m != 0)
			return -1; /* We require IEEE Little Endian for now */
		if (o != 0)
			return -1; /* Reserved number; forced 0 */
		if (t == 1 && p != 5)
			return -1; /* Text matrix? Force element length=1 */
		if (t == 2)
			return -1; /* Sparse matrix fails */
		switch (p) {
		case 0:
			return 8;
		case 1:
			return 4;
		case 2:
			return 4;
		case 3:
			return 2;
		case 4:
			return 2;
		case 5:
			return 1;
		default:
			return -1;
		}
	}
	public int getSize(){
		return allInfo.length;
	}
	public ArrayList<String> getNames(){
		ArrayList<String>ret=new ArrayList<String>();
		for (int i=0;i<allInfo.length;i++){
			ret.add(allInfo[i].name);
		}
		return ret;
	}
	private int find_var(String varName) {
		ModelicaMatVariable key = new ModelicaMatVariable();
		key.name = varName;
		return allInfo[Arrays.binarySearch(allInfo, key)].index;
	}

	public double[] getValues(String name) throws Exception {
		return read_vals(find_var(name));
	}
	
	public double[] read_vals(String name) throws Exception {
		ModelicaMatVariable key = new ModelicaMatVariable();
		key.name = name;
		int allInfoIndex=Arrays.binarySearch(allInfo, key);
		if (allInfo[allInfoIndex].isParam) {
			double ret[] = new double[nrows];
			for(int i=0;i<ret.length;i++){
				ret[i]=params[Math.abs(allInfo[allInfoIndex].index)-1];
			}
			return ret;
		}else{
			return read_vals(find_var(name));
		}
	}

	private double[] read_vals(int varIndex) throws Exception {
		int absVarIndex = Math.abs(varIndex);
		assert (absVarIndex > 0 && absVarIndex <= nvar);
		double ret[] = new double[nrows];

		if (binTrans == 1) {
			int i;
			if (doublepresision == 1) {
				for (i = 0; i < nrows; i++) {
					this.beraf.seek(var_offset + Double.SIZE / 8 * (i * nvar + absVarIndex - 1));
					ret[i] = this.beraf.readBEDouble();
					if (varIndex < 0)
						ret[i] = -ret[i];
				}
			} else {
				for (i = 0; i < nrows; i++) {
					this.beraf.seek(var_offset + Float.SIZE / 8 * (i * nvar + absVarIndex - 1));
					ret[i] = this.beraf.readBEFloat();
					if (varIndex < 0)
						ret[i] = -ret[i];
				}
			}
		}
		if (binTrans == 0) {
			int i;
			if (doublepresision == 1) {
				this.beraf.seek(var_offset + Double.SIZE / 8 * (absVarIndex - 1));
				for (i = 0; i < nrows; i++) {
					ret[i] = this.beraf.readBEDouble();
					if (varIndex < 0)
						ret[i] = -ret[i];
				}
			} else {
				this.beraf.seek(var_offset + Float.SIZE / 8 * (absVarIndex - 1));
				for (i = 0; i < nrows; i++) {
					ret[i] = this.beraf.readBEFloat();
					if (varIndex < 0)
						ret[i] = -ret[i];
				}
			}
		}
		return ret;
	}

	public double startTime()
	{
	  return params[0];
	}
	public double stopTime()
	{
	  return params[nparam];
	}
	
	public void print_all_vars()
	{
	  int i;
	  System.out.print("allSortedVars(\""+this.path+"\") => {");
	  for (i=0; i<this.nall; i++)
		  System.out.print("\""+ allInfo[i].name +"\",");
	  System.out.println( "}");
	}
	
	
	
	protected void finalize(){
		try {
			this.beraf.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReadMatlab4 input;
		try {
//			input = new ReadMatlab4("TwoTanksExample.GenVeMs_for__TanksConnectedPI_1.VeM_for__Scenario__Change_of_input_flow_res.mat");
			input = new ReadMatlab4("TwoTanksExample.GenVeMs_for__SystemEnvironment_1.VeM_for__Change_of_input_flow_res.mat");

//			input.print_all_vars();
//			ArrayList<String> namen=input.getNames();
//			System.out.println("namen.size()="+namen.size());
//			for(String n:namen){
//				double erg[]=input.read_vals(n);
//				System.out.println(n+" -> "+Arrays.toString(erg));
//			}
//			double erg[]=input.read_vals("time");
//			System.out.println("time -> "+Arrays.toString(erg));
			
			double erg[]=input.read_vals("req_003_0_max_level_of_liquid_in_tank.tankHeight");
			System.out.println("req_003_0_max_level_of_liquid_in_tank.tankHeight -> "+Arrays.toString(erg));
			
			double erg2[]=input.read_vals("req_003_1_max_level_of_liquid_in_tank.tankHeight");
			System.out.println("req_003_1_max_level_of_liquid_in_tank.tankHeight -> "+Arrays.toString(erg2));

			System.err.println("Both should be 2 all the time..!");
			
//			System.out.println("start: "+input.startTime()+" stop: " + input.stopTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private class MHeader {
		public int type = 0;
		public int mrows = 0;
		public int ncols = 0;
		public int imagf = 0;
		public int namelen = 0;

		public int read(BigEndianRandomAccessFile beraf) throws IOException {
			type=beraf.readBEInt();
			mrows=beraf.readBEInt();
			ncols=beraf.readBEInt();
			imagf=beraf.readBEInt();
			namelen=beraf.readBEInt();
			return 1;
		}
		@Override
		public String toString() {
			return "[type: " + type+ "\tmrows: " + mrows + "\tncols: " + ncols + "\timagf: "+imagf+"\tnamelen: "+namelen+"]";
		}
	}

	private class ModelicaMatVariable implements Comparable<ModelicaMatVariable>{
		public String name = new String();
		public String descr;
		public boolean isParam;
		public int index;
		@Override
		public String toString() {
			return "name: "+name+" descr: "+ descr + " isParam: " + isParam + " index: " + index;
		}
		@Override
		public int compareTo(ModelicaMatVariable o) {
			return this.name.compareTo(o.name);
		}
	}
	
	private class BigEndianRandomAccessFile extends RandomAccessFile{

		public BigEndianRandomAccessFile(String file, String mode) throws FileNotFoundException {
			super(file,mode);
		}
		public BigEndianRandomAccessFile(File file, String mode) throws FileNotFoundException {
			super(file,mode);
		}

		public int readBEInt() throws IOException {
			return readUnsignedByte() | readUnsignedByte()<<8 | readUnsignedByte()<<16 | readUnsignedByte()<<24;
		}
		public long readBEUnsignedInt() throws IOException {
			return ((long)readUnsignedByte()) | ((long)readUnsignedByte())<<8 | ((long)readUnsignedByte())<<16 | ((long)readUnsignedByte())<<24;
		}
		public int readBEUnsignedShort() throws IOException {
			return readUnsignedByte() | readUnsignedByte()<<8;
		}
		public float readBEFloat() throws IOException {
			int start=0;
			int i = 0; 
		    int cnt = 0; 
		    byte[] tmp = new byte[Float.SIZE/8]; 
		    for (i = start; i < (start + tmp.length); i++) { 
		        tmp[cnt] = readByte(); 
		        cnt++; 
		    } 
		    long accum = 0; 
		    i = 0; 
		    for ( int shiftBy = 0; shiftBy < 64; shiftBy += 8 ) { 
		        accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy; 
		        i++; 
		    } 
			return (float) Double.longBitsToDouble(accum); 
		}
		public double readBEDouble() throws IOException {
			int start=0;
			int i = 0;
		    int cnt = 0; 
		    byte[] tmp = new byte[Double.SIZE/8]; 
		    for (i = start; i < (start + tmp.length); i++) { 
		        tmp[cnt] = readByte(); 
		        cnt++; 
		    } 
		    long accum = 0; 
		    i = 0; 
		    for ( int shiftBy = 0; shiftBy < 64; shiftBy += 8 ) { 
		        accum |= ( (long)( tmp[i] & 0xff ) ) << shiftBy; 
		        i++; 
		    } 
			return Double.longBitsToDouble(accum); 
		}
	}

	@Override
	public String getFilePath() {
		return this.path;
	}

	@Override
	public void releaseFile() throws IOException {
		beraf.close();
	}
	
}
