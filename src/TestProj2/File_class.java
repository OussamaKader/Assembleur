package TestProj2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class File_class {
	public static int line_id;
	public static File file=null;
	public static void FILE(String contenu) {
		AOProject_Partie_2.valide=true;
		line_id=0;
		contenu=contenu.toUpperCase().trim();
		if(contenu.endsWith("END"))
			contenu=contenu.substring(0, contenu.length()-3);
		else {
			AOProject_Partie_2.ERROR("ERROR absence de END");
			return;
		}
	    try {
	        file=new File("C:\\Users\\elkay\\Desktop\\Test1.MPC");
	        if(file.createNewFile());
	    }catch(Exception e){
	    	System.out.println("error le fichier n'a pas crée.");
	    }
	    try(BufferedWriter writer=new BufferedWriter(new FileWriter(file))){
	    	writer.write(contenu);
	    }catch(IOException e) {
	    	System.out.println("error écriture dans le fichie");
	    }
	    String ligne = null;
	    try(BufferedReader rd=new BufferedReader(new FileReader(file))){
	    	while((ligne=rd.readLine())!=null) {
	    		line_id++;
	    		ligne=ligne.trim();
	    		int a=0;
	    		int dtect_mode=ligne.indexOf('#');
	    		if(dtect_mode!=-1) {
	    			a=1;
	    			if(!DetectError.ImediatError(ligne)) {
	    				AOProject_Partie_2.ERROR("ERROR AT LINE"+line_id);
	    				break;
	    				}
	    			}
	    	    dtect_mode=ligne.indexOf('<');
	    	    if(dtect_mode!=-1&&a==0) {
	    	    	a=1;
	    			if(!DetectError.DirectError(ligne)) {
	    				AOProject_Partie_2.ERROR("ERROR AT LINE"+line_id);
	    				break;
	    				}
	    			}
	    	    dtect_mode=ligne.indexOf('>');
	    	     if(dtect_mode!=-1&&a==0) {
	    	    	 a=1;
	    	    	dtect_mode=ligne.indexOf('[');
	    	    	 if(dtect_mode!=-1) {
	    			   if(!DetectError.EtenduInDirectError(ligne)) {
	    				    AOProject_Partie_2.ERROR("ERROR AT LINE "+line_id);
		    				break;
	    	    	 	}
	    			   }
	    			   else {
	    				   if(!DetectError.EtenduDirectError(ligne)) {
	    					   AOProject_Partie_2.ERROR("ERROR AT LINE "+line_id);
	   	    				   break;
	    				   }
	    				   }
	    	    } 
	    	    dtect_mode=ligne.indexOf(',');
	    	    if(dtect_mode!=-1&&a==0) {
	    	    	a=1;
	    	    	if(!DetectError.IndexeDetectionError(ligne)) {
	    	    		AOProject_Partie_2.ERROR("ERROR AT LINE "+line_id);
	    				break;
	    	    	}
	    	    }
	    	    if(a==0)
	    	    if(!DetectError.InherentDetectionError(ligne.trim())) {
	    	    	AOProject_Partie_2.ERROR("ERROR AT LINE "+line_id);
	    	    	break;
	    	    	}
	    	}
	    }catch(IOException e) {
	    	AOProject_Partie_2.ERROR(e.getMessage());
	    }
	    finally {file.delete();}
	    }
	}
	


