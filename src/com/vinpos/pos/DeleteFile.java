package com.vinpos.pos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
/**
 *
 * @author sd
 */
public class DeleteFile extends WriteFile {
    public void deleteFile(String fileName){
        File f = new File(fileName);
        f.delete();

    }
    
}
