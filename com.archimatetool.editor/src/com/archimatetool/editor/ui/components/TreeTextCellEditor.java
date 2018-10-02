/**
 * This program and the accompanying materials
 * are made available under the terms of the License
 * which accompanies this distribution in the file LICENSE.txt
 */
package com.archimatetool.editor.ui.components;

import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import com.archimatetool.editor.ui.UIUtils;

/**
 * Tree Text CellEditor
 * 
 * @author Phillip Beauvoir
 */
public class TreeTextCellEditor extends TextCellEditor {
    int minHeight = 0;
    CellEditorGlobalActionHandler fGlobalActionHandler;

    public TreeTextCellEditor(Tree tree) {
        super(tree, SWT.BORDER);
        Text txt = (Text)getControl();
        
        // Filter out nasties
        UIUtils.applyInvalidCharacterFilter(txt);
        
        // Not sure if we need this
        //UIUtils.conformSingleTextControl(txt);

        FontData[] fontData = txt.getFont().getFontData();
        if(fontData != null && fontData.length > 0) {
            minHeight = fontData[0].getHeight() + 10;
        }
    }

    @Override
    public LayoutData getLayoutData() {
        LayoutData data = super.getLayoutData();
        if(minHeight > 0) {
            data.minimumHeight = minHeight;
        }
        return data;
    }
    
    @Override
    protected void doSetFocus() {
        super.doSetFocus();
        
        // Clear global key binds
        // We do this on doSetFocus() rather than activate() because in the case where we open an EditorPart on new diagram
        // the focus gets transferred to the EditorPart and we end up with the wrong actionBars
        fGlobalActionHandler = new CellEditorGlobalActionHandler();
        fGlobalActionHandler.clearGlobalActions();
    }
    
    @Override
    public void deactivate() {
        super.deactivate();
        
        // Restore global key binds
        if(fGlobalActionHandler != null) {
            fGlobalActionHandler.restoreGlobalActions();
            fGlobalActionHandler = null;
        }
    }
}
