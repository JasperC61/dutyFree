package start;
/*
請使用Java寫一個main方法程式用來檢驗因以下事項所產生的程式
營利事業統一編號檢查碼邏輯修正說明
一、營利事業統一編號（下稱統一編號）供營利事業及扣繳單位配號使用，預估空號將於113年用罄。
二、為擴增統一編號號碼並與現行配賦之統一編號相容（新舊統一編號格式相同），後續請公私部門配合修改統一編號檢核程式，主要係修正「檢查邏輯由可被『10』整除改為可被『5』整除」，相關說明詳如附件。
三、全國公私部門倘有使用統一編號檢核程式，請於112年3月31日前完成統一編號檢核程式修改作業，相關系統文件請併同檢視修正。
四、預計112年4月以後，將視舊號餘存狀況逐步釋出新產製之統一編號
*/
public class CheckTaxID {
	  // main方法: 程式的入口點，執行時會先從此方法開始

	public static void main(String[] args) {
		 // 定義一組測試的營利事業統一編號
		        String[] taxIDs = {"83204729", "399270913", "67308274","12371287"};
		        // 遍歷每一個統一編號，檢查其有效性
		        for (String taxID : taxIDs) {
		        	// 使用isValidTaxID方法檢查統一編號的有效性
		            if (isValidTaxID(taxID)) {
		            	// 如果統一編號有效，輸出 "is valid."
		                System.out.println(taxID + " is valid.");
		            } else {
		            	 // 如果統一編號無效，輸出 "is invalid."
		                System.out.println(taxID + " is invalid.");
		            }
		        }
		    }
	 // isValidTaxID方法: 檢查統一編號是否有效
		    public static boolean isValidTaxID(String taxID) {
		    	// 檢查統一編號的長度是否為8位數，且是否全部為數字
		        if (taxID.length() != 8 || !taxID.matches("\\d{8}")) {
		            return false;
		        }
		     // 定義乘數數組，用於計算每位數的乘積
		        int[] multipliers = {1, 2, 1, 2, 1, 2, 4, 1};
		        int sum = 0;
		        // 遍歷統一編號的每一位數字，根據乘數數組計算乘積
		        
		        for (int i = 0; i < 8; i++) {
		            int product = Character.getNumericValue(taxID.charAt(i)) * multipliers[i];
		            // 將每位數的乘積拆分相加，累加到sum變數
		            sum += (product / 10) + (product % 10);
		        }
		        // 最後檢查sum是否能被5整除，能整除則統一編號有效
		        return sum % 5 == 0;
		    
		
		



	
	}

}
