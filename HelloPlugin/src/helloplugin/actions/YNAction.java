package helloplugin.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class YNAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;

	@Override
	public void run(IAction action) {
		// TODO 自動生成されたメソッド・スタブ
		boolean answer = MessageDialog.openQuestion(window.getShell(), "質問ダイアログ", "Eclipse好きですか？");

		if (answer) {
			MessageDialog.openInformation(window.getShell(), "Eclipse好き", "照れます///");
		} else {
			MessageDialog.openInformation(window.getShell(), "Eclipse好きじゃない", "(´・ω・｀)");
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void dispose() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void init(IWorkbenchWindow window) {
		// TODO 自動生成されたメソッド・スタブ
		this.window = window;
	}

}
