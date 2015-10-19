package jp.co.nri.ttsplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.co.tabocom.teratermstation.model.TargetNode;
import jp.co.tabocom.teratermstation.plugin.TeraTermStationPlugin;
import jp.co.tabocom.teratermstation.ui.action.TeraTermStationAction;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.swt.widgets.Shell;

public class MultiConnectPlugin implements TeraTermStationPlugin {

    @Override
    public List<TeraTermStationAction> getActions(TargetNode node, Shell shell, ISelectionProvider selectionProvider) {
        return new ArrayList<TeraTermStationAction>(Arrays.asList(new MultiConnectAction(node, shell, selectionProvider)));
    }

    @Override
    public PreferencePage getPreferencePage() {
        return new MultiConnectPreferencePage();
    }
}
