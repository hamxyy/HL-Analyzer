package com.shs.hl.ui.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

/**
 * This is the perspective used in the product
 * for editing hearing language code 
 */
public class HLPerspectiveFactory implements IPerspectiveFactory {

	public static final String SHS_PERSPECTIVE_ID = "com.shs.hl.ui.perspective";

	@Override
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

    public void defineActions(IPageLayout layout) {
        // Add "show views".
		layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
        layout.addShowViewShortcut(IPageLayout.ID_PROBLEM_VIEW);
        layout.addShowViewShortcut(IPageLayout.ID_PROGRESS_VIEW);
        layout.addShowViewShortcut("org.eclipse.ui.console.ConsoleView");
        layout.addActionSet(IPageLayout.ID_NAVIGATE_ACTION_SET);
    }

    /**
     * Defines the initial layout for a page.
     * @param layout The layout we are filling
     */
    public void defineLayout(IPageLayout layout) {
        // Editors are placed for free.
        String editorArea = layout.getEditorArea();

        // Top left.
        IFolderLayout topLeft = layout.createFolder(
                "topLeft", IPageLayout.LEFT, (float) 0.26, editorArea);//$NON-NLS-1$
		topLeft.addView(IPageLayout.ID_PROJECT_EXPLORER);
        topLeft.addPlaceholder(IPageLayout.ID_BOOKMARKS);

        // Add a placeholder for the old navigator to maintain compatibility
        topLeft.addPlaceholder("org.eclipse.ui.views.ResourceNavigator"); //$NON-NLS-1$

        // Bottom left.
        IFolderLayout bottomLeft = layout.createFolder(
                "bottomLeft", IPageLayout.BOTTOM, (float) 0.50,//$NON-NLS-1$
                "topLeft");//$NON-NLS-1$
        bottomLeft.addView(IPageLayout.ID_OUTLINE);

        // Bottom right.
		IFolderLayout bottomRight = layout.createFolder(
                "bottomRight", IPageLayout.BOTTOM, (float) 0.66,//$NON-NLS-1$
                editorArea);
		
		bottomRight.addView("org.eclipse.ui.console.ConsoleView");
		bottomRight.addView(IPageLayout.ID_PROBLEM_VIEW);		
    }


}
