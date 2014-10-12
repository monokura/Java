package customview.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.part.ViewPart;

public class ButtonView extends ViewPart {
	Button clickBtn ;

	public ButtonView() {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO 自動生成されたメソッド・スタブ
		clickBtn = new Button(parent , SWT.NONE);
		clickBtn.setText("クリック");
		clickBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();

				IWorkbenchAction a = ActionFactory.SAVE.create(window);
				//IWorkbenchAction a = ActionFactory.SAVE_ALL.create(window);
				//IWorkbenchPage page = window.getActivePage();
				//IEditorPart editor = page.getActiveEditor();
				//getViewSite().getActionBars().setGlobalActionHandler(
				//         ActionFactory.SAVE.getId(), a);
				a.run();

				//window.getActivePage().getActiveEditor().doSave(null);

				String t = a.getId();
				clickBtn.setText(t);
			}

		});
	}

	@Override
	public void setFocus() {
		// TODO 自動生成されたメソッド・スタブ

	}

}
