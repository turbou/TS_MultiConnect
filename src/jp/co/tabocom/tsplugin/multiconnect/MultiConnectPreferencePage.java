package jp.co.tabocom.tsplugin.multiconnect;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class MultiConnectPreferencePage extends PreferencePage {

    private Text maxConnectionTxt;

    public MultiConnectPreferencePage() {
        super("マルチ接続設定");
    }

    @Override
    protected Control createContents(Composite parent) {
        final Composite composite = new Composite(parent, SWT.NONE);
        composite.setLayout(new GridLayout(2, false));
        IPreferenceStore preferenceStore = getPreferenceStore();

        // ========== TeraTermマクロの場所 ========== //
        new Label(composite, SWT.LEFT).setText("最大接続数：");
        maxConnectionTxt = new Text(composite, SWT.BORDER);
        maxConnectionTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        maxConnectionTxt.setText(preferenceStore.getString(PreferenceConstants.MAX_CONNECTION));

        noDefaultAndApplyButton();
        return composite;
    }

    @Override
    public boolean performOk() {
        IPreferenceStore ps = getPreferenceStore();
        if (ps == null) {
            return true;
        }
        if (this.maxConnectionTxt != null) {
            ps.setValue(PreferenceConstants.MAX_CONNECTION, this.maxConnectionTxt.getText());
        }
        return true;
    }

}
