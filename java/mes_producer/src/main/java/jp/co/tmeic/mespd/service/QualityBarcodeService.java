package jp.co.tmeic.mespd.service;

import javax.annotation.Resource;

import jp.co.tmeic.mespd.entity.MPrinter;

import org.seasar.extension.jdbc.JdbcManager;

/**
 * QualityBarcodeServiceのサービスクラスです。
 *
 */
public class QualityBarcodeService {

	@Resource
	protected JdbcManager jdbcManager;

	@Resource
	protected DLatestQualityBarcodeService dLatestQualityBarcodeService;
	
	@Resource
	protected MPrinterService mPrinterService;
	
	@Resource
	protected DQualtyLabelPrintQueueService dQualtyLabelPrintQueueService;

	/**
	 * バーコードNoを生成します。
	 *
	 * @return 品質ラベル
	 */
	public String getQualityBarcode() {
		String qualityBarcodeOld = dLatestQualityBarcodeService.selectBarcode();
		String qualityBarcode = "";
		int numberBarcode = 0;
		if (qualityBarcodeOld == null || qualityBarcodeOld.equals("ZZZZZ")) {
			numberBarcode = 1;
		}else{
			numberBarcode = BarcodeToNumber(qualityBarcodeOld) + 1;
		}
		qualityBarcode = NumberToBarcode(numberBarcode);
		if(qualityBarcodeOld!=null){
			dLatestQualityBarcodeService.updateBarcode(NumberToBarcode(numberBarcode), qualityBarcodeOld);
		}else{
			dLatestQualityBarcodeService.insertBarcode(qualityBarcode);
		}	
		return qualityBarcode;
	}
	
	public void printQualityBarcode(String qcBarcode,String Barcode) {
		MPrinter mPrinter = mPrinterService.printQualityLabel();
		dQualtyLabelPrintQueueService.addQueueQualityLabel(mPrinter.printerId,mPrinter.printerType,qcBarcode,Barcode);
	}
	
	private static String NumberToBarcode(int num){
		String barcode = "";
	    for(int i = num ; i>0 ; i/=34){
	    	barcode = IntToBar(i % 34) + barcode ;
	    }
	    int sizeBar = barcode.length();
	    if(sizeBar<5){
	    	for(int i = sizeBar;i<5;i++)
	    		barcode = '0' + barcode ;
	    }
		return barcode;
	}
	
	private static int BarcodeToNumber(String barcode){
		double sum = 0;
		barcode = barcode.toUpperCase();
		char bar[] = barcode.toCharArray();
		int sizeBar = barcode.length();
		int barPow = barcode.length()-1;
		int pow = 34;
		 for(int i=0;i<sizeBar;i++){
			 sum += BarToInt(bar[i])*Math.pow(pow,barPow--);
		 }
		return (int)sum;
	}
	
	private static int BarToInt(char i){
		switch(i){
		case '0' : return 0;
		case '1' : return 1;
		case '2' : return 2;
		case '3' : return 3;
		case '4' : return 4;
		case '5' : return 5;
		case '6' : return 6;
		case '7' : return 7;
		case '8' : return 8;
		case '9' : return 9;
		case 'A' : return 10;
		case 'B' : return 11;
		case 'C' : return 12;
		case 'D' : return 13;
		case 'E' : return 14;
		case 'F' : return 15;
		case 'G' : return 16;
		case 'H' : return 17;
		case 'J' : return 18;
		case 'K' : return 19;
		case 'L' : return 20;
		case 'M' : return 21;
		case 'N' : return 22;
		case 'P' : return 23;
		case 'Q' : return 24;
		case 'R' : return 25;
		case 'S' : return 26;
		case 'T' : return 27;
		case 'U' : return 28;
		case 'V' : return 29;
		case 'W' : return 30;
		case 'X' : return 31;
		case 'Y' : return 32;
		case 'Z' : return 33;
		default  : return 0;
		}
	}
	
	private static char IntToBar(int i){
		switch(i){
		case 10 : return 'A';
		case 11 : return 'B';
		case 12 : return 'C';
		case 13 : return 'D';
		case 14 : return 'E';
		case 15 : return 'F';
		case 16 : return 'G';
		case 17 : return 'H';
		case 18 : return 'J';
		case 19 : return 'K';
		case 20 : return 'L';
		case 21 : return 'M';
		case 22 : return 'N';
		case 23 : return 'P';
		case 24 : return 'Q';
		case 25 : return 'R';
		case 26 : return 'S';
		case 27 : return 'T';
		case 28 : return 'U';
		case 29 : return 'V';
		case 30 : return 'W';
		case 31 : return 'X';
		case 32 : return 'Y';
		case 33 : return 'Z';
		default  : return  Character.forDigit(i, 10);
		}
	}
}