/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cheuk.licenseheaderchecker.resource;

import cheuk.licenseheaderchecker.LicenseHeaderCheckerParser;
import hudson.FilePath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author cheuk
 */
public class Common {
    static public String sourceDir;
    static public String licenseDir;
    static public boolean failBuild; 
    static public boolean ignoreHidden; 
    static public boolean specificTypes;
    static public String fileTypes;
    
    static public List<FilePath> locateFiles(FilePath rootDir){
        List<FilePath> fileList;
        fileList = new ArrayList<FilePath>();

        try {
            if (rootDir.isDirectory()){
                Iterator<FilePath> it = rootDir.list().iterator();
                while ( it.hasNext() ){
                    FilePath element = it.next();
                    fileList.addAll(locateFiles(element));
                }
            }
            else {
                fileList.add(rootDir);
            }
        } catch (IOException ex) {
            Logger.getLogger(LicenseHeaderCheckerParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(LicenseHeaderCheckerParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileList;
    }
}
