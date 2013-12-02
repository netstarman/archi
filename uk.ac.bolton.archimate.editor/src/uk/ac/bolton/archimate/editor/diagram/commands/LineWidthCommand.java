/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package uk.ac.bolton.archimate.editor.diagram.commands;

import uk.ac.bolton.archimate.editor.model.commands.EObjectFeatureCommand;
import uk.ac.bolton.archimate.model.IArchimatePackage;
import uk.ac.bolton.archimate.model.ILineObject;


/**
 * Line Width Command
 *
 * @author Phillip Beauvoir
 */
public class LineWidthCommand extends EObjectFeatureCommand {
    
    public LineWidthCommand(ILineObject object, int lineWidth) {
        super(Messages.LineWidthCommand_0, object, IArchimatePackage.Literals.LINE_OBJECT__LINE_WIDTH, lineWidth);
    }
}