package jp.co.tabocom.tsplugin.multiconnect;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferenceStore;
import org.eclipse.swt.widgets.ToolTip;

import jp.co.tabocom.teratermstation.Main;
import jp.co.tabocom.teratermstation.TeratermStationShell;
import jp.co.tabocom.teratermstation.model.TargetNode;
import jp.co.tabocom.teratermstation.ui.EnvTabItem;
import jp.co.tabocom.teratermstation.ui.action.TeratermStationAction;

public class MultiConnectAction extends TeratermStationAction {
    private static final int MIN_NUM = 1;
    private static final int DEFAULT_MAX_NUM = 10;
    private static final int BULK_INTERVAL = 1700;
    int max = DEFAULT_MAX_NUM;

    public MultiConnectAction(TargetNode[] nodes, Object value, TeratermStationShell shell) {
        super("同サーバ複数接続", "icon.png", nodes, value, shell);
        Main main = this.shell.getMain();
        PreferenceStore ps = main.getPreferenceStore();
        String psMax = ps.getString(PreferenceConstants.MAX_CONNECTION);
        try {
            max = Integer.valueOf(psMax);
        } catch (NumberFormatException nfe) {
            // 何もしない.
        }
    }

    @Override
    public void run() {
        String dialogMsg = String.format("同サーバに複数接続する数を指定してください。（最大%d）", max);
        final String errorMsg = String.format("%dから%dまでの数値を指定してください。", MIN_NUM, max);
        InputDialog dialog = new InputDialog(this.shell, "同サーバ複数接続", dialogMsg, String.valueOf(MIN_NUM), new IInputValidator() {
            @Override
            public String isValid(String str) {
                try {
                    int num = Integer.valueOf(str);
                    if (num < MIN_NUM || num > max) {
                        return errorMsg;
                    }
                } catch (NumberFormatException nfe) {
                    return errorMsg;
                }
                return null;
            }
        });
        if (dialog.open() == Dialog.OK) {
            Main main = this.shell.getMain();
            EnvTabItem tabItem = main.getCurrentTabItem();
            int multipleCnt = Integer.valueOf(dialog.getValue());
            TargetNode node = nodes[0];
            try {
                for (int idx = 1; idx <= multipleCnt; idx++) {
                    tabItem.makeAndExecuteTTL(node, idx, null);
                    Thread.sleep(BULK_INTERVAL); // スリープしなくても問題はないけど、あまりにも連続でターミナルが開くのもあれなので。
                }
                if (main.isTtlOnly()) {
                    // TTLファイルの作成のみだったら、ファイル作成後、ダイアログを出す。
                    MessageDialog.openInformation(this.shell, "TTLマクロ生成", "TTLマクロを生成しました。");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ToolTip getToolTip() {
        return null;
    }
}
