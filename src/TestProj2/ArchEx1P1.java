package TestProj2;


public class ArchEx1P1 {

	public static String BaseConvertion(String baseIn,String baseFi,String val) {
		int baseI=Integer.parseInt(baseIn);
		int baseF=Integer.parseInt(baseFi);
		char[] Ver = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		boolean check = false;
		 val = val.toUpperCase();
		for (int i = 0; i < val.length(); i++) {
			check = false;
			for (int j = 0; j < baseI; j++) {
				if (val.charAt(i) == Ver[j]) {
					check = true;
					break;
				}
			}
			if (!check) {
				return "Erreur: votre nombre ne correspond pas à la base spécifiée.";
			}
		}

		int dic = 0;
		for (int i = 0; i < val.length(); i++) {
			for (int j = 0; j < baseI; j++) {
				if (val.charAt(i) == Ver[j]) {
					dic += j * Math.pow(baseI, val.length() - i - 1);
					break;
				}
			}
		}
		if(baseF==10)
			return String.valueOf(dic);
		StringBuilder result = new StringBuilder();
		if(dic==0)
			return "0";
		while (dic > 0) {
			int rest = dic % baseF;
			result.insert(0, Ver[rest]);
			dic /= baseF;
		}
		return result.toString();
	}
	
	
	
	
	
	
	
	public static String Jungle(String choix,String baseI,String baseF,String val) {
		String pas;
		if(choix=="valeur non signe") {
			if(baseI=="complement à 1"||baseI=="complement à 2"||baseI=="valeur signe") {
				if(!Ex2Partie1.check(val))
					return "Erreur: votre nombre ne correspond pas à la base Binaire.";
				else {
					if(baseF=="complement à 1"||baseF=="complement à 2"||baseF=="valeur signe") {
						if(val.charAt(0)=='0')
							return val;
						else
							return '0'+val;	
					}
					else
						return BaseConvertion ("2",baseF.substring(baseF.length()-2, baseF.length()),val);	
				}
			}
			else {
				if(baseF=="complement à 1"||baseF=="complement à 2"||baseF=="valeur signe")
					return "0"+BaseConvertion (baseI.substring(baseI.length()-2, baseI.length()),"2",val);
				else
					return BaseConvertion (baseI.substring(baseI.length()-2, baseI.length()),baseF.substring(baseF.length()-2, baseF.length()),val);
			}
		}
		else {
			if(baseI.substring(baseI.length()-2, baseI.length())=="10") {
				if(baseF=="complement à 1")
					return Ex2Partie1.CompA1(val);
				else if(baseF=="complement à 2"||baseF=="Base 2")
						return Ex2Partie1.CompA2(val);	
				else if(baseF=="valeur signe")
					return Ex2Partie1.NbrSigne(val);	
				else {
					if(baseF.substring(baseF.length()-2, baseF.length())=="10") {
						try {
							Integer.parseInt(val);
							return val;
						}catch(Exception e) {
							return "Erreur: votre nombre ne correspond pas à la base décimal.";
						}
					}
					else {
						pas =Ex2Partie1.CompA2(val);
						if(pas.charAt(0)=='E')
							return pas;
						else {
							return BaseConvertion ("2",baseF.substring(baseF.length()-2, baseF.length()),pas);
						}
						
					}
				}
			}
			else {
				if(baseF=="complement à 1") {
					pas=BaseConvertion (baseF.substring(baseF.length()-2, baseF.length()),"2",val);
					if(pas.charAt(0)=='E')
						return pas;
					pas=Ex2Partie1.CmpA_2_ver_base_10(pas);
					return Ex2Partie1.CompA1(pas);
				}
					
				}
				
			}
		
		return null;
	}
}
