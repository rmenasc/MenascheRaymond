/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 * MainMethod.java
 */
package edu.du.menascheraymond;

import edu.du.menascheraymond.view.MainMenuFrame;

/**
 * Main method class.
 * @author raymondmenasche
 */
public class MainMethod {
    
    public static void main(String[] args) {

        try {
            MainMenuFrame.runApplication();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}