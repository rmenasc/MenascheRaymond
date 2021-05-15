/*
 * University College, University of Denver student project.
 * Not intended for production or distribution. 
 * Java Programming ICT4361-1.
 */
package edu.du.menascheraymond.model.business.factory;

import edu.du.menascheraymond.model.business.command.Command;

/**
 *
 * @author raymondmenasche
 */
public interface CommandFactory {
    public Command getCommand(String withType);
}
